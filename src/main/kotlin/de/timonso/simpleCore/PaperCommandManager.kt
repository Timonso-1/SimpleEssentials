package de.timonso.simpleCore

import de.timonso.simpleCore.command.flyCommand
import de.timonso.simpleCore.command.godCommand
import de.timonso.simpleCore.command.vanilla.seedCommand
import de.timonso.simpleCore.command.vanilla.teleportCommand

object PaperCommandManager {
    fun registerALL() {
        flyCommand()
        godCommand()
        seedCommand()
        teleportCommand()
    }
}