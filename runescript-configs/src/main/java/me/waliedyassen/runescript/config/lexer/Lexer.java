/*
 * Copyright (c) 2019 Walied K. Yassen, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package me.waliedyassen.runescript.config.lexer;

import me.waliedyassen.runescript.config.lexer.token.Kind;
import me.waliedyassen.runescript.lexer.LexerBase;
import me.waliedyassen.runescript.lexer.token.Token;

/**
 * Represents the configuration parser {@link LexerBase} implementation.
 *
 * @author Walied K. Yassen
 */
public final class Lexer extends LexerBase<Kind> {

    /**
     * Constructs a new {@link Lexer} type object instance.
     *
     * @param tokenizer
     *         the tokenizer which we will take all the {@link Token} objects from.
     */
    public Lexer(Tokenizer tokenizer) {
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
