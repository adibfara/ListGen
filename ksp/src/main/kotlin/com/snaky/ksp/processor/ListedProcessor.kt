package com.snaky.ksp.processor

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.google.devtools.ksp.validate
import com.snaky.ksp.annotation.Arrayed
import com.snaky.ksp.annotation.Listed
import com.snaky.ksp.annotation.Mapped
import com.snaky.ksp.processor.visitor.ListedVisitor
import com.snaky.ksp.processor.visitor.MappedVisitor
import kotlin.reflect.KClass

internal class ListedProcessor(
    private val fileGenerator: FileWriter,
) : SymbolProcessor {

    private val listVisitor = ListedVisitor()
    private val mapVisitor = MappedVisitor()
    override fun process(resolver: Resolver): List<KSAnnotated> {

        val listedFunctions: Sequence<KSFunctionDeclaration> =
            resolver.findAnnotations(Listed::class)
        val arrayedFunctions: Sequence<KSFunctionDeclaration> =
            resolver.findAnnotations(Arrayed::class)
        val mappedFunctions: Sequence<KSFunctionDeclaration> =
            resolver.findAnnotations(Mapped::class)
        if (!listedFunctions.iterator().hasNext() && !arrayedFunctions.iterator()
                .hasNext()
        ) return emptyList()

        fileGenerator.createFile(listedFunctions, Listed::class, "GeneratedLists", "listOf") {
            it.accept(listVisitor, Unit)
        }
        fileGenerator.createFile(arrayedFunctions, Arrayed::class, "GeneratedArrays", "arrayOf") {
            it.accept(listVisitor, Unit)
        }
        fileGenerator.createFile(mappedFunctions, Mapped::class, "GeneratedMaps", "mapOf") {
            it.accept(mapVisitor, Unit)
        }
        return (listedFunctions + arrayedFunctions).filterNot { it.validate() }.toList()
    }

    private fun Resolver.findAnnotations(
        kClass: KClass<*>,
    ) = getSymbolsWithAnnotation(
        kClass.qualifiedName.toString())
        .filterIsInstance<KSFunctionDeclaration>().filter {
            it.parameters.isEmpty()
        }
}

