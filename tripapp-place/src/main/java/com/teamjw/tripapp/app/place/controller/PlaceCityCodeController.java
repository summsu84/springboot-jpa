package com.teamjw.tripapp.app.place.controller;


import com.teamjw.tripapp.app.place.domain.PlaceCityCode;
import com.teamjw.tripapp.app.place.domain.PlaceThemeCode;
import com.teamjw.tripapp.app.place.service.PlaceCityCodeService;
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

/**
 *  도시 정보 컨트롤러
 */
@RestController
public class PlaceCityCodeController {
	@Value("#{environment['SERVICE_ENDPOINT'] ?: 'localhost:8080'}")
	private String serviceEndpoint;


	@Autowired
    private PlaceCityCodeService placeCityCodeService;

	private static final Logger logger = LoggerFactory.getLogger(PlaceCityCodeController.class);

    /**
     *  City 코드를 가져온다.
     * @return
     */
    @RequestMapping(value = "/place/code/country/city", method = RequestMethod.GET)
    public List<PlaceCityCode> getPlaceCityCodeAll() {
        return placeCityCodeService.getPlaceCityCodeList();
    }

    @RequestMapping(value = "/place/code/country/{countrycode}/city", method = RequestMethod.GET)
    public List<PlaceCityCode> getPlaceCityCodeByCountryCode() {
        return placeCityCodeService.getPlaceCityCodeList();
    }

    /**
     *  City 코드 등록 한다.
     * @param countrycode
     * @param placeCityCode
     * @return
     */
    @RequestMapping(value = "/place/code/country/{countrycode}/city", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public PlaceCityCode createPlaceCityCodeByCountryCode(@PathVariable(value = "countrycode") String countrycode, @RequestBody PlaceCityCode placeCityCode) {
        return placeCityCodeService.createPlaceCityCodeByCountryCode(countrycode, placeCityCode);
    }

    /**
     *  City 코드 Id에 따라 조회 한다.
     * @param code
     * @return
     */
    @RequestMapping(value = "/place/code/country/{countrycode}/city/{code}", method = RequestMethod.GET)
    public Optional<PlaceCityCode> getPlaceCityCodeByCountryCodeAndCityCode(@PathVariable(value = "code") String code) {
        return placeCityCodeService.getPlacCityCodeByCode(code);
    }

/*    @RequestMapping(value = "/place", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Place updatePlace(@PathVariable(value = "authorId") Long authorId, @RequestBody Author author) {
        return authorService.updateAuthorById(authorId, author);
    }*/

    /**
     *  City 코드 Id 에 따라 삭제 한다.
     * @param code
     * @return
     */
    @RequestMapping(value = "/place/code/country/{countrycode}/city/{code}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deletePlaceById(@PathVariable(value = "code") String code) {
        return placeCityCodeService.deletePlaceCodeByCode(code);
    }

}