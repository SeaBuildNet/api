package net.seabuild.api.util

import dev.rollczi.litecommands.argument.Argument
import dev.rollczi.litecommands.argument.parser.ParseCompletedResult
import dev.rollczi.litecommands.argument.parser.ParseResult
import dev.rollczi.litecommands.argument.resolver.ArgumentResolver
import dev.rollczi.litecommands.invocation.Invocation
import dev.rollczi.litecommands.suggestion.SuggestionContext
import dev.rollczi.litecommands.suggestion.SuggestionResult
import org.bukkit.command.CommandSender
import kotlin.reflect.KClass

abstract class SimplifiedTypedArgumentResolver<T : Any>(
    type: KClass<T>,
) : TypedArgumentResolver<T>(type.java) {
    abstract fun parseArgument(argument: String): ParseResult<T>

    abstract fun suggestArguments(): Collection<String>

    override fun parse(
        invocation: Invocation<CommandSender>,
        context: Argument<T?>,
        argument: String,
    ): ParseResult<T> = parseArgument(argument)

    override fun suggest(
        invocation: Invocation<CommandSender>,
        argument: Argument<T?>,
        context: SuggestionContext,
    ): SuggestionResult = suggestArguments().stream().collect(SuggestionResult.collector())
}

abstract class TypedArgumentResolver<T : Any>(
    val type: Class<T>,
) : ArgumentResolver<CommandSender, T>() {
    fun failure(text: String): ParseCompletedResult<T> = ParseResult.failure<T>(prefixedMiniMessage(text))

    fun success(t: T): ParseCompletedResult<T> = ParseResult.success<T>(t)
}
