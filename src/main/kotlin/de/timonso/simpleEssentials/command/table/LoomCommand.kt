package de.timonso.simpleEssentials.command.table

import de.timonso.simpleEssentials.util.permission.SimpleEssentialsRegistry
import de.timonso.simpleEssentials.util.prefix.CommandPrefix
import dev.jorel.commandapi.kotlindsl.commandTree
import dev.jorel.commandapi.kotlindsl.playerExecutor
import dev.slne.surf.surfapi.core.api.messages.adventure.sendText

fun loomCommand() = commandTree("loom") {
    withPermission(SimpleEssentialsRegistry.LOOM_COMMAND)
    playerExecutor { player, _ ->
        player.openLoom(null, true)
        player.sendText {
            append(CommandPrefix.COMMAND_PREFIX)
            info("Der Webstuhl wurde geöffnet")
            info(".")
        }
    }
}