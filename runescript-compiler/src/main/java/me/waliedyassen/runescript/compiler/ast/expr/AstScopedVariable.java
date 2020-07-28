/*
 * Copyright (c) 2020 Walied K. Yassen, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package me.waliedyassen.runescript.compiler.ast.expr;

import lombok.Getter;
import me.waliedyassen.runescript.commons.document.Range;
import me.waliedyassen.runescript.compiler.ast.visitor.AstVisitor;
import me.waliedyassen.runescript.compiler.util.VariableScope;


/**
 * A scoped variable AST expression node.
 *
 * @author Walied K. Yassen
 */
public final class AstScopedVariable extends AstBaseVariable {

    /**
     * The scope of the variable.
     */
    @Getter
    private final VariableScope scope;

    /**
     * Constructs a new {@link AstScopedVariable} type object instance.
     *
     * @param range
     *         the node source code range.
     * @param scope
     *         the scope of the varaible.
     * @param name
     *         the name of the variable.
     */
    public AstScopedVariable(Range range, VariableScope scope, AstIdentifier name) {
        super(range, name);
        this.scope = scope;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <E, S> E accept(AstVisitor<E, S> visitor) {
        return visitor.visit(this);
    }
}
