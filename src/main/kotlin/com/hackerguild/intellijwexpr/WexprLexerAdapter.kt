package com.hackerguild.intellijwexpr

import com.intellij.lexer.FlexAdapter

class WexprLexerAdapter : FlexAdapter(WexprLexer(null))
{

}