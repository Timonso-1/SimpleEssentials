package de.timonso.simpleCore.util.permission

import dev.slne.surf.surfapi.bukkit.api.permission.PermissionRegistry

object SimpleCorePermissionRegistry : PermissionRegistry() {
    private const val PREFIX = "simplecore"

    val FLY_COMMAND = create("$PREFIX.fly.command")
    val FLY_COMMAND_OTHERS = create("$PREFIX.fly.others.command")
    val GOD_COMMAND = create("$PREFIX.god.command")
    val GOD_COMMAND_OTHERS = create("$PREFIX.god.others.command")
}
