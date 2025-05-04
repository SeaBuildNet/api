package net.seabuild.api

import com.google.common.base.Preconditions
import org.bukkit.plugin.Plugin

object Api {
    private var allowed = true
    private val plugins = mutableListOf<Plugin>()
    val components = mutableMapOf<Class<*>, Any>()

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

    fun <T : Any> registerComponent(t: T, clazz: Class<T>) {
        this.components[clazz] = t;
    }

    fun <T: Any> getComponent(clazz: Class<T>): T {
        return clazz.cast(this.components.get(clazz))
    }

    inline fun <reified T: Any> registerComponent(t: T) {
        this.components[T::class.java] = t;
    }

    inline fun <reified T: Any> getComponent(): T {
        return components[T::class.java] as T
    }
}