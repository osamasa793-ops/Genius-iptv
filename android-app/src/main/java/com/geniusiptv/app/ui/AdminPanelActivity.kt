package com.geniusiptv.app.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.geniusiptv.app.databinding.ActivityAdminPanelBinding
import com.geniusiptv.app.utils.AuthenticationManager

class AdminPanelActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdminPanelBinding
    private lateinit var authManager: AuthenticationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminPanelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        authManager = AuthenticationManager(this)

        setupListeners()
    }

    private fun setupListeners() {
        // زر تسجيل دخول الإدمن
        binding.adminLoginButton.setOnClickListener {
            performAdminLogin()
        }

        // زر إضافة قناة
        binding.addChannelButton.setOnClickListener {
            showAddChannelDialog()
        }

        // زر إضافة فيلم
        binding.addMovieButton.setOnClickListener {
            showAddMovieDialog()
        }

        // زر إضافة مسلسل
        binding.addSeriesButton.setOnClickListener {
            showAddSeriesDialog()
        }
    }

    private fun performAdminLogin() {
        val username = binding.adminUsername.text.toString().trim()
        val password = binding.adminPassword.text.toString().trim()

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "أدخل اسم المستخدم وكلمة المرور", Toast.LENGTH_SHORT).show()
            return
        }

        if (authManager.validateAdminLogin(username, password)) {
            Toast.makeText(this, "تم تسجيل الدخول بنجاح", Toast.LENGTH_SHORT).show()
            // إظهار لوحة التحكم
            binding.adminLoginSection.visibility = android.view.View.GONE
            binding.adminControlPanel.visibility = android.view.View.VISIBLE
        } else {
            Toast.makeText(this, "بيانات غير صحيحة", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showAddChannelDialog() {
        Toast.makeText(this, "إضافة قناة جديدة", Toast.LENGTH_SHORT).show()
        // سيتم تطوير Dialog منفصل
    }

    private fun showAddMovieDialog() {
        Toast.makeText(this, "إضافة فيلم جديد", Toast.LENGTH_SHORT).show()
        // سيتم تطوير Dialog منفصل
    }

    private fun showAddSeriesDialog() {
        Toast.makeText(this, "إضافة مسلسل جديد", Toast.LENGTH_SHORT).show()
        // سيتم تطوير Dialog منفصل
    }
}