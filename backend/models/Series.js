const mongoose = require('mongoose');

const seriesSchema = new mongoose.Schema({
    name: {
        type: String,
        required: true
    },
    category: {
        type: String,
        enum: ['تركي', 'مصري', 'خليجي', 'سوري', 'أجنبي'],
        required: true
    },
    imageUrl: String,
    description: String,
    seasons: [{
        seasonNumber: Number,
        episodes: [{
            episodeNumber: Number,
            title: String,
            description: String,
            streamUrl: String,
            duration: Number,
            thumbnail: String
        }]
    }],
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

module.exports = mongoose.model('Series', seriesSchema);
