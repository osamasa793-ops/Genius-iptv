const express = require('express');
const cors = require('cors');
const dotenv = require('dotenv');
const mongoose = require('mongoose');

dotenv.config();

const app = express();

// Middleware
app.use(cors());
app.use(express.json());
app.use(express.urlencoded({ extended: true }));

// قاعدة البيانات
mongoose.connect(process.env.MONGO_URI || 'mongodb://localhost:27017/genius-iptv')
    .then(() => console.log('✅ تم الاتصال بقاعدة البيانات'))
    .catch(err => console.log('❌ خطأ في الاتصال:', err));

// الصفحة الرئيسية
app.get('/', (req, res) => {
    res.json({
        message: 'Genius IPTV API',
        version: '1.0.0',
        status: 'online'
    });
});

// فحص الاتصال
app.get('/api/health', (req, res) => {
    res.json({
        status: 'healthy',
        timestamp: new Date(),
        uptime: process.uptime()
    });
});

// مسارات الـ API
app.use('/api/channels', require('./routes/channels'));
app.use('/api/movies', require('./routes/movies'));
app.use('/api/series', require('./routes/series'));
app.use('/api/users', require('./routes/users'));
app.use('/api/admin', require('./routes/admin'));

// معالج الأخطاء
app.use((err, req, res, next) => {
    console.error(err);
    res.status(500).json({
        error: 'حدث خطأ في السيرفر',
        message: process.env.NODE_ENV === 'development' ? err.message : undefined
    });
});

const PORT = process.env.PORT || 5000;
app.listen(PORT, () => {
    console.log(`🚀 السيرفر يعمل على المنفذ ${PORT}`);
});
