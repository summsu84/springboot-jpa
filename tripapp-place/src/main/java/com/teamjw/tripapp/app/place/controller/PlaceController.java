package com.teamjw.tripapp.app.place.controller;


import com.teamjw.tripapp.app.place.domain.Place;
import com.teamjw.tripapp.app.place.repository.PlaceRepository;
import com.teamjw.tripapp.app.place.service.PlaceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @RequestMapping(value = "/place", method = RequestMethod.GET)
    public List<Place> getPlaces() {
        return placeService.getPlaces();
    }

    //
    @RequestMapping(value = "/place", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Place createPlace(@RequestBody Place place) {
        return placeService.createPlace(place);
    }

    @RequestMapping(value = "/place/{placeId}", method = RequestMethod.GET)
    public Optional<Place> getPlaceById(@PathVariable(value = "placeId") Long placeId) {
        return placeService.getPlaceById(placeId);
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