const jwt = require('jsonwebtoken');
const db = require("../../../db");


exports.login = (req,res)=>{
  const {userId, password} = req.body;
  const secret = req.app.get('jwt-secret');

  const checkUser = (userId, password)=>{
    let userModel = require("../../../models/user")(db.sequelize,db.Sequelize.DataTypes)
    userModel.findAll({
      attributes:["userId","password"],
      where:{userId:userId}
    })
    .bind(res)
    .then(result=>{
      if(result.count == 0){ // 사용자가 존재하지 않는 경우
        throw new error("user does not exist")
      }
      // 사용자가 존재하는 경우 비밀번호 체크
      let user = result.rows[0];
      if(user.dataValues.password !== password){ // 비밀번호가 일치하지 않는 경우
        throw new error("login failed")
      }else{ // 로그인 성공
        return new Promise((resolve,reject)=>{ // jwt 토큰 발행 및 promise 반환
          jwt.sign(
            { // jwt payload
              _id:user.dataValues.userId
            },
            secret, // jwt secret
            { // jwt options
              expiresIn: '1d',
              issuer: 'sims_auth',
              subject: 'userInfo'
            },
            // callback
            (err,token)=>{
              err === true? reject(err):resolve(token);
            }
          );
        });
      }
    })
  }
  
  // 로그인 성공 응답 반환
  const respond = (token)=>{
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
  checkUser(userId,password)
    .then(respond) // resolve
    .catch(onError) // reject
}



