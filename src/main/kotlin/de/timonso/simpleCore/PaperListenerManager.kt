package de.timonso.simpleCore

import de.timonso.simpleCore.listener.EnderChestListener
import de.timonso.simpleCore.listener.LastLocationListener
import de.timonso.simpleCore.listener.PlayerConnectionListener

object PaperListenerManager {
    fun registerAll() {
        PlayerConnectionListener()
        LastLocationListener()
        EnderChestListener()
    }
}