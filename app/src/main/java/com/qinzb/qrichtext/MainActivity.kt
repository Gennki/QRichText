package com.qinzb.qrichtext

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.support.v4.widget.ImageViewCompat
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import cn.qzb.richeditor.RE
import cn.qzb.richeditor.RichTextActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : RichTextActivity(), View.OnClickListener {

    private val iconSelectColor = Color.BLACK
    private val iconDefaultColor = Color.parseColor("#CDCDCD")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            RE.reFreshState()
        }

        // init rich text editor
        RE.init(editor)
        RE.setPlaceHolder("Input text here...")
        RE.setPadding(20, 20, 20, 20)
        RE.getEditor().setOnDecorationChangeListener(this)
        RE.getEditor().setOnTextChangeListener(this)
        RE.setTextBackgroundColor(Color.WHITE)

        initViews()
        initListener()
    }


    private fun initViews() {
        changeIconColor(action_bold, iconDefaultColor)
        changeIconColor(action_italic, iconDefaultColor)
        changeIconColor(action_underline, iconDefaultColor)
        changeIconColor(action_font_size, Color.BLACK)
        changeIconColor(action_font_color, Color.BLACK)
        changeIconColor(action_font_background_color, Color.WHITE)
    }

    private fun initListener() {
        action_bold.setOnClickListener(this)
        action_italic.setOnClickListener(this)
        action_font_color.setOnClickListener(this)
        action_font_size.setOnClickListener(this)
        action_img.setOnClickListener(this)
        action_underline.setOnClickListener(this)
        action_font_background_color.setOnClickListener(this)
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.action_bold -> {// 加粗
                RE.setBold()
                if (RE.isBold) changeIconColor(action_bold, iconSelectColor) else changeIconColor(action_bold, iconDefaultColor)
            }
            R.id.action_italic -> {// 斜体
                RE.setItalic()
                if (RE.isItalic) changeIconColor(action_italic, iconSelectColor) else changeIconColor(action_italic, iconDefaultColor)
            }
            R.id.action_underline -> {// 下划线
                RE.setUnderLine()
                if (RE.isUnderline) changeIconColor(action_underline, iconSelectColor) else changeIconColor(action_underline, iconDefaultColor)
            }
            R.id.action_font_size -> {// 字号
                val fontSize = Random().nextInt(7) + 1
                RE.setTextSize(fontSize)
                Toast.makeText(this, "Font size is " + fontSize + "pt", Toast.LENGTH_SHORT).show()
            }
            R.id.action_img -> RE.insertImage("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3441287165,971217759&fm=200&gp=0.jpg", "请检查网络")// 插入图片
            R.id.action_font_color -> {// 字体颜色
                val color = randomColor()
                RE.setTextColor(color)
                changeIconColor(action_font_color, color)
            }
            R.id.action_font_background_color -> {// 字体背景色
                val color = randomColor()
                RE.setTextBackgroundColor(color)
                changeIconColor(action_font_background_color, color)
            }
        }
    }


    // 随机颜色
    private fun randomColor(): Int {
        val result = StringBuffer()
        result.append("#")
        for (i in 0 until 6) {
            result.append(Integer.toHexString(Random().nextInt(16)))
        }
        return Color.parseColor(result.toString())
    }

    // 改变底部图标颜色
    private fun changeIconColor(imageView: ImageView, color: Int) {
        ImageViewCompat.setImageTintList(imageView, ColorStateList.valueOf(color))
    }


}
