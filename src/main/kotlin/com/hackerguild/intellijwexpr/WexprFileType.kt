package com.hackerguild.intellijwexpr

import com.intellij.openapi.fileTypes.LanguageFileType
import javax.swing.Icon

/**
 * File type for wexpr
 */
class WexprFileType private constructor() : LanguageFileType(WexprLanguage.Instance)
{
    companion object
    {
        val Instance = WexprFileType();
    }

    // --- LanguageFileType

    override fun getName(): String {
        return "Wexpr File";
    }

    override fun getDescription(): String {
        return "Wolf expressions file";
    }

    override fun getDefaultExtension(): String {
        return "wexpr";
    }

    override fun getIcon(): Icon? {
        return WexprIcons.File;
    }
}