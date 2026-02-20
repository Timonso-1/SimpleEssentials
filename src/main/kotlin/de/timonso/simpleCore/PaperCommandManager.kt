package de.timonso.simpleCore

import de.timonso.simpleCore.command.flyCommand
import de.timonso.simpleCore.command.godCommand
import de.timonso.simpleCore.command.healCommand
import de.timonso.simpleCore.command.pingCommand
import de.timonso.simpleCore.command.vanilla.*

object PaperCommandManager {
    fun registerALL() {
        flyCommand()
        godCommand()
        seedCommand()
        teleportCommand()
        summonCommand()
        whitelistCommand()
        pingCommand()
        healCommand()
        opCommand()
        deopCommand()
    }
}