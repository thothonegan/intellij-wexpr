package com.hackerguild.intellijwexpr

import com.intellij.lang.Commenter

class WexprCommenter : Commenter {
    override fun getLineCommentPrefix(): String {
        return ";"
    }

    override fun getBlockCommentPrefix(): String {
        return ";(--"
    }

    override fun getBlockCommentSuffix(): String {
        return "--)"
    }

    override fun getCommentedBlockCommentPrefix(): String? {
        return null
    }

    override fun getCommentedBlockCommentSuffix(): String? {
        return null
    }
}