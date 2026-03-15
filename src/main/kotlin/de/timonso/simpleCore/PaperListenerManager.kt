package de.timonso.simpleCore

import de.timonso.simpleCore.listener.PlayerConnectionListener
import de.timonso.simpleCore.listener.LastLocationListener
import dev.slne.surf.surfapi.bukkit.api.event.register

object PaperListenerManager {
    fun registerAll() {
        PlayerConnectionListener().register()
        LastLocationListener().register()
    }
}