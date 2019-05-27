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

    /**
     *  모든 커멘트를 가져온다.
     * @return
     */
    @RequestMapping(value = "/place/comment", method = RequestMethod.GET)
    public List<PlaceComment> getPlaceCommets() {
        return placeCommentService.getPlaces();
    }

    /**
     *  커멘트 등록 한다.
     * @param place
     * @return
     */
    @RequestMapping(value = "/place/comment", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public PlaceComment createPlaceComment(@RequestBody PlaceComment place) {
        return placeCommentService.createPlaceComment(place);
    }


    /**
     *  Place Id를 기반으로 Place Comment를 등록 한다.
     * @param placeId
     * @param placeComment
     * @return
     */
    @RequestMapping(value = "/place/{placeId}/comment", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public PlaceComment createPlaceCommetById(@PathVariable(value = "placeId") Long placeId, @RequestBody PlaceComment placeComment) {
        return placeCommentService.createPlaceCommentById(placeId, placeComment);
    }

    /**
     *  커멘트 Id에 따른 커멘트를 가져온다.
     * @param commentId
     * @return
     */
    @RequestMapping(value = "/place/comment/{commentId}", method = RequestMethod.GET)
    public Optional<PlaceComment> getPlaceCommentById(@PathVariable(value = "commentId") Long commentId) {
        return placeCommentService.getPlaceCommentById(commentId);
    }

    // userId에 따라서 커멘트를 가져온다.
    @RequestMapping(value = "/place/user/{userId}/comment", method = RequestMethod.GET)
    public List<PlaceComment> getPlaceCommentByUserId(@PathVariable(value = "userId") Long userId) {
        return placeCommentService.getPlaceCommentByUserId(userId);
    }




    /**
     *  커멘트 Id 에 따라 커멘트를 삭제 한다.
     * @param commentId
     * @return
     */
    @RequestMapping(value = "/place/comment/{commentId}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deletePlaceCommentById(@PathVariable(value = "commentId") long commentId) {
        return placeCommentService.deletePlaceCommentById(commentId);
    }

}