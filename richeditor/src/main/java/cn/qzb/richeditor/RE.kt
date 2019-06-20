package cn.qzb.richeditor

import android.app.Activity
import android.graphics.Color
import android.text.TextUtils
import android.util.Log
import android.view.inputmethod.InputMethodManager

class RE {
    var editor: RichEditor? = null
    var fontColor = Color.BLACK
    var fontBackGroundColor = Color.WHITE
    var fontSize = 3
    var isBold = false
    var isItalic = false
    var isUnderline = false
    var isFocus = false// 是否获取到焦点
    private var preState = ""

    var html: String
        get() = if (editor!!.html == null || TextUtils.isEmpty(editor!!.text) && !editor!!.html.contains("<img") && editor!!.html.startsWith("<") && !editor!!.html.contains("&nbsp")) {
            ""
        } else {
            editor!!.html
        }
        set(value) {
            editor!!.html = value
        }

    companion object {
        fun getInstance(mEditor: RichEditor): RE {
            val re = RE()
            re.init(mEditor)
            return re
        }
    }

    private fun init(mEditor: RichEditor) {
        editor = mEditor

        mEditor.setOnDecorationChangeListener { text, types ->
            Log.e("onStateChangeListener", text)
            Log.e("onStateChangeListener", html)
            if (preState == text) {
                return@setOnDecorationChangeListener
            }
            preState = text
            if (editor!!.canUpdate) {
                editor?.setTextColor(fontColor)
                editor?.setTextBackgroundColor(fontBackGroundColor)
                editor!!.setFontSize(fontSize)

                if (isBold != types.contains(RichEditor.Type.BOLD)) {
                    editor?.setBold()
                }
                if (isItalic != types.contains(RichEditor.Type.ITALIC)) {
                    editor?.setItalic()
                }
                if (isUnderline != types.contains(RichEditor.Type.UNDERLINE)) {
                    editor?.setUnderline()
                }
            }

        }
        mEditor.setOnTextChangeListener { _ ->
            isFocus = true// 文本改动过,说明肯定获取到了焦点
        }
    }


    fun setPlaceHolder(placeHolder: String) {
        editor!!.setPlaceholder(placeHolder)
    }

    /**
     * 是否可编辑
     */
    fun setEditable(editable: Boolean) {
        editor!!.setInputEnabled(editable)
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
        this.fontSize = fontSize
        editor!!.setFontSize(fontSize)
        reFreshState()
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
     * 这里加延时,是因为如果刚进页面就刷新状态的话,有时候页面还没有渲染好,刷新的状态获取不到
     * 一般如果出现状态错乱,可以尝试这里加点延时看看效果
     */
    fun reFreshState(delay: Long = 0) {
        editor!!.postDelayed({
            editor!!.refreshState()
        }, delay)
    }


    /**
     * 获取焦点,需要注意调用此方法后,placeholder会消失
     */
    fun focus() {
        editor!!.focusEditor()
    }

    /**
     * 将编辑框滑动到最后
     */
    fun moveToEnd() {
        editor!!.moveToEnd()
    }

    /**
     * 使用场景一般为要编辑某段富文本的时候,刚进入页面的时候,光标要显示到最后,并且编辑框的内容也要滑动到底部
     * 将div滑动到最后,可以配合focus()一起使用
     * 需要注意的是,刚进入页面的时候马上调用此方法可能会无效,因为页面还没有渲染好
     * 最好延时几百毫秒后调用
     */
    fun moveToEndEdit() {
        focus()
        editor!!.postDelayed({
            val imm = editor!!.context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(editor, 0)
            moveToEnd()
            reFreshState(300)
        }, 200)
    }


    fun setLineHeight( heightInPixel:Int) {
        editor!!.setLineHeight(heightInPixel)
    }
}
