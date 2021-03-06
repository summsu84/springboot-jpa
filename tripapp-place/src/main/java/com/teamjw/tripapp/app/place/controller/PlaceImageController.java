package com.teamjw.tripapp.app.place.controller;


import com.teamjw.tripapp.app.place.domain.PlaceImage;
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
public class PlaceImageController {
	@Value("#{environment['SERVICE_ENDPOINT'] ?: 'localhost:8080'}")
	private String serviceEndpoint;


	@Autowired
    private PlaceImageService placeImageService;

	private static final Logger logger = LoggerFactory.getLogger(PlaceImageController.class);


    /**
     *  모든 이미지 리스트를 가져온다.
     * @return
     */
    @RequestMapping(value = "/place/image", method = RequestMethod.GET)
    public List<PlaceImage> getPlaceImages() {
        return placeImageService.getPlaces();
    }

    /**
     *  PlaceId에 해당하는 이미지를 등록 한다.
     * @param placeId
     * @param placeImage
     * @return
     */
    @RequestMapping(value = "/place/{placeId}/image", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public PlaceImage createPlaceImage(@PathVariable(value = "placeId") Long placeId, @RequestBody PlaceImage placeImage) {
        return placeImageService.createPlaceImage(placeId, placeImage);
    }

    @RequestMapping(value = "/place/image/{imageId}", method = RequestMethod.GET)
    public Optional<PlaceImage> getPlaceImageById(@PathVariable(value = "imageId") Long imageId) {
        return placeImageService.getPlaceById(imageId);
    }

/*    @RequestMapping(value = "/place", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Place updatePlace(@PathVariable(value = "authorId") Long authorId, @RequestBody Author author) {
        return authorService.updateAuthorById(authorId, author);
    }*/

    @RequestMapping(value = "/place/image/{imageId}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deletePlacImageeById(@PathVariable(value = "imageId") long imageId) {
        return placeImageService.deletePlaceById(imageId);
    }

}