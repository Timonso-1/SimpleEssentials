package de.timonso.simpleCore.command

import de.timonso.simpleCore.manager.EnderChestManager
import de.timonso.simpleCore.util.permission.SimpleCorePermissionRegistry
import de.timonso.simpleCore.util.prefix.CommandPrefix
import dev.jorel.commandapi.kotlindsl.commandTree
import dev.jorel.commandapi.kotlindsl.entitySelectorArgumentOnePlayer
import dev.jorel.commandapi.kotlindsl.getValue
import dev.jorel.commandapi.kotlindsl.playerExecutor
import dev.slne.surf.surfapi.core.api.messages.adventure.sendText
import org.bukkit.entity.Player

fun enderchestCommand() = commandTree("enderchest") {
    withPermission(SimpleCorePermissionRegistry.ENDERCHEST_COMMAND)
    withAliases("ec", "echest")
    playerExecutor { player, _ ->
        player.openInventory(player.enderChest)
        player.sendText {
            append(CommandPrefix.COMMAND_PREFIX)
            info("Deine Endertruhe wurde geöffnet")
            info(".")
        }
    }

    entitySelectorArgumentOnePlayer("target") {
        withPermission(SimpleCorePermissionRegistry.ENDERCHEST_COMMAND_OTHERS)
        playerExecutor { player, args ->
            val target: Player by args
            EnderChestManager.viewers[player.uniqueId] = target.uniqueId
            player.openInventory(target.enderChest)
            player.sendText {
                append(CommandPrefix.COMMAND_PREFIX)
                info("Die Endertruhe von")
                appendSpace()
                variableValue(target.name)
                appendSpace()
                info("wurde geöffnet")
                info(".")
            }
        }
    }
}