const express = require('express');
const router = express.Router();
const axios = require('axios');
const Channel = require('../models/Channel');
const Movie = require('../models/Movie');
const Series = require('../models/Series');

// دالة لتحليل ملفات M3U
const parseM3U = (content) => {
    const lines = content.split('\n');
    const channels = [];
    let currentChannel = null;

    for (let i = 0; i < lines.length; i++) {
        const line = lines[i].trim();

        if (line.startsWith('#EXTINF')) {
            // استخراج معلومات القناة من الـ metadata
            const nameMatch = line.match(/,([^,]*?)$/);
            const name = nameMatch ? nameMatch[1].trim() : 'Unknown';
            const logoMatch = line.match(/tvg-logo="([^"]*?)"/);
            const logo = logoMatch ? logoMatch[1] : '';
            const groupMatch = line.match(/group-title="([^"]*?)"/);
            const category = groupMatch ? groupMatch[1] : 'أخرى';

            currentChannel = {
                name,
                category,
                imageUrl: logo,
                streamUrl: '',
                description: name
            };
        } else if (line && !line.startsWith('#') && currentChannel) {
            currentChannel.streamUrl = line;
            channels.push(currentChannel);
            currentChannel = null;
        }
    }

    return channels;
};

// دالة لتحليل ملفات JSON (للأفلام والمسلسلات)
const parseJSON = (content) => {
    try {
        return JSON.parse(content);
    } catch (error) {
        throw new Error('صيغة JSON غير صحيحة');
    }
};

// استيراد القنوات من رابط M3U أو M3U8
router.post('/import-channels', async (req, res) => {
    try {
        const { url } = req.body;

        if (!url) {
            return res.status(400).json({ error: 'يجب إدخال رابط الـ M3U' });
        }

        // جلب محتوى الملف
        const response = await axios.get(url, { timeout: 30000 });
        const content = response.data;

        // تحليل الملف
        const channels = parseM3U(content);

        if (channels.length === 0) {
            return res.status(400).json({ error: 'لم يتم العثور على قنوات في الملف' });
        }

        // حفظ القنوات في قاعدة البيانات (بدون حفظ تلقائي)
        // سيتم إرجاع القنوات للمراجعة من قبل الإدمن
        res.json({
            message: `تم العثور على ${channels.length} قناة`,
            channels: channels,
            totalFound: channels.length
        });
    } catch (error) {
        res.status(500).json({
            error: 'خطأ في استيراد القنوات',
            details: error.message
        });
    }
});

// حفظ القنوات المستوردة بعد المراجعة من الإدمن
router.post('/save-channels', async (req, res) => {
    try {
        const { channels } = req.body;

        if (!channels || !Array.isArray(channels)) {
            return res.status(400).json({ error: 'البيانات غير صحيحة' });
        }

        const savedChannels = [];
        for (const channel of channels) {
            const newChannel = new Channel({
                name: channel.name,
                category: channel.category,
                imageUrl: channel.imageUrl,
                streamUrl: channel.streamUrl,
                description: channel.description
            });
            await newChannel.save();
            savedChannels.push(newChannel);
        }

        res.json({
            message: `تم حفظ ${savedChannels.length} قناة بنجاح`,
            channels: savedChannels
        });
    } catch (error) {
        res.status(500).json({ error: 'خطأ في حفظ القنوات' });
    }
});

// تحديث فئة القناة والاسم
router.put('/update-channel/:id', async (req, res) => {
    try {
        const { name, category, description } = req.body;
        
        const updatedChannel = await Channel.findByIdAndUpdate(
            req.params.id,
            {
                name: name || undefined,
                category: category || undefined,
                description: description || undefined,
                updatedAt: new Date()
            },
            { new: true }
        );

        if (!updatedChannel) {
            return res.status(404).json({ error: 'القناة غير موجودة' });
        }

        res.json({
            message: 'تم تحديث القناة بنجاح',
            channel: updatedChannel
        });
    } catch (error) {
        res.status(500).json({ error: 'خطأ في تحديث القناة' });
    }
});

