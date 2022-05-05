package com.snaky.ksp.processor.visitor

import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.google.devtools.ksp.symbol.KSNode
import com.google.devtools.ksp.visitor.KSEmptyVisitor
import com.snaky.ksp.annotation.Mapped
import com.snaky.ksp.processor.models.ProcessedFunction
import com.snaky.ksp.processor.utils.imports

internal class MappedVisitor() :
    KSEmptyVisitor<Unit, ProcessedFunction?>() {

    override fun defaultHandler(node: KSNode, data: Unit): ProcessedFunction? {
        return null
    }

    override fun visitFunctionDeclaration(
        function: KSFunctionDeclaration,
        data: Unit,
    ): ProcessedFunction? {
        return ProcessedFunction(
            function.imports(),
            function.mapDeclaration()
        )
    }

    private fun KSFunctionDeclaration.mapDeclaration(): String {
        val key = annotationKeyParameter()
        val name = simpleName.asString()
        return "\"$key\" to $name()"
    }

    private fun KSFunctionDeclaration.annotationKeyParameter(): String {
        val annotation =
            annotations.first { it.shortName.asString() == Mapped::class.simpleName.toString() }
        val name = annotation.arguments.first { it.name?.asString() == "key" }
        return name.value as String
    }
}