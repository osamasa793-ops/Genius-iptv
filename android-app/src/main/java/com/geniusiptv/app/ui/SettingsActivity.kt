package com.geniusiptv.app.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
from com.geniusiptv.app.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        setupSettings()
    }

    private fun setupSettings() {
        // سيتم إضافة الإعدادات هنا
        // اختبار الاتصال بـ Backend
        // خيارات المستخدم
        // معلومات الحساب
    }
}