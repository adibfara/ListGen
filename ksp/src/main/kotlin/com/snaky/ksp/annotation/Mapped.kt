package com.snaky.ksp.annotation

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class Mapped(
    val name: String,
    val key: String,
)
