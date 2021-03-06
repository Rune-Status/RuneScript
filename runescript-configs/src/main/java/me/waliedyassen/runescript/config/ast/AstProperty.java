/*
 * Copyright (c) 2019 Walied K. Yassen, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package me.waliedyassen.runescript.config.ast;

import lombok.Getter;
import me.waliedyassen.runescript.commons.document.Range;
import me.waliedyassen.runescript.config.ast.value.AstValue;
import me.waliedyassen.runescript.config.ast.visitor.AstVisitor;

/**
 * Represents a configuration property tree node.
 *
 * @author Walied K. Yassen
 */
public final class AstProperty extends AstNode {

    /**
     * The property key.
     */
    @Getter
    private final AstIdentifier key;

    /**
     * The property value.
     */
    @Getter
    private final AstValue[] values;

    /**
     * Constructs a new {@link AstProperty} type object instance.
     *
     * @param range
     *         the node source range.
     * @param key
     *         the key of the property.
     * @param values
     *         the values of the property.
     */
    public AstProperty(Range range, AstIdentifier key, AstValue[] values) {
        super(range);
        this.key = key;
        this.values = values;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <R> R visit(AstVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
