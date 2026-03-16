package de.timonso.simpleEssentials.listener

import de.timonso.simpleEssentials.util.prefix.ConnectionPrefix
import dev.slne.surf.surfapi.bukkit.api.util.forEachPlayer
import dev.slne.surf.surfapi.core.api.messages.adventure.sendText
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

class PlayerConnectionListener : Listener {

    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        event.joinMessage(null)
        val player = event.player
        forEachPlayer {
            it.sendText {
                append(ConnectionPrefix.JOIN_PREFIX)
                variableValue(player.name)
            }
        }
    }

    @EventHandler
    fun onPlayerQuit(event: PlayerQuitEvent) {
        event.quitMessage(null)
        val player = event.player
        forEachPlayer {
            it.sendText {
                append(ConnectionPrefix.LEAVE_PREFIX)
                variableValue(player.name)
            }
        }
    }
}