package com.rongyi.webviewdemo.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import com.rongyi.webviewdemo.R
import kotlinx.android.synthetic.main.activity_web.*

/**
 * Demo class
 *
 * @author yikwing
 * @date 2017/10/22
 */
class WebViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)

        val stringExtra = intent.getStringExtra("url")

        val settings = webview.settings
        //开启缩放
        settings.setSupportZoom(true)
        //使用js
        settings.javaScriptEnabled = true;

        webview.webChromeClient = WebChromeClient()
        webview.webViewClient = WebViewClient()

        //加载本地网页
        webview.loadUrl(stringExtra)
    }


}