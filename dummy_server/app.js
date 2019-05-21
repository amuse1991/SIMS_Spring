const express = require('express');
const logger = require('morgan');
const receiver = require("./service/receiverService/receiverService")
const sender = require("./service/senderService/senderService")

const port = 3500;

var app = express();
app.use(logger('dev'));
var http = require('http').Server(app);
var io = require('socket.io')(http);

app.get("/",(req,res)=>{
  res.send("<p></p>");
})

let dataset = []; // 위성에서 수신한 데이터를 저장할 배열
var sendIdx = 1;
var dataSendInterval;

/*
  SIMS server 에서 connection 요청이 오면 웹 소켓 연결을 시작한다.
*/
io.on('connection',function(socket){ // ()=> 쓰면 안됨
  console.log("SIMS Server sends connection request");
  io.emit('conn_ack',socket.id);

  // 위성에서 데이터 수신중인지 확인
  socket.on('check_receive',()=>{
    console.log("Check if satellite data is being received")
    if(dataset.length > 0){
      console.log("now receiving satellite data")
      io.emit('check_ack')
    }else{
      console.log("Satellite data not being received")
      io.emit('check_nak')
    }
  })

  // 위성에서 데이터 수신중인 경우 실행
  socket.on("sync_req",()=>{
    let res = [];
    for(let data of dataset){
        res.push({
          type:data.type,
          name:data.name
        })
    }
    io.emit("sync_ack",res) // init 을 위한 데이터 타입 배열 전송
  })

  socket.on("ready",()=>{ // client 측의 데이터 수신 준비 완료 이벤트
    /*
      데이터 송신
      개발 단계에서는 읽어온 DB데이터를 초 단위로 전송
    */
    dataSendInterval = setInterval(()=>{
        let res = []
        for(let dataElem of dataset){
          try{
            res.push({
              type:dataElem.type,
              name:dataElem.name,
              data:dataElem.data[sendIdx-1]
            })
          }catch{
            sendIdx = 1;
          }
        }
        sendIdx += 1;
        console.log(res);
        io.emit("send",res);
      },1000)
  })

  // 연결 종료 요청 수신시 실행
  socket.on('disconnect',function(){
    console.log('disconnect...')
    clearInterval(dataSendInterval)
    sendIdx = 1;
    socket.disconnect(true);
  })
});

receiver.receiveData() // 데이터셋 수신
  .then(res=>{
    dataset = res;
    console.log("successfully received dataset");
    // 데이터셋 수신 후 서버 대기 시작
    http.listen(port, ()=>{
      console.log("central control server running on port "+port)
    });
})


 

