package de.timonso.simpleEssentials

import de.timonso.simpleEssentials.listener.EnderChestListener
import de.timonso.simpleEssentials.listener.LastLocationListener
import de.timonso.simpleEssentials.listener.PlayerConnectionListener

object PaperListenerManager {
    fun registerAll() {
        PlayerConnectionListener()
        LastLocationListener()
        EnderChestListener()
    }
}