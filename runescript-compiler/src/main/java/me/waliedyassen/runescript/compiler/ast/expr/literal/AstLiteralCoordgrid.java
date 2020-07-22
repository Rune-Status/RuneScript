/*
 * Copyright (c) 2020 Walied K. Yassen, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package me.waliedyassen.runescript.compiler.ast.expr.literal;

import me.waliedyassen.runescript.commons.document.Range;
import me.waliedyassen.runescript.compiler.ast.visitor.AstVisitor;

/**
 * Represents a coordgrid literal expression node.
 *
 * @author Walied K. Yassen
 */
public final class AstLiteralCoordgrid extends AstLiteral<Integer> {

    /**
     * Constructs a new {@link AstLiteralCoordgrid} type object instance.
     *
     * @param range the node source code range.
     * @param value the value of the literal.
     */
    public AstLiteralCoordgrid(Range range, int value) {
        super(range, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <E, S> E accept(AstVisitor<E, S> visitor) {
        return visitor.visit(this);
    }
}