// استيراد الأفلام من رابط JSON
router.post('/import-movies', async (req, res) => {
    try {
        const { url } = req.body;

        if (!url) {
            return res.status(400).json({ error: 'يجب إدخال رابط الملف' });
        }

        const response = await axios.get(url, { timeout: 30000 });
        const movies = Array.isArray(response.data) ? response.data : [response.data];

        if (movies.length === 0) {
            return res.status(400).json({ error: 'لم يتم العثور على أفلام' });
        }

        res.json({
            message: `تم العثور على ${movies.length} فيلم`,
            movies: movies,
            totalFound: movies.length
        });
    } catch (error) {
        res.status(500).json({
            error: 'خطأ في استيراد الأفلام',
            details: error.message
        });
    }
});

// حفظ الأفلام المستوردة
router.post('/save-movies', async (req, res) => {
    try {
        const { movies } = req.body;

        if (!movies || !Array.isArray(movies)) {
            return res.status(400).json({ error: 'البيانات غير صحيحة' });
        }

        const savedMovies = [];
        for (const movie of movies) {
            const newMovie = new Movie({
                name: movie.name,
                category: movie.category || 'أخرى',
                imageUrl: movie.imageUrl || movie.poster,
                streamUrl: movie.streamUrl || movie.url,
                description: movie.description || '',
                releaseDate: movie.releaseDate || '',
                duration: movie.duration || 0,
                rating: movie.rating || 0
            });
            await newMovie.save();
            savedMovies.push(newMovie);
        }

        res.json({
            message: `تم حفظ ${savedMovies.length} فيلم بنجاح`,
            movies: savedMovies
        });
    } catch (error) {
        res.status(500).json({ error: 'خطأ في حفظ الأفلام' });
    }
});

// استيراد المسلسلات من رابط JSON
router.post('/import-series', async (req, res) => {
    try {
        const { url } = req.body;

        if (!url) {
            return res.status(400).json({ error: 'يجب إدخال رابط الملف' });
        }

        const response = await axios.get(url, { timeout: 30000 });
        const seriesList = Array.isArray(response.data) ? response.data : [response.data];

        if (seriesList.length === 0) {
            return res.status(400).json({ error: 'لم يتم العثور على مسلسلات' });
        }

        res.json({
            message: `تم العثور على ${seriesList.length} مسلسل`,
            series: seriesList,
            totalFound: seriesList.length
        });
    } catch (error) {
        res.status(500).json({
            error: 'خطأ في استيراد المسلسلات',
            details: error.message
        });
    }
});

// حفظ المسلسلات المستوردة
router.post('/save-series', async (req, res) => {
    try {
        const { series } = req.body;

        if (!series || !Array.isArray(series)) {
            return res.status(400).json({ error: 'البيانات غير صحيحة' });
        }

        const savedSeries = [];
        for (const ser of series) {
            const newSeries = new Series({
                name: ser.name,
                category: ser.category || 'أخرى',
                imageUrl: ser.imageUrl || ser.poster,
                description: ser.description || '',
                seasons: ser.seasons || [],
                rating: ser.rating || 0
            });
            await newSeries.save();
            savedSeries.push(newSeries);
        }

        res.json({
            message: `تم حفظ ${savedSeries.length} مسلسل بنجاح`,
            series: savedSeries
        });
    } catch (error) {
        res.status(500).json({ error: 'خطأ في حفظ المسلسلات' });
    }
});

// تحديث بيانات الفيلم
router.put('/update-movie/:id', async (req, res) => {
    try {
        const { name, category, description, rating } = req.body;

        const updatedMovie = await Movie.findByIdAndUpdate(
            req.params.id,
            {
                name: name || undefined,
                category: category || undefined,
                description: description || undefined,
                rating: rating || undefined,
                updatedAt: new Date()
            },
            { new: true }
        );

        if (!updatedMovie) {
            return res.status(404).json({ error: 'الفيلم غير موجود' });
        }

        res.json({
            message: 'تم تحديث الفيلم بنجاح',
            movie: updatedMovie
        });
    } catch (error) {
        res.status(500).json({ error: 'خطأ في تحديث الفيلم' });
    }
});

module.exports = router;
