package de.timonso.simpleCore.command

import de.timonso.simpleCore.service.lastLocationService
import de.timonso.simpleCore.util.permission.SimpleCorePermissionRegistry
import de.timonso.simpleCore.util.prefix.CommandPrefix
import dev.jorel.commandapi.kotlindsl.commandTree
import dev.jorel.commandapi.kotlindsl.playerExecutor
import dev.slne.surf.surfapi.core.api.messages.adventure.sendText

fun backCommand() = commandTree("back") {
    withPermission(SimpleCorePermissionRegistry.BACK_COMMAND)
    playerExecutor { player, _ ->
        val lastLocation = lastLocationService.getLatestLocation(player.uniqueId)
        if (lastLocation != null) {
            player.teleport(lastLocation)
            player.sendText {
                append(CommandPrefix.COMMAND_PREFIX)
                success("Du wurdest zu deinem letzten Standort teleportiert")
                success(".")
            }
        } else {
            player.sendText {
                append(CommandPrefix.COMMAND_PREFIX)
                error("Es konnte kein letzter Standort gefunden werden")
                error(".")
            }
        }
    }
}