package de.timonso.simpleCore

import de.timonso.simpleCore.command.*
import de.timonso.simpleCore.command.vanilla.*
import de.timonso.simpleCore.tables.anvilCommand
import de.timonso.simpleCore.tables.cartographyTableCommand
import de.timonso.simpleCore.tables.grindstoneCommand
import de.timonso.simpleCore.tables.loomCommand
import de.timonso.simpleCore.tables.smithingTableCommand
import de.timonso.simpleCore.tables.stonecutterCommand
import de.timonso.simpleCore.tables.workbenchCommand
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
    }
}