package de.timonso.simpleCore.command

import de.timonso.simpleCore.util.permission.SimpleCorePermissionRegistry
import dev.jorel.commandapi.kotlindsl.anyExecutor
import dev.jorel.commandapi.kotlindsl.commandTree
import dev.jorel.commandapi.kotlindsl.entitySelectorArgumentOnePlayer
import dev.jorel.commandapi.kotlindsl.getValue
import dev.slne.surf.surfapi.core.api.messages.adventure.buildText
import dev.slne.surf.surfapi.core.api.messages.adventure.clickOpensUrl
import dev.slne.surf.surfapi.core.api.messages.adventure.clickRunsCommand
import dev.slne.surf.surfapi.core.api.messages.adventure.sendText
import org.bukkit.attribute.Attribute
import org.bukkit.entity.Player

fun playerInfoCommand() = commandTree("playerinfo") {
    withPermission(SimpleCorePermissionRegistry.PLAYER_INFO_COMMAND)
    entitySelectorArgumentOnePlayer("player") {
        anyExecutor { executor, args ->
            val player: Player by args

            executor.sendText {
                appendInfoPrefix(); info("Spielerinformationen")
                appendSpace()
                appendNewline()

                appendInfoPrefix(); info("Name: "); variableValue(player.name); appendNewline()
                appendInfoPrefix(); info("UUID: "); variableValue(player.uniqueId.toString()); appendNewline()
                appendInfoPrefix(); info("IP: "); variableValue("${player.address?.address?.hostAddress}:${player.address?.port}"); appendNewline()
                appendInfoPrefix(); info("Host: "); variableValue("${player.virtualHost?.hostName ?: "Unbekannt"}: ${player.virtualHost?.port ?: "Unbekannt"}"); appendNewline()
                appendInfoPrefix(); info("Client: "); variableValue(player.clientBrandName ?: "Unbekannt"); appendNewline()
                appendInfoPrefix(); info("Leben: "); variableValue("${player.health}/${player.getAttribute(Attribute.MAX_HEALTH)?.value ?: 20.0}"); appendNewline()
                appendInfoPrefix(); info("Hunger: "); variableValue("${player.foodLevel}/20"); appendNewline()
                appendInfoPrefix(); info("Ping: "); variableValue("${player.ping}ms"); appendNewline()
                appendInfoPrefix(); info("Standort: "); variableValue("Welt: ${player.world.name}, X: ${player.location.blockX}, Y: ${player.location.blockY}, Z: ${player.location.blockZ}"); appendNewline()
                appendInfoPrefix().append(buildText { success("[NameMC Profil]").clickOpensUrl("https://de.namemc.com/profile/${player.name}").hoverEvent(buildText { info("Klicke um folgenden Link zu öffnen: https://de.namemc.com/profile/${player.name}"); appendNewline()
                        })
                })
                    appendSpace()
                    append(buildText { success("[Teleport]").clickRunsCommand("/tp ${player.name}").hoverEvent(buildText { info("Teleportiere dich zu ${player.name}") }) })
            }
        }
    }
}