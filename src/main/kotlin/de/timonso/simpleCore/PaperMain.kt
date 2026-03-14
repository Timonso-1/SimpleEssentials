package de.timonso.simpleCore

import org.bukkit.plugin.java.JavaPlugin

class PaperMain : JavaPlugin() {

    override fun onEnable() {
        PaperCommandManager.registerALL()
        PaperListenerManager.registerAll()

        server.consoleSender.sendMessage("SimpleCore has been enabled!")
    }

    override fun onDisable() {
            server.consoleSender.sendMessage("SimpleCore has been disabled!")
    }
}
