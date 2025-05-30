package net.seabuild.api.annon

import de.flammenfuchs.injections.annon.AlternativeTypeDef
import kotlin.reflect.KClass

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
@AlternativeTypeDef
annotation class Component(val value: KClass<*>)