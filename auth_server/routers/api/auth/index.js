const router = require("express").Router();
const controller = require("./auth.controller");
const authMiddleware = require("../../../moddlewares/auth")
router.post("/login", controller.login);

// router.use("/verify",authMiddleware); // 인증 성공시에만 next실행됨
// router.get("/verify",controller.checkAuth);

module.exports = router;