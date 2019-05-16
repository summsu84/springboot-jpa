var mysql = require('mysql');
var db_config = require('../../db-config');

var DatabaseManager = {
    //Table Model
    connection : null,
    placeModel: null,
    festivalModel : null,

    // 데이터베이스 초기화
    init: function(){
		var self = this;
        // Connection to our chat database
        console.log("Connecting mariaDB ");
        self.connection = mysql.createConnection({
            host    : db_config.host,
            port    : db_config.port,
            user    : db_config.user,
            password : db_config.password,
            database : db_config.database,
        });

        self.connection.connect(function(err) {
            if (err) {
                console.error('db connection error');
                console.error(err);
                throw err;
            }else
            {
                console.log("db connection success");
                self.setupSchema();
            }
        });
    },

    // 스키마
    setupSchema : function(){

        this.placeModel = require('./model/placeModel').init(this.connection);
        this.festivalModel = require('./model/festivalModel').init(this.connection);
    }

}

module["exports"] = DatabaseManager;