package de.timonso.simpleCore.command.vanilla

import de.timonso.simpleCore.util.permission.SimpleCorePermissionRegistry
import de.timonso.simpleCore.util.prefix.CommandPrefix
import dev.jorel.commandapi.kotlindsl.*
import dev.slne.surf.surfapi.core.api.messages.adventure.buildText
import dev.slne.surf.surfapi.core.api.messages.adventure.clickCopiesToClipboard
import dev.slne.surf.surfapi.core.api.messages.adventure.sendText
import org.bukkit.World

fun seedCommand() = commandTree("seed") {
    withPermission(SimpleCorePermissionRegistry.SEED_COMMAND)

    playerExecutor { player, _ ->
        player.sendText {
            append(CommandPrefix.COMMAND_PREFIX)
            info("Der Seed der Welt")
            appendSpace()
            variableValue(player.world.name)
            appendSpace()
            info("ist")
            appendSpace()
            variableValue(player.world.seed)
            info(".")
            clickCopiesToClipboard(player.world.seed.toString())
            hoverEvent(buildText {
                info("Klicke, um den Seed in die Zwischenablage zu kopieren.")
            })
        }
    }
    worldArgument("world") {
        anyExecutor { executor, args ->
            val world: World by args

            executor.sendText {
                append(CommandPrefix.COMMAND_PREFIX)
                info("Der Seed der Welt")
                appendSpace()
                variableValue(world.name)
                appendSpace()
                info("ist")
                appendSpace()
                variableValue(world.seed)
                info(".")
                clickCopiesToClipboard(world.seed.toString())
                hoverEvent(buildText {
                    info("Klicke, um den Seed in die Zwischenablage zu kopieren.")
                })
            }
        }
    }
}