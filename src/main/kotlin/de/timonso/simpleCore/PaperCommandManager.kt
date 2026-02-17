package de.timonso.simpleCore

import de.timonso.simpleCore.command.flyCommand
import de.timonso.simpleCore.command.godCommand

object PaperCommandManager {
    fun registerALL() {
        flyCommand()
        godCommand()
    }
}