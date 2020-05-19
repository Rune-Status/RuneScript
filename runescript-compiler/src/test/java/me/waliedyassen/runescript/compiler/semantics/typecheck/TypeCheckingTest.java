/*
 * Copyright (c) 2019 Walied K. Yassen, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package me.waliedyassen.runescript.compiler.semantics.typecheck;

import lombok.Data;
import me.waliedyassen.runescript.commons.stream.BufferedCharStream;
import me.waliedyassen.runescript.compiler.Compiler;
import me.waliedyassen.runescript.compiler.ast.AstScript;
import me.waliedyassen.runescript.compiler.codegen.opcode.CoreOpcode;
import me.waliedyassen.runescript.compiler.env.CompilerEnvironment;
import me.waliedyassen.runescript.compiler.lexer.Lexer;
import me.waliedyassen.runescript.compiler.lexer.token.Kind;
import me.waliedyassen.runescript.compiler.lexer.tokenizer.Tokenizer;
import me.waliedyassen.runescript.compiler.parser.ScriptParser;
import me.waliedyassen.runescript.compiler.parser.ScriptParserTest;
import me.waliedyassen.runescript.compiler.semantics.SemanticChecker;
import me.waliedyassen.runescript.compiler.symbol.SymbolTable;
import me.waliedyassen.runescript.compiler.util.trigger.TriggerType;
import me.waliedyassen.runescript.type.PrimitiveType;
import me.waliedyassen.runescript.type.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TypeCheckingTest {

    SemanticChecker checker;
    CompilerEnvironment environment;

    @BeforeEach
    void setupChecker() {
        environment = new CompilerEnvironment();
        for (ScriptParserTest.TestTriggerType triggerType : ScriptParserTest.TestTriggerType.values()) {
            environment.registerTrigger(triggerType);
        }
        var table = new SymbolTable();
        checker = new SemanticChecker(environment, table);
    }

    @Test
    void testScriptDeclaration() throws IOException {
        checkString("[proc,test]");
        assertEquals(0, checker.getErrors().size());
        assertEquals(1, checker.getSymbolTable().getScripts().size());
    }

    @Test
    void testScriptDeclarationDuplicate() throws IOException {
        checkString("[proc,test][proc,test]");
        assertEquals(1, checker.getErrors().size());
        assertEquals(1, checker.getSymbolTable().getScripts().size());
    }

    @Test
    void testLocalDeclaration() throws IOException {
        checkString("[proc,test] def_int $local = 5;");
        assertEquals(0, checker.getErrors().size());
    }

    @Test
    void testLocalDeclarationDuplicate() throws IOException {
        checkString("[proc,test] def_int $local = 5;  def_int $local = 3;");
        assertEquals(1, checker.getErrors().size());
    }

    @Test
    void testArray() throws IOException {
        checkResource("array_01.rs2");
        assertEquals(0, checker.getErrors().size());
        checkResource("array_02.rs2");
        assertEquals(1, checker.getErrors().size());
    }

    @Test
    void testCalc() throws IOException {
        checkResource("calc_01.rs2");
        assertEquals(0, checker.getErrors().size());
        checkResource("calc_02.rs2");
        assertEquals(4, checker.getErrors().size());
    }

    @Test
    void testCall() throws IOException {
        checkResource("call_01.rs2");
        checker.getErrors().forEach(System.out::println);
        assertEquals(0, checker.getErrors().size());
    }

    @Test
    void testTriggerInvalid() throws IOException {
        checkString("[nonexistent,erroring](int $test)");
        assertEquals(1, checker.getErrors().size());
    }

    @Test
    void testTriggerParametersTypes() throws IOException {
        final var TRIGGER_TYPE = new TriggerTypeImpl("paramtest", null, null, true, new Type[]{PrimitiveType.INT, PrimitiveType.STRING}, false, null);
        environment.registerTrigger(TRIGGER_TYPE);
        checkString("[paramtest,erroring](int $test)");
        assertEquals(1, checker.getErrors().size());
        checkString("[paramtest,working](int $test, string $test1)");
        assertEquals(0, checker.getErrors().size());
    }

    @Test
    void testTriggerNoParamters() throws IOException {
        final var TRIGGER_TYPE = new TriggerTypeImpl("paramtest", null, null, false, null, false, null);
        environment.registerTrigger(TRIGGER_TYPE);
        checkString("[paramtest,erroring](int $test)");
        assertEquals(1, checker.getErrors().size());
        checkString("[paramtest,working]");
        assertEquals(0, checker.getErrors().size());
    }

    @Test
    void testTriggerReturnsTypes() throws IOException {
        final var TRIGGER_TYPE = new TriggerTypeImpl("returntest", null, null, false, null, true, new Type[]{PrimitiveType.INT, PrimitiveType.STRING});
        environment.registerTrigger(TRIGGER_TYPE);
        checkString("[returntest,erroring](int)");
        assertEquals(1, checker.getErrors().size());
        checkString("[returntest,working](int,string)");
        checker.getErrors().forEach(System.out::println);
        assertEquals(0, checker.getErrors().size());
    }

    @Test
    void testTriggerNoReturns() throws IOException {
        final var TRIGGER_TYPE = new TriggerTypeImpl("paramtest", null, null, false, null, false, null);
        environment.registerTrigger(TRIGGER_TYPE);
        checkString("[paramtest,erroring](int)");
        assertEquals(1, checker.getErrors().size());
        checkString("[paramtest,working]");
        assertEquals(0, checker.getErrors().size());
    }

    void checkResource(String name) throws IOException {
        checker.getSymbolTable().getScripts().clear();
        checker.getErrors().clear();
        try (var stream = getClass().getResourceAsStream(name)) {
            var tokenizer = new Tokenizer(Compiler.createLexicalTable(), new BufferedCharStream(stream));
            var lexer = new Lexer(tokenizer);
            var parser = new ScriptParser(environment, checker.getSymbolTable(), lexer);
            var scripts = new ArrayList<AstScript>();
            do {
                scripts.add(parser.script());
            } while (lexer.remaining() > 0);
            checker.executePre(scripts);
            checker.execute(scripts);
        }
    }

    void checkString(String text) throws IOException {
        checker.getSymbolTable().getScripts().clear();
        checker.getErrors().clear();
        try (var stream = new ByteArrayInputStream(text.getBytes())) {
            var tokenizer = new Tokenizer(Compiler.createLexicalTable(), new BufferedCharStream(stream));
            var lexer = new Lexer(tokenizer);
            var parser = new ScriptParser(environment, checker.getSymbolTable(), lexer);
            var scripts = new ArrayList<AstScript>();
            do {
                scripts.add(parser.script());
            } while (lexer.remaining() > 0);
            checker.executePre(scripts);
            checker.execute(scripts);
        }
    }

    @Data
    final class TriggerTypeImpl implements TriggerType {

        private final String representation;
        private final Kind operator;
        private final CoreOpcode opcode;
        private final boolean arguments;
        private final Type[] argumentTypes;
        private final boolean returns;
        private final Type[] returnTypes;

        @Override
        public boolean hasArguments() {
            return arguments;
        }

        @Override
        public boolean hasReturns() {
            return returns;
        }
    }
}