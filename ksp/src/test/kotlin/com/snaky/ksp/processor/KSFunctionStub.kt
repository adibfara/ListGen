package com.snaky.ksp.processor

import com.google.devtools.ksp.symbol.FunctionKind
import com.google.devtools.ksp.symbol.KSAnnotation
import com.google.devtools.ksp.symbol.KSDeclaration
import com.google.devtools.ksp.symbol.KSFile
import com.google.devtools.ksp.symbol.KSFunction
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.google.devtools.ksp.symbol.KSName
import com.google.devtools.ksp.symbol.KSNode
import com.google.devtools.ksp.symbol.KSType
import com.google.devtools.ksp.symbol.KSTypeParameter
import com.google.devtools.ksp.symbol.KSTypeReference
import com.google.devtools.ksp.symbol.KSValueParameter
import com.google.devtools.ksp.symbol.KSVisitor
import com.google.devtools.ksp.symbol.Location
import com.google.devtools.ksp.symbol.Modifier
import com.google.devtools.ksp.symbol.Origin
import com.snaky.ksp.processor.models.ProcessedFunction

internal class KSFunctionStub(val processedFunction: ProcessedFunction) : KSFunctionDeclaration {
    override val annotations: Sequence<KSAnnotation>
        get() = TODO("not implemented")
    override val containingFile: KSFile?
        get() = TODO("not implemented")
    override val declarations: Sequence<KSDeclaration>
        get() = TODO("not implemented")
    override val docString: String?
        get() = TODO("not implemented")
    override val extensionReceiver: KSTypeReference?
        get() = TODO("not implemented")
    override val functionKind: FunctionKind
        get() = TODO("not implemented")
    override val isAbstract: Boolean
        get() = TODO("not implemented")
    override val isActual: Boolean
        get() = TODO("not implemented")
    override val isExpect: Boolean
        get() = TODO("not implemented")
    override val location: Location
        get() = TODO("not implemented")
    override val modifiers: Set<Modifier>
        get() = TODO("not implemented")
    override val origin: Origin
        get() = TODO("not implemented")
    override val packageName: KSName
        get() = TODO("not implemented")
    override val parameters: List<KSValueParameter>
        get() = TODO("not implemented")
    override val parent: KSNode?
        get() = TODO("not implemented")
    override val parentDeclaration: KSDeclaration?
        get() = TODO("not implemented")
    override val qualifiedName: KSName?
        get() = TODO("not implemented")
    override val returnType: KSTypeReference?
        get() = TODO("not implemented")
    override val simpleName: KSName
        get() = TODO("not implemented")
    override val typeParameters: List<KSTypeParameter>
        get() = TODO("not implemented")

    override fun <D, R> accept(visitor: KSVisitor<D, R>, data: D): R {
        return visitor.visitFunctionDeclaration(this, data)
    }

    override fun asMemberOf(containing: KSType): KSFunction {
        TODO()
    }

    override fun findActuals(): Sequence<KSDeclaration> {
        TODO()
    }

    override fun findExpects(): Sequence<KSDeclaration> {
        TODO()
    }

    override fun findOverridee(): KSDeclaration? {
        TODO()
    }
}