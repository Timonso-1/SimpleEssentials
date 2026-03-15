package de.timonso.simpleCore.tables

import de.timonso.simpleCore.util.permission.SimpleCorePermissionRegistry
import de.timonso.simpleCore.util.prefix.CommandPrefix
import dev.jorel.commandapi.kotlindsl.commandTree
import dev.jorel.commandapi.kotlindsl.playerExecutor
import dev.slne.surf.surfapi.core.api.messages.adventure.sendText

fun smithingTableCommand() = commandTree("smithing-table") {
    withPermission(SimpleCorePermissionRegistry.SMITHING_TABLE_COMMAND)
    playerExecutor { player, _ ->
        player.openSmithingTable(null, true)
        player.sendText {
            append(CommandPrefix.COMMAND_PREFIX)
            info("Der Schmiedetisch wurde geöffnet")
            info(".")
        }
    }
}