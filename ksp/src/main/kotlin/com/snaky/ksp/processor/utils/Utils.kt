package com.snaky.ksp.processor.utils

import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import java.io.OutputStream
import kotlin.reflect.KClass

internal operator fun OutputStream.plusAssign(text: String) {
    write(text.toByteArray())
}

internal fun KSFunctionDeclaration.annotationNameParameter(annotationClass: KClass<*>): String {
    val annotation =
        annotations.first { it.shortName.asString() == annotationClass.simpleName.toString() }
    val name = annotation.arguments.first { it.name?.asString() == "name" }
    return name.value as String
}

internal fun KSFunctionDeclaration.imports(): List<String> {
    return listOf(packageName.asString() + "." + simpleName.asString())
}

internal fun KSFunctionDeclaration.declaration(): String {
    return simpleName.asString() + "()"
}

internal fun KSFunctionDeclaration.isValid(): Boolean {
    return this.parameters.isEmpty()
}