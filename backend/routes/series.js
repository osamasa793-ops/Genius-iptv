const express = require('express');
const router = express.Router();
const Series = require('../models/Series');

// الحصول على جميع المسلسلات
router.get('/', async (req, res) => {
    try {
        const series = await Series.find({ isActive: true });
        res.json(series);
    } catch (error) {
        res.status(500).json({ error: 'خطأ في جلب المسلسلات' });
    }
});

// الحصول على مسلسل واحد
router.get('/:id', async (req, res) => {
    try {
        const series = await Series.findById(req.params.id);
        if (!series) return res.status(404).json({ error: 'المسلسل غير موجود' });
        res.json(series);
    } catch (error) {
        res.status(500).json({ error: 'خطأ في جلب المسلسل' });
    }
});

// إضافة مسلسل جديد
router.post('/', async (req, res) => {
    try {
        const newSeries = new Series(req.body);
        await newSeries.save();
        res.status(201).json(newSeries);
    } catch (error) {
        res.status(500).json({ error: 'خطأ في إضافة المسلسل' });
    }
});

// تحديث مسلسل
router.put('/:id', async (req, res) => {
    try {
        const updatedSeries = await Series.findByIdAndUpdate(req.params.id, req.body, { new: true });
        res.json(updatedSeries);
    } catch (error) {
        res.status(500).json({ error: 'خطأ في تحديث المسلسل' });
    }
});

// حذف مسلسل
router.delete('/:id', async (req, res) => {
    try {
        await Series.findByIdAndDelete(req.params.id);
        res.json({ message: 'تم حذف المسلسل بنجاح' });
    } catch (error) {
        res.status(500).json({ error: 'خطأ في حذف المسلسل' });
    }
});

module.exports = router;
