const mongoose = require('mongoose');

const importLogSchema = new mongoose.Schema({
    importType: {
        type: String,
        enum: ['channels', 'movies', 'series'],
        required: true
    },
    sourceUrl: String,
    itemsFound: Number,
    itemsSaved: Number,
    status: {
        type: String,
        enum: ['pending', 'completed', 'failed'],
        default: 'pending'
    },
    errorMessage: String,
    importedItems: [{
        itemId: mongoose.Schema.Types.ObjectId,
        name: String,
        category: String
    }],
    createdAt: {
        type: Date,
        default: Date.now
    },
    completedAt: Date
});

module.exports = mongoose.model('ImportLog', importLogSchema);
