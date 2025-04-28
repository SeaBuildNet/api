package net.seabuild.api.util

import org.bukkit.Sound
import org.bukkit.entity.Player

class Player {

    fun Player.sendPrefixedMiniMessage(message: String) {
        this.sendMessage(miniMessage(Constants.CHAT_PREFIX + message))
    }

    fun Player.sendMiniMessage(message: String) {
        this.sendMessage(miniMessage(message))
    }

    fun Player.playSound(sound: Sound) {
        this.playSound(this.location, sound, 1f, 1f)
    }

    fun Player.playSound(sound: Sound, volume: Float, pitch: Float) {
        this.playSound(this.location, sound, volume, pitch)
    }

}