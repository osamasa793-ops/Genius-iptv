const express = require('express');
const router = express.Router();
const Channel = require('../models/Channel');

// الحصول على التصنيفات المتاحة
router.get('/channel-categories', (req, res) => {
    const categories = [
        'رياضة',
        'أخبار',
        'أطفال',
        'أردني',
        'سوري',
        'خليجي',
        'أجنبي',
        'أخرى'
    ];
    res.json(categories);
});

router.get('/movie-categories', (req, res) => {
    const categories = [
        'أكشن',
        'دراما',
        'كوميديا',
        'رعب',
        'خيال علمي',
        'أجنبي',
        'أخرى'
    ];
    res.json(categories);
});

router.get('/series-categories', (req, res) => {
    const categories = [
        'تركي',
        'مصري',
        'خليجي',
        'سوري',
        'أجنبي',
        'أخرى'
    ];
    res.json(categories);
});

module.exports = router;
