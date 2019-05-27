const apiCaller = require("../../utils/apiCaller");
const uri = require("../routeUri");

module.exports.getSatList = (req,res)=>{
    // req.url하면 쿼리 파라미터 조회
    apiCaller(uri.satellites.getSatList(req.url))
        .get()
        .then(result=>{
            res.status(200).json(result)
        })
        .catch(err=>{
            res.status(404).json(err)
        })
}

module.exports.getSatInfo = (req,res)=>{
    // path variable
    const {satellite_code} = req.params

    apiCaller(uri.satellites.getSatInfo(satellite_code))
        .get()
        .then(result=>{
            res.status(200).json(result)
        })
        .catch(err=>{
            res.status(404).json(err)
        })
}


module.exports.getSatMeta = (req,res)=>{
    // path variable
    const {satellite_code} = req.params

    apiCaller(uri.satellites.getSatMeta(satellite_code))
        .get()
        .then(result=>{
            res.status(200).json(result)
        })
        .catch(err=>{
            res.status(404).json(err)
        })
}

