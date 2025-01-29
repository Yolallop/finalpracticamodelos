

const bcrypt = require('bcrypt');

const users = {};

// Aquí se almacenan los datos de los usuarios
users.data = {};

// Genera un hash para la contraseña
users.generateHash = function (password, callback) {
    bcrypt.hash(password, 10, callback);
};

// Compara una contraseña con un hash
users.comparePass = async function (password, hash) {
    return await bcrypt.compare(password, hash);
};

// Registra un usuario nuevo
users.register = function (username, password) {
    if (users.data.hasOwnProperty(username)) {
        throw new Error(`El usuario ${username} ya existe.`);
    }

    users.generateHash(password, function (err, hash) {
        if (err) {
            throw new Error(`Error al generar el hash para ${username}.`);
        }

        users.data[username] = {
            username,
            hash,
            lastLogin: new Date().toISOString(),
            games: {} // Relación con los juegos
        };
    });
};

// Verifica si las credenciales de inicio de sesión son correctas
users.isLoginRight = async function (username, password) {
    if (!users.data.hasOwnProperty(username)) {
        return false;
    }
    return await users.comparePass(password, users.data[username].hash);
};

module.exports = users;
