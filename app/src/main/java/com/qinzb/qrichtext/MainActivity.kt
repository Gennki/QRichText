package com.qinzb.qrichtext

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.support.v4.widget.ImageViewCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import cn.qzb.richeditor.RE
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val iconSelectColor = Color.BLACK
    private val iconDefaultColor = Color.parseColor("#CDCDCD")
    private lateinit var re: RE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        // init rich text editor
        re = RE.getInstance(editor)
        re.setPlaceHolder("Input text here...")
        re.setPadding(20, 20, 20, 20)
        re.setTextBackgroundColor(Color.WHITE)

        initViews()
        initListener()
        initData()

        if (savedInstanceState != null) {
            re.reFreshState()
        }
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

    private fun initData() {
        re.html = "<span style=\"background-color: rgb(255, 255, 255);\">QRICHTEXT</span><img src=\"https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3441287165,971217759&amp;fm=200&amp;gp=0.jpg\" width=\"100%\" alt=\"image\"><img src=\"https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3441287165,971217759&amp;fm=200&amp;gp=0.jpg\" width=\"100%\" alt=\"image\"><img src=\"https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3441287165,971217759&amp;fm=200&amp;gp=0.jpg\" width=\"100%\" alt=\"image\"><img src=\"https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3441287165,971217759&amp;fm=200&amp;gp=0.jpg\" width=\"100%\" alt=\"image\"><br><b style=\"\"><font size=\"6\" style=\"background-color: rgb(255, 255, 255);\">QRichText</font><br><font style=\"\"><font color=\"#9d9d14\"><span style=\"background-color: rgb(255, 255, 255);\">编辑</span></font><br><font color=\"#4fe1d8\"><span style=\"background-color: rgb(244, 68, 254);\">自动跳转到最底下</span></font></font></b>"
        re.moveToEndEdit()
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // 加载toolbar.xml这个菜单文件
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // 处理各个按钮的点击事件
        when (item.itemId) {
            R.id.preview -> {
                val intent = Intent(this, PreviewActivity::class.java)
                intent.putExtra("html", re.html)
                startActivity(intent)
            }
        }
        return true
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.action_bold -> {// 加粗
                re.setBold()
                if (re.isBold) changeIconColor(action_bold, iconSelectColor) else changeIconColor(action_bold, iconDefaultColor)
            }
            R.id.action_italic -> {// 斜体
                re.setItalic()
                if (re.isItalic) changeIconColor(action_italic, iconSelectColor) else changeIconColor(action_italic, iconDefaultColor)
            }
            R.id.action_underline -> {// 下划线
                re.setUnderLine()
                if (re.isUnderline) changeIconColor(action_underline, iconSelectColor) else changeIconColor(action_underline, iconDefaultColor)
            }
            R.id.action_font_size -> {// 字号
                val fontSize = Random().nextInt(7) + 1
                re.setTextSize(fontSize)
                Toast.makeText(this, "Font size is " + fontSize + "pt", Toast.LENGTH_SHORT).show()
            }
            R.id.action_img -> re.insertImage("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3441287165,971217759&fm=200&gp=0.jpg", "image")// 插入图片
            R.id.action_font_color -> {// 字体颜色
                val color = randomColor()
                re.setTextColor(color)
                changeIconColor(action_font_color, color)
            }
            R.id.action_font_background_color -> {// 字体背景色
                val color = randomColor()
                re.setTextBackgroundColor(color)
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
