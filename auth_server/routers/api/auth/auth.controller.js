const jwt = require('jsonwebtoken');
const db = require("../../../db");
const Op = db.Sequelize.Op;


exports.login = (req,res)=>{
  const {userId, password} = req.body;
  const secret = req.app.get('jwt-secret');

  const getUser = (userId) => {
    return new Promise((resolve,rejcet)=>{
      let userModel = require("../../../models/user")(db.sequelize,db.Sequelize.DataTypes)
      userModel.findAll({
        attributes:["user_id","password","role"],
        where:{user_id:{[Op.eq]:userId}}
      })
      .then(result=>{
        if(result.length == 0){ // 사용자가 존재하지 않는 경우
          rejcet("user does not exist")
        }
        let user = result[0].dataValues;
        resolve(user)
      })
    })
  }
  
  const getRoleNameByRoleId = (user) =>{
    return new Promise((resolve,reject)=>{
      let roleModel = require("../../../models/role")(db.sequelize,db.Sequelize.DataTypes);
      var role_id = user.role
      roleModel.findOne({
        attributes:["role_name"],
        where:{role_id:{[Op.eq]:role_id}}
      })
      .then(result=>{
        user.role = result;
        resolve(user);
      })
    })
  }

  const checkUser = (user)=>{
    // 비밀번호 확인
    if(user.password !== password){
      throw new error("login failed")
    }else{
      return new Promise((resolve,reject)=>{
        jwt.sign(
          { // jwt payload
            _id:user.user_id,
            role:user.role
          },
          secret, // jwt secret
          { // jwt options
            expiresIn: '1d',
            issuer: 'sims_auth',
            subject: 'userInfo'
          },
          // callback
          (err,token)=>{
            console.log("token")
            if(err) reject(err)
            resolve(token)
          });
      })
    }
  }
  
  // 로그인 성공 응답 반환
  const respond = (token)=>{
    console.log(token)
    res.json({
      message:"successfully logged in",
      token // 토큰 반환
    })
  }
  
  // 에러 발생시
  const onError = (error)=>{
    res.status(403).json({ // forbidden
      message:error.message
    })
  }
  
    // 로그인 처리
  getUser(userId)
    .then(getRoleNameByRoleId)
    .then(checkUser)
    .then(respond)// resolve
    .catch(onError) // reject
}

exports.checkAuth = (req,res)=>{
  // 인증 실패의 경우 authMiddleware에서 처리
  res.json({
    success: true,
    info: req.decoded
  })
}

