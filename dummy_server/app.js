const express = require('express');
const logger = require('morgan');
const dao = require("./dao/mongoDao");

const port = 3500;

var app = express();
app.use(logger('dev'));
var http = require('http').Server(app);
var io = require('socket.io')(http);

app.get("/",()=>{
  res.send("<p></p>");
})

let receivedData = []; // 위성에서 수신한 데이터를 저장할 배열

/*
  데이터 수신
  (개발 단계에서는 db에 미리 저장된 데이터를 읽어옴)
*/
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
              name:"fcs",
              data:res
            })
            console.log(receivedData)
          })
      })
  })
http.listen(port, ()=>{
  console.log("central control server running on "+port)
});
 

