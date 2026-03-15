package de.timonso.simpleCore.util.permission

import dev.slne.surf.surfapi.bukkit.api.permission.PermissionRegistry

object SimpleCorePermissionRegistry : PermissionRegistry() {
    private const val PREFIX = "simplecore"

    val FLY_COMMAND = create("$PREFIX.fly.command")
    val FLY_COMMAND_OTHERS = create("$PREFIX.fly.others.command")
    val GOD_COMMAND = create("$PREFIX.god.command")
    val GOD_COMMAND_OTHERS = create("$PREFIX.god.others.command")
    val SEED_COMMAND = create("$PREFIX.seed.command")
    val TELEPORT_COMMAND = create("$PREFIX.teleport.command")
    val TELEPORT_COMMAND_OTHERS = create("$PREFIX.teleport.others.command")
    val SUMMON_COMMAND = create("$PREFIX.summon.command")
    val WHITELIST_COMMAND = create("$PREFIX.whitelist.command")
    val HEAL_COMMAND = create("$PREFIX.heal.command")
    val HEAL_COMMAND_OTHERS = create("$PREFIX.heal.others.command")
    val PING_COMMAND = create("$PREFIX.ping.command")
    val PING_COMMAND_OTHERS = create("$PREFIX.ping.others.command")
    val OP_COMMAND = create("$PREFIX.operator.command")
    val DEOP_COMMAND = create("$PREFIX.deop.command")
    val SPAWN_COMMAND = create("$PREFIX.spawn.command")
    val SPAWN_COMMAND_WORLD = create("$PREFIX.spawn.world.command")
    val SPAWN_COMMAND_SET = create("$PREFIX.spawn.set.command")
    val SIMPLECORE_COMMAND = create("$PREFIX.command")
    val WORLD_COMMAND = create("$PREFIX.world.command")
    val HAT_COMMAND = create("$PREFIX.hat.command")
    val HAT_COMMAND_OTHERS = create("$PREFIX.hat.others.command")
    val PLAYER_INFO_COMMAND = create("$PREFIX.playerinfo.command")
    val ANVIL_COMMAND = create("$PREFIX.anvil.command")
    val CARTOGRAPHY_TABLE_COMMAND = create("$PREFIX.cartography.command")
    val GRINDSTONE_COMMAND = create("$PREFIX.grindstone.command")
    val LOOM_COMMAND = create("$PREFIX.loom.command")
    val SMITHING_TABLE_COMMAND = create("$PREFIX.smithingtable.command")
    val STONECUTTER_COMMAND = create("$PREFIX.stonecutter.command")
    val WORKBENCH_COMMAND = create("$PREFIX.workbench.command")
    val SET_WORLD_SPAWN_COMMAND = create("$PREFIX.setworldspawn.command")
    val BACK_COMMAND = create("$PREFIX.back.command")
    val KILL_COMMAND = create("$PREFIX.kill.command")
    val KILL_COMMAND_OTHERS = create("$PREFIX.kill.others.command")
}