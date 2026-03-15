package de.timonso.simpleCore.command.table

import de.timonso.simpleCore.util.permission.SimpleCorePermissionRegistry
import de.timonso.simpleCore.util.prefix.CommandPrefix
import dev.jorel.commandapi.kotlindsl.commandTree
import dev.jorel.commandapi.kotlindsl.playerExecutor
import dev.slne.surf.surfapi.core.api.messages.adventure.sendText

fun stonecutterCommand() = commandTree("stonecutter") {
    withPermission(SimpleCorePermissionRegistry.STONECUTTER_COMMAND)
    playerExecutor { player, _ ->
        player.openStonecutter(null, true)
        player.sendText {
            append(CommandPrefix.COMMAND_PREFIX)
            info("Der Steinschneider wurde geöffnet")
            info(".")
        }
    }
}