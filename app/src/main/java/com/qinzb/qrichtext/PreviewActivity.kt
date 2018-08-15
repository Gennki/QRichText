package com.qinzb.qrichtext

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import cn.qzb.richeditor.RE
import kotlinx.android.synthetic.main.activity_main.*

class PreviewActivity : AppCompatActivity() {

    private lateinit var re: RE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preview)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initViews()
        initData()

        if (savedInstanceState != null) {
            re.reFreshState()
        }

    }

    private fun initViews() {
        // init rich text editor
        re = RE.getInstance(editor)
        re.setPadding(20, 20, 20, 20)
        re.setTextBackgroundColor(Color.WHITE)
        re.setEditable(false)// 预览模式不可编辑
    }

    private fun initData() {
        val html = intent.getStringExtra("html")
        re.html = html
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // 处理各个按钮的点击事件
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return true
    }


}
