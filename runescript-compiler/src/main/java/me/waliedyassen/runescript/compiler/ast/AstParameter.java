/*
 * Copyright (c) 2019 Walied K. Yassen, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package me.waliedyassen.runescript.compiler.ast;

import lombok.Getter;
import me.waliedyassen.runescript.commons.document.Range;
import me.waliedyassen.runescript.compiler.ast.expr.AstIdentifier;
import me.waliedyassen.runescript.compiler.ast.visitor.AstVisitor;
import me.waliedyassen.runescript.type.Type;

/**
 * Represents a parameter AST node.
 *
 * @author Walied K. Yassen
 */
public final class AstParameter extends AstNode {

    /**
     * The type of the parameter.
     */
    @Getter
    private final Type type;

    /**
     * The name of the parameter.
     */
    @Getter
    private final AstIdentifier name;

    /**
     * Construct a new {@link AstScript} type object instance.
     *
     * @param range
     *         the node source range.
     * @param type
     *         the type of the parameter.
     * @param name
     *         the name of the parameter.
     */
    public AstParameter(Range range, Type type, AstIdentifier name) {
        super(range);
        this.type = type;
        this.name = addChild(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <E, S> S accept(AstVisitor<E, S> visitor) {
        return visitor.visit(this);
    }
}
