const router = require("express").Router();
const satelliteCtrl = require("./satellite.ctrl")

/*======================
    user 권한 API
======================*/
// 위성 리스트 조회
router.get("/",satelliteCtrl.getSatList);
// 위성 정보 조회
router.get("/:satellite_code",satelliteCtrl.getSatInfo);
// 위성 메타 정보 조회
router.get("/:satellite_code/meta",satelliteCtrl.getSatMeta);


module.exports = router;