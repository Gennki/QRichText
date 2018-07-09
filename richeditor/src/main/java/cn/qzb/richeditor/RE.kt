package cn.qzb.richeditor

import android.graphics.Color
import android.text.TextUtils
import android.util.Log

object RE {

    var editor: RichEditor? = null
    var fontColor = Color.BLACK
    var fontBackGroundColor = Color.WHITE
    var isBold = false
    var isItalic = false
    var isUnderline = false
    var fontSize = 3
    var isFocus = false// 是否获取到焦点

    val html: String
        get() = if (editor!!.html == null || TextUtils.isEmpty(editor!!.text) && !editor!!.html.contains("<img") && editor!!.html.startsWith("<") && !editor!!.html.contains("&nbsp")) {
            ""
        } else {
            editor!!.html
        }


    fun init(mEditor: RichEditor) {
        RE.editor = mEditor
        mEditor.setOnDecorationChangeListener { text, types ->
            Log.e("onStateChangeListener", text)
            Log.e("onStateChangeListener", RE.html)
            if (!text.contains(Integer.toHexString(RE.fontColor).substring(2), true)) {
                RE.editor?.setTextColor(RE.fontColor)
            }
            if (!text.contains(Integer.toHexString(RE.fontBackGroundColor).substring(2), true)) {
                RE.editor?.setTextBackgroundColor(RE.fontBackGroundColor)
            }
            if (!text.contains(RE.fontSize.toString() + "pt", true)) {
                RE.editor!!.setFontSize(RE.fontSize)
            }

            if (RE.isBold != types.contains(RichEditor.Type.BOLD)) {
                RE.editor?.setBold()
            }
            if (RE.isItalic != types.contains(RichEditor.Type.ITALIC)) {
                RE.editor?.setItalic()
            }
            if (RE.isUnderline != types.contains(RichEditor.Type.UNDERLINE)) {
                RE.editor?.setUnderline()
            }
        }
        mEditor.setOnTextChangeListener { _ ->
            RE.isFocus = true// 文本改动过,说明肯定获取到了焦点
        }
    }


    fun setPlaceHolder(placeHolder: String) {
        editor!!.setPlaceholder(placeHolder)
    }

    fun setBold() {
        isBold = !isBold
        editor!!.setBold()
    }

    fun setItalic() {
        isItalic = !isItalic
        editor!!.setItalic()
    }

    fun setTextColor(color: Int) {
        fontColor = color
        editor!!.setTextColor(color)
        reFreshState()
    }

    fun setTextBackgroundColor(color: Int) {
        fontBackGroundColor = color
        editor!!.setTextBackgroundColor(color)
        reFreshState()
    }

    /**
     * The size value must be between 1 and 7,
     * default value is 3
     *
     * @param fontSize
     */
    fun setTextSize(fontSize: Int) {
        if (fontSize < 0 || fontSize > 7) {
            throw IllegalStateException("The size value must be between 1 and 7")
        }
        RE.fontSize = fontSize
        editor!!.setFontSize(fontSize)
        reFreshState()
    }

    fun setPadding(left: Int, top: Int, right: Int, bottom: Int) {
        editor!!.setPadding(left, top, right, bottom)
    }

    @JvmOverloads
    fun insertImage(url: String, alt: String, imageWidthPercent: Int = 100) {
        if (!isFocus) {
            editor!!.focusEditor()
        }
        editor!!.insertImage(url, alt, imageWidthPercent)
    }

    fun setUnderLine() {
        isUnderline = !isUnderline
        editor!!.setUnderline()
    }

    fun reFreshState() {
        editor!!.refreshState()
    }
}
