package com.hackerguild.intellijwexpr

import com.hackerguild.intellijwexpr.psi.WexprTypes
import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.HighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IElementType

class WexprSyntaxHighlighter : SyntaxHighlighterBase()
{
    companion object {
        val BareValue = createTextAttributesKey("WEXPR_VALUE", DefaultLanguageHighlighterColors.STRING)
        val QuotedValue = createTextAttributesKey("WEXPR_QUOTED_VALUE", DefaultLanguageHighlighterColors.STRING)
        val ReferenceDeclaration = createTextAttributesKey("WEXPR_REFERENCE_DECLARE", DefaultLanguageHighlighterColors.LABEL)
        val ReferenceInject = createTextAttributesKey("WEXPR_REFERENCE_INJECT", DefaultLanguageHighlighterColors.LABEL)
        val BlockComment = createTextAttributesKey("WEXPR_BLOCK_COMMENT", DefaultLanguageHighlighterColors.BLOCK_COMMENT)
        val LineComment = createTextAttributesKey("WEXPR_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT)
        val BadCharacter = createTextAttributesKey("WEXPR_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER)

        val BareValueKeys = arrayOf(BareValue)
        val QuotedValueKeys = arrayOf(QuotedValue)
        val ReferenceDeclarationKeys = arrayOf(ReferenceDeclaration)
        val ReferenceInjectKeys = arrayOf(ReferenceInject)
        val BlockCommentKeys = arrayOf(BlockComment)
        val LineCommentKeys = arrayOf(LineComment)
        val BadCharacterKeys = arrayOf(BadCharacter)

    }

    // --- SyntaxHighlighterBase

    override fun getHighlightingLexer(): Lexer {
        return WexprLexerAdapter()
    }

    override fun getTokenHighlights(tokenType: IElementType?): Array<TextAttributesKey> {
        if (tokenType == WexprTypes.BARE_VALUE) {
            return BareValueKeys
        }

        if (tokenType == WexprTypes.QUOTED_VALUE) {
            return QuotedValueKeys;
        }

        if (tokenType == WexprTypes.REFERENCE_DECLARATION) {
            return ReferenceDeclarationKeys;
        }

        if (tokenType == WexprTypes.REFERENCE_INJECT) {
            return ReferenceInjectKeys;
        }

        if (tokenType == WexprTypes.BLOCK_COMMENT) {
            return BlockCommentKeys;
        }

        if (tokenType == WexprTypes.LINE_COMMENT) {
            return LineCommentKeys;
        }

        // IGNORE FOR NOW: Grammar needs to be completely token safe
        //if (tokenType == TokenType.BAD_CHARACTER) {
        //    return BadCharacterKeys;
        //}

        return arrayOf();
    }
}