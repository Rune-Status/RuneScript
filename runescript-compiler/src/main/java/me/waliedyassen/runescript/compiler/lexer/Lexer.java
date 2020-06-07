/*
 * Copyright (c) 2019 Walied K. Yassen, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package me.waliedyassen.runescript.compiler.lexer;

import lombok.Getter;
import lombok.var;
import me.waliedyassen.runescript.compiler.lexer.token.Kind;
import me.waliedyassen.runescript.compiler.lexer.tokenizer.Tokenizer;
import me.waliedyassen.runescript.lexer.LexerBase;
import me.waliedyassen.runescript.lexer.table.LexicalTable;
import me.waliedyassen.runescript.lexer.token.Token;

/**
 * Represents the script parser {@link LexerBase} implementation.
 *
 * @author Walied K. Yassen
 */
public final class Lexer extends LexerBase<Kind> {

    /**
     * The lexical table which the lexer is using.
     */
    @Getter
    private final LexicalTable<Kind> lexicalTable;

    /**
     * Constructs a new {@link Lexer} type object instance.
     *
     * @param tokenizer the tokenizer which we will take all the {@link Token} objects from.
     */
    public Lexer(Tokenizer tokenizer) {
        super(tokenizer.range());
        this.lexicalTable = tokenizer.getTable();
        tokens:
        do {
            var token = tokenizer.parse();
            switch (token.getKind()) {
                case EOF:
                    break tokens;
                case COMMENT:
                    continue tokens;
                default:
                    tokens.add(token);
            }
        } while (true);
    }
}
