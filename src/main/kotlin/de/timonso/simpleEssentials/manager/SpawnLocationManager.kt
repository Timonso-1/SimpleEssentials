package de.timonso.simpleEssentials.manager

import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.plugin.java.JavaPlugin

object SpawnLocationManager {
    private lateinit var plugin: JavaPlugin
    private const val ROOT = "spawns"
    private const val DEFAULT_YAW = 0.0f
    private const val DEFAULT_PITCH = 0.0f

    fun init(plugin: JavaPlugin) {
        this.plugin = plugin
    }

    private fun basePath(worldName: String): String = "$ROOT.$worldName"

    private fun hasSpawnCoordinates(worldName: String): Boolean {
        val config = plugin.config
        val path = basePath(worldName)
        return config.contains("$path.x") && config.contains("$path.y") && config.contains("$path.z")
    }

    private fun getRotation(path: String, key: String, defaultValue: Float): Float {
        val fullPath = "$path.$key"
        return if (plugin.config.contains(fullPath)) {
            plugin.config.getDouble(fullPath).toFloat()
        } else {
            defaultValue
        }
    }

    fun setSpawnLocation(location: Location) {
        check(::plugin.isInitialized) { "Der SpawnLocationManager wurde nicht initialisiert" }
        val world = location.world ?: return

        val config = plugin.config
        val path = basePath(world.name)
        config.set("$path.x", location.x)
        config.set("$path.y", location.y)
        config.set("$path.z", location.z)
        config.set("$path.yaw", location.yaw)
        config.set("$path.pitch", location.pitch)
        plugin.saveConfig()
    }

    fun getSpawnLocation(worldName: String): Location? {
        if (!::plugin.isInitialized) return null

        val config = plugin.config
        val path = basePath(worldName)
        val world = Bukkit.getWorld(worldName) ?: return null
        if (!hasSpawnCoordinates(worldName)) return null

        val yaw = getRotation(path, "yaw", DEFAULT_YAW)
        val pitch = getRotation(path, "pitch", DEFAULT_PITCH)

        return Location(
            world,
            config.getDouble("$path.x"),
            config.getDouble("$path.y"),
            config.getDouble("$path.z"),
            yaw,
            pitch
        )
    }

    fun hasSpawnLocation(worldName: String): Boolean {
        if (!::plugin.isInitialized) return false
        return hasSpawnCoordinates(worldName)
    }
}

