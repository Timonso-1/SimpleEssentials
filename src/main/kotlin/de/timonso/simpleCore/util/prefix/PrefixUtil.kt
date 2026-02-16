package de.timonso.simpleCore.util.prefix

import dev.slne.surf.surfapi.core.api.messages.Colors
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextColor

object PrefixUtil {
    private const val NAME = "SimpleCore"
    private const val DEFAULT_FALLBACK_COLOR: Int = 0x3B9201

    val PREFIX: Component = Component.text(">> ").color(colorFromSurf(Colors.DARK_SPACER))
        .append(Component.text(NAME).color(colorFromSurf(Colors.PREFIX_COLOR)))
        .append(Component.text(" | ").color(NamedTextColor.DARK_GRAY))

    private fun colorFromSurf(value: Any?) = when (value) {
        is TextColor -> value
        is Int -> TextColor.color(value)
        is Number -> TextColor.color(value.toInt())
        is String -> kotlin.runCatching { TextColor.color(value.removePrefix("#").toInt(16)) }
            .getOrElse { TextColor.color(DEFAULT_FALLBACK_COLOR) }

        else -> TextColor.color(DEFAULT_FALLBACK_COLOR)
    }
}