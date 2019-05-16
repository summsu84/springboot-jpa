package com.teamjw.tripapp.app.place.controller;


import com.teamjw.tripapp.app.place.domain.PlaceThemeCode;
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
public class PlaceCountryCodeController {
	@Value("#{environment['SERVICE_ENDPOINT'] ?: 'localhost:8080'}")
	private String serviceEndpoint;


	@Autowired
    private PlaceThemeService placeThemeService;

	private static final Logger logger = LoggerFactory.getLogger(PlaceCountryCodeController.class);

    /**
     *  Country 코드를 가져온다.
     * @return
     */
    @RequestMapping(value = "/place/countrycode", method = RequestMethod.GET)
    public List<PlaceThemeCode> getPlaceThemeCodes() {
        return placeThemeService.getPlaces();
    }

    /**
     *  Country 코드 등록 한다.
     * @param placeThemeCode
     * @return
     */
    @RequestMapping(value = "/place/countrycode", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public PlaceThemeCode createPlaceThemeCode(@RequestBody PlaceThemeCode placeThemeCode) {
        return placeThemeService.createPlaceThemeCode(placeThemeCode);
    }

    /**
     *  Country 코드 Id에 따라 조회 한다.
     * @param themeId
     * @return
     */
    @RequestMapping(value = "/place/countrycode/{id}", method = RequestMethod.GET)
    public Optional<PlaceThemeCode> getPlaceThemeCodeById(@PathVariable(value = "id") Long themeId) {
        return placeThemeService.getPlaceThemeCodeById(themeId);
    }

/*    @RequestMapping(value = "/place", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Place updatePlace(@PathVariable(value = "authorId") Long authorId, @RequestBody Author author) {
        return authorService.updateAuthorById(authorId, author);
    }*/

    /**
     *  Country 코드 Id 에 따라 삭제 한다.
     * @param themeId
     * @return
     */
    @RequestMapping(value = "/place/countrycode/{Id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deletePlaceById(@PathVariable(value = "Id") Long themeId) {
        return placeThemeService.deletePlaceThemeById(themeId);
    }
/*
	@RequestMapping(value = "/place/{placeName}", method = RequestMethod.GET)
	public ResponseEntity<List<Place>> getPlaceList(@PathVariable String placeName){
		List<Place> placeList = this.placeRepository.findByPlaceName(placeName);

		return new ResponseEntity<List<Place>>(placeList, HttpStatus.OK);
	}

    @RequestMapping(value="/test", method = RequestMethod.POST)
    public @ResponseBody
    User test(@RequestBody User input) {

        return demoService.userSaveAndUpdate(input);
    }

    @RequestMapping(value="/user/{name}", method = RequestMethod.GET)
    public @ResponseBody
    List<User> getUser(@PathVariable String name) {

        return demoService.getUser(name);
    }

    @RequestMapping(value = "/getAllUsers", method = RequestMethod.GET)
    public List<User> getUsers() {
        return demoService.getUsers();
    }

    @RequestMapping(value = "/{userNo}/userProfile", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserProfile createBook(@PathVariable(value = "userNo") Integer userNo, @RequestBody UserProfile userProfile) {
        return userProfileService.createUserProfile(userNo, userProfile);
    }
    */


}