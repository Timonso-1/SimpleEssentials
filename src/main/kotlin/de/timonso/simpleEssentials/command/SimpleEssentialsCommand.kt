package de.timonso.simpleEssentials.command

import de.timonso.simpleEssentials.util.permission.SimpleEssentialsRegistry
import de.timonso.simpleEssentials.util.prefix.CommandPrefix
import dev.jorel.commandapi.kotlindsl.commandTree
import dev.jorel.commandapi.kotlindsl.literalArgument
import dev.jorel.commandapi.kotlindsl.playerExecutor
import dev.slne.surf.surfapi.core.api.messages.adventure.sendText
import org.bukkit.plugin.java.JavaPlugin
import java.util.logging.Level

fun simpleCoreCommand(plugin: JavaPlugin) = commandTree("simpleessentials") {

    withPermission(SimpleEssentialsRegistry.SIMPLEESSENTIALS_COMMAND)
    literalArgument("reload") {
        playerExecutor { player, _ ->
            try {
                plugin.reloadConfig()

                player.sendText {
                    append(CommandPrefix.COMMAND_PREFIX)
                    success("Die Config wurde erfolgreich neu geladen")
                    success(".")
                }
            } catch (exception: RuntimeException) {
                plugin.logger.log(Level.SEVERE, "Fehler beim Neuladen der Config", exception)
                player.sendText {
                    append(CommandPrefix.COMMAND_PREFIX)
                    error("Beim Neuladen der Config ist ein Fehler aufgetreten")
                    error(".")
                }
            }
        }
    }
}