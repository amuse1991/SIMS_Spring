const request = require('request')
const expect = require('chai').expect;

const routeUri = require("../routes/routeUri")

// create user test
describe(`User API test`,()=>{
    let token

    function login(id,pwd){
        request.get(routeUri.login(),{userId:id,password:pwd},(err,res,result)=>{
            console.log(res)
        })
    }

    before(()=>{login()})
})