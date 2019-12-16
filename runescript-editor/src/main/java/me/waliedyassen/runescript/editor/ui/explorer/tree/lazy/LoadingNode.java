/*
 * Copyright (c) 2019 Walied K. Yassen, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package me.waliedyassen.runescript.editor.ui.explorer.tree.lazy;

import me.waliedyassen.runescript.editor.ui.explorer.tree.ExplorerNode;

/**
 * A placeholder node used to make the tree expandable so it will trigger the expand listener.
 *
 * @author Walied K. Yassen
 */
public final class LoadingNode extends ExplorerNode<Void> {

    /**
     * Constructs a new {@link LoadingNode} type object instance.
     */
    public LoadingNode() {
        super(null);
        setUserObject("Loading");
        setAllowsChildren(false);
    }
}
