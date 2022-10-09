package com.hackerguild.intellijwexpr

import com.hackerguild.intellijwexpr.psi.WexprArray
import com.hackerguild.intellijwexpr.psi.WexprMap
import com.hackerguild.intellijwexpr.psi.WexprMapKeyValue
import com.hackerguild.intellijwexpr.psi.WexprValue
import com.hackerguild.intellijwexpr.psi.impl.WexprArrayImplementation
import com.hackerguild.intellijwexpr.psi.impl.WexprMapImplementation
import com.hackerguild.intellijwexpr.psi.impl.WexprMapKeyValueImplementation
import com.hackerguild.intellijwexpr.psi.impl.WexprValueImplementation
import com.intellij.ide.projectView.PresentationData
import com.intellij.ide.structureView.StructureViewTreeElement
import com.intellij.ide.util.treeView.smartTree.TreeElement
import com.intellij.navigation.ItemPresentation
import com.intellij.psi.NavigatablePsiElement
import com.intellij.psi.util.PsiTreeUtil

class WexprStructureViewElement constructor(private val element: NavigatablePsiElement): StructureViewTreeElement {

    override fun getPresentation(): ItemPresentation {
        val presentation = element.presentation
        return presentation
            // hack for now, show the class and text
            ?: PresentationData(element::class.simpleName?.replace("Implementation", "")
                ?.replace("Wexpr", ""),
                element.text, WexprIcons.File, null
            )
    }

    override fun getChildren(): Array<TreeElement> {
        if (element is WexprFile || element is WexprArray || element is WexprMap || element is WexprMapKeyValue)
        {
            val properties = PsiTreeUtil.getChildrenOfAnyType(element,
                WexprValue::class.java, WexprArray::class.java, WexprMap::class.java, WexprMapKeyValue::class.java
            )
            val treeElements: ArrayList<TreeElement> = ArrayList(properties.size)
            for (property in properties) {
                if (property is WexprValue) {
                    treeElements.add(WexprStructureViewElement(property as WexprValueImplementation))
                }
                if (property is WexprArray) {
                    treeElements.add(WexprStructureViewElement(property as WexprArrayImplementation))
                }
                if (property is WexprMap) {
                    treeElements.add(WexprStructureViewElement(property as WexprMapImplementation))
                }
                if (property is WexprMapKeyValue) {
                    treeElements.add(WexprStructureViewElement(property as WexprMapKeyValueImplementation))
                }
            }
            return treeElements.toArray(arrayOfNulls<TreeElement>(0))
        }

        /*
        if (element is WexprExpressionImplementation)
        {
            val properties = PsiTreeUtil.getChildrenOfTypeAsList(element, WexprExpression::class.java)
            val treeElements: ArrayList<TreeElement> = ArrayList(properties.size)
            for (property in properties) {
                treeElements.add(WexprStructureViewElement(property as WexprExpressionImplementation))
            }
            return treeElements.toArray(arrayOfNulls<TreeElement>(0))
        }
*/

        return arrayOf()
    }

    override fun navigate(requestFocus: Boolean) {
        element.navigate(requestFocus)
    }

    override fun canNavigate(): Boolean {
        return element.canNavigate()
    }

    override fun canNavigateToSource(): Boolean {
        return element.canNavigateToSource()
    }

    override fun getValue(): Any {
        return element
    }
}