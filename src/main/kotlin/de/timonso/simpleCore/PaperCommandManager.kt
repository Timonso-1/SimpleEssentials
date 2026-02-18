package de.timonso.simpleCore

import de.timonso.simpleCore.command.*
import de.timonso.simpleCore.command.vanilla.*

object PaperCommandManager {
    fun registerALL() {
        flyCommand()
        godCommand()
        seedCommand()
    }
}