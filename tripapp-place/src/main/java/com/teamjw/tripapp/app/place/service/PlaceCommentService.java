package com.teamjw.tripapp.app.place.service;

import com.teamjw.tripapp.app.place.domain.Place;
import com.teamjw.tripapp.app.place.domain.PlaceComment;
import com.teamjw.tripapp.app.place.repository.PlaceCommentRepository;
import com.teamjw.tripapp.app.place.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
    @Autowired
    PlaceRepository placeRepository;

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

    public PlaceComment createPlaceCommentById(Long placeId, PlaceComment placeComment) {
        Set<PlaceComment> placeComments = new HashSet<>();
        Place place1 = new Place();

        Optional<Place> byId = placeRepository.findById(placeId);
        if (!byId.isPresent()) {
            throw new ResourceNotFoundException("placeId with id " + placeId + " does not exist");
        }
        Place place = byId.get();

        //tie Author to Book
        placeComment.setPlace(place);

        PlaceComment placeComment1 = placeCommentRepository.save(placeComment);
        //tie Book to Author
        placeComments.add(placeComment1);
        //place1.set(books);

        return placeComment1;
    }

    //사용자의 아이디에 따라서 커멘트를 가져온다.
    public List<PlaceComment> getPlaceCommentByUserId(Long userId) {
        return placeCommentRepository.findByUserId(userId);
    }
}
