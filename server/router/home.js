const express = require('express')

const router = express.Router();

router.get('/', (req, res) => {
    res.send('welcome to the site');
});

module.exports = router;