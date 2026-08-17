package com.streamlitwrapper

import android.content.Intent
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var webView: WebView
    private lateinit var prefs: android.content.SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        prefs = getSharedPreferences(Constants.PREF_NAME, MODE_PRIVATE)
        webView = findViewById(R.id.webview)
        val btnSetting: ImageButton = findViewById(R.id.btnSetting)

        // WebView配置，适配Streamlit
        val webSettings: WebSettings = webView.settings
        webSettings.javaScriptEnabled = true
        webSettings.domStorageEnabled = true
        webSettings.allowFileAccess = false
        webSettings.useWideViewPort = true
        webSettings.loadWithOverviewMode = true
        webSettings.setSupportZoom(true)
        webSettings.builtInZoomControls = true
        webSettings.displayZoomControls = false

        webView.webViewClient = object : WebViewClient(){}
        webView.webChromeClient = WebChromeClient()

        // 设置按钮跳转设置页
        btnSetting.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }

        loadServerUrl()
    }

    private fun loadServerUrl(){
        val url = prefs.getString(Constants.KEY_SERVER_URL, null)
        if(url.isNullOrBlank()){
            Toast.makeText(this,"请先点击右下角设置填写Streamlit地址",Toast.LENGTH_LONG).show()
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
            return
        }
        webView.loadUrl(url)
    }

    // 返回键处理
    override fun onBackPressed() {
        if(webView.canGoBack()){
            webView.goBack()
        }else{
            super.onBackPressed()
        }
    }

    override fun onResume() {
        super.onResume()
        // 从设置页回来重新加载
        loadServerUrl()
    }
}