module.exports = (sequelize, DataTypes) =>{
    var Role = sequelize.define("role",{
        role_id:{
            type: DataTypes.BIGINT,
            autoIncrement: true,
            primaryKey: true 
        },
        role_name:{
            type: DataTypes.STRING,
            allowNull: false
        },
        description:{
            type:DataTypes.STRING
        }
    },{
        tableName:"role",
        timestamps:false
    })
    return Role
}