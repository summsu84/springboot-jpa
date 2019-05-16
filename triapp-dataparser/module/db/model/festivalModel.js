/**
 * Created by JJW on 2017-07-20.
 */
var FestivalModel = function(){

};

FestivalModel.prototype.connection = null;

FestivalModel.prototype.init = function(connection){

    console.log(">>Festival init .. connection value : " + connection)

    this.connection = connection;

}



FestivalModel.prototype.insertFestivalList = function(festivalList, callback){
    var self = this;

    var sql = "INSERT INTO TB_FESTIVAL  (FESTIVAL_ID, FESTIVAL_NAME, FESTIVAL_CLASS, FESTIVAL_TARGET, FESTIVAL_PLACE, FESTIVAL_START_DATE, FESTIVAL_END_DATE, FESTIVAL_PAYMENT, FESTIVAL_X_COORD, FESTIVAL_Y_COORD, FESTIVAL_AREA, FESTIVAL_LINK) VALUES ? ";
    var values = festivalList;



    var query = self.connection.query(sql, [values],  function (err, rows){

        if (err)
            console.error(err);
        else {
            console.log(rows);
            if (callback)
                callback(err, rows);
        }
    })
}




module["exports"] = new FestivalModel();