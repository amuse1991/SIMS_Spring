module.exports = (sequelize, DataTypes) =>{
    var User = sequelize.define("user",{
        user_code:{
            type: DataTypes.BIGINT,
            autoIncrement: true,
            primaryKey: true 
        },
        user_id:{
            type: DataTypes.STRING,
            allowNull: false
        },
        password:{
            type:DataTypes.STRING,
            allowNull: false
        },
        dept:{
            type: DataTypes.STRING
        },
        position:{
            type:DataTypes.STRING
        },
        email:{
            type:DataTypes.STRING
        },
        phone:{
            type:DataTypes.STRING
        }
    },{
        tableName:"user",
        timestamps:false
    })
    return User
}