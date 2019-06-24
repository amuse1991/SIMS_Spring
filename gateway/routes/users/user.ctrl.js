const apiCaller = require("../../utils/apiCaller");
const uri = require("../routeUri");

module.exports.createUser = (req,res)=>{
    apiCaller(uri.users.users())
        .post(JSON.parse(req.body))
        .then(data=>{
            console.log(data)
        })
        .catch(err=>{
            console.log(err)
        })
}

// 유저 리스트 조회
module.exports.getUserList = (req,res)=>{
    apiCaller(uri.users.users())
    .get()
    .then(users=>{
        res.status(200).send({
            success:true,
            users
        })
    })
    .catch(err=>{
        res.status(400).send({
            success:false,
            message:err
        })
    })
}

// 유저 정보 조회
module.exports.getUserInfo = (req,res)=>{
    let userId = req.params.userId
    apiCaller(uri.users.users(userId))
    .get({user_id:userId})
    .then(user=>{
        res.status(200).send({
            success:true,
            user
        })
    })
    .catch(err=>{
        res.status(400).send({
            success:false,
            message:err
        })
    })
}
// 유저 정보 수정
module.exports.updateUser = (req,res)=>{
    let userId = req.params.userId
    apiCaller(uri.users.users(userId))
    .post(JSON.parse(req.body))
    .then(_=>{
        res.status(200).send({
            success:true
        })
    })
    .catch(err=>{
        res.status(400).send({
            success:false,
            message:err
        })
    })
}

// 유저 정보 삭제
module.exports.deleteUser = (req,res)=>{
    let userId = req.params.userId
    apiCaller(uri.users.users(userId))
    .delete({user_id:userId})
    .then(_=>{
        res.status(200).send({
            success:true
        })
    })
    .catch(err=>{
        res.status(400).send({
            success:false,
            message:err
        })
    })
}