package de.timonso.simpleCore.command.vanilla

import de.timonso.simpleCore.util.permission.SimpleCorePermissionRegistry
import de.timonso.simpleCore.util.prefix.CommandPrefix
import dev.jorel.commandapi.kotlindsl.*
import dev.slne.surf.surfapi.core.api.messages.adventure.sendText
import org.bukkit.Location
import org.bukkit.entity.Entity
import org.bukkit.entity.Player

fun teleportCommand() = commandTree("teleport") {
    withPermission(SimpleCorePermissionRegistry.TELEPORT_COMMAND)
    withAliases("tp")

    entitySelectorArgumentOnePlayer("target") {
        playerExecutor { player, args ->
            val target: Player by args
            player.teleport(target.location)

            player.sendText {
                append(CommandPrefix.COMMAND_PREFIX)
                success("Du wurdest zu")
                appendSpace()
                variableValue(target.name)
                appendSpace()
                success("teleportiert.")
            }
        }
    }

    locationArgument("location") {
        playerExecutor { player, args ->
            val location: Location by args
            player.teleport(location)

            player.sendText {
                append(CommandPrefix.COMMAND_PREFIX)
                success("Du wurdest zu")
                appendSpace()
                variableValue("${location.blockX}, ${location.blockY}, ${location.blockZ}")
                appendSpace()
                success("teleportiert.")
            }
        }
    }

    entitySelectorArgumentManyPlayers("players") {
        withPermission(SimpleCorePermissionRegistry.TELEPORT_COMMAND_OTHERS)

        entitySelectorArgumentOnePlayer("target") {
            anyExecutor { executor, args ->
                val players: Collection<Player> by args
                val target: Player by args
                players.forEach { it.teleport(target.location) }

                if (players.size == 1) {
                    executor.sendText {
                        append(CommandPrefix.COMMAND_PREFIX)
                        success("Der Spieler")
                        appendSpace()
                        variableValue(players.first().name)
                        appendSpace()
                        success("wurde zu")
                        appendSpace()
                        variableValue(target.name)
                        appendSpace()
                        success("teleportiert.")
                    }
                } else {
                    executor.sendText {
                        append(CommandPrefix.COMMAND_PREFIX)
                        variableValue(players.size)
                        appendSpace()
                        success("Spieler wurden zu")
                        appendSpace()
                        variableValue(target.name)
                        appendSpace()
                        success("teleportiert.")
                    }
                }

                players.forEach {
                    it.sendText {
                        append(CommandPrefix.COMMAND_PREFIX)
                        success("Du wurdest zu")
                        appendSpace()
                        variableValue(target.name)
                        appendSpace()
                        success("teleportiert.")
                    }
                }
            }
        }

        locationArgument("location") {
            anyExecutor { executor, args ->
                val players: Collection<Player> by args
                val location: Location by args
                players.forEach { it.teleport(location) }

                if (players.size == 1) {
                    executor.sendText {
                        append(CommandPrefix.COMMAND_PREFIX)
                        success("Der Spieler")
                        appendSpace()
                        variableValue(players.first().name)
                        appendSpace()
                        success("wurde zu")
                        appendSpace()
                        variableValue("${location.blockX}, ${location.blockY}, ${location.blockZ}")
                        appendSpace()
                        success("teleportiert.")
                    }
                } else {
                    executor.sendText {
                        append(CommandPrefix.COMMAND_PREFIX)
                        variableValue(players.size)
                        appendSpace()
                        success("Spieler wurden zu")
                        appendSpace()
                        variableValue("${location.blockX}, ${location.blockY}, ${location.blockZ}")
                        appendSpace()
                        success("teleportiert.")
                    }
                }

                players.forEach {
                    it.sendText {
                        append(CommandPrefix.COMMAND_PREFIX)
                        success("Du wurdest zu")
                        appendSpace()
                        variableValue("${location.blockX}, ${location.blockY}, ${location.blockZ}")
                        appendSpace()
                        success("teleportiert.")
                    }
                }
            }
        }
    }

    entitySelectorArgumentOneEntity("entity") {
        withPermission(SimpleCorePermissionRegistry.TELEPORT_COMMAND_OTHERS)

        locationArgument("location") {
            anyExecutor { executor, args ->
                val entity: Entity by args
                val location: Location by args
                entity.teleport(location)

                executor.sendText {
                    append(CommandPrefix.COMMAND_PREFIX)
                    success("Das Entity wurde zu")
                    appendSpace()
                    variableValue("${location.blockX}, ${location.blockY}, ${location.blockZ}")
                    appendSpace()
                    success("teleportiert.")
                }
            }
        }

        entitySelectorArgumentOnePlayer("targetPlayer") {
            anyExecutor { executor, args ->
                val entity: Entity by args
                val targetPlayer: Player by args
                entity.teleport(targetPlayer.location)

                executor.sendText {
                    append(CommandPrefix.COMMAND_PREFIX)
                    success("Das Entity wurde zu")
                    appendSpace()
                    variableValue(targetPlayer.name)
                    appendSpace()
                    success("teleportiert.")
                }
            }
        }
    }
}