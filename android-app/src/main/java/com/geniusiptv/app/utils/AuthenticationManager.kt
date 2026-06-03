package com.geniusiptv.app.utils

import android.content.Context
import com.geniusiptv.app.data.AdminCredentials

class AuthenticationManager(private val context: Context) {

    private val adminCredentials = AdminCredentials(
        username = "Osama1989",
        password = "Miramaya2026"
    )

    // التحقق من بيانات الإدمن
    fun validateAdminLogin(username: String, password: String): Boolean {
        return username == adminCredentials.username && password == adminCredentials.password
    }

    // التحقق من كود التفعيل (محاكاة)
    fun validateActivationCode(code: String): Boolean {
        // في التطبيق الفعلي، سيتم التحقق من Server
        return code.length >= 6 && code.matches(Regex("[A-Z0-9]{6,}?"))
    }

    // التحقق من بيانات اسم المستخدم وكلمة المرور العادية
    fun validateUserCredentials(username: String, password: String): Boolean {
        // في التطبيق الفعلي، سيتم التحقق من Server
        return username.isNotEmpty() && password.isNotEmpty() && password.length >= 6
    }

    // إنشاء رمز الوصول (Token)
    fun generateAccessToken(): String {
        return "TOKEN_" + System.currentTimeMillis() + "_" + (Math.random() * 10000).toInt()
    }
}