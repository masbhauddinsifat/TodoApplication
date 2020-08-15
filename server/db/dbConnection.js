const mongoose = require('mongoose');

const connection  = mongoose.connect('mongodb+srv://masbha:masbha@todo.yuvj3.mongodb.net/TodoApplication?retryWrites=true&w=majority',
    {
        useCreateIndex: true,
        useNewUrlParser: true,
        useUnifiedTopology: true
    })
    .then(() => console.log("connected to mongodb..."))
    .catch((error) => {
        console.log("Something went wrong");
        console.log(error)
    });

    module.exports = connection;