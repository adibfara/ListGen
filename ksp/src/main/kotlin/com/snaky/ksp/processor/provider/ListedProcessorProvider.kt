package com.snaky.ksp.processor.provider

import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider
import com.snaky.ksp.processor.FileContentGenerator
import com.snaky.ksp.processor.FileWriter
import com.snaky.ksp.processor.ListedProcessor

class ListedProcessorProvider : SymbolProcessorProvider {
    override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor {
        val fileWriter = FileWriter(FileContentGenerator(), environment.codeGenerator)
        return ListedProcessor(fileWriter)
    }
}