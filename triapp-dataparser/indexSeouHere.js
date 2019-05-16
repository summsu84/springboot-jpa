// basicServer.js
var http = require('http');
var request = require("request");
var xml2js = require('xml2js');
var fs = require('fs');
var start = true;

//DB 로딩
var DatabaseManager = require('./module/db/DatabaseManager');
DatabaseManager.init();
var PlaceModel = require("./module/db/model/placeModel");

http.createServer(function (req, res) {
    res.writeHead(200, {'Content-Type': 'text/html'});
    res.end('Hello World');
    console.log(">>>Listening..value : " + start);
    start = false;
    console.log(req.url);
    if(req.url != '/favicon.ico') {

        var filename = __dirname + "\\data\\seoul_place_info.json";

        console.log(">>filename : " + filename);

        readJsonFile(filename);
    }

}).listen(50000, '127.0.0.1');

function readJsonFile(filename)
{

    fs.readFile(filename, function (err, data){

        if(err) throw err;

        var jsonData = JSON.parse(data);

        console.log(">>jsonData : " + jsonData);

        var datalist = jsonData.DATA;
        var convDataList = new Array();
        var coordList = new Array();
        var imageList = new Array();

        for (var i in datalist)
        {
            var data = datalist[i];

            if(data.CODENAME == '도서관')
            {
                data['CODE'] = 1;
            }else if(data.CODENAME == '공연장')
            {
                data['CODE'] = 2;
            }else if(data.CODENAME == '문화원')
            {
                data['CODE'] = 3;
            }else if(data.CODENAME == '미술관')
            {
                data['CODE'] = 4;
            }else if(data.CODENAME == '박물관')
            {
                data['CODE'] = 5;
            }else if(data.CODENAME == '기념관')
            {
                data['CODE'] = 6;
            }else if(data.CODENAME == '문화예술회관')
            {
                data['CODE'] = 7;
            }else
            {
                data['CODE'] = 0;
            }

            var strI = i.toString();

            for(var j = strI.length ; j < 4 ;  j++ )
            {
                var zero = '0';
                strI = zero.concat(strI);
            }

            //var utc = new Date().toJSON().slice(0,10).replace(/-/g,'/');

            var newData =
            {
                'create_user_name': 'admin',
                'created_time':'2019-05-16 09:00:00',
                'del_yn': 'N',
                'update_user_name' :'admin',
                'updated_time': '2019-05-16 09:00:00',
                'use_yn' : 'Y',
                'place_address' : data.ADDR,
                'place_cd' : "PL" + strI,
                'place_english_description' : '',
                'place_english_name' : '',
                'place_homepage' : data.HOMEPAGE,
                'place_inqury' : 0,
                'place_korean_description' : data.ETC_DESC,
                'place_korean_name' : data.FAC_NAME,
                'place_lat' : data.X_COORD,
                'place_like' : 0,
                'place_lon': data.Y_COORD,
                'place_phone' : data.PHNE,
                'place_country_id' : 1,
                'place_theme_id' : 1,
                'place_type_id' : data.CODE,
                'place_open_time' : data.OPENHOUR,
                'place_close_time': '',
                'place_close_day' : data.CLOSEDAY,
                'place_fee' : data.ENTR_FEE
            }
            var stringArray = new Array();
            stringArray.push(newData.create_user_name);
            stringArray.push(newData.created_time);
            stringArray.push(newData.del_yn);
            stringArray.push(newData.update_user_name);
            stringArray.push(newData.updated_time);
            stringArray.push(newData.use_yn);
            stringArray.push(newData.place_address);
            stringArray.push(newData.place_cd);
            stringArray.push(newData.place_english_description);
            stringArray.push(newData.place_english_name);
            stringArray.push(newData.place_homepage);
            stringArray.push(newData.place_inqury);
            stringArray.push(newData.place_korean_description);
            stringArray.push(newData.place_korean_name);
            stringArray.push(newData.place_lat);
            stringArray.push(newData.place_like);
            stringArray.push(newData.place_lon);
            stringArray.push(newData.place_phone);
            stringArray.push(newData.place_country_id);
            stringArray.push(newData.place_theme_id);
            stringArray.push(newData.place_type_id);
            stringArray.push(newData.place_open_time);
            stringArray.push(newData.place_close_time);
            stringArray.push(newData.place_close_day);
            stringArray.push(newData.place_fee);

            var stringCoordArray = new Array();
            stringCoordArray.push(newData.PLACE_ID);
            stringCoordArray.push(1);
            stringCoordArray.push(parseFloat(data.X_COORD));
            stringCoordArray.push(parseFloat(data.Y_COORD));

            var stringImagArray = new Array();
            stringImagArray.push(newData.create_user_name);
            stringImagArray.push(newData.created_time);
            stringImagArray.push(newData.del_yn);
            stringImagArray.push(newData.update_user_name);
            stringImagArray.push(newData.updated_time);
            stringImagArray.push(newData.use_yn);
            stringImagArray.push(1);
            stringImagArray.push(data.MAIN_IMG);
            stringImagArray.push(parseInt(i) + 1);

            convDataList.push(stringArray);
            //coordList.push(stringCoordArray);
            imageList.push(stringImagArray);
        }

        PlaceModel.insertImageList(imageList, function(err2, data2){
            if(err2)
            {
                console.log("error : " + err2);

            }else {

                console.log("data : " + data2);

            }
        })

        /*PlaceModel.insertPlaceList(convDataList, function(err, data)
        {
            if(err)
            {
                console.log("erro : " + err);

            }else
            {
                console.log("data : " + data);
            }
        })*/

    })
}


console.log('Server running at http://127.0.0.1:50000/');