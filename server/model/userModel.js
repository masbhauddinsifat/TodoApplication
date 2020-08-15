const mongoose = require('mongoose');
const jwt = require('jsonwebtoken');

userSchema = new mongoose.Schema({
    fullName: {
        type: String,
        required: true,
        maxlength: 255,
        trim: true
    },
    email: {
        type: String,
        required: true,
        unique: true,
        trim: true,
        maxlength: 255
    },
    password: {
        type: String,
        required: true,
        trim: true,
        maxlength: 1000
    },
    isAdmin: {
        type: Boolean,
        default: false
    },
    isActive: {
        type: Boolean,
        default: true
    }
});

userSchema.methods.generateAuthToken = function () {
    const token = jwt.sign({ _id: this._id, email: this.email, isAdmin: this.isAdmin }, 'jwtPass');
    return token;
}

module.exports = mongoose.model('User', userSchema);