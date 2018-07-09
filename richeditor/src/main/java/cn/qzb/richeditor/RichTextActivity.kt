package cn.qzb.richeditor

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log

open class RichTextActivity : AppCompatActivity(), RichEditor.OnDecorationStateListener, RichEditor.OnTextChangeListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onTextChange(text: String) {
        RE.isFocus = true// 文本改动过,说明肯定获取到了焦点
    }

    override fun onStateChangeListener(text: String, types: List<RichEditor.Type>) {
        Log.e("onStateChangeListener", text)
        Log.e("onStateChangeListener", RE.getHtml())
        if (!text.contains(Integer.toHexString(RE.fontColor).substring(2), true)) {
            RE.getEditor().setTextColor(RE.fontColor)
        }
        if (!text.contains(RE.fontSize.toString() + "pt", true)) {
            RE.getEditor()!!.setFontSize(RE.fontSize)
        }

        if (RE.isBold != types.contains(RichEditor.Type.BOLD)) {
            RE.getEditor().setBold()
        }
        if (RE.isItalic != types.contains(RichEditor.Type.ITALIC)) {
            RE.getEditor().setItalic()
        }

    }


}
