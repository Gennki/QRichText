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
    var isPreFontSizeChange = false// 防止改完背景色后,再改变字体大小,背景色没有填充满的bug

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
            if (isPreFontSizeChange) {// 防止改完背景色后,再改变字体大小,背景色没有填充满的bug... js的bug
                editor!!.setTextBackgroundColor(fontBackGroundColor + 1)
                editor!!.insertText("1")
                editor!!.deleteOneWord()
                editor!!.setTextBackgroundColor(fontBackGroundColor)
                isPreFontSizeChange = false
            }
        }
    }


    fun setPlaceHolder(placeHolder: String) {
        editor!!.setPlaceholder(placeHolder)
    }

    /**
     * 加粗或取消加粗
     */
    fun setBold() {
        isBold = !isBold
        editor!!.setBold()
    }

    /**
     * 斜体或取消斜体
     */
    fun setItalic() {
        isItalic = !isItalic
        editor!!.setItalic()
    }

    /**
     * 设置字体颜色
     */
    fun setTextColor(color: Int) {
        fontColor = color
        editor!!.setTextColor(color)
        reFreshState()
    }

    /**
     * 设置文字背景色
     */
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
        isPreFontSizeChange = true
    }

    fun setPadding(left: Int, top: Int, right: Int, bottom: Int) {
        editor!!.setPadding(left, top, right, bottom)
    }

    /**
     * 插入图片
     * imageWidthPercent: 图片占屏宽度百分比
     */
    fun insertImage(url: String, alt: String, imageWidthPercent: Int = 100) {
        if (!isFocus) {
            editor!!.focusEditor()
        }
        editor!!.insertImage(url, alt, imageWidthPercent)
    }

    /**
     * 图片大小自适应
     * 原始图片有多大,插入就有多大,宽高不做限制
     */
    fun insertImageWrapWidth(url: String, alt: String) {
        if (!isFocus) {
            editor!!.focusEditor()
        }
        editor!!.insertImageWrapWidth(url, alt)
    }

    /**
     * 下划线或取消下划线
     */
    fun setUnderLine() {
        isUnderline = !isUnderline
        editor!!.setUnderline()
    }

    /**
     * 刷新编辑框状态
     */
    fun reFreshState() {
        editor!!.refreshState()
    }

    /**
     * 将div滑动到最后,可以配合focus()一起使用
     * 使用场景一般为要编辑某段富文本的时候,刚进入页面的时候,光标要显示到最后,并且编辑框的内容也要滑动到底部
     * 需要注意的是,刚进入页面的时候马上调用此方法可能会无效,因为页面还没有渲染好
     * 最好延时几百毫秒后调用
     */
    fun moveToEnd() {
        editor!!.moveToEnd()
    }

    /**
     * 获取焦点,需要注意调用此方法后,placeholder会消失
     */
    fun focus() {
        editor!!.focusEditor()
    }
}
