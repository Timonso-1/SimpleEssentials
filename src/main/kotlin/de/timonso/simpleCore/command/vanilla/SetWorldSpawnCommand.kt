package de.timonso.simpleCore.command.vanilla

import de.timonso.simpleCore.util.permission.SimpleCorePermissionRegistry
import de.timonso.simpleCore.util.prefix.CommandPrefix
import dev.jorel.commandapi.kotlindsl.commandTree
import dev.jorel.commandapi.kotlindsl.getValue
import dev.jorel.commandapi.kotlindsl.locationArgument
import dev.jorel.commandapi.kotlindsl.playerExecutor
import dev.slne.surf.surfapi.core.api.messages.adventure.sendText
import org.bukkit.Location

fun setWorldSpawnCommand() = commandTree("setworldspawn") {
    withPermission(SimpleCorePermissionRegistry.SET_WORLD_SPAWN_COMMAND)
    playerExecutor { player, _ ->
        player.world.spawnLocation = player.location
        player.sendText {
            append(CommandPrefix.COMMAND_PREFIX)
            success("Der Wiedereinstiegspunkt der Welt")
            appendSpace()
            variableValue(player.world.name)
            appendSpace()
            success("wurde auf deine aktuelle Position gesetzt")
            success(".")
        }
    }

    locationArgument("location") {
        playerExecutor { player, args ->
            val location: Location by args
            player.world.spawnLocation = location
            player.sendText {
                append(CommandPrefix.COMMAND_PREFIX)
                success("Der Wiedereinstiegspunkt der Welt")
                appendSpace()
                variableValue(player.world.name)
                appendSpace()
                success("wurde auf die Koordinaten")
                appendSpace()
                variableValue("${location.blockX} ${location.blockY} ${location.blockZ}")
                appendSpace()
                success("gesetzt")
                success(".")
            }
        }
    }
}
