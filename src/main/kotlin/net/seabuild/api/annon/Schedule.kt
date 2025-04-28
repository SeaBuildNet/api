package net.seabuild.api.annon

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class Schedule(
    val delay: Long,
    val repeat: Long = -1,
    val period: Long = -1L
)