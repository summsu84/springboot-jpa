package com.teamjw.tripapp.app.place.service;

import com.teamjw.tripapp.app.place.domain.Place;
import com.teamjw.tripapp.app.place.domain.PlaceThemeCode;
import com.teamjw.tripapp.app.place.repository.PlaceRepository;
import com.teamjw.tripapp.app.place.repository.PlaceThemeCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 *
 * 여행지 정보 테마 코드 서비스 클래스
 * DESC : 여행지 정보 테마 코드 모델
 * DATE : 2019.04.17
 *
 *
 *
 * @place teamjw - JJW
 */

@Service
public class PlaceThemeService {

    @Autowired
    PlaceThemeCodeRepository placeThemeCodeRepository;


    public List<PlaceThemeCode> getPlaces() {
        return placeThemeCodeRepository.findAll();
    }

    public Optional<PlaceThemeCode> getPlaceThemeCodeByName(Long themeCode) {
        if (!placeThemeCodeRepository.existsById(themeCode)) {
            throw new ResourceNotFoundException("PlaceThemeCode with id " + themeCode + " not found");
        }
        return placeThemeCodeRepository.findById(themeCode);
    }

    public PlaceThemeCode createPlaceThemeCode(PlaceThemeCode placeThemeCode) {
        return placeThemeCodeRepository.save(placeThemeCode);
    }

    public Optional<PlaceThemeCode> getPlaceThemeCodeById(Long themeId) {
        if (!placeThemeCodeRepository.existsById(themeId)) {
            throw new ResourceNotFoundException("PlaceThemeCode with id " + themeId + " not found");
        }
        //Long no = new Long(1);
        //Optional<PlaceThemeCode> tmp = placeThemeCodeRepository.findById(no);
        return placeThemeCodeRepository.findById(themeId);
    }

    public ResponseEntity<Object> deletePlaceThemeById(Long themeId) {
        if (!placeThemeCodeRepository.existsById(themeId)) {
            throw new ResourceNotFoundException("PlaceThemeCode with id " + themeId + " not found");
        }

        placeThemeCodeRepository.deleteById(themeId);

        return ResponseEntity.ok().build();
    }
}
