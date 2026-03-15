package de.timonso.simpleCore.command.table

import de.timonso.simpleCore.util.permission.SimpleCorePermissionRegistry
import de.timonso.simpleCore.util.prefix.CommandPrefix
import dev.jorel.commandapi.kotlindsl.commandTree
import dev.jorel.commandapi.kotlindsl.playerExecutor
import dev.slne.surf.surfapi.core.api.messages.adventure.sendText

fun cartographyTableCommand() = commandTree("cartography-table") {
    withPermission(SimpleCorePermissionRegistry.CARTOGRAPHY_TABLE_COMMAND)
    playerExecutor { player, _ ->
        player.openCartographyTable(null, true)
        player.sendText {
            append(CommandPrefix.COMMAND_PREFIX)
            info("Der Kartentisch wurde geöffnet")
            info(".")
        }
    }
}