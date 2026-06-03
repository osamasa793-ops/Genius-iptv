const express = require('express');
const router = express.Router();
const Movie = require('../models/Movie');

// الحصول على جميع الأفلام
router.get('/', async (req, res) => {
    try {
        const movies = await Movie.find({ isActive: true });
        res.json(movies);
    } catch (error) {
        res.status(500).json({ error: 'خطأ في جلب الأفلام' });
    }
});

// الحصول على فيلم واحد
router.get('/:id', async (req, res) => {
    try {
        const movie = await Movie.findById(req.params.id);
        if (!movie) return res.status(404).json({ error: 'الفيلم غير موجود' });
        res.json(movie);
    } catch (error) {
        res.status(500).json({ error: 'خطأ في جلب الفيلم' });
    }
});

// إضافة فيلم جديد
router.post('/', async (req, res) => {
    try {
        const newMovie = new Movie(req.body);
        await newMovie.save();
        res.status(201).json(newMovie);
    } catch (error) {
        res.status(500).json({ error: 'خطأ في إضافة الفيلم' });
    }
});

// تحديث فيلم
router.put('/:id', async (req, res) => {
    try {
        const updatedMovie = await Movie.findByIdAndUpdate(req.params.id, req.body, { new: true });
        res.json(updatedMovie);
    } catch (error) {
        res.status(500).json({ error: 'خطأ في تحديث الفيلم' });
    }
});

// حذف فيلم
router.delete('/:id', async (req, res) => {
    try {
        await Movie.findByIdAndDelete(req.params.id);
        res.json({ message: 'تم حذف الفيلم بنجاح' });
    } catch (error) {
        res.status(500).json({ error: 'خطأ في حذف الفيلم' });
    }
});

module.exports = router;
