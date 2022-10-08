package com.hackerguild.intellijwexpr

import com.intellij.lang.Language

/**
 * Language for *.wexpr files
 */
public class WexprLanguage private constructor() : Language("Wexpr")
{
    companion object {
        /**
         * Static instance of the language
         */
        val Instance = WexprLanguage();
    }

}