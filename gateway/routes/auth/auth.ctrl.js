const apiCaller = require("../../utils/apiCaller")
module.exports.login = (req,res)=>{
    let userId = req.body.userId;
    let password = req.body.password
    console.log("login called")
    apiCaller("http://localhost:3300/api/auth/login",{userId:userId,password:password})
        .post()
        .then(data=>{
            console.log(data)
        })
        .catch(err=>{
            console.log(err)
        })
}