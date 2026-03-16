package de.timonso.simpleEssentials.command.vanilla

import de.timonso.simpleEssentials.util.permission.SimpleEssentialsRegistry
import de.timonso.simpleEssentials.util.prefix.CommandPrefix
import dev.jorel.commandapi.kotlindsl.*
import dev.slne.surf.surfapi.core.api.messages.adventure.sendText
import org.bukkit.entity.Entity
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player

fun killCommand() = commandTree("kill") {
    withPermission(SimpleEssentialsRegistry.KILL_COMMAND)
    playerExecutor { player, _ ->
        player.health = 0.0

        player.sendText {
            append(CommandPrefix.COMMAND_PREFIX)
            info("Du hast dich selbst getötet")
            info(".")
        }
    }
    entitySelectorArgumentManyEntities("targets") {
        withPermission(SimpleEssentialsRegistry.KILL_COMMAND_OTHERS)
        anyExecutor { executor, args ->
            val targets: Collection<Entity> by args

            targets.forEach {
                if (it is LivingEntity) {
                    it.health = 0.0
                    if (it is Player) {
                        it.sendText {
                            append(CommandPrefix.COMMAND_PREFIX)
                            info("Du wurdest von")
                            appendSpace()
                            variableValue(executor.name)
                            appendSpace()
                            info("getötet")
                            info(".")
                        }
                    }
                } else {
                    it.remove()
                    executor.sendText {
                        append(CommandPrefix.COMMAND_PREFIX)
                        error("Die Entität(en)")
                        appendSpace()
                        variableValue(it.type.name)
                        appendSpace()
                        error("konnte(n) nicht getötet werden")
                        error(".")
                    }
                }
            }

            executor.sendText {
                append(CommandPrefix.COMMAND_PREFIX)
                success("Du hast")
                appendSpace()
                variableValue(targets.size.toString())
                appendSpace()
                success("Entität(en) getötet.")
            }
        }
    }
}