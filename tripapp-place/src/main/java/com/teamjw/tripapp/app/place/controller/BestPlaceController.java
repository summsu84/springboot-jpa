package com.teamjw.tripapp.app.place.controller;


import com.teamjw.tripapp.app.place.domain.BestPlace;
import com.teamjw.tripapp.app.place.service.BestPlaceService;
import com.teamjw.tripapp.app.place.service.PlaceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BestPlaceController {
	@Value("#{environment['SERVICE_ENDPOINT'] ?: 'localhost:8080'}")
	private String serviceEndpoint;


	@Autowired
    private BestPlaceService bestPlaceService;

	private static final Logger logger = LoggerFactory.getLogger(BestPlaceController.class);

    /**
     *  모든 베스트 장소를 가져온다.
     * @return
     */
    @RequestMapping(value = "/best/place", method = RequestMethod.GET)
    public List<BestPlace> getBestPlaces() {
        return bestPlaceService.getBestPlaces();
    }


    /**
     *  베스트 장소 Id에 따른 장소 검색
     * @param id
     * @return
     */
    @RequestMapping(value = "/best/place/{id}", method = RequestMethod.GET)
    public BestPlace getPlaceById(@PathVariable(value = "id") Long id) {
        return (bestPlaceService.getBestPlaceById(id)).get();
    }

}