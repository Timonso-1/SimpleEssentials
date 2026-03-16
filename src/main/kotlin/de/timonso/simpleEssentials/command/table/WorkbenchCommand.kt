package de.timonso.simpleEssentials.command.table

import de.timonso.simpleEssentials.util.permission.SimpleEssentialsRegistry
import de.timonso.simpleEssentials.util.prefix.CommandPrefix
import dev.jorel.commandapi.kotlindsl.commandTree
import dev.jorel.commandapi.kotlindsl.playerExecutor
import dev.slne.surf.surfapi.core.api.messages.adventure.sendText

fun workbenchCommand() = commandTree("workbench") {
    withPermission(SimpleEssentialsRegistry.WORKBENCH_COMMAND)
    playerExecutor { player, _ ->
        player.openWorkbench(null, true)
        player.sendText {
            append(CommandPrefix.COMMAND_PREFIX)
            info("Die Werkbank wurde geöffnet")
            info(".")
        }
    }
}