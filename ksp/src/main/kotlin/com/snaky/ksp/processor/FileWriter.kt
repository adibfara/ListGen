package com.snaky.ksp.processor

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.symbol.KSFile
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.snaky.ksp.processor.models.ProcessedFunction
import com.snaky.ksp.processor.models.kspPackage
import com.snaky.ksp.processor.utils.annotationNameParameter
import com.snaky.ksp.processor.utils.plusAssign
import java.io.OutputStream
import kotlin.reflect.KClass

internal class FileWriter(
    private val fileContentGenerator: FileContentGenerator,
    private val codeGenerator: CodeGenerator,

    ) {
    fun createFile(
        listedFunctions: Sequence<KSFunctionDeclaration>,
        annotation: KClass<*>,
        fileName: String,
        functionName: String,
        visitorTransform: (KSFunctionDeclaration) -> ProcessedFunction?,
    ) {
        if (!listedFunctions.iterator().hasNext()) return
        val listSymbols = listedFunctions.groupBy { it.annotationNameParameter(annotation) }

        val content = fileContentGenerator.generateContent(
            listSymbols, functionName, visitorTransform
        )
        val files =
            listSymbols.values.flatMap { functions -> functions.mapNotNull { it.containingFile } }

        val file: OutputStream = createFile(files, fileName)
        file += content
        file.close()
    }

    private fun createFile(
        files: List<KSFile>,
        fileName: String,
    ) = codeGenerator.createNewFile(
        Dependencies(
            false,
            *files.toList().toTypedArray(),
        ),
        kspPackage(),
        fileName
    )
}