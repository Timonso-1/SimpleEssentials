package de.timonso.simpleEssentials.command.table

import de.timonso.simpleEssentials.util.permission.SimpleEssentialsRegistry
import de.timonso.simpleEssentials.util.prefix.CommandPrefix
import dev.jorel.commandapi.kotlindsl.commandTree
import dev.jorel.commandapi.kotlindsl.playerExecutor
import dev.slne.surf.surfapi.core.api.messages.adventure.sendText

fun stonecutterCommand() = commandTree("stonecutter") {
    withPermission(SimpleEssentialsRegistry.STONECUTTER_COMMAND)
    playerExecutor { player, _ ->
        player.openStonecutter(null, true)
        player.sendText {
            append(CommandPrefix.COMMAND_PREFIX)
            info("Der Steinschneider wurde geöffnet")
            info(".")
        }
    }
}