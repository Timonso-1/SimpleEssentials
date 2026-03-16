package de.timonso.simpleEssentials.listener

import de.timonso.simpleEssentials.manager.EnderChestManager
import de.timonso.simpleEssentials.util.permission.SimpleEssentialsRegistry
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent

class EnderChestListener : Listener {

    @EventHandler
    fun onClick(event: InventoryClickEvent) {
        val player = event.whoClicked as Player
        val targetUUID = EnderChestManager.viewers[player.uniqueId] ?: return
        if(player.uniqueId == targetUUID) return
        if (!player.hasPermission(SimpleEssentialsRegistry.ENDERCHEST_COMMAND_MODIFY)) {
            event.isCancelled = true
        }
    }

    @EventHandler
    fun onClose(event: InventoryCloseEvent) {
        val player = event.player as? Player ?: return
        EnderChestManager.viewers.remove(player.uniqueId)
    }
}
