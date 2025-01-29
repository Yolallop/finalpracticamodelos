const users = require('./models/user.model');
const games = require('./models/games.model');

const database = {};

// Referenciamos los modelos
database.users = users;
database.games = games;

// Inicializa algunos usuarios por defecto
function initializeUsers() {
    const NAMES = ["admin", "user", "bob", "ana"];
    NAMES.forEach((username) => {
        database.users.register(username, "1234");
    });
}

// Inicializa la base de datos
function initializeDB() {
    initializeUsers();
    console.log("Base de datos inicializada.");
}

// Llamamos a la función de inicialización
initializeDB();

module.exports = database;
