/*
 * Copyright (c) 2020 Walied K. Yassen, All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package me.waliedyassen.runescript.compiler.symbol.impl;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.waliedyassen.runescript.compiler.codegen.opcode.Opcode;
import me.waliedyassen.runescript.compiler.symbol.Symbol;
import me.waliedyassen.runescript.type.Type;

/**
 * Represents command information.
 *
 * @author Walied K. Yassen
 */
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
public final class CommandInfo extends Symbol {

    /**
     * The opcode of this command.
     */
    @Getter
    private final Opcode opcode;

    /**
     * The name of the command.
     */
    @Getter
    private final String name;

    /**
     * The return type of the command.
     */
    @Getter
    private final Type type;

    /**
     * The argument type(s) of the command.
     */
    @Getter
    private final Type[] arguments;

    /**
     * Whether or not this command is a hook command.
     */
    @Getter
    private final boolean hook;

    /**
     * The type of transmits the hook must have if present.
     */
    @Getter
    private final Type hookType;

    /**
     * Whether or not this command can be alternative.
     */
    @Getter
    private final boolean alternative;
}
