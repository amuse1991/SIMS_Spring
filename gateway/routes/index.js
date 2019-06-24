const router = require("express").Router()

// 인증 미들웨어
const decodeToken = require("../middlewares/decodeToken")
const verify = require("../middlewares/verify")

const users = require("./users")
const satellite = require("./satellite")
const chart = require("./chart")

// 인증 수행
router.use(decodeToken)
router.use(verify("user"))

router.use("/users",users);
router.use("/satellites",satellite);
router.use("/charts",chart)

module.exports = router;