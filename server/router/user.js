const express = require('express');
const lodash = require('lodash');
const validator = require('validator');
const bcrypt = require('bcrypt');
// const jwt = require('jsonwebtoken');
const userModel = require('../model/userModel');

const router = express.Router();

router.post('/', async (req, res) => {
    try {
        let body = lodash.pick(req.body, ['fullName', 'email', 'password', 'isAdmin', 'isActive'])

        isValidEmail = validator.isEmail(body.email);
        if (!isValidEmail) {
            return res.status(400).send("Invalid Email Address")
        }

        const email = await userModel.findOne({ email: body.email });
        if (email) {
            return res.status(400).send("Already signed up with this mail. try new one ....")
        }

        const salt = await bcrypt.genSalt(10);
        body.password = await bcrypt.hash(body.password, salt);

        const user = new userModel(body);

        await user.save();
        const token = user.generateAuthToken();

        res.header('x-auth-token', token).status(201)
            .send(lodash.pick(user, ['fullName', 'email', 'isAdmin', 'isActive']));

    } catch (error) {
        res.status(500).send("Internal Server Error");
    }
});

router.post('/login', async (req, res) => {

    isValidEmail = validator.isEmail(req.body.email);
    console.log(isValidEmail)
    if (!isValidEmail) {
        return res.status(400).send('Invalid Email...')
    }

    const user = await userModel.findOne({ email: req.body.email });
    if (!user) {
        return res.status(400).send("Invalid Email Address");
    }

    const isValidPassword = await bcrypt.compare(req.body.password, user.password);
    if (!isValidPassword) {
        return res.status(400).send('Invalid password. please provide correct password');
    }

    const token = user.generateAuthToken();

    res.header('x-auth-token', token)
        .send(lodash.pick(user, ['_id', 'fullName', 'email', 'isAdmin', 'isActive']));
})

module.exports = router;