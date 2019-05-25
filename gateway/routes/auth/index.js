const router = require("express").Router();
const authCtrl = require("./auth.ctrl")

router.post("/",authCtrl.login);

module.exports = router;