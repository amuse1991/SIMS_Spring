const request = require('request');

module.exports = function(url,body){
    let options = {
        headers: {'Content-Type': 'application/json'},
        url: url,
        body: JSON.stringify(body)
    };

    return{
        // http 메소드별로 호출
        post : () => new Promise((resolve,reject)=>{
            request.post(options,(err,res,result)=>{
                switch(res.statusCode){
                    case 200:
                        resolve(JSON.parse(result));
                    default:
                        reject(JSON.parse(err));
                }
            })
        })
    } 
}