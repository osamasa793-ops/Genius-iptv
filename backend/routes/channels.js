const express = require('express');
const router = express.Router();
const Channel = require('../models/Channel');

// الحصول على جميع القنوات
router.get('/', async (req, res) => {
    try {
        const channels = await Channel.find({ isActive: true });
        res.json(channels);
    } catch (error) {
        res.status(500).json({ error: 'خطأ في جلب القنوات' });
    }
});

// الحصول على قناة واحدة
router.get('/:id', async (req, res) => {
    try {
        const channel = await Channel.findById(req.params.id);
        if (!channel) return res.status(404).json({ error: 'القناة غير موجودة' });
        res.json(channel);
    } catch (error) {
        res.status(500).json({ error: 'خطأ في جلب القناة' });
    }
});

// إضافة قناة جديدة (للإدمن فقط)
router.post('/', async (req, res) => {
    try {
        const newChannel = new Channel(req.body);
        await newChannel.save();
        res.status(201).json(newChannel);
    } catch (error) {
        res.status(500).json({ error: 'خطأ في إضافة القناة' });
    }
});

// تحديث قناة
router.put('/:id', async (req, res) => {
    try {
        const updatedChannel = await Channel.findByIdAndUpdate(req.params.id, req.body, { new: true });
        res.json(updatedChannel);
    } catch (error) {
        res.status(500).json({ error: 'خطأ في تحديث القناة' });
    }
});

// حذف قناة
router.delete('/:id', async (req, res) => {
    try {
        await Channel.findByIdAndDelete(req.params.id);
        res.json({ message: 'تم حذف القناة بنجاح' });
    } catch (error) {
        res.status(500).json({ error: 'خطأ في حذف القناة' });
    }
});

module.exports = router;
