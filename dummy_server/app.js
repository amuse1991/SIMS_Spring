const express = require('express');
const logger = require('morgan');
const receiver = require("./service/receiverService/receiverService")
const sender = require("./service/senderService/senderService")

const port = 3500;

var app = express();
app.use(logger('dev'));
var http = require('http').Server(app);
var io = require('socket.io')(http);

app.get("/",()=>{
  res.send("<p></p>");
})

let dataset = []; // 위성에서 수신한 데이터를 저장할 배열

/*
  SIMS server 에서 connection 요청이 오면 웹 소켓 연결을 시작한다.
*/
io.on('sims_server_conn-req',(socket)=>{
  sender.run();
});

receiver.receiveData() // 데이터셋 수신
  .then(res=>{
    dataset = res;
    console.log("successfully received dataset");
    // 데이터셋 수신 후 서버 대기 시작
    http.listen(port, ()=>{
      console.log("central control server running on port"+port)
    });
})


 

