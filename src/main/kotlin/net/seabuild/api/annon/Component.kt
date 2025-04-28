package net.seabuild.api.annon

import kotlin.reflect.KClass

annotation class Component(val impl: KClass<Any>)