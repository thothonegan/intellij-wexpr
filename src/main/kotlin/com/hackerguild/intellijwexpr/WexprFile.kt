package com.hackerguild.intellijwexpr

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.lang.Language
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider

public class WexprFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, WexprLanguage.Instance)
{
    override fun getFileType(): FileType {
        return WexprFileType.Instance;
    }

    override fun toString(): String {
        return "Wexpr File";
    }
}