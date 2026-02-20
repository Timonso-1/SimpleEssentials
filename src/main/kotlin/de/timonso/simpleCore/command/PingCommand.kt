package de.timonso.simpleCore.command

import de.timonso.simpleCore.util.permission.SimpleCorePermissionRegistry
import de.timonso.simpleCore.util.prefix.PrefixUtil
import dev.jorel.commandapi.kotlindsl.*
import dev.slne.surf.surfapi.core.api.messages.adventure.sendText
import org.bukkit.entity.Player

fun pingCommand() = commandTree("ping") {
    withPermission(SimpleCorePermissionRegistry.PING_COMMAND)
    playerExecutor { player, _ ->

        player.sendText {
            append(PrefixUtil.PREFIX)
            info("Dein Ping beträgt")
            appendSpace()
            variableValue("${player.ping}ms")
            info(".")
        }
    }

    entitySelectorArgumentOnePlayer("target") {
        withPermission(SimpleCorePermissionRegistry.PING_COMMAND_OTHERS)
        anyExecutor { executor, args ->
            val target: Player by args

            executor.sendText {
                append(PrefixUtil.PREFIX)
                info("Der Spieler")
                appendSpace()
                variableValue(target.name)
                appendSpace()
                info("hat einen Ping von")
                appendSpace()
                variableValue("${target.ping}ms")
                info(".")
            }
        }
    }
}