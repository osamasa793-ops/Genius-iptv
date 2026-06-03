package com.geniusiptv.app.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.geniusiptv.app.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private var isCodeLogin = true // true = كود التفعيل, false = username/password

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // إخفاء الـ Action Bar
        supportActionBar?.hide()

        setupUI()
        setupListeners()
    }

    private fun setupUI() {
        // إظهار حقل كود التفعيل افتراضياً
        binding.usernameInputLayout.visibility = android.view.View.GONE
        binding.passwordInputLayout.visibility = android.view.View.GONE
        binding.activationCodeInputLayout.visibility = android.view.View.VISIBLE
    }

    private fun setupListeners() {
        // التبديل بين نمط الدخول
        binding.toggleLoginMode.setOnClickListener {
            isCodeLogin = !isCodeLogin
            if (isCodeLogin) {
                binding.usernameInputLayout.visibility = android.view.View.GONE
                binding.passwordInputLayout.visibility = android.view.View.GONE
                binding.activationCodeInputLayout.visibility = android.view.View.VISIBLE
                binding.toggleLoginMode.text = "استخدام اسم المستخدم"
            } else {
                binding.usernameInputLayout.visibility = android.view.View.VISIBLE
                binding.passwordInputLayout.visibility = android.view.View.VISIBLE
                binding.activationCodeInputLayout.visibility = android.view.View.GONE
                binding.toggleLoginMode.text = "استخدام كود التفعيل"
            }
        }

        // زر الدخول
        binding.loginButton.setOnClickListener {
            performLogin()
        }
    }

    private fun performLogin() {
        if (isCodeLogin) {
            val code = binding.activationCodeInput.text.toString().trim()
            if (code.isEmpty()) {
                Toast.makeText(this, "أدخل كود التفعيل", Toast.LENGTH_SHORT).show()
                return
            }
            // التحقق من الكود (سيتم ربطه بـ Backend لاحقاً)
            loginWithCode(code)
        } else {
            val username = binding.usernameInput.text.toString().trim()
            val password = binding.passwordInput.text.toString().trim()
            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "أدخل اسم المستخدم وكلمة المرور", Toast.LENGTH_SHORT).show()
                return
            }
            // التحقق من بيانات المستخدم (سيتم ربطه بـ Backend لاحقاً)
            loginWithCredentials(username, password)
        }
    }

    private fun loginWithCode(code: String) {
        // محاكاة التحقق من الكود
        if (code.length >= 6) {
            startMainActivity()
        } else {
            Toast.makeText(this, "كود غير صحيح", Toast.LENGTH_SHORT).show()
        }
    }

    private fun loginWithCredentials(username: String, password: String) {
        // محاكاة التحقق من بيانات المستخدم
        if (username.isNotEmpty() && password.isNotEmpty()) {
            startMainActivity()
        } else {
            Toast.makeText(this, "بيانات غير صحيحة", Toast.LENGTH_SHORT).show()
        }
    }

    private fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}