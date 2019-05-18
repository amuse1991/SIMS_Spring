const assert = require('assert');
const MongoClient = require('mongodb').MongoClient;


const dbUrl = 'mongodb://localhost:27017/simsdb_mongo_local?useSSL=false';
const dbName = 'simsdb_mongo_local';


module.exports.getData = ()=>{
    return new Promise((resolve,reject)=>{
        dbClient = MongoClient(dbUrl); 
        dbClient.connect((err)=>{ // db server 연결
            assert.equal(null,err)

            const db = dbClient.db(dbName)

            findDocuments(db,'fcs',(res)=>{
                // console.log(res);
                dbClient.close();
                resolve(res);
            })
        })
    });
}

const findDocuments = (db, collectionName, callback)=>{
    const collection = db.collection(collectionName);
    collection.find({}).toArray((err, docs)=>{
      assert.equal(err, null);
      callback(docs);
    });
  }