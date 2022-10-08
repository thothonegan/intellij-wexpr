package com.hackerguild.intellijwexpr.psi

import com.hackerguild.intellijwexpr.WexprLanguage
import com.intellij.psi.tree.IElementType
import org.jetbrains.annotations.NonNls

class WexprElementType(debugName: @NonNls String) : IElementType(debugName, WexprLanguage.Instance)
{

}