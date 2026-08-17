package com.streamlitwrapper

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SettingsActivity : AppCompatActivity() {
    private lateinit var prefs: SharedPreferences
    private lateinit var etUrl: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        prefs = getSharedPreferences(Constants.PREF_NAME, MODE_PRIVATE)

        etUrl = findViewById(R.id.etUrl)
        val btnSave: Button = findViewById(R.id.btnSave)

        // 读取已保存地址
        val savedUrl = prefs.getString(Constants.KEY_SERVER_URL, "")
        etUrl.setText(savedUrl)

        btnSave.setOnClickListener {
            val url = etUrl.text.toString().trim()
            if(url.isEmpty()){
                Toast.makeText(this, R.string.msg_empty_url, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            prefs.edit()
                .putString(Constants.KEY_SERVER_URL, url)
                .apply()
            Toast.makeText(this, "已保存", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}