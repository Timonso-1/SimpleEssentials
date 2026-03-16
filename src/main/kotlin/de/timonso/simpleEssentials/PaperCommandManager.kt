package de.timonso.simpleEssentials

import de.timonso.simpleEssentials.command.*
import de.timonso.simpleEssentials.command.table.*
import de.timonso.simpleEssentials.command.vanilla.*
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
        anvilCommand()
        cartographyTableCommand()
        grindstoneCommand()
        loomCommand()
        smithingTableCommand()
        stonecutterCommand()
        workbenchCommand()
        setWorldSpawnCommand()
        backCommand()
        killCommand()
        enderchestCommand()
    }
}