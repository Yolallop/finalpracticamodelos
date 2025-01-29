const express = require('express');
const router = express.Router();

router.get('/', function (req, res, next) {
  res.render('game_snake', { user: req.session.user });
});

module.exports = router;
