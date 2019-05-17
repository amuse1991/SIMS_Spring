const assert = require('assert');
const dbManager = require('../../bin/dbManager');


module.exports.getData = (dbClient, dataName, callback)=>{
    dbClient.connect(err=>{
        // db 서버 연결 검사
        assert.equal(null, err);
        const db = dbClient.db(dbManager.env.dbName);
        // collection 조회
        const collection = db.collection(dataName);
        collection.find({}).toArray(function(err, docs) {
            assert.equal(err, null);
            console.log("Found the following records");
            console.log(docs);
            callback(docs);
          });
    })
}