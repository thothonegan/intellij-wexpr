package com.hackerguild.intellijwexpr;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.hackerguild.intellijwexpr.psi.WexprTypes;
import com.intellij.psi.TokenType;

%%

%class WexprLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{ return;
%eof}

NULL_LITERAL = "null"|"nil"
BARE_VALUE = [^*\"#@();\[\]\^<>\r\n \t]+
QUOTED_VALUE = \"([^\"\\]|\\([rnt\"\\]))*\"
REF_DECLARATION = \[[A-Za-z_]+[A-Za-z0-9_]*]
REF_INJECT = \*\[[A-Za-z_]+[A-Za-z0-9_]*]
WHITESPACE_STRING = [\n\r\t ]*
LINE_COMMENT = ;.*
BLOCK_COMMENT = ;\(--[\s\S]*?--\)

//%state YYINITIAL

%%

<YYINITIAL> {
    {NULL_LITERAL} { return WexprTypes.NULL_LITERAL; }
    {BARE_VALUE} { return WexprTypes.BARE_VALUE; }
    {QUOTED_VALUE} { return WexprTypes.QUOTED_VALUE; }

    {REF_DECLARATION} { return WexprTypes.REF_DECLARATION; }
    {REF_INJECT} { return WexprTypes.REF_INJECT; }
    {WHITESPACE_STRING} { return WexprTypes.WHITESPACE_STRING; }

    {LINE_COMMENT} { return WexprTypes.LINE_COMMENT; }
    {BLOCK_COMMENT} { return WexprTypes.BLOCK_COMMENT; }
}

[^] { return TokenType.BAD_CHARACTER; }
