package net.seabuild.api

import com.google.common.base.Preconditions
import org.bukkit.plugin.Plugin

object Api {
    private var allowed = true

    private val plugins = mutableListOf<Plugin>()

    fun hook(plugin: Plugin) {
        Preconditions.checkState(allowed, "Can't hook after onLoad()")
        plugins.add(plugin)
    }

    fun disallowHooking() {
        allowed = false
    }

    fun plugins(): List<Plugin> {
        return plugins
    }
}