/**
 * Created by JJW on 2017-07-20.
 * DESC : 장소 모델
 */
var PlaceModel = function(){

};

PlaceModel.prototype.connection = null;

PlaceModel.prototype.init = function(connection){

    console.log(">>PlaceModel init .. connection value : " + connection)

    this.connection = connection;

}

/**
 *  장소를 모두 가져온다.
 * @param callback
 */
PlaceModel.prototype.selectPlaceList = function(callback){

    var self = this;

    var query = self.connection.query('select * from TB_PLACES', function (err, rows){

        if (err)
            console.error(err);
        else {
            console.log(rows);
            if (callback)
                callback(err, rows);
        }
    })

}


/**
 *  장소를 등록 한다.
 * @param placeList
 * @param callback
 */
PlaceModel.prototype.insertPlaceList = function(placeList, callback){
    var self = this;

    var sql = "INSERT INTO trip_tbl_place  (create_user_name, created_time, del_yn, update_user_name, updated_time, use_yn, place_address, place_cd, place_english_description, place_english_name, place_homepage, place_inqury, place_korean_description, place_korean_name,place_lat, place_like , place_lon, place_phone, place_country_id, place_theme_id, place_type_id, place_open_time, place_close_time, place_close_day,place_fee) VALUES ? ";
    var values = placeList;

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

/**
 *  좌표를 등록 한다.
 * @param coordList
 * @param callback
 */
PlaceModel.prototype.insertCoordList = function(coordList, callback){
    var self = this;

    var sql = "INSERT INTO TB_PLACE_COORD  (PLACE_ID, COORD_NO, X_COORD, Y_COORD) VALUES ? ";
    var values = coordList;



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

/**
 *  이미지를 등록 한다.
 * @param imageList
 * @param callback
 */
PlaceModel.prototype.insertImageList = function(imageList, callback){
    var self = this;

    var sql = "INSERT INTO trip_tbl_place_image  (create_user_name, created_time, del_yn, update_user_name, updated_time, use_yn, seq, url, place_id) VALUES ? ";
    var values = imageList;



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


module["exports"] = new PlaceModel();