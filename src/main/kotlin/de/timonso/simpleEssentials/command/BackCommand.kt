package de.timonso.simpleEssentials.command

import de.timonso.simpleEssentials.service.lastLocationService
import de.timonso.simpleEssentials.util.permission.SimpleEssentialsRegistry
import de.timonso.simpleEssentials.util.prefix.CommandPrefix
import dev.jorel.commandapi.kotlindsl.commandTree
import dev.jorel.commandapi.kotlindsl.playerExecutor
import dev.slne.surf.surfapi.core.api.messages.adventure.sendText

fun backCommand() = commandTree("back") {
    withPermission(SimpleEssentialsRegistry.BACK_COMMAND)
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