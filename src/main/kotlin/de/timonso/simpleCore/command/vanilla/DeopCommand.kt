package de.timonso.simpleCore.command.vanilla

import de.timonso.simpleCore.util.permission.SimpleCorePermissionRegistry
import de.timonso.simpleCore.util.prefix.CommandPrefix
import dev.jorel.commandapi.kotlindsl.anyExecutor
import dev.jorel.commandapi.kotlindsl.commandTree
import dev.jorel.commandapi.kotlindsl.entitySelectorArgumentOnePlayer
import dev.jorel.commandapi.kotlindsl.getValue
import dev.slne.surf.surfapi.core.api.messages.adventure.sendText
import org.bukkit.entity.Player

fun deopCommand() = commandTree("deop") {
    withPermission(SimpleCorePermissionRegistry.DEOP_COMMAND)
    entitySelectorArgumentOnePlayer("player") {
        anyExecutor { executor, args ->
            val player: Player by args

            if (!player.isOp) {
                executor.sendText {
                    append(CommandPrefix.COMMAND_PREFIX)
                    error("Der Spieler ist kein Operator.")
                }
            } else {
                player.isOp = false
                executor.sendText {
                    append(CommandPrefix.COMMAND_PREFIX)
                    success("Der Spieler")
                    appendSpace()
                    variableValue(player.name)
                    appendSpace()
                    success("ist nun kein Operator mehr")
                    success(".")
                }

                player.sendText {
                    append(CommandPrefix.COMMAND_PREFIX)
                    info("Du bist nun kein Operator mehr")
                    info(".")
                }
            }
        }
    }
}