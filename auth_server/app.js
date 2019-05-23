/* =========================
    load the dependencies
============================*/
const express = require("express");
const bodyParser = require("body-parser");
const morgan = require("morgan"); 
// const Sequelize = require('sequelize');
const db = require("./db")

/* =========================
    load the config
============================*/
const config = require("./config");
const port = process.env.PORT || 3300;

/* =========================
    database configuration
============================*/
// const sequelize = new Sequelize(config.database.uri);

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

// api 라우터 설정
app.use("/api",require("./routers/api"))

// 서버 listen
db
    .authenticate()
    .then(()=>{
        console.log("database connection has been established successfully.");
        app.listen(port, ()=>{
            console.log(`auth server is running on port ${port}`)
        })
    })
    .catch(err=>{
        console.error("Unable to connect to the database:",err)
    })
