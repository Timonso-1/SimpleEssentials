package de.timonso.simpleCore

import de.timonso.simpleCore.command.*
import de.timonso.simpleCore.command.vanilla.*
import org.bukkit.plugin.java.JavaPlugin

class PaperCommandManager(private val plugin: JavaPlugin) {
    fun registerCommands() {
        flyCommand()
        godCommand()
        seedCommand()
        teleportCommand()
        summonCommand()
        whitelistCommand()
        pingCommand()
        healCommand()
        opCommand()
        deopCommand()
        spawnCommand()
        simpleCoreCommand(plugin)
        worldCommand()
        hatCommand()
        playerInfoCommand()
    }
}