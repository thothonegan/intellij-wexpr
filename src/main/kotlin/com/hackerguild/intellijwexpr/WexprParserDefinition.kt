package com.hackerguild.intellijwexpr

import com.hackerguild.intellijwexpr.psi.WexprTypes
import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.lang.PsiParser
import com.intellij.lexer.Lexer
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet

class WexprParserDefinition : ParserDefinition
{
    companion object
    {
        val File = IFileElementType(WexprLanguage.Instance);
    }

    // --- ParserDefinition

    override fun createLexer(project: Project?): Lexer {
        return WexprLexerAdapter()
    }

    override fun createParser(project: Project?): PsiParser {
        return WexprParser()
    }

    override fun getFileNodeType(): IFileElementType {
        return File
    }

    override fun getCommentTokens(): TokenSet {
        return WexprTokenSets.Comments
    }

    override fun getStringLiteralElements(): TokenSet {
        return TokenSet.EMPTY
    }

    override fun createElement(node: ASTNode?): PsiElement {
        return WexprTypes.Factory.createElement(node)
    }

    override fun createFile(viewProvider: FileViewProvider): PsiFile {
        return WexprFile(viewProvider)
    }
}
