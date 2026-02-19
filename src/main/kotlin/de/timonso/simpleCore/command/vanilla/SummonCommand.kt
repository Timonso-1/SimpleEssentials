package de.timonso.simpleCore.command.vanilla

import de.timonso.simpleCore.util.permission.SimpleCorePermissionRegistry
import de.timonso.simpleCore.util.prefix.PrefixUtil
import dev.jorel.commandapi.kotlindsl.*
import dev.slne.surf.surfapi.core.api.messages.adventure.sendText

fun summonCommand() = commandTree("summon") {
    withPermission(SimpleCorePermissionRegistry.SUMMON_COMMAND)

    entityTypeArgument("entityType") {
        playerExecutor { player, args ->
            val entityType = args[0] as org.bukkit.entity.EntityType
            player.world.spawnEntity(player.location, entityType)
            player.sendText {
                append(PrefixUtil.PREFIX)
                success("Du hast")
                appendSpace()
                variableValue(entityType.name.lowercase().replace("_", " "))
                success("x")
                appendSpace()
                success("gespawnt")
                success(".")
            }
        }

        integerArgument("ammount") {
            playerExecutor { player, args ->
                val entityType = args[0] as org.bukkit.entity.EntityType
                val ammount = args[1] as Int
                repeat(ammount) {
                    player.world.spawnEntity(player.location, entityType)
                }

                player.sendText {
                    append(PrefixUtil.PREFIX)
                    success("Du hast")
                    appendSpace()
                    variableValue(ammount.toString())
                    success("x")
                    appendSpace()
                    variableValue(entityType.name.lowercase().replace("_", " "))
                    appendSpace()
                    success("gespawnt")
                    success(".")
                }
            }

            locationArgument("location") {
                playerExecutor { player, args ->
                    val entityType = args[0] as org.bukkit.entity.EntityType
                    val amount = args[1] as Int
                    val location = args[2] as org.bukkit.Location
                    repeat(amount) {
                        player.world.spawnEntity(location, entityType)
                    }

                    player.sendText {
                        append(PrefixUtil.PREFIX)
                        success("Du hast")
                        appendSpace()
                        variableValue(amount.toString())
                        success("x")
                        appendSpace()
                        variableValue(entityType.name.lowercase().replace("_", " "))
                        appendSpace()
                        success("gespawnt")
                        success(".")
                    }
                }
            }
        }
    }
}