package com.teamjw.tripapp.app.place.controller;


import com.teamjw.tripapp.app.place.domain.BestPlaceTypeCode;
import com.teamjw.tripapp.app.place.service.BestPlaceTypeService;
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
public class BestPlaceTypeCodeController {
	@Value("#{environment['SERVICE_ENDPOINT'] ?: 'localhost:8080'}")
	private String serviceEndpoint;


	@Autowired
    private BestPlaceTypeService bestPlaceTypeService;

	private static final Logger logger = LoggerFactory.getLogger(BestPlaceTypeCodeController.class);

    /**
     *  모든 베스트 타입 코드를 가져온다.
     * @return
     */
    @RequestMapping(value = "/best/place/type", method = RequestMethod.GET)
    public List<BestPlaceTypeCode> getBestPlaceTypeCodes() {
        return bestPlaceTypeService.getBestPlaceTypeCode();
    }

    /**
     *  베스트 타입 코드를 등록 한다.
     * @param bestPlaceTypeCode
     * @return
     */
    @RequestMapping(value = "/best/place/type", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public BestPlaceTypeCode createBestPlaceTypeCode(@RequestBody BestPlaceTypeCode bestPlaceTypeCode) {
        return bestPlaceTypeService.createBestPlaceTypeCode(bestPlaceTypeCode);
    }

    /**
     *  특정 베스트 타입 ID의 타입 가져온다.
     * @param typeId
     * @return
     */
    @RequestMapping(value = "/best/place/type/{id}", method = RequestMethod.GET)
    public Optional<BestPlaceTypeCode> getBestPlaceTypeCodeById(@PathVariable(value = "id") Long typeId) {
        return bestPlaceTypeService.getBestPlaceTypeCodeById(typeId);
    }

    /**
     *  특정 베스트 타입 ID를 삭제 한다.
     * @param typeId
     * @return
     */
    @RequestMapping(value = "/best/place/type/{Id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deletePlaceById(@PathVariable(value = "Id") Long typeId) {
        return bestPlaceTypeService.deleteBestPlaceTypeCodeById(typeId);
    }
}