package net.seabuild.api.util

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver

val miniMessage =
    MiniMessage
        .builder()
        .tags(
            TagResolver
                .builder()
                .resolver(TagResolver.standard())
                .resolver(Placeholder.styling("def", TextColor.fromHexString(Constants.COLOR_DEFAULT)!!))
                .resolver(Placeholder.styling("prim", TextColor.fromHexString(Constants.COLOR_PRIMARY)!!))
                .resolver(Placeholder.styling("sec", TextColor.fromHexString(Constants.COLOR_SECONDARY)!!))
                .resolver(Placeholder.styling("back", TextColor.fromHexString(Constants.COLOR_BACKGROUND)!!))
                .resolver(Placeholder.styling("error", TextColor.fromHexString(Constants.COLOR_ERROR)!!))
                .build(),
        ).build()

fun miniMessage(message: String): Component {
    return miniMessage.deserialize(message)
}