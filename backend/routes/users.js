const express = require('express');
const router = express.Router();
const User = require('../models/User');

// التحقق من كود التفعيل
router.get('/:code', async (req, res) => {
    try {
        const user = await User.findOne({ activationCode: req.params.code });
        if (!user) return res.status(404).json({ error: 'المستخدم غير موجود' });
        
        // التحقق من انتهاء الاشتراك
        const isExpired = new Date() > user.expiryDate;
        
        res.json({
            id: user._id,
            username: user.username,
            activationCode: user.activationCode,
            expiryDate: user.expiryDate,
            isActive: user.isActive && !isExpired,
            createdDate: user.createdAt
        });
    } catch (error) {
        res.status(500).json({ error: 'خطأ في التحقق من المستخدم' });
    }
});

// إضافة مستخدم جديد
router.post('/', async (req, res) => {
    try {
        const newUser = new User(req.body);
        await newUser.save();
        res.status(201).json(newUser);
    } catch (error) {
        res.status(500).json({ error: 'خطأ في إضافة المستخدم' });
    }
});

module.exports = router;
