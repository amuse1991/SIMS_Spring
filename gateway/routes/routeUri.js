const config = require("../config")
const serverEnv = config.server.devEnv;
const satServerBasePath = `http://${serverEnv.satellite.host}:${serverEnv.satellite.port}`;
const chartServerBasePath = `http://${serverEnv.chart.host}:${serverEnv.chart.port}`;
const authServerBasePath = `http://${serverEnv.auth.host}:${serverEnv.auth.port}`;
const userServerBasePath = `http://${serverEnv.users.host}:${serverEnv.users.port}`;

module.exports = {
    basePath:{
        satellite : satServerBasePath,
        chart : chartServerBasePath,
        auth : authServerBasePath,
        user : userServerBasePath
    },
    users:{
        users: (userId) => userId===undefined?`${userServerBasePath}/api/users`:`${userServerBasePath}/api/users/${userId}`
    },
    auth:{
        login: () =>`${authServerBasePath}/api/auth/login`
    },
    satellites:{
        getSatList: (queryPrams) => `${satServerBasePath}/api/satellites`,
        getSatInfo: (pathVar) => `${satServerBasePath}/api/satellites/${pathVar}`,
        getSatMeta: (pathVar) => `${satServerBasePath}/api/satellites/${pathVar}/meta`
    }
}