package com.snaky.ksp.processor.models

internal data class ProcessedFunction(
    val imports: List<String>,
    val text: String,
)

internal fun kspPackage() = "com.snaky.ksp"
