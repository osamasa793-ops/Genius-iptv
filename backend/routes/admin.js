const express = require('express');
const router = express.Router();
const bcrypt = require('bcryptjs');
const jwt = require('jsonwebtoken');

const ADMIN_USERNAME = process.env.ADMIN_USERNAME || 'Osama1980';
const ADMIN_PASSWORD = process.env.ADMIN_PASSWORD || 'Miramaya2026';
const JWT_SECRET = process.env.JWT_SECRET || 'your_secret_key';

// تسجيل دخول الإدمن
router.post('/login', (req, res) => {
    try {
        const { username, password } = req.body;
        
        if (username !== ADMIN_USERNAME || password !== ADMIN_PASSWORD) {
            return res.status(401).json({ error: 'بيانات غير صحيحة' });
        }
        
        const token = jwt.sign(
            { username, role: 'admin' },
            JWT_SECRET,
            { expiresIn: '24h' }
        );
        
        res.json({
            message: 'تم تسجيل الدخول بنجاح',
            token,
            username
        });
    } catch (error) {
        res.status(500).json({ error: 'خطأ في تسجيل الدخول' });
    }
});

// التحقق من صحة التوكن
router.post('/verify-token', (req, res) => {
    try {
        const token = req.headers.authorization?.split(' ')[1];
        if (!token) return res.status(401).json({ error: 'لا يوجد توكن' });
        
        const decoded = jwt.verify(token, JWT_SECRET);
        res.json({ valid: true, user: decoded });
    } catch (error) {
        res.status(401).json({ error: 'توكن غير صحيح' });
    }
});

module.exports = router;
