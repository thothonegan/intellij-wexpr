package com.hackerguild.intellijwexpr.psi

import com.hackerguild.intellijwexpr.WexprLanguage
import com.intellij.psi.tree.IElementType
import org.jetbrains.annotations.NonNls

class WexprTokenType(debugName: @NonNls String) : IElementType(debugName, WexprLanguage.Instance) {

    public override fun toString(): String {
        return "WexprTokenType." + super.toString()
    }
}