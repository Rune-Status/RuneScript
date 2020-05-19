/*
 * Copyright (c) 2019 Walied K. Yassen, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package me.waliedyassen.runescript.compiler.ast.expr;

import lombok.Getter;
import me.waliedyassen.runescript.commons.document.Range;
import me.waliedyassen.runescript.compiler.ast.visitor.AstVisitor;

/**
 * Represents a hook expression.
 *
 * @author Walied K. Yassen
 */
public final class AstHook extends AstExpression {

    /**
     * The name of the hook.
     */
    @Getter
    private final AstIdentifier name;

    /**
     * The arguments of the hook.
     */
    @Getter
    private final AstExpression[] arguments;

    /**
     * The transmits of the hook.
     */
    @Getter
    private final AstIdentifier[] transmits;

    /**
     * Constructs a new {@link AstHook} type object instance.
     *
     * @param range
     *         the expression source code range.
     * @param name
     *         the name of the hook.
     * @param arguments
     *         the arguments of the hook.
     * @param transmits
     *         the transmits of the hook.
     */
    public AstHook(Range range, AstIdentifier name, AstExpression[] arguments, AstIdentifier[] transmits) {
        super(range);
        this.name = addChild(name);
        this.arguments = arguments != null ? addChild(arguments) : null;
        this.transmits = transmits != null ? addChild(transmits) : null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <E, S> E accept(AstVisitor<E, S> visitor) {
        return null;
    }
}