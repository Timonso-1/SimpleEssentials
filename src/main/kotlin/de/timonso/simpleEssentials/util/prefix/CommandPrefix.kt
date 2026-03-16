package de.timonso.simpleEssentials.util.prefix

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextColor

object CommandPrefix {
    private const val NAME = "SE"
    private const val PREFIX_COLOR: Int = 0x3B92D1

    val COMMAND_PREFIX: Component = Component.text(">> ", NamedTextColor.DARK_GRAY)
        .append(Component.text(NAME).color(TextColor.color(PREFIX_COLOR)))
        .append(Component.text(" | ", NamedTextColor.DARK_GRAY))
}