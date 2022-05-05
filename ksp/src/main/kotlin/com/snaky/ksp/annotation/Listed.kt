package com.snaky.ksp.annotation


@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class Listed(
    val name: String,
)
