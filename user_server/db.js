const Sequelize = require('sequelize');
const config = require("./config");
var db = {};

/* =============================
    database config
================================ */

const {dbName, username, password, dbConfig, uri} = config.database; 
var sequelize = new Sequelize(dbName,username,password,dbConfig);

db.sequelize = sequelize;
db.Sequelize = Sequelize;

module.exports = db;