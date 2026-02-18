package de.timonso.simpleCore.command.vanilla

import de.timonso.simpleCore.util.permission.SimpleCorePermissionRegistry
import de.timonso.simpleCore.util.prefix.PrefixUtil
import dev.jorel.commandapi.kotlindsl.anyExecutor
import dev.jorel.commandapi.kotlindsl.commandTree
import dev.jorel.commandapi.kotlindsl.playerExecutor
import dev.jorel.commandapi.kotlindsl.worldArgument
import dev.slne.surf.surfapi.core.api.messages.adventure.buildText
import dev.slne.surf.surfapi.core.api.messages.adventure.clickCopiesToClipboard
import dev.slne.surf.surfapi.core.api.messages.adventure.sendText
import org.bukkit.World

fun seedCommand() = commandTree("seed") {
    withPermission(SimpleCorePermissionRegistry.SEED_COMMAND)
    playerExecutor { player, _ ->
        player.sendText {
            append(PrefixUtil.PREFIX)
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
            val world = args[0] as World

            executor.sendText {
                append(PrefixUtil.PREFIX)
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