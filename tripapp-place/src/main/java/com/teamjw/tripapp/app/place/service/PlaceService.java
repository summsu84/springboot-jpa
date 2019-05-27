package com.teamjw.tripapp.app.place.service;

import com.teamjw.tripapp.app.place.domain.*;
import com.teamjw.tripapp.app.place.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 *
 * 여행지 정보 서비스 클래스
 * DESC : 여행지 정보 모델
 * DATE : 2019.04.17
 *
 * 여행지 전체 조회, ID 기반 조회, 생성, 수정, 삭제 서비스
 *
 * @place teamjw - JJW
 */

@Service
public class PlaceService {

    @Autowired
    PlaceRepository placeRepository;

    @Autowired
    PlaceCountryService placeCountryService;

    @Autowired
    PlaceThemeService placeThemeService;

    @Autowired
    PlaceTypeService placeTypeService;

    @Autowired
    PlaceCityCodeService placeCityCodeService;



    /**
     *  장소 조회
     * @return
     */
    public List<Place> getPlaces() {
        return placeRepository.findAll();
    }


    public Optional<Place> getPlaceById(Long placeId) {
        if (!placeRepository.existsById(placeId)) {
            throw new ResourceNotFoundException("Place with id " + placeId + " not found");
        }
        return placeRepository.findById(placeId);
    }

    //
    public Page<Place> getPlaceByPage(Pageable page){

        return placeRepository.findAll(page);
    }


    public Place createPlace(Place place) {
        return placeRepository.save(place);

    }

    public Place updatePlaceById(Long placeId, Place placeRequest) {
        if (!placeRepository.existsById(placeId)) {
            throw new ResourceNotFoundException("Place with id " + placeId + " not found");
        }
        Optional<Place> place = placeRepository.findById(placeId);

        if (!place.isPresent()) {
            throw new ResourceNotFoundException("Place with id " + placeId + " not found");
        }

        Place place1 = place.get();
/*        place1.setFirstName(placeRequest.getFirstName());
        place1.setLastName(placeRequest.getLastName());*/
        return placeRepository.save(place1);

    }

    public ResponseEntity<Object> deletePlaceById(long placeId) {
        if (!placeRepository.existsById(placeId)) {
            throw new ResourceNotFoundException("Place with id " + placeId + " not found");
        }

        placeRepository.deleteById(placeId);

        return ResponseEntity.ok().build();

    }

    public Place createPlaceAll(Place place) {



        // Country City Code
        Optional<PlaceCityCode> byId = placeCityCodeService.getPlacCityCodeByCode("11");
        PlaceCityCode placeCityCode = byId.get();
        // TehemeCode
        Optional<PlaceThemeCode> byid1 = placeThemeService.getPlaceThemeCodeById((long) 1);
        PlaceThemeCode placeThemeCode = byid1.get();
        // Type Code
        Optional<PlaceTypeCode> byid2 = placeTypeService.getPlaceTypeCodeById((long) 1);
        PlaceTypeCode placeTypeCode = byid2.get();

        place.addPlaceCityCode(placeCityCode);
        place.addPlaceTheme(placeThemeCode);
        place.addPlaceType(placeTypeCode);

        Place place1 = placeRepository.save(place);


        return place1;
    }

    public Place createPlaceByCountryAndThemeAndType(Place place, String countryId, Long themeId, Long typeId) {
        // Country Code
        Optional<PlaceCountryCode> byId = placeCountryService.getPlaceCountryCode(countryId);
        PlaceCountryCode placeCountryCode = byId.get();
        // TehemeCode
        Optional<PlaceThemeCode> byid1 = placeThemeService.getPlaceThemeCodeById(themeId);
        PlaceThemeCode placeThemeCode = byid1.get();
        // Type Code
        Optional<PlaceTypeCode> byid2 = placeTypeService.getPlaceTypeCodeById(typeId);
        PlaceTypeCode placeTypeCode = byid2.get();

        //place.addPlaceCountry(placeCountryCode);
        place.addPlaceTheme(placeThemeCode);
        place.addPlaceType(placeTypeCode);

        Place place1 = placeRepository.save(place);


        return place1;
    }
}
