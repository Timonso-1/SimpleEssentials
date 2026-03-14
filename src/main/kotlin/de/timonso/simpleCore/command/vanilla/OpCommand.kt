package de.timonso.simpleCore.command.vanilla

import de.timonso.simpleCore.util.permission.SimpleCorePermissionRegistry
import de.timonso.simpleCore.util.prefix.CommandPrefix
import dev.jorel.commandapi.kotlindsl.anyExecutor
import dev.jorel.commandapi.kotlindsl.commandTree
import dev.jorel.commandapi.kotlindsl.entitySelectorArgumentOnePlayer
import dev.jorel.commandapi.kotlindsl.getValue
import dev.slne.surf.surfapi.core.api.messages.adventure.sendText
import org.bukkit.entity.Player

fun opCommand() = commandTree("op") {
    withPermission(SimpleCorePermissionRegistry.OP_COMMAND)
    entitySelectorArgumentOnePlayer("player") {
        anyExecutor { executor, args ->
            val player: Player by args

            if (player.isOp) {
                executor.sendText {
                    append(CommandPrefix.COMMAND_PREFIX)
                    error("Der Spieler ist bereits ein Operator.")
                }
            } else {
                player.isOp = true
                executor.sendText {
                    append(CommandPrefix.COMMAND_PREFIX)
                    success("Der Spieler")
                    appendSpace()
                    variableValue(player.name)
                    appendSpace()
                    success("ist nun ein Operator")
                    success(".")
                }

                player.sendText {
                    append(CommandPrefix.COMMAND_PREFIX)
                    info("Du bist nun ein Operator")
                    info(".")
                }
            }
        }
    }
}
