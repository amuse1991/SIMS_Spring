/* =========================
    load the dependencies
============================*/
const express = require("express");
const bodyParser = require("body-parser");
const morgan = require("morgan"); //서버 로그를 출력하는 미들웨어

/* =========================
    load the config
============================*/
const config = require("./config");
const outerPort = process.env.PORT || config.outerPort;
const innerPort = config.innerPort;

/* =========================
    express configuration
============================*/
const app = express();

// url 쿼리 및 json 파싱
app.use(bodyParser.urlencoded({extended:false}))
app.use(bodyParser.json())

// request 로그(테스트 환경일 경우 로그가 찍히지 않도록 설정)
if(process.env.NODE_ENV !== 'test'){
    app.use(morgan('dev'));
}

// JWT 비밀키 변수 설정
app.set("jwt-secret",config.secret)

// 테스트용 인덱스 페이지
app.get("/",(req,res)=>{
    res.send("gateway")
})


app.use("/api/admin",require("./routes/admin")) // admin 권한 api 라우트
app.use("/api/auth",require("./routes/auth"))
app.use("/api",require("./routes")) // 일반 사용자 권한 api 라우트

// 외부port open
app.listen(outerPort, ()=>{
    console.log(`gateway is running on port ${outerPort}`)
})