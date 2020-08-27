/*
 * Copyright (c) 2020 Walied K. Yassen, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package me.waliedyassen.runescript.compiler.syntax.stmt;

import lombok.Getter;
import me.waliedyassen.runescript.commons.document.Range;
import me.waliedyassen.runescript.compiler.syntax.expr.ExpressionSyntax;
import me.waliedyassen.runescript.compiler.syntax.visitor.SyntaxVisitor;

/**
 * Represents a return expression statement.
 *
 * @author Walied K. Yassen
 */
public final class ReturnStatementSyntax extends StatementSyntax {

    /**
     * The returned expressions.
     */
    @Getter
    private final ExpressionSyntax[] expressions;

    /**
     * Construct a new {@link ReturnStatementSyntax} type object instance.
     *
     * @param range
     *         the node source code range.
     * @param expressions
     *         the returned expressions.
     */
    public ReturnStatementSyntax(Range range, ExpressionSyntax[] expressions) {
        super(range);
        this.expressions = addChild(expressions);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T accept(SyntaxVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
