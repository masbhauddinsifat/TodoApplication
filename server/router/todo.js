const express = require('express');
const mongoose = require('mongoose');
const Todo = require('../model/todoModel');
const auth = require('../middlewear/auth');


const router = express.Router();

router.get("/", auth, async (req, res) => {
    try {
        let todo = await Todo.find({ author: req.user._id });
        if (!todo) {
            return res.status(404).send('No todo Found');
        }

        res.send(todo);
    } catch (error) {
        res.status(500).send('Enternal server Error')
    }
})

router.get('/:id', auth, async (req, res) => {
    try {
        const id = req.params.id;
        isValidId = mongoose.isValidObjectId(id);

        if (!isValidId) {
            return res.status(400).send('Invalid object id')
        }

        const todo = await Todo.findOne({ _id: id, author: req.user._id });
        if (!todo) {
            return res.status(400).send('No todo found with this id')
        }

        res.send(todo);

    } catch (error) {
        res.status(500).send('Internal server Error');
    }
})

router.post("/", auth, async (req, res) => {
    try {

        let todo = new Todo({
            ...req.body,
            author: req.user._id
        })

        await todo.save();

        res.status(201).send(todo);

    } catch (error) {
        res.status(500).send(error)
    }
})

router.put('/:id', auth, async (req, res) => {
    const id = req.params.id;
    const isValidId = mongoose.isValidObjectId(id);

    if (!isValidId) {
        return res.status(400).send('Invalid Object Id')
    }

    const todo = await Todo.findOneAndUpdate({ _id: id, author: req.user._id }, {
        $set: {
            ...req.body
        }
    }, { new: true });

    if (!todo) {
        return res.status(400).send("No todo Found for this id");
    }
    res.send(todo);
})

router.delete('/:id', auth, async (req, res) => {
    try {

        const id = req.params.id;
        const isValidId = mongoose.isValidObjectId(id);

        if (!isValidId) {
            return res.status(400).send("Invalid Objcet Id")
        }

        const todo = await Todo.findOneAndDelete({ _id: id, author: req.user._id });
        if (!todo) {
            return res.status(400).send("No todo found with this id");
        }
        res.send(todo);

    } catch (error) {
        res.status(500).send("Internal Server Error")
    }
})


module.exports = router;