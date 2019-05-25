const jwt = require('jsonwebtoken')

const authMiddleware = (req, res, next) => {
    // http 헤더 또는 url 에서 토큰 값 읽기
    const token = req.headers['x-access-token'] || req.query.token

    // 토큰 존재하지 않는 경우
    if(!token) {
        return res.status(403).json({
            success: false,
            message: 'not logged in'
        })
    }

    // 토큰 검증
    const verify = new Promise(
        (resolve, reject) => {
            jwt.verify(token, req.app.get('jwt-secret'), (err, decoded) => {
                if(err) reject(err)
                resolve(decoded)
            })
        }
    )

    // 인증 에러 발생시
    const onError = (error) => {
        res.status(403).json({
            success: false,
            message: error.message
        })
    }

    verify
    .then((decoded)=>{
        req.decoded = decoded
        next()
    })
    .catch(onError)
}

module.exports = authMiddleware