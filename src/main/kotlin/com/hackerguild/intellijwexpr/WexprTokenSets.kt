package com.hackerguild.intellijwexpr

import com.hackerguild.intellijwexpr.psi.WexprTypes
import com.intellij.psi.tree.TokenSet

interface WexprTokenSets
{
    companion object {
       // val Identifiers = TokenSet.create(WexprTypes.IDENTIFIER)

        val Comments = TokenSet.create(
            WexprTypes.LINE_COMMENT,
            WexprTypes.BLOCK_COMMENT
        )
    }
}
