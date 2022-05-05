package com.snaky.ksp.processor.visitor

import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.google.devtools.ksp.symbol.KSNode
import com.google.devtools.ksp.visitor.KSEmptyVisitor
import com.snaky.ksp.processor.models.ProcessedFunction
import com.snaky.ksp.processor.utils.declaration
import com.snaky.ksp.processor.utils.imports

internal class ListedVisitor() :
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
            function.declaration()
        )
    }
}