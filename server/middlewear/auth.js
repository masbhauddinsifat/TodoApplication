const jwt = require('jsonwebtoken');

let auth = (req, res, next) => {
    const token = req.header('x-auth-token');
    if (!token) {
        return res.status(401).send('Access denied. No auth token provided...')
    }

    try {
        const payload = jwt.verify(token, 'jwtPass');
        req.user = payload;
        next();

    } catch (error) {
        res.status(400).send('Invalid auth token.')
    }
}

module.exports = auth;