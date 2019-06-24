const db = require("../db")
const Op = db.Sequelize.Op;
let userModel = require("../models/user")(db.sequelize,db.Sequelize.DataTypes);

module.exports.createUser = (req,res)=>{

    const createUser = async (reqObj)=>{
        const {user_id, password, name, dept, position, email, phone, role} = reqObj;
        await userModel.create({user_id:user_id, password:password, name:name, dept:dept, 
            position:position, email:email, phone:phone, role:role})
    }

    const respond = ()=>{
        res.json({
          message:"user created"
        })
    }

    const onError = (error)=>{
    res.status(400).json({
        message:error.message
        })
    }

    createUser(req.body)
        .then(respond)
        .catch(onError)

}

// 유저 리스트 조회
module.exports.getUserList = (req,res)=>{
    const respond = (users)=>{
        res.json({
            users
        })
    }

    const onError = (error)=>{
        res.status(404).json({
            message:error.message
            })
        }
    
    userModel.findAll()
        .then(respond)
        .catch(onError)
}

// 유저 정보 조회
module.exports.getUserInfo = (req,res)=>{
    const userId = req.params.user_id;
    const respond = (user)=>{
        res.json({
            user
        })
    }

    const onError = (error)=>{
        res.status(404).json({
            message:error.message
            })
        }
    
    userModel.findOne({where:{user_id:{[Op.eq]:userId}}})
        .then(respond)
        .catch(onError)
}

// 유저 정보 수정
module.exports.updateUser = (req,res)=>{
    const userId = req.params.user_id;
    const {user_id, password, name, dept, position, email, phone, role} = req.body;

    const respond = (user)=>{
        res.json({
            user
        })
    }

    const onError = (error)=>{
        res.status(404).json({
            message:error.message
            })
        }
    
    userModel.update({user_id:user_id, password:password, name:name, dept:dept, 
            position:position, email:email, phone:phone, role:role}
        ,{where:{user_id:{[Op.eq]:userId}}})
        .then(respond)
        .catch(onError)
}

// 유저 정보 삭제
module.exports.deleteUser = (req,res)=>{
    const userId = req.params.user_id;
    const respond = (user)=>{
        res.json({
            user
        })
    }

    const onError = (error)=>{
        res.status(404).json({
            message:error.message
            })
        }
    
    userModel.destroy({where:{user_id:{[Op.eq]:userId}}})
        .then(respond)
        .catch(onError)
}