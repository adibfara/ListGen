package com.snaky.ksp.processor

import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.snaky.ksp.processor.models.ProcessedFunction
import com.snaky.ksp.processor.models.kspPackage
import org.junit.Assert.assertEquals
import org.junit.Test

class FileContentGeneratorTest {
    private fun createGenerator() = FileContentGenerator()
    private val visitorTransform: (KSFunctionDeclaration) -> ProcessedFunction? = {
        (it as KSFunctionStub).processedFunction
    }

    @Test
    fun `empty list generates nothing`() {
        val generator = createGenerator()
        val content = generator.generateContent(mapOf(), "function") {
            ProcessedFunction(listOf("import"),
                "text")
        }
        assertEquals("package ${kspPackage()}", content)
    }

    @Test
    fun `list of one element is generated correctly`() {
        val generator = createGenerator()
        val element1 = KSFunctionStub(ProcessedFunction(
            listOf("1"),
            "first string"
        ))
        val element2 = KSFunctionStub(ProcessedFunction(
            listOf("2"),
            "second string"
        ))

        val content = generator.generateContent(mapOf(
            "test2" to listOf(
                element1, element2
            )
        ), "mapOf", visitorTransform)
        assertEquals("""package ${kspPackage()}
            |import 1
            |import 2
            |
            |
            |val test2 = mapOf(first string, second string)
        """.trimMargin(), content)
    }
}