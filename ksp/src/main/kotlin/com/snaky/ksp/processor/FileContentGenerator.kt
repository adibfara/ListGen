package com.snaky.ksp.processor

import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.snaky.ksp.processor.models.ProcessedFunction
import com.snaky.ksp.processor.models.kspPackage

internal class FileContentGenerator {
    fun generateContent(
        listSymbols: Map<String, List<KSFunctionDeclaration>>,
        functionName: String,
        visitorTransform: (KSFunctionDeclaration) -> ProcessedFunction?,
    ): String {
        return generateTexts(listSymbols, visitorTransform, functionName)
    }

    private fun generateTexts(
        listSymbols: Map<String, List<KSFunctionDeclaration>>,
        visitorTransform: (KSFunctionDeclaration) -> ProcessedFunction?,
        functionName: String,
    ): String {
        return buildString {

            append("package ${kspPackage()}")

            if (listSymbols.isEmpty()) return@buildString

            val processedFunctions = listSymbols.map { (name, functions) ->
                Pair(name, functions.map(visitorTransform).filterNotNull())

            }

            val imports =
                processedFunctions.flatMap { it.second.map { it.imports }.flatten() }
                    .importDeclarations()

            append(imports)

            val functionTexts = processedFunctions.map { (name, function) ->
                function.joinToString(prefix = "val $name = $functionName(",
                    separator = ", ",
                    postfix = ")") { it.text }
            }.joinToString("\n", prefix = "\n")

            append(functionTexts)
        }
    }

    private fun List<String>.importDeclarations(): String {
        if (isEmpty()) return ""
        return this.map {
            "import $it"
        }.joinToString("\n", prefix = "\n", postfix = "\n\n")
    }
}