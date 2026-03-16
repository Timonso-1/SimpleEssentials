package de.timonso.simpleEssentials

import de.timonso.simpleEssentials.manager.SpawnLocationManager
import org.bukkit.plugin.java.JavaPlugin

class PaperMain : JavaPlugin() {

    override fun onEnable() {
        saveDefaultConfig()
        SpawnLocationManager.init(this)

        PaperCommandManager(this).registerCommands()
        PaperListenerManager.registerAll()

        server.consoleSender.sendMessage("SimpleEssentials has been enabled!")
    }

    override fun onDisable() {
            server.consoleSender.sendMessage("SimpleEssentials has been disabled!")
    }
}
