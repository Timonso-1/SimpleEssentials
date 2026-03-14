package de.timonso.simpleCore.command.vanilla

import de.timonso.simpleCore.util.permission.SimpleCorePermissionRegistry
import de.timonso.simpleCore.util.prefix.CommandPrefix
import dev.jorel.commandapi.SuggestionInfo
import dev.jorel.commandapi.arguments.ArgumentSuggestions
import dev.jorel.commandapi.kotlindsl.*
import dev.slne.surf.surfapi.core.api.font.toSmallCaps
import dev.slne.surf.surfapi.core.api.messages.CommonComponents
import dev.slne.surf.surfapi.core.api.messages.adventure.buildText
import dev.slne.surf.surfapi.core.api.messages.adventure.clickCopiesToClipboard
import dev.slne.surf.surfapi.core.api.messages.adventure.sendText
import dev.slne.surf.surfapi.core.api.messages.pagination.Pagination
import dev.slne.surf.surfapi.core.api.service.PlayerLookupService
import kotlinx.coroutines.runBlocking
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Bukkit
import org.bukkit.OfflinePlayer
import org.bukkit.command.CommandSender
import java.util.*

fun whitelistCommand() = commandTree("whitelist") {
    withPermission(SimpleCorePermissionRegistry.WHITELIST_COMMAND)

    literalArgument("off") {
        anyExecutor { executor, _ ->
            if (!Bukkit.hasWhitelist()) {
                executor.sendText {
                    append(CommandPrefix.COMMAND_PREFIX)
                    error("Die Whitelist ist bereits deaktiviert")
                    error(".")
                }
            } else {
                Bukkit.setWhitelist(false)
                executor.sendText {
                    append(CommandPrefix.COMMAND_PREFIX)
                    success("Die Whitelist wurde deaktiviert")
                    success(".")
                }
            }
        }
    }

    literalArgument("on") {
        anyExecutor { executor, _ ->
            if (Bukkit.hasWhitelist()) {
                executor.sendText {
                    append(CommandPrefix.COMMAND_PREFIX)
                    error("Die Whitelist ist bereits aktiviert")
                    error(".")
                }
            } else {
                Bukkit.setWhitelist(true)
                executor.sendText {
                    append(CommandPrefix.COMMAND_PREFIX)
                    success("Die Whitelist wurde aktiviert")
                    success(".")
                }
            }
        }
    }

    literalArgument("info") {
        anyExecutor { executor, _ ->
            if (Bukkit.hasWhitelist()) {
                executor.sendText {
                    append(CommandPrefix.COMMAND_PREFIX)
                    info("Die Whitelist ist aktuell")
                    appendSpace()
                    variableValue("aktiviert")
                    info(".")
                }
            } else {
                executor.sendText {
                    append(CommandPrefix.COMMAND_PREFIX)
                    info("Die Whitelist ist aktuell")
                    appendSpace()
                    variableValue("deaktiviert")
                    info(".")
                }
            }
        }
    }

    literalArgument("add") {
        stringArgument("name") {
            anyExecutor { executor, args ->
                val input = args[0] as String
                val resolved = runBlocking { resolveFromNameSuspend(input) }

                if (resolved == null) {
                    executor.sendText {
                        append(CommandPrefix.COMMAND_PREFIX)
                        error("Der Spieler")
                        appendSpace()
                        variableValue(input)
                        appendSpace()
                        error("existiert nicht")
                        error(".")
                    }
                    return@anyExecutor
                }

                val (uuid, canonicalName) = resolved
                val target = Bukkit.getOfflinePlayer(uuid)

                if (target.isWhitelisted) {
                    executor.sendText {
                        append(CommandPrefix.COMMAND_PREFIX)
                        info("Der Spieler")
                        appendSpace()
                        variableValue(canonicalName)
                        appendSpace()
                        info("steht bereits auf der Whitelist")
                        info(".")
                    }
                } else {
                    target.isWhitelisted = true
                    executor.sendText {
                        append(CommandPrefix.COMMAND_PREFIX)
                        success("Der Spieler")
                        appendSpace()
                        variableValue(canonicalName)
                        appendSpace()
                        success("wurde zur Whitelist hinzugefügt")
                        success(".")
                    }
                }
            }
        }
    }

    literalArgument("remove") {
        stringArgument("name") {
            replaceSuggestions(
                ArgumentSuggestions.strings { _: SuggestionInfo<CommandSender> ->
                    Bukkit.getWhitelistedPlayers()
                        .map { player ->
                            player.name
                                ?: runBlocking { PlayerLookupService.getUsername(player.uniqueId) }
                                ?: player.uniqueId.toString()
                        }
                        .toTypedArray()
                }
            )
            anyExecutor { executor, args ->
                val input = args[0] as String
                val resolved = runBlocking { resolveFromNameSuspend(input) }

                if (resolved == null) {
                    executor.sendText {
                        append(CommandPrefix.COMMAND_PREFIX)
                        error("Der Spieler")
                        appendSpace()
                        variableValue(input)
                        appendSpace()
                        error("existiert nicht")
                        error(".")
                    }
                    return@anyExecutor
                }

                val (uuid, canonicalName) = resolved
                val target = Bukkit.getOfflinePlayer(uuid)

                if (!target.isWhitelisted) {
                    executor.sendText {
                        append(CommandPrefix.COMMAND_PREFIX)
                        info("Der Spieler")
                        appendSpace()
                        variableValue(canonicalName)
                        appendSpace()
                        info("steht nicht auf der Whitelist")
                        info(".")
                    }
                } else {
                    target.isWhitelisted = false
                    executor.sendText {
                        append(CommandPrefix.COMMAND_PREFIX)
                        success("Der Spieler")
                        appendSpace()
                        variableValue(canonicalName)
                        appendSpace()
                        success("wurde von der Whitelist entfernt")
                        success(".")
                    }
                }
            }
        }
    }

    literalArgument("list") {
        integerArgument("page", optional = true) {
            anyExecutor { executor, args ->
                val page: Int = if (args.get(0) == null) 1 else args[0] as Int

                executor.sendText {
                    append(CommandPrefix.COMMAND_PREFIX)
                    info("Die Whitelistinformationen werden gerade geladen...")
                }

                val whitelistedPlayers = Bukkit.getWhitelistedPlayers()
                    .map { player ->
                        val displayName = player.name ?: runBlocking { PlayerLookupService.getUsername(player.uniqueId) } ?: player.uniqueId.toString()
                        Pair(player, displayName)
                    }
                    .sortedWith(compareByDescending<Pair<OfflinePlayer, String>> { it.first.isOnline }
                        .thenBy { it.second.lowercase(Locale.getDefault()) })
                    .map { it.first }

                if (whitelistedPlayers.isEmpty()) {
                    executor.sendText {
                        append(CommandPrefix.COMMAND_PREFIX)
                        info("Es sind keine Spieler auf der Whitelist")
                        info(".")
                    }
                    return@anyExecutor
                }

                val pagination = Pagination<OfflinePlayer> {
                    title {
                        primary("Whitelist".toSmallCaps(), TextDecoration.UNDERLINED, TextDecoration.BOLD)
                        spacer(" | (${whitelistedPlayers.size})")
                    }
                    rowRenderer { row, _ ->
                        val uuidString = row.uniqueId.toString()
                        val displayName = row.name ?: runBlocking { PlayerLookupService.getUsername(row.uniqueId) } ?: uuidString

                        listOf(
                            buildText {
                                append(CommonComponents.EM_DASH)
                                appendSpace()
                                variableValue(displayName)
                                appendSpace()
                                spacer("(")
                                if (row.isOnline) {
                                    success("•".toSmallCaps(), TextDecoration.BOLD)
                                } else {
                                    error("•".toSmallCaps(), TextDecoration.BOLD)
                                }
                                spacer(")")

                                clickCopiesToClipboard(uuidString)
                                hoverEvent(buildText {
                                    info("Klicke, um die UUID in die Zwischenablage zu kopieren:")
                                    appendSpace()
                                    variableValue(uuidString)
                                })
                            }
                        )
                    }
                }
                executor.sendText {
                    append(pagination.renderComponent(whitelistedPlayers, page))
                }
            }
        }
    }
}
private suspend fun resolveFromNameSuspend(name: String): Pair<UUID, String>? {
    val uuid = PlayerLookupService.getUuid(name) ?: return null
    val canonical = PlayerLookupService.getUsername(uuid) ?: name
    return Pair(uuid, canonical)
}
