package de.timonso.simpleEssentials.command

import de.timonso.simpleEssentials.util.permission.SimpleEssentialsRegistry
import de.timonso.simpleEssentials.util.prefix.CommandPrefix
import dev.jorel.commandapi.kotlindsl.*
import dev.slne.surf.surfapi.core.api.messages.adventure.sendText
import org.bukkit.entity.Player

fun godCommand() = commandTree("god") {
    withPermission(SimpleEssentialsRegistry.GOD_COMMAND)
    playerExecutor { player, _ ->
        player.isInvulnerable = !player.isInvulnerable

        if (player.isInvulnerable) {
            player.sendText {
                append(CommandPrefix.COMMAND_PREFIX)
                success("Du bist nun im Godmode")
                success(".")
            }
        } else {
            player.sendText {
                append(CommandPrefix.COMMAND_PREFIX)
                success("Du bist nun nicht mehr im Godmode")
                success(".")
            }
        }
    }

    entitySelectorArgumentOnePlayer("target") {
        withPermission(SimpleEssentialsRegistry.GOD_COMMAND_OTHERS)
        anyExecutor { executor, args ->
            val target: Player by args

            target.isInvulnerable = !target.isInvulnerable

            if (target.isInvulnerable) {
                executor.sendText {
                    append(CommandPrefix.COMMAND_PREFIX)
                    success("Der Spieler")
                    appendSpace()
                    variableValue(target.name)
                    appendSpace()
                    success("ist nun im Godmode")
                    success(".")
                }
                target.sendText {
                    append(CommandPrefix.COMMAND_PREFIX)
                    info("Du bist nun im Godmode")
                    info(".")
                }
            } else {
                executor.sendText {
                    append(CommandPrefix.COMMAND_PREFIX)
                    success("Der Spieler")
                    appendSpace()
                    variableValue(target.name)
                    appendSpace()
                    success("ist nun nicht mehr im Godmode")
                    success(".")
                }
                target.sendText {
                    append(CommandPrefix.COMMAND_PREFIX)
                    info("Du bist nun nicht mehr im Godmode")
                    info(".")
                }
            }
        }
    }
}