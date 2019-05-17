package com.teamjw.tripapp.app.place.service;

import com.teamjw.tripapp.app.place.domain.BestPlaceTypeCode;
import com.teamjw.tripapp.app.place.domain.PlaceTypeCode;
import com.teamjw.tripapp.app.place.repository.BestPlaceTypeRepository;
import com.teamjw.tripapp.app.place.repository.PlaceTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 *
 * 베스트 여행지 정보 타입 서비스 클래스
 * DESC : 여행지 정보 타입 서비스
 * DATE : 2019.05.17
 *
 * 여행지 타입 조회
 *
 * @place teamjw - JJW
 */

@Service
public class BestPlaceTypeService {

    @Autowired
    BestPlaceTypeRepository bestPlaceTypeRepository;

    public Optional<BestPlaceTypeCode> getBestPlaceTypeCodeById(Long typeId) {
        if (!bestPlaceTypeRepository.existsById(typeId)) {
            throw new ResourceNotFoundException("PlaceTypeCode with id " + typeId + " not found");
        }
        return bestPlaceTypeRepository.findById(typeId);
    }

    public List<BestPlaceTypeCode> getBestPlaceTypeCode() {
        return bestPlaceTypeRepository.findAll();
    }


    public BestPlaceTypeCode createBestPlaceTypeCode(BestPlaceTypeCode bestPlaceTypeCode) {
        return bestPlaceTypeRepository.save(bestPlaceTypeCode);
    }

    public ResponseEntity<Object> deleteBestPlaceTypeCodeById(Long typeId) {
        if (!bestPlaceTypeRepository.existsById(typeId)) {
            throw new ResourceNotFoundException("PlaceThemeCode with id " + typeId + " not found");
        }

        bestPlaceTypeRepository.deleteById(typeId);

        return ResponseEntity.ok().build();
    }
}
