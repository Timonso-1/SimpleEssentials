plugins {
    id("dev.slne.surf.surfapi.gradle.paper-plugin") version "1.21.11+"
}

group = "de.timonso"
version = findProperty("version") as String

surfPaperPluginApi {
    mainClass("de.timonso.simpleCore.PaperMain")
    generateLibraryLoader(false)

    authors.addAll("Timonso")
    description = "SimpleCore adds many useful features."
}