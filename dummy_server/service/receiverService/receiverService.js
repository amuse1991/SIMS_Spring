const dao = require("../../dao/mongoDao");


/*
  데이터 수신
  (개발 단계에서는 db에 미리 저장된 데이터를 읽어옴)
*/
module.exports.receiveData = ()=>{
    return new Promise((resolve,reject)=>{
        let receivedData = [];
        dao.getData("fcs") // fcs 데이터
        .then(res=>{
            receivedData.push({
            type:"tm",
            name:"fcs",
            data:res
            })
            dao.getData("wod") // wod 데이터
            .then(res=>{
                receivedData.push({
                type:"tm",
                name:"wod",
                data:res
                })
                dao.getData("tc") // tc데이터
                .then(res=>{
                    receivedData.push({
                    type:"tc",
                    name:"tc",
                    data:res
                    })
                    
                    resolve(receivedData) // 데이터 수신 완료
                })
            })
        })
    })
}