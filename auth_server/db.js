const Sequelize = require('sequelize');
const config = require("./config");
exports.db = new Sequelize(config.database.uri);