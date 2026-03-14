package de.timonso.simpleCore.util.prefix

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextColor

object CommandPrefix {
    private const val NAME = "SimpleCore"
    private const val PREFIX_COLOR: Int = 0x3B92D1

    val COMMAND_PREFIX: Component = Component.text(">> ", NamedTextColor.DARK_GRAY)
        .append(Component.text(NAME).color(TextColor.color(PREFIX_COLOR)))
        .append(Component.text(" | ", NamedTextColor.DARK_GRAY))
}