/* =============================================
    디코딩된 토큰값을 기반으로 적절한 접근인지 판단
    적절하면 next() 실행
    적절하지 않으면 403 응답 반환
================================================ */

// requiredRole : api가 요구하는 접근 권한
const verify = function(requiredRole){
    const getRoleLevel = (roleName)=>{
        switch(roleName){
            case "admin":
                return 0
            case "user":
                return 1
        }
    }
    return (req,res,next) => {
        const token = req.decoded;
        let userRole = getRoleLevel(token.role.role_name);
        requiredRole = getRoleLevel(requiredRole);

        /*=========================================================
            자신의 권한과 같거나, 더 작은 권한일 경우 엑세스 허용
            ex) admin 일때 admin은 admin api와 user api
            모두 접근 가능
        ============================================================*/
        if(requiredRole <= userRole){
            next()
        }else{
            throw new error(`Unable to access api. required : ${requiredRole} , current : ${userRole}`)
        }
    }
}

module.exports = verify;