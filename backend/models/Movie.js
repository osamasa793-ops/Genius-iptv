const mongoose = require('mongoose');

const movieSchema = new mongoose.Schema({
    name: {
        type: String,
        required: true
    },
    category: {
        type: String,
        enum: ['أكشن', 'درما', 'كوميديا', 'رعب', 'خيال علمي'],
        required: true
    },
    imageUrl: String,
    streamUrl: {
        type: String,
        required: true
    },
    description: String,
    releaseDate: String,
    duration: Number, // بالدقائق
    rating: {
        type: Number,
        min: 0,
        max: 10
    },
    isActive: {
        type: Boolean,
        default: true
    },
    createdAt: {
        type: Date,
        default: Date.now
    },
    updatedAt: {
        type: Date,
        default: Date.now
    }
});

module.exports = mongoose.model('Movie', movieSchema);
