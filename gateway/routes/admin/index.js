const router = require("express").Router();
const satellite = require("./satellite")

// 인증 미들웨어
const decodeToken = require("../../middlewares/decodeToken")
// const decodeToken = require("../middlewares/decodeToken")
const verify = require("../../middlewares/verify")

// 인증 수행
router.use(decodeToken)
router.use(verify("admin"))

router.use("/satellites",satellite);

module.exports = router;