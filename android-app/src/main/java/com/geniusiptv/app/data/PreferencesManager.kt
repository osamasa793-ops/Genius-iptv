package com.geniusiptv.app.data

import android.content.Context
import android.content.SharedPreferences

class PreferencesManager(context: Context) {
    private val preferences: SharedPreferences =
        context.getSharedPreferences("GeniusIPTV", Context.MODE_PRIVATE)

    // حفظ بيانات المستخدم
    fun saveUserData(username: String, activationCode: String, expiryDate: Long) {
        preferences.edit().apply {
            putString("user_username", username)
            putString("user_activation_code", activationCode)
            putLong("user_expiry_date", expiryDate)
            putBoolean("is_logged_in", true)
            apply()
        }
    }

    // الحصول على بيانات المستخدم
    fun getUserData(): Pair<String?, String?> {
        val username = preferences.getString("user_username", null)
        val code = preferences.getString("user_activation_code", null)
        return Pair(username, code)
    }

    // التحقق من انتهاء الاشتراك
    fun isSubscriptionExpired(): Boolean {
        val expiryDate = preferences.getLong("user_expiry_date", 0)
        return System.currentTimeMillis() > expiryDate
    }

    // حفظ بيانات الإدمن
    fun saveAdminToken(token: String) {
        preferences.edit().apply {
            putString("admin_token", token)
            putLong("admin_login_time", System.currentTimeMillis())
            apply()
        }
    }

    // الحصول على رمز الإدمن
    fun getAdminToken(): String? {
        return preferences.getString("admin_token", null)
    }

    // التحقق من أن المستخدم إدمن
    fun isAdminLoggedIn(): Boolean {
        return getAdminToken() != null
    }

    // تسجيل الخروج
    fun logout() {
        preferences.edit().apply {
            clear()
            apply()
        }
    }
}