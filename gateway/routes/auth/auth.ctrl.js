const apiCaller = require("../../utils/apiCaller");
const uri = require("../routeUri");

module.exports.login = (req,res)=>{
    let userId = req.body.user_id;
    let password = req.body.password
    apiCaller(uri.auth.login())
        .post({userId:userId,password:password})
        .then(data=>{
            res.status(200).json(data)
        })
        .catch(err=>{
            res.status(403).json(err)
        })
}