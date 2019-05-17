const MongoClient = require('mongodb').MongoClient;

module.exports.env = {
  url: 'mongodb://localhost:27017/simsdb_mongo_local?useSSL=false',
  dbName : 'simsdb_mongo_local'
}

module.exports.getDbClient =()=>{
  return new Promise((resolve)=>{
    const url = this.env.url;
    // db 클라이언트 생성
    dbClient = MongoClient(url);

    resolve(dbClient);
  })
}