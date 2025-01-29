const express = require('express');
const path = require('path');
const cookieParser = require('cookie-parser');
const logger = require('morgan');
const session = require('express-session');

// Importar las rutas
const indexRouter = require('./routes/index');
const gameSnakeRouter = require('./routes/game_snake');
const gamesRouter = require('./routes/games');

const app = express();

// Configuración del motor de vistas
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'ejs');

// Middleware
app.use(logger('dev'));
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));

// Configuración de sesiones
app.use(
    session({
        secret: 'mi-secreto',
     
        saveUninitialized: true,
        cookie: { secure: false }, // Cambiar a true si usas HTTPS
    })
);

// Rutas
app.use('/', indexRouter);
app.use('/game_snake', gameSnakeRouter); // Ruta para el juego Snake
app.use('/games', gamesRouter); // Ruta para manejar las puntuaciones

// Captura de errores 404
app.use(function (req, res, next) {
    const err = new Error('Not Found');
    err.status = 404;
    next(err);
});

// Manejador de errores
app.use(function (err, req, res, next) {
    res.locals.message = err.message;
    res.locals.error = req.app.get('env') === 'development' ? err : {};
    res.status(err.status || 500);
    res.render('error');
});

module.exports = app;
