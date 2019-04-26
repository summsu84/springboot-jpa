package com.teamjw.tripapp.app.place.service;

import com.teamjw.tripapp.app.place.domain.Place;
import com.teamjw.tripapp.app.place.domain.PlaceComment;
import com.teamjw.tripapp.app.place.repository.PlaceCommentRepository;
import com.teamjw.tripapp.app.place.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 *
 * 여행지 정보 코멘트 서비스 클래스
 * DESC : 여행지 정보 코멘트 모델
 * DATE : 2019.04.17
 *
 * 여행지 전체 조회, ID 기반 조회, 생성, 수정, 삭제 서비스
 *
 * @place teamjw - JJW
 */

@Service
public class PlaceCommentService {

    @Autowired
    PlaceCommentRepository placeCommentRepository;


    /**
     *  장소 조회
     * @return
     */
    public List<PlaceComment> getPlaces() {
        return placeCommentRepository.findAll();
    }


    public Optional<PlaceComment> getPlaceCommentById(Long placeId) {
        if (!placeCommentRepository.existsById(placeId)) {
            throw new ResourceNotFoundException("Place with id " + placeId + " not found");
        }
        return placeCommentRepository.findById(placeId);
    }


    public PlaceComment createPlaceComment(PlaceComment place) {
        return placeCommentRepository.save(place);

    }

    public PlaceComment updatePlaceCommentById(Long placeId, PlaceComment placeRequest) {
        if (!placeCommentRepository.existsById(placeId)) {
            throw new ResourceNotFoundException("Place with id " + placeId + " not found");
        }
        Optional<PlaceComment> place = placeCommentRepository.findById(placeId);

        if (!place.isPresent()) {
            throw new ResourceNotFoundException("Place with id " + placeId + " not found");
        }

        PlaceComment place1 = place.get();
/*        place1.setFirstName(placeRequest.getFirstName());
        place1.setLastName(placeRequest.getLastName());*/
        return placeCommentRepository.save(place1);

    }

    public ResponseEntity<Object> deletePlaceCommentById(long placeId) {
        if (!placeCommentRepository.existsById(placeId)) {
            throw new ResourceNotFoundException("Place with id " + placeId + " not found");
        }

        placeCommentRepository.deleteById(placeId);

        return ResponseEntity.ok().build();

    }
}
