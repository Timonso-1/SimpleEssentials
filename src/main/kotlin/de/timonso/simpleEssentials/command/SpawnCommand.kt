package de.timonso.simpleEssentials.command

import de.timonso.simpleEssentials.manager.SpawnLocationManager
import de.timonso.simpleEssentials.util.permission.SimpleEssentialsRegistry
import de.timonso.simpleEssentials.util.prefix.CommandPrefix
import dev.jorel.commandapi.kotlindsl.*
import dev.slne.surf.surfapi.core.api.messages.adventure.sendText
import org.bukkit.Location
import org.bukkit.World
import org.bukkit.entity.Player

private fun resolveSpawnLocationOrNotify(player: Player, worldName: String): Location? {
    if (!SpawnLocationManager.hasSpawnLocation(worldName)) {
        player.sendText {
            append(CommandPrefix.COMMAND_PREFIX)
            info("In der Welt")
            appendSpace()
            variableValue(worldName)
            appendSpace()
            info("ist noch kein Spawn gesetzt")
            info(".")
        }
        return null
    }

    return SpawnLocationManager.getSpawnLocation(worldName) ?: run {
        player.sendText {
            append(CommandPrefix.COMMAND_PREFIX)
            error("Der Spawnpunkt konnte nicht geladen werden")
            error(".")
        }
        null
    }
}

fun spawnCommand() = commandTree("spawn") {
    withPermission(SimpleEssentialsRegistry.SPAWN_COMMAND)

    playerExecutor { player, _ ->
        val worldName = player.world.name
        val location = resolveSpawnLocationOrNotify(player, worldName) ?: return@playerExecutor

        player.teleport(location)
        player.sendText {
            append(CommandPrefix.COMMAND_PREFIX)
            success("Du wurdest zum Spawn der Welt")
            appendSpace()
            variableValue(worldName)
            appendSpace()
            success("teleportiert")
            success(".")
        }
    }

    literalArgument("set") {
        withPermission(SimpleEssentialsRegistry.SPAWN_COMMAND_SET)
        playerExecutor { player, _ ->
            val location = player.location
            SpawnLocationManager.setSpawnLocation(location)
            player.sendText {
                append(CommandPrefix.COMMAND_PREFIX)
                info("Du hast den Spawnpunkt in der Welt")
                appendSpace()
                variableValue(player.world.name)
                appendSpace()
                info("erfolgreich bei den Koordinaten")
                appendSpace()
                variableValue("${location.blockX}, ${location.blockY}, ${location.blockZ}")
                appendSpace()
                info("gesetzt")
                info(".")
            }
        }
    }

    literalArgument("world") {
        withPermission(SimpleEssentialsRegistry.SPAWN_COMMAND_WORLD)

        worldArgument("world") {
            playerExecutor { player, args ->
                val world: World by args
                val worldName = world.name
                val location = resolveSpawnLocationOrNotify(player, worldName) ?: return@playerExecutor

                player.teleport(location)
                player.sendText {
                    append(CommandPrefix.COMMAND_PREFIX)
                    success("Du wurdest zum Spawn der Welt")
                    appendSpace()
                    variableValue(worldName)
                    appendSpace()
                    success("teleportiert")
                    success(".")
                }
            }
        }
    }
}
