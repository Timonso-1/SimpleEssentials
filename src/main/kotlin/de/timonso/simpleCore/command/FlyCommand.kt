package de.timonso.simpleCore.command

import de.timonso.simpleCore.util.permission.SimpleCorePermissionRegistry
import de.timonso.simpleCore.util.prefix.CommandPrefix
import dev.jorel.commandapi.kotlindsl.*
import dev.slne.surf.surfapi.core.api.messages.adventure.sendText
import org.bukkit.entity.Player

fun flyCommand() = commandTree("fly") {
    withPermission(SimpleCorePermissionRegistry.FLY_COMMAND)
    playerExecutor { player, _ ->

        if (player.allowFlight) {
            player.allowFlight = false
            player.isFlying = false

            player.sendText {
                append(CommandPrefix.COMMAND_PREFIX)
                success("Du kannst nun nicht mehr fliegen")
                success(".")
            }
        } else {
            player.allowFlight = true
            player.isFlying = true

            player.sendText {
                append(CommandPrefix.COMMAND_PREFIX)
                success("Du kannst nun fliegen")
                success(".")
            }
        }
    }

    entitySelectorArgumentOnePlayer("target") {
        withPermission(SimpleCorePermissionRegistry.FLY_COMMAND_OTHERS)
        anyExecutor { executor, args ->
            val target: Player by args

            if (target.allowFlight) {
                target.allowFlight = false
                target.isFlying = false

                executor.sendText {
                    append(CommandPrefix.COMMAND_PREFIX)
                    success("Der Spieler")
                    appendSpace()
                    variableValue(target.name)
                    appendSpace()
                    success("kann nun nicht mehr fliegen")
                    success(".")
                }
                target.sendText {
                    append(CommandPrefix.COMMAND_PREFIX)
                    info("Du kannst nun nicht mehr fliegen")
                    info(".")
                }
            } else {
                target.allowFlight = true
                target.isFlying = true

                executor.sendText {
                    append(CommandPrefix.COMMAND_PREFIX)
                    success("Der Spieler")
                    appendSpace()
                    variableValue(target.name)
                    appendSpace()
                    success("kann nun fliegen.")
                    success(".")
                }
                target.sendText {
                    append(CommandPrefix.COMMAND_PREFIX)
                    info("Du kannst nun fliegen")
                    info(".")
                }
            }
        }
    }
}