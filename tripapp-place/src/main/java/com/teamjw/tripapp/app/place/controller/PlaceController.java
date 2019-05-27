package com.teamjw.tripapp.app.place.controller;


import com.teamjw.tripapp.app.place.domain.Place;
import com.teamjw.tripapp.app.place.repository.PlaceRepository;
import com.teamjw.tripapp.app.place.service.PlaceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@RestController
public class PlaceController {
	@Value("#{environment['SERVICE_ENDPOINT'] ?: 'localhost:8080'}")
	private String serviceEndpoint;


	@Autowired
    private PlaceService placeService;

	private static final Logger logger = LoggerFactory.getLogger(PlaceController.class);

    /**
     *  모든 장소를 가져온다.
     * @return
     */
    @RequestMapping(value = "/place", method = RequestMethod.GET)
    public List<Place> getPlaces() {
        return placeService.getPlaces();
    }

    //page
    @RequestMapping(value = "/place/page/{pgno}/{size}", method = RequestMethod.GET)
    public Page<Place> getPlacesByPage(@PathVariable Integer pgno,
                                       @PathVariable Integer size
                                       ) {
        PageRequest pageRequest = PageRequest.of(pgno - 1, size, Sort.Direction.DESC, "placeCd");    //new PageRequest is deprecated
        return placeService.getPlaceByPage(pageRequest);
    }

    /**
     *  장소를 등록 한다.
     * @param place
     * @return
     */
    @RequestMapping(value = "/place", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Place createPlace(@RequestBody Place place) {
        return placeService.createPlaceAll(place);
    }

    @RequestMapping(value = "/place/country/{countryId}/theme/{themeId}/type/{typeId}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Place createPlaceByCountryAndThemeAndType(@RequestBody Place place, @PathVariable(value = "countryId") String countryId,
        @PathVariable(value = "themeId") Long themeId, @PathVariable(value = "typeId") Long typeId ) {
        return placeService.createPlaceByCountryAndThemeAndType(place, countryId, themeId, typeId);
    }



    /**
     *  장소 Id에 따른 장소 검색
     * @param placeId
     * @return
     */
    @RequestMapping(value = "/place/{placeId}", method = RequestMethod.GET)
    public Place getPlaceById(@PathVariable(value = "placeId") Long placeId) {
        return (placeService.getPlaceById(placeId)).get();
    }

/*    @RequestMapping(value = "/place", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Place updatePlace(@PathVariable(value = "authorId") Long authorId, @RequestBody Author author) {
        return authorService.updateAuthorById(authorId, author);
    }*/

    @RequestMapping(value = "/place/{placeId}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deletePlaceById(@PathVariable(value = "placeId") long placeId) {
        return placeService.deletePlaceById(placeId);
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