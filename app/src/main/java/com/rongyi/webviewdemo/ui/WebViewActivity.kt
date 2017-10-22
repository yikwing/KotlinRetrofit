package com.rongyi.webviewdemo.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import com.rongyi.webviewdemo.R
import kotlinx.android.synthetic.main.activity_web.*
import android.view.KeyEvent.KEYCODE_BACK


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

        webview.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {

                if (newProgress == 100) {
                    myProgressBar.visibility = View.INVISIBLE;
                } else {
                    if (View.INVISIBLE == myProgressBar.visibility) {
                        myProgressBar.visibility = View.VISIBLE;
                    }
                    myProgressBar.progress = newProgress;
                }
            }

        }
        webview.webViewClient = WebViewClient()

        //加载本地网页
        webview.loadUrl(stringExtra)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && webview.canGoBack()) {
            webview.goBack()// 返回前一个页面
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

}