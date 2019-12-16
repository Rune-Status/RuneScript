/*
 * Copyright (c) 2019 Walied K. Yassen, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package me.waliedyassen.runescript.editor.ui.explorer.tree;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * The base class for all of the tree nodes in the project explorer tree.
 *
 * @author Walied K. Yassen
 */
@RequiredArgsConstructor
public abstract class ExplorerNode<T> extends DefaultMutableTreeNode {

    /**
     * The value of the explorer node.
     */
    @Getter
    private final T value;
}
