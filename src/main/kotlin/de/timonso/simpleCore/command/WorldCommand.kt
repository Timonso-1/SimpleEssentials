package de.timonso.simpleCore.command

import de.timonso.simpleCore.util.permission.SimpleCorePermissionRegistry
import de.timonso.simpleCore.util.prefix.CommandPrefix
import dev.jorel.commandapi.kotlindsl.*
import dev.slne.surf.surfapi.core.api.messages.adventure.sendText
import org.bukkit.World

fun worldCommand() = commandTree("world") {
    withPermission(SimpleCorePermissionRegistry.WORLD_COMMAND)

    literalArgument("join") {

        worldArgument("world") {
            playerExecutor { player, args ->
                val world: World by args

                if (player.world == world) {
                    player.sendText {
                        append(CommandPrefix.COMMAND_PREFIX)
                        error("Du bist bereits in dieser Welt")
                        error(".")
                    }
                    return@playerExecutor
                }

                player.teleport(world.spawnLocation)
                player.sendText {
                    append(CommandPrefix.COMMAND_PREFIX)
                    success("Du wurdest in die")
                    appendSpace()
                    variableValue(world.name)
                    appendSpace()
                    success("Welt teleportiert")
                    success(".")
                }
            }
        }
    }
}