package de.timonso.simpleEssentials.command.vanilla

import de.timonso.simpleEssentials.util.permission.SimpleEssentialsRegistry
import de.timonso.simpleEssentials.util.prefix.CommandPrefix
import dev.jorel.commandapi.kotlindsl.*
import dev.slne.surf.surfapi.core.api.messages.adventure.sendText
import org.bukkit.Location
import org.bukkit.entity.EntityType

fun summonCommand() = commandTree("summon") {
    withPermission(SimpleEssentialsRegistry.SUMMON_COMMAND)

    entityTypeArgument("entityType") {
        playerExecutor { player, args ->
            val entityType: EntityType by args
            player.world.spawnEntity(player.location, entityType)
            player.sendText {
                append(CommandPrefix.COMMAND_PREFIX)
                success("Du hast")
                appendSpace()
                variableValue("1x")
                appendSpace()
                variableValue(entityType.name.lowercase().replace("_", " "))
                appendSpace()
                success("gespawnt")
                success(".")
            }
        }

        integerArgument("amount") {
            playerExecutor { player, args ->
                val entityType: EntityType by args
                val amount: Int by args
                repeat(amount) {
                    player.world.spawnEntity(player.location, entityType)
                }

                player.sendText {
                    append(CommandPrefix.COMMAND_PREFIX)
                    success("Du hast")
                    appendSpace()
                    variableValue("${amount}x")
                    appendSpace()
                    variableValue(entityType.name.lowercase().replace("_", " "))
                    appendSpace()
                    success("gespawnt")
                    success(".")
                }
            }

            locationArgument("location") {
                playerExecutor { player, args ->
                    val entityType: EntityType by args
                    val amount: Int by args
                    val location: Location by args
                    repeat(amount) {
                        player.world.spawnEntity(location, entityType)
                    }

                    player.sendText {
                        append(CommandPrefix.COMMAND_PREFIX)
                        success("Du hast")
                        appendSpace()
                        variableValue("${amount}x")
                        appendSpace()
                        variableValue(entityType.name.lowercase().replace("_", " "))
                        appendSpace()
                        success("bei den Koordinaten")
                        appendSpace()
                        variableValue("${location.blockX}, ${location.blockY}, ${location.blockZ}")
                        appendSpace()
                        success("gespawnt")
                        success(".")
                    }
                }
            }
        }
    }
}