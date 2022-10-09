package com.hackerguild.intellijwexpr

import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.options.colors.AttributesDescriptor
import com.intellij.openapi.options.colors.ColorDescriptor
import com.intellij.openapi.options.colors.ColorSettingsPage
import javax.swing.Icon

class WexprColorSettingsPage : ColorSettingsPage {
    companion object {
        val Descriptors = arrayOf(
            AttributesDescriptor("Bare value", WexprSyntaxHighlighter.BareValue),
            AttributesDescriptor("Quoted value", WexprSyntaxHighlighter.QuotedValue),
            AttributesDescriptor("Reference declaration", WexprSyntaxHighlighter.ReferenceDeclaration),
            AttributesDescriptor("Reference injection", WexprSyntaxHighlighter.ReferenceInject),
            AttributesDescriptor("Block comment", WexprSyntaxHighlighter.BlockComment),
            AttributesDescriptor("Line comment", WexprSyntaxHighlighter.LineComment),
            AttributesDescriptor("Bad character", WexprSyntaxHighlighter.BadCharacter)
        )
    }

    override fun getAttributeDescriptors(): Array<AttributesDescriptor> {
        return Descriptors
    }

    override fun getColorDescriptors(): Array<ColorDescriptor> {
        return ColorDescriptor.EMPTY_ARRAY
    }

    override fun getDisplayName(): String {
        return "Wexpr"
    }

    override fun getIcon(): Icon {
        return WexprIcons.File
    }

    override fun getHighlighter(): SyntaxHighlighter {
        return WexprSyntaxHighlighter()
    }

    override fun getDemoText(): String {
        return """
#(
    ; line comment
    ; values
    1.0
    true
    null
    "quoted value"
    
    ; array
    #(1 2 3)
    
    ;(--
        map with injection
    --)
    @(
        red [redVal] #(255 0 0)
        green #(0 255 0)
        blue #(0 0 255)
        
        danger *[red]
    )
)
""".trimIndent()
    }

    override fun getAdditionalHighlightingTagToDescriptorMap(): MutableMap<String, TextAttributesKey>? {
        return null
    }
}