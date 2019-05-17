package com.teamjw.tripapp.app.place.service;

import com.teamjw.tripapp.app.place.domain.*;
import com.teamjw.tripapp.app.place.repository.BestPlaceMapperRepository;
import com.teamjw.tripapp.app.place.repository.BestPlaceRepository;
import com.teamjw.tripapp.app.place.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 *
 * 베스트 여행지 정보 서비스 클래스
 * DESC : 여행지 정보 모델
 * DATE : 2019.05.17
 *
 * 여행지 전체 조회, ID 기반 조회, 생성, 수정, 삭제 서비스
 *
 * @place teamjw - JJW
 */

@Service
public class BestPlaceService {

    @Autowired
    BestPlaceRepository bestPlaceRepository;

    @Autowired
    BestPlaceMapperRepository bestPlaceMapperRepository;


    /**
     * 베스트 장소 조회
     * @return
     */
    public List<BestPlace> getBestPlaces() {
        return bestPlaceRepository.findAll();
    }


    public Optional<BestPlace> getBestPlaceById(Long id) {
        if (!bestPlaceRepository.existsById(id)) {
            throw new ResourceNotFoundException("Place with id " + id + " not found");
        }
        return bestPlaceRepository.findById(id);
    }

}
