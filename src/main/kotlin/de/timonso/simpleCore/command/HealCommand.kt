package de.timonso.simpleCore.command

import de.timonso.simpleCore.util.permission.SimpleCorePermissionRegistry
import de.timonso.simpleCore.util.prefix.CommandPrefix
import dev.jorel.commandapi.kotlindsl.*
import dev.slne.surf.surfapi.core.api.messages.adventure.sendText
import org.bukkit.attribute.Attribute
import org.bukkit.entity.Player

fun healCommand() = commandTree("heal") {
    withPermission(SimpleCorePermissionRegistry.HEAL_COMMAND)

    playerExecutor { player, _ ->
        player.health = player.getAttribute(Attribute.MAX_HEALTH)?.value ?: 20.0
        player.foodLevel = 20
        player.fireTicks = 0
        player.sendText {
            append(CommandPrefix.COMMAND_PREFIX)
            success("Du wurdest geheilt")
            success(".")
        }
    }

    entitySelectorArgumentOnePlayer("target") {
        withPermission(SimpleCorePermissionRegistry.HEAL_COMMAND_OTHERS)
        anyExecutor { executor, args ->
            val target: Player by args

            target.health = target.getAttribute(Attribute.MAX_HEALTH)?.value ?: 20.0
            target.foodLevel = 20
            target.fireTicks = 0

            executor.sendText {
                append(CommandPrefix.COMMAND_PREFIX)
                success("Der Spieler")
                appendSpace()
                variableValue(target.name)
                appendSpace()
                success("wurde geheilt")
                success(".")
            }

            target.sendText {
                append(CommandPrefix.COMMAND_PREFIX)
                info("Du wurdest geheilt")
                info(".")
            }
        }
    }
}