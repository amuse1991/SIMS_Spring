const apiCaller = require("../../utils/apiCaller");
const uri = require("../routeUri");
module.exports.createUser = (req,res)=>{
    apiCaller(uri.users.createUser,JSON.parse(req.body))
        .post()
        .then(data=>{
            console.log(data)
        })
        .catch(err=>{
            console.log(err)
        })
}

// 유저 리스트 조회
module.exports.getUserList = (req,res)=>{
    console.log("get user list")
}
// 유저 정보 조회
module.exports.getUserInfo = (req,res)=>{
    console.log("get user info")
}
// 유저 정보 수정
module.exports.updateUser = (req,res)=>{
    console.log("update user")
}

// 유저 정보 삭제
module.exports.deleteUser = (req,res)=>{
    console.log("delete user")
}