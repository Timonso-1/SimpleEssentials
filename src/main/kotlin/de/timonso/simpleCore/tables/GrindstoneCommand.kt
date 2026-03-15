package de.timonso.simpleCore.tables

import de.timonso.simpleCore.util.permission.SimpleCorePermissionRegistry
import de.timonso.simpleCore.util.prefix.CommandPrefix
import dev.jorel.commandapi.kotlindsl.commandTree
import dev.jorel.commandapi.kotlindsl.playerExecutor
import dev.slne.surf.surfapi.core.api.messages.adventure.sendText

fun grindstoneCommand() = commandTree("grindstone") {
    withPermission(SimpleCorePermissionRegistry.GRINDSTONE_COMMAND)
    playerExecutor { player, _ ->
        player.openGrindstone(null, true)
        player.sendText {
            append(CommandPrefix.COMMAND_PREFIX)
            info("Der Schleifstein wurde geöffnet")
            info(".")
        }
    }
}