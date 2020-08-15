const mongoose = require("mongoose");

todoSchema = new mongoose.Schema({
    title: {
        type: String,
        required: true,
        minlength: 2,
        maxlength: 255,
        trim: true
    },
    isComplete: {
        type: Boolean,
        default: false
    },
    author: {
        type: mongoose.Schema.Types.ObjectId,
        ref: "users",
        required: true
    }
});

module.exports = mongoose.model('Todo', todoSchema);