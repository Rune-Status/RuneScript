/*
 * Copyright (c) 2020 Walied K. Yassen, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package me.waliedyassen.runescript.compiler.syntax.stmt;

import lombok.Getter;
import lombok.Setter;
import me.waliedyassen.runescript.commons.document.Range;
import me.waliedyassen.runescript.compiler.syntax.expr.ExpressionSyntax;
import me.waliedyassen.runescript.compiler.syntax.visitor.SyntaxVisitor;

/**
 * Represents an AST switch statement case.
 *
 * @author Walied K. Yassen
 */
public final class SwitchCaseSyntax extends StatementSyntax {

    /**
     * The keys of the switch case.
     */
    @Getter
    private final ExpressionSyntax[] keys;

    /**
     * The switch case block statement.
     */
    @Getter
    private final BlockStatementSyntax code;

    /**
     * The resolved keys of this switch case.
     */
    @Getter @Setter
    private int[] resolvedKeys;

    /**
     * Constructs a new {@link SwitchCaseSyntax} type object instance.
     *
     * @param range
     *         the node source code range.
     * @param keys
     *         the keys of the switch case.
     * @param code
     *         the switch case block statement.
     */
    public SwitchCaseSyntax(Range range, ExpressionSyntax[] keys, BlockStatementSyntax code) {
        super(range);
        this.keys = addChild(keys);
        this.code = addChild(code);
    }

    /**
     * Checks whether or not this switch case is the default case.
     *
     * @return <code>true</code> if it is otherwise <code>false</code>.
     */
    public boolean isDefault() {
        return keys.length == 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T accept(SyntaxVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

