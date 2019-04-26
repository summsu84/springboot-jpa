package com.teamjw.tripapp.app.place.controller;


import com.teamjw.tripapp.app.place.domain.PlaceComment;
import com.teamjw.tripapp.app.place.domain.PlaceImage;
import com.teamjw.tripapp.app.place.service.PlaceCommentService;
import com.teamjw.tripapp.app.place.service.PlaceImageService;
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
public class PlaceCommentController {
	@Value("#{environment['SERVICE_ENDPOINT'] ?: 'localhost:8080'}")
	private String serviceEndpoint;


	@Autowired
    private PlaceCommentService placeCommentService;

	private static final Logger logger = LoggerFactory.getLogger(PlaceCommentController.class);

    @RequestMapping(value = "/place/comment", method = RequestMethod.GET)
    public List<PlaceComment> getPlaceCommets() {
        return placeCommentService.getPlaces();
    }

    //
    @RequestMapping(value = "/place/comment", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public PlaceComment createPlaceComment(@RequestBody PlaceComment place) {
        return placeCommentService.createPlaceComment(place);
    }

    @RequestMapping(value = "/place/comment/{commentId}", method = RequestMethod.GET)
    public Optional<PlaceComment> getPlaceCommentById(@PathVariable(value = "commentId") Long commentId) {
        return placeCommentService.getPlaceCommentById(commentId);
    }


    @RequestMapping(value = "/place/comment/{commentId}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deletePlaceCommentById(@PathVariable(value = "commentId") long commentId) {
        return placeCommentService.deletePlaceCommentById(commentId);
    }

}