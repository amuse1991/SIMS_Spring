/* =========================
    load the dependencies
============================*/
const express = require("express");
const bodyParser = require("body-parser");
const morgan = require("morgan"); 
const db = require("./db")

/* =========================
    load the config
============================*/
const config = require("./config");
const port = process.env.PORT || 3400;

/* =========================
    express configuration
============================*/
const app = express();

app.use(bodyParser.urlencoded({extended:false}))
app.use(bodyParser.json())

if(process.env.NODE_ENV !== 'test'){
    app.use(morgan('dev'));
}

// api 라우터 설정
app.use("/users",require("./api"))

// 서버 listen
db.sequelize
    .authenticate()
    .then(()=>{
        console.log("database connection has been established successfully.");
        app.listen(port, ()=>{
            console.log(`user server is running on port ${port}`)
        })
    })
    .catch(err=>{
        console.error("Unable to connect to the database:",err)
    })