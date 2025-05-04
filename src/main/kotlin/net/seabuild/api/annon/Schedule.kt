package net.seabuild.api.annon

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class Schedule(
    val delay: Long,
    val period: Long = -1L,
    val async: Boolean = false
)