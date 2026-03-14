package de.timonso.simpleCore

import de.timonso.simpleCore.manager.SpawnLocationManager
import org.bukkit.plugin.java.JavaPlugin

class PaperMain : JavaPlugin() {

    override fun onEnable() {
        saveDefaultConfig()
        SpawnLocationManager.init(this)

        PaperCommandManager(this).registerCommands()
        PaperListenerManager.registerAll()

        server.consoleSender.sendMessage("SimpleCore has been enabled!")
    }

    override fun onDisable() {
            server.consoleSender.sendMessage("SimpleCore has been disabled!")
    }
}
