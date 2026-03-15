package de.timonso.simpleCore

import de.timonso.simpleCore.command.*
import de.timonso.simpleCore.command.vanilla.*
import de.timonso.simpleCore.command.table.anvilCommand
import de.timonso.simpleCore.command.table.cartographyTableCommand
import de.timonso.simpleCore.command.table.grindstoneCommand
import de.timonso.simpleCore.command.table.loomCommand
import de.timonso.simpleCore.command.table.smithingTableCommand
import de.timonso.simpleCore.command.table.stonecutterCommand
import de.timonso.simpleCore.command.table.workbenchCommand
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