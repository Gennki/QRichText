package com.qinzb.qrichtext

import android.os.Bundle
import android.view.View
import cn.qzb.richeditor.RE
import cn.qzb.richeditor.RichTextActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : RichTextActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        initListener()
    }


    private fun initViews() {
        RE.init(this, editor)
        RE.setPlaceHolder("Input text here...")
    }

    private fun initListener() {
        action_bold.setOnClickListener(this)
        action_italic.setOnClickListener(this)
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.action_bold -> RE.setBold()
            R.id.action_italic -> RE.setItalic()
        }
    }
}
