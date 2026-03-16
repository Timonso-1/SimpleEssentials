package de.timonso.simpleEssentials.command

import de.timonso.simpleEssentials.util.permission.SimpleEssentialsRegistry
import de.timonso.simpleEssentials.util.prefix.CommandPrefix
import dev.jorel.commandapi.kotlindsl.*
import dev.slne.surf.surfapi.core.api.messages.adventure.sendText
import org.bukkit.entity.Player

fun pingCommand() = commandTree("ping") {
    withPermission(SimpleEssentialsRegistry.PING_COMMAND)
    playerExecutor { player, _ ->

        player.sendText {
            append(CommandPrefix.COMMAND_PREFIX)
            info("Dein Ping beträgt")
            appendSpace()
            variableValue("${player.ping}ms")
            info(".")
        }
    }

    entitySelectorArgumentOnePlayer("target") {
        withPermission(SimpleEssentialsRegistry.PING_COMMAND_OTHERS)
        anyExecutor { executor, args ->
            val target: Player by args

            executor.sendText {
                append(CommandPrefix.COMMAND_PREFIX)
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