const Sequelize = require("sequelize") 
const db = require("../db")
const Model = Sequelize.Model;

class User extends Model{}
User.init({
    userCode:{
        type: Sequelize.Long,
        autoIncrement: true,
        primaryKey: true 
    },
    userId:{
        type: Sequelize.String,
        allowNull: false
    },
    password:{
        type:Sequelize.String,
        allowNull: false
    },
    dept:{
        type: Sequelize.String
    },
    position:{
        type:Sequelize.String
    },
    email:{
        type:Sequelize.String
    },
    phone:{
        type:Sequelize.String
    }
},{
    sequelize:db,
    modelName:"user"
})
