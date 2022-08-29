package com.xinto.partialgen

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.*
import com.google.devtools.ksp.validate
import com.xinto.ksputil.*

internal class PartialProcessor(val codeGenerator: CodeGenerator) : SymbolProcessor {

    override fun process(resolver: Resolver): List<KSAnnotated> {
        val symbols = resolver.getSymbolsWithAnnotation(PARTIAL_ANNOTATION_IDENTIFIER)
        val ret = symbols.filter { !it.validate() }.toList()
        symbols
            .filter { it is KSClassDeclaration && it.validate() }
            .forEach {
                it.accept(PartialVisitor(), Unit)
            }
        return ret
    }

    inner class PartialVisitor : KSVisitorVoid() {

        override fun visitClassDeclaration(classDeclaration: KSClassDeclaration, data: Unit) {
            if (classDeclaration.classKind != ClassKind.CLASS)
                return

            val constructorParams = classDeclaration.primaryConstructor?.parameters
                ?: return

            val packageName = classDeclaration.packageName.asString()
            val classShortName = classDeclaration.qualifiedName?.getShortName() ?: "<ERROR>"
            val classQualifiedName = classDeclaration.qualifiedName?.asString() ?: "<ERROR>"

            val partialClassShortName = classDeclaration.simpleName.asString() + "Partial"

            val imports = mutableListOf(
                classQualifiedName,
                "com.xinto.partialgen.PartialValue",
                "com.xinto.partialgen.getOrElse",
            )
        }
    }
}