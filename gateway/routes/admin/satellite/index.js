const router = require("express").Router();
const satelliteCtrl = require("./satellite.ctrl")

/*======================
    admin 권한 API
======================*/
// 위성 등록
router.post("/",satelliteCtrl.createSat);
// 위성 정보 수정
router.put("/:satellite_code",satelliteCtrl.updateSat);
// 위성 삭제
router.delete("/:satellite_code",satelliteCtrl.deleteSat);


module.exports = router;