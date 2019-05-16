package com.teamjw.tripapp.app.place.service;

import com.teamjw.tripapp.app.place.domain.PlaceTypeCode;
import com.teamjw.tripapp.app.place.repository.PlaceTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 *
 * 여행지 정보 타입 서비스 클래스
 * DESC : 여행지 정보 타입 서비스
 * DATE : 2019.04.17
 *
 * 여행지 타입 조회
 *
 * @place teamjw - JJW
 */

@Service
public class PlaceTypeService {

    @Autowired
    PlaceTypeRepository placeTypeRepository;



    public Optional<PlaceTypeCode> getPlaceTypeCodeById(Long typeId) {
        if (!placeTypeRepository.existsById(typeId)) {
            throw new ResourceNotFoundException("PlaceTypeCode with id " + typeId + " not found");
        }
        return placeTypeRepository.findById(typeId);
    }

    public List<PlaceTypeCode> getPlaceTypeCode() {
        return placeTypeRepository.findAll();
    }


    public PlaceTypeCode createPlaceTypeCode(PlaceTypeCode placeThemeCode) {
        return placeTypeRepository.save(placeThemeCode);
    }

    public ResponseEntity<Object> deletePlaceTypeCodeById(Long typeId) {
        if (!placeTypeRepository.existsById(typeId)) {
            throw new ResourceNotFoundException("PlaceThemeCode with id " + typeId + " not found");
        }

        placeTypeRepository.deleteById(typeId);

        return ResponseEntity.ok().build();
    }
}
