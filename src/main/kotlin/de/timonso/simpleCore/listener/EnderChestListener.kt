package de.timonso.simpleCore.listener

import de.timonso.simpleCore.manager.EnderChestManager
import de.timonso.simpleCore.util.permission.SimpleCorePermissionRegistry
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
        if (!player.hasPermission(SimpleCorePermissionRegistry.ENDERCHEST_COMMAND_MODIFY)) {
            event.isCancelled = true
        }
    }

    @EventHandler
    fun onClose(event: InventoryCloseEvent) {
        val player = event.player as? Player ?: return
        EnderChestManager.viewers.remove(player.uniqueId)
    }
}
