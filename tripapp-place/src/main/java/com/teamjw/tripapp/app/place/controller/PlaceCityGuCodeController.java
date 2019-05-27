package com.teamjw.tripapp.app.place.controller;


import com.teamjw.tripapp.app.place.domain.PlaceCityGuCode;
import com.teamjw.tripapp.app.place.domain.PlaceThemeCode;
import com.teamjw.tripapp.app.place.service.PlaceCityGuCodeService;
import com.teamjw.tripapp.app.place.service.PlaceThemeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PlaceCityGuCodeController {
	@Value("#{environment['SERVICE_ENDPOINT'] ?: 'localhost:8080'}")
	private String serviceEndpoint;


	@Autowired
    private PlaceCityGuCodeService placeCityGuCodeService;

	private static final Logger logger = LoggerFactory.getLogger(PlaceCityGuCodeController.class);

    /**
     *  전체 city 코드를 가져온다.
     * @return
     */
    @RequestMapping(value = "/place/code/county/city/gu", method = RequestMethod.GET)
    public List<PlaceCityGuCode> getPlaceThemeCodes() {
        return placeCityGuCodeService.getPlaceCityGuCode();
    }

    /**
     *  해당 city 코드의 구 정보를 가져온다.
     * @return
     */
    @RequestMapping(value = "/place/code/country/{countrycode}/city/{citycode}/gu", method = RequestMethod.GET)
    public List<PlaceCityGuCode> getPlaceCityGuCodeByCountryCodeAndCityCode(@PathVariable(value = "countrycode") String countrycode,
                                                                            @PathVariable(value = "citycode") String citycode) {
        return placeCityGuCodeService.getPlaceCityGuCodeByCountryCodeAndCityCode(countrycode, citycode);
    }


    /**
     * City 코드 등록 한다.
     * @param countrycode
     * @param citycode
     * @param placeCityGuCode
     * @return
     */
    @RequestMapping(value = "/place/code/country/{countrycode}/city/{citycode}/gu", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public PlaceCityGuCode createPlaceThemeCode(@PathVariable(value = "countrycode") String countrycode,
                                                @PathVariable(value = "citycode") String citycode,
                                                @RequestBody PlaceCityGuCode placeCityGuCode) {
        return placeCityGuCodeService.createPlaceCityGuCodeByCountryCodeAndCityCode(countrycode, citycode, placeCityGuCode);
    }


    /**
     * City 구 코드 Id에 따라 조회 한다.
     * @param countrycode
     * @param citycode
     * @param code
     * @return
     */
    @RequestMapping(value = "/place/code/country/{countrycode}/city/{citycode}/gu/{code}", method = RequestMethod.GET)
    public Optional<PlaceCityGuCode> getPlaceThemeCodeById(@PathVariable(value = "countrycode") String countrycode,
                                                           @PathVariable(value = "citycode") String citycode,
                                                           @PathVariable(value = "code") String code) {
        return placeCityGuCodeService.getPlaceCityGuCodeByCountryCodeAndCityCodeAndGuCode(countrycode, citycode, code);
    }


    /**
     * Country 코드 Id 에 따라 삭제 한다.
     * @param countrycode
     * @param citycode
     * @param code
     * @return
     */
    @RequestMapping(value = "/place/code/country/{countrycode}/city/{citycode}/gu/{code}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deletePlaceById(@PathVariable(value = "countrycode") String countrycode,
                                                  @PathVariable(value = "citycode") String citycode,
                                                  @PathVariable(value = "code") String code) {
        return placeCityGuCodeService.deletePlaceCityGuCodeByCityGuCode(countrycode, citycode, code);
    }


}