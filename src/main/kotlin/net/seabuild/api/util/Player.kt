package net.seabuild.api.util

import org.bukkit.entity.Player

class Player {

    fun Player.sendPrefixedMiniMessage(message: String) {
        this.sendMessage(miniMessage(Constants.CHAT_PREFIX + message))
    }

    fun Player.sendMiniMessage(message: String) {
        this.sendMessage(miniMessage(message))
    }

}