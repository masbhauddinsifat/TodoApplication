const express = require('express');
const morgan = require('morgan')
const home = require('./router/home')
const todo = require('./router/todo');
const user = require('./router/user')

require('./db/dbConnection');

const app = express();
app.use(express.json());

if (app.get('env') === 'development') {
    app.use(morgan('dev'));
}

app.use('/', home);
app.use('/api/v1/todos', todo);
app.use('/api/v1/users', user);

const port = process.env.PORT || 9000;
app.listen(port, () => {
    console.log(`server started at ${port}`);
});