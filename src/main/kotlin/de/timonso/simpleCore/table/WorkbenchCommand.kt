package de.timonso.simpleCore.table

import de.timonso.simpleCore.util.permission.SimpleCorePermissionRegistry
import de.timonso.simpleCore.util.prefix.CommandPrefix
import dev.jorel.commandapi.kotlindsl.commandTree
import dev.jorel.commandapi.kotlindsl.playerExecutor
import dev.slne.surf.surfapi.core.api.messages.adventure.sendText

fun workbenchCommand() = commandTree("workbench") {
    withPermission(SimpleCorePermissionRegistry.WORKBENCH_COMMAND)
    playerExecutor { player, _ ->
        player.openWorkbench(null, true)
        player.sendText {
            append(CommandPrefix.COMMAND_PREFIX)
            info("Die Werkbank wurde geöffnet")
            info(".")
        }
    }
}