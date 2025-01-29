const express = require('express');
const router = express.Router();
const games = require('../database/models/games.model');

/* GET para mostrar puntuaciones */
router.get('/', function (req, res) {
    res.json(games.data);
});



module.exports = router;
