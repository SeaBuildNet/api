package net.seabuild.api.annon

import kotlin.reflect.KClass

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class FallbackImpl(val value: KClass<*>)