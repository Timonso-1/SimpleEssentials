package de.timonso.simpleCore.command

import de.timonso.simpleCore.util.permission.SimpleCorePermissionRegistry
import de.timonso.simpleCore.util.prefix.PrefixUtil
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
                append(PrefixUtil.PREFIX)
                success("Du kannst nun nicht mehr fliegen.")
            }
        } else {
            player.allowFlight = true
            player.isFlying = true

            player.sendText {
                append(PrefixUtil.PREFIX)
                success("Du kannst nun fliegen.")
            }
        }
    }

    entitySelectorArgumentOnePlayer("target") {
        withPermission(SimpleCorePermissionRegistry.FLY_COMMAND_OTHERS)
        anyExecutor { executor, args ->
            val target = args[0] as Player

            if (target.allowFlight) {
                target.allowFlight = false
                target.isFlying = false

                executor.sendText {
                    append(PrefixUtil.PREFIX)
                    variableValue(target.name)
                    appendSpace()
                    success("kann nun nicht mehr fliegen.")
                }
                target.sendText {
                    append(PrefixUtil.PREFIX)
                    info("Du kannst nun nicht mehr fliegen.")
                }
            } else {
                target.allowFlight = true
                target.isFlying = true

                executor.sendText {
                    append(PrefixUtil.PREFIX)
                    variableValue(target.name)
                    appendSpace()
                    success("kann nun fliegen.")
                }
                target.sendText {
                    append(PrefixUtil.PREFIX)
                    info("Du kannst nun fliegen.")
                }
            }
        }
    }
}