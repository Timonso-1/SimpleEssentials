package de.timonso.simpleCore.util.prefix

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor

object ConnectionPrefix {
    val JOIN_PREFIX: Component = Component.text("[", NamedTextColor.GRAY)
        .append(Component.text("+", NamedTextColor.GREEN))
        .append(Component.text("] ", NamedTextColor.GRAY))

    val LEAVE_PREFIX: Component = Component.text("[", NamedTextColor.GRAY)
        .append(Component.text("-", NamedTextColor.RED))
        .append(Component.text("] ", NamedTextColor.GRAY))
}