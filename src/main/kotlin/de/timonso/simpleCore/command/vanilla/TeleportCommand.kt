package de.timonso.simpleCore.command.vanilla

import de.timonso.simpleCore.util.permission.SimpleCorePermissionRegistry
import de.timonso.simpleCore.util.prefix.PrefixUtil
import dev.jorel.commandapi.kotlindsl.*
import dev.slne.surf.surfapi.core.api.messages.adventure.sendText

fun teleportCommand() = commandTree("teleport") {
    withPermission(SimpleCorePermissionRegistry.TELEPORT_COMMAND)
    withAliases("tp")

    entitySelectorArgumentOnePlayer("target") {
        playerExecutor { player, args ->
            val target = args[0] as org.bukkit.entity.Player
            player.teleport(target.location)

            player.sendText {
                append(PrefixUtil.PREFIX)
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
            val location = args[0] as org.bukkit.Location
            player.teleport(location)

            player.sendText {
                append(PrefixUtil.PREFIX)
                success("Du wurdest zu")
                appendSpace()
                variableValue("(${location.x}, ${location.y}, ${location.z})")
                appendSpace()
                success("teleportiert.")
            }
        }
    }

    entitySelectorArgumentManyPlayers("players") {
        withPermission(SimpleCorePermissionRegistry.TELEPORT_COMMAND_OTHERS)

        entitySelectorArgumentOnePlayer("target") {
            anyExecutor { executor, args ->
                val players = args[0] as List<org.bukkit.entity.Player>
                val target = args[1] as org.bukkit.entity.Player
                players.forEach { it.teleport(target.location) }

                if (players.size == 1) {
                    executor.sendText {
                        append(PrefixUtil.PREFIX)
                        variableValue(players[0].name)
                        appendSpace()
                        success("wurde zu")
                        appendSpace()
                        variableValue(target.name)
                        appendSpace()
                        success("teleportiert.")
                    }
                } else {
                    executor.sendText {
                        append(PrefixUtil.PREFIX)
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
                        append(PrefixUtil.PREFIX)
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
                val players = args[0] as List<org.bukkit.entity.Player>
                val location = args[1] as org.bukkit.Location
                players.forEach { it.teleport(location) }

                if (players.size == 1) {
                    executor.sendText {
                        append(PrefixUtil.PREFIX)
                        variableValue(players[0].name)
                        appendSpace()
                        success("wurde zu")
                        appendSpace()
                        variableValue("(${location.x}, ${location.y}, ${location.z})")
                        appendSpace()
                        success("teleportiert.")
                    }
                } else {
                    executor.sendText {
                        append(PrefixUtil.PREFIX)
                        variableValue(players.size)
                        appendSpace()
                        success("Spieler wurden zu")
                        appendSpace()
                        variableValue("(${location.x}, ${location.y}, ${location.z})")
                        appendSpace()
                        success("teleportiert.")
                    }
                }

                players.forEach {
                    it.sendText {
                        append(PrefixUtil.PREFIX)
                        success("Du wurdest zu")
                        appendSpace()
                        variableValue("(${location.x}, ${location.y}, ${location.z})")
                        appendSpace()
                        success("teleportiert.")
                    }
                }
            }
        }
    }
}