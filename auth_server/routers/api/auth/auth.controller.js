const jwt = require('jsonwebtoken')
const userModel = require("../../../models/user")

exports.login = (req,res)=>{
    const {userId, password} = req.body;
    const secret = req.app.get('jwt-secret');
    
    // 로그인 요청 정보 확인 및 JWT 생성

}

const _checkUser = (userId, password)=>{
    userModel
    exports.login = (req, res) => {
        let id = req.body.id;
        let pwd = req.body.pwd;
        userModel.findAndCountAll({
          attributes:['Id','Pwd'],
          where:{Id:id}
          })
          .bind(res)
          .then(result=>{
            if(result.count == 0){ //id가 존재하지 않는 경우
              return res.status(404).json({error:'unknown user'});
            }
            let userData = result.rows[0];
            if(userData.dataValues.Pwd !== pwd){ //pwd가 일치하지 않는 경우
              return res.status(404).json({error:'incorrect password'});
            }
            //정상 로그인
            return res.status(200).json(userData);
          })
      };
}