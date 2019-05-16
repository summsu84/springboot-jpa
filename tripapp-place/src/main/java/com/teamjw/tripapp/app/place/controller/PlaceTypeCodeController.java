package com.teamjw.tripapp.app.place.controller;


import com.teamjw.tripapp.app.place.domain.PlaceThemeCode;
import com.teamjw.tripapp.app.place.domain.PlaceTypeCode;
import com.teamjw.tripapp.app.place.service.PlaceThemeService;
import com.teamjw.tripapp.app.place.service.PlaceTypeService;
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
public class PlaceTypeCodeController {
	@Value("#{environment['SERVICE_ENDPOINT'] ?: 'localhost:8080'}")
	private String serviceEndpoint;


	@Autowired
    private PlaceTypeService placeTypeService;

	private static final Logger logger = LoggerFactory.getLogger(PlaceTypeCodeController.class);

    /**
     *  모든 타입 코드를 가져온다.
     * @return
     */
    @RequestMapping(value = "/place/typecode", method = RequestMethod.GET)
    public List<PlaceTypeCode> getPlaceTypeCodes() {
        return placeTypeService.getPlaceTypeCode();
    }

    /**
     *  타입 코드를 등록 한다.
     * @param placeTypeCode
     * @return
     */
    @RequestMapping(value = "/place/typecode", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public PlaceTypeCode createPlaceTypeCode(@RequestBody PlaceTypeCode placeTypeCode) {
        return placeTypeService.createPlaceTypeCode(placeTypeCode);
    }

    /**
     *  특정 ID의 타입 가져온다.
     * @param typeId
     * @return
     */
    @RequestMapping(value = "/place/typecode/{id}", method = RequestMethod.GET)
    public Optional<PlaceTypeCode> getPlaceThemeCodeById(@PathVariable(value = "id") Long typeId) {
        return placeTypeService.getPlaceTypeCodeById(typeId);
    }


/*    @RequestMapping(value = "/place", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Place updatePlace(@PathVariable(value = "authorId") Long authorId, @RequestBody Author author) {
        return authorService.updateAuthorById(authorId, author);
    }*/

    /**
     *  특정 ID의 테마코드를 삭제 한다.
     * @param typeId
     * @return
     */
    @RequestMapping(value = "/place/typecode/{Id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deletePlaceById(@PathVariable(value = "Id") Long typeId) {
        return placeTypeService.deletePlaceTypeCodeById(typeId);
    }
}