const router = require('express').Router()
const userCtrl = require("./users.ctrl")

// 유저 생성
router.post("/",userCtrl.createUser);
// 유저 리스트 조회
router.get("/",userCtrl.getUserList);
// 유저 정보 조회
router.get("/:user_id",userCtrl.getUserInfo);
// 유저 정보 수정
router.put("/:user_id",userCtrl.updateUser);
// 유저 정보 삭제
router.delete("/:user_id",userCtrl.deleteUser);


module.exports = router