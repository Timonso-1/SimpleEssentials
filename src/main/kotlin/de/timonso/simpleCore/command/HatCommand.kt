package de.timonso.simpleCore.command

import de.timonso.simpleCore.util.permission.SimpleCorePermissionRegistry
import de.timonso.simpleCore.util.prefix.CommandPrefix
import dev.jorel.commandapi.kotlindsl.commandTree
import dev.jorel.commandapi.kotlindsl.entitySelectorArgumentOnePlayer
import dev.jorel.commandapi.kotlindsl.getValue
import dev.jorel.commandapi.kotlindsl.playerExecutor
import dev.slne.surf.surfapi.core.api.messages.adventure.buildText
import dev.slne.surf.surfapi.core.api.messages.adventure.sendText
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.event.HoverEvent
import org.bukkit.entity.Player

fun hatCommand() = commandTree("hat") {
    withPermission(SimpleCorePermissionRegistry.HAT_COMMAND)
    playerExecutor { player, _ ->
        val itemInMainHand = player.inventory.itemInMainHand
        player.inventory.helmet = itemInMainHand

        if (itemInMainHand.type.isAir) {
            player.sendText {
                append(CommandPrefix.COMMAND_PREFIX)
                success("Du hast deinen Hut entfernt")
                success(".")
            }
        } else {
            player.sendText {
                append(CommandPrefix.COMMAND_PREFIX)
                append(buildText {
                    success("Du hast dir einen Hut aufgesetzt")
                    success(".")
                    hoverEvent(HoverEvent.showText(Component.text(itemInMainHand.type.name)))
                })
            }
        }
    }
    entitySelectorArgumentOnePlayer("player") {
        withPermission(SimpleCorePermissionRegistry.HAT_COMMAND_OTHERS)
        playerExecutor { executor, args ->
            val player: Player by args
            val itemInMainHand = executor.inventory.itemInMainHand
            player.inventory.helmet = itemInMainHand

            if (itemInMainHand.type.isAir) {
                executor.sendText {
                    append(CommandPrefix.COMMAND_PREFIX)
                    success("Du hast den Hut von")
                    appendSpace()
                    variableValue(player.name)
                    appendSpace()
                    success("entfernt")
                    success(".")
                }
                player.sendText {
                    append(CommandPrefix.COMMAND_PREFIX)
                    success("Dir wurde dein Hut entfernt")
                    success(".")
                }
            } else {
                executor.sendText {
                    append(CommandPrefix.COMMAND_PREFIX)
                    success("Du hast")
                    appendSpace()
                    variableValue(player.name)
                    appendSpace()
                    success("einen")
                    appendSpace()
                    append(buildText {
                        success("Hut")
                        hoverEvent(HoverEvent.showText(Component.text(itemInMainHand.type.name)))
                    })
                    appendSpace()
                    success("aufgesetzt")
                    success(".")
                }
                player.sendText {
                    append(CommandPrefix.COMMAND_PREFIX)
                    append(buildText {
                        success("Dir wurde ein Hut aufgesetzt")
                        success(".")
                        hoverEvent(HoverEvent.showText(Component.text(itemInMainHand.type.name)))
                    })
                }
            }
        }
    }
}