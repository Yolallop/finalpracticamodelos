const express = require('express');
const router = express.Router();
const users = require('../database/modules/user.model'); // Importa tus usuarios

// Muestra el formulario de login
router.get('/', (req, res) => {
  res.render('register', { user: req.session.user });
});

// Procesa el formulario de login
router.post('/', async (req, res) => {
  const user = req.body.user;
  const pass = req.body.pass;

  let confirmPass=  req.body.confirmPass
  if (pass.length >= 8 && pass === confirmPass) {
    if(!users[user]){
        user.register(user, pass, function(){
            req.session.user = users[user];
            req.session.message = "Welcome!";
            res.redirect("/restricted");
        })
    }
 } else {
        req.session.error = "El usuario ya existe";
        res.redirect("/register");
    }

});
module.exports = router;