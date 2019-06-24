const request = require('request');

module.exports = function(url){
    return{
        // http 메소드별로 호출
        post : (body) => new Promise((resolve,reject)=>{
            let options = {
                headers: {'Content-Type': 'application/json'},
                url: url,
                body: JSON.stringify(body)
            };
            request.post(options,(err,res,result)=>{
                if(res === undefined){
                    throw new error("server not responding")
                }
                switch(res.statusCode){
                    case 200:
                        resolve(JSON.parse(result));
                    default:
                        reject(JSON.parse(err));
                }
            })
        }),
        get: (query)=> new Promise((resolve,reject)=>{
            let options = {
                uri: url,
                qs:query // {queryParam:value}
            };
            request.get(options,(err,res,result)=>{
                if(res === undefined){
                    throw new Error("server not responding")
                }
                switch(res.statusCode){
                    case 200:
                        resolve(JSON.parse(result));
                    default:
                        reject(JSON.parse(err));
                }
            })
        }),
        put : (body) => new Promise((resolve,reject)=>{
            let options = {
                headers: {'Content-Type': 'application/json'},
                url: url,
                body: JSON.stringify(body)
            };
            request.put(options,(err,res,result)=>{
                if(res === undefined){
                    throw new error("server not responding")
                }
                switch(res.statusCode){
                    case 200:
                        resolve(JSON.parse(result));
                    default:
                        reject(JSON.parse(err));
                }
            })
        }),

        delete: (query)=> new Promise((resolve,reject)=>{
            let options = {
                uri: url,
                qs:query // {queryParam:value}
            };
            request.delete(options,(err,res,result)=>{
                if(res === undefined){
                    throw new Error("server not responding")
                }
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