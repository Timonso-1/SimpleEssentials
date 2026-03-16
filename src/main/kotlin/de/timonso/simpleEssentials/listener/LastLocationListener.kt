package de.timonso.simpleEssentials.listener

import de.timonso.simpleEssentials.service.lastLocationService
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.PlayerTeleportEvent

class LastLocationListener : Listener {

    @EventHandler
    fun onPlayerTeleport(event: PlayerTeleportEvent) {
        val player = event.player
        lastLocationService.setLatestLocation(player.uniqueId, event.from)
    }

    @EventHandler
    fun onPlayerDeath(event: PlayerDeathEvent) {
        val player = event.entity
        lastLocationService.setLatestLocation(player.uniqueId, player.location)
    }
}


