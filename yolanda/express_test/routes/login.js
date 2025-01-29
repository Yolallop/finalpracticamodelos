const express = require('express');
const router = express.Router();
const users = require('../database/modules/user.model'); // Importa tus usuarios

// Muestra el formulario de login
router.get('/', (req, res) => {
  res.render('login', { user: req.session.user });
});

// Procesa el formulario de login
router.post('/', async (req, res) => {
  const user = req.body.user;
  const pass = req.body.pass;

  if (await users.isLoginRight(user, pass)) {
    req.session.user = { username: user }; // Guarda el usuario en la sesión
    req.session.message = '¡Login correcto!';
    res.redirect('/restricted'); // Redirige a zona restringida
  } else {
    req.session.error = 'Usuario o contraseña incorrectos.';
    res.redirect('/login');
  }
});

module.exports = router;
