package com.teamjw.tripapp.app.place.service;

import com.teamjw.tripapp.app.place.domain.Place;
import com.teamjw.tripapp.app.place.domain.PlaceImage;
import com.teamjw.tripapp.app.place.repository.PlaceImageRepository;
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
 * 여행지 이미지 정보 서비스 클래스
 * DESC : 여행지 이미지 정보 모델
 * DATE : 2019.04.17
 *
 * 여행지 전체 조회, ID 기반 조회, 생성, 수정, 삭제 서비스
 *
 * @place teamjw - JJW
 */

@Service
public class PlaceImageService {

    @Autowired
    PlaceImageRepository placeImageRepository;
    @Autowired
    PlaceRepository placeRepository;

    /**
     *  장소 조회
     * @return
     */
    public List<PlaceImage> getPlaces() {
        return placeImageRepository.findAll();
    }


    public Optional<PlaceImage> getPlaceById(Long placeId) {
        if (!placeImageRepository.existsById(placeId)) {
            throw new ResourceNotFoundException("Place with id " + placeId + " not found");
        }
        return placeImageRepository.findById(placeId);
    }


    public PlaceImage createPlaceImage(PlaceImage placeImage) {
        return placeImageRepository.save(placeImage);

    }

    public PlaceImage updatePlaceById(Long placeImageId, PlaceImage placeRequest) {
        if (!placeImageRepository.existsById(placeImageId)) {
            throw new ResourceNotFoundException("placeImageId with id " + placeImageId + " not found");
        }
        Optional<PlaceImage> placeImage = placeImageRepository.findById(placeImageId);

        if (!placeImage.isPresent()) {
            throw new ResourceNotFoundException("Place with id " + placeImageId + " not found");
        }

        PlaceImage placeImage1 = placeImage.get();
/*        place1.setFirstName(placeRequest.getFirstName());
        place1.setLastName(placeRequest.getLastName());*/
        return placeImageRepository.save(placeImage1);

    }

    public ResponseEntity<Object> deletePlaceById(long placeId) {
        if (!placeImageRepository.existsById(placeId)) {
            throw new ResourceNotFoundException("Place with id " + placeId + " not found");
        }

        placeImageRepository.deleteById(placeId);

        return ResponseEntity.ok().build();

    }

    /**
     *  Place ID를 기반으로 Place Image 정보를 저장한다.
     * @param placeId
     * @param placeImage
     * @return
     */
    public PlaceImage createPlaceImage(Long placeId, PlaceImage placeImage) {
        Set<PlaceImage> placeImages = new HashSet<>();
        Place place1 = new Place();

        Optional<Place> byId = placeRepository.findById(placeId);
        if (!byId.isPresent()) {
            throw new ResourceNotFoundException("placeId with id " + placeId + " does not exist");
        }
        Place place = byId.get();

        //tie Author to Book
        placeImage.setPlace(place);

        PlaceImage placeImage1 = placeImageRepository.save(placeImage);
        //tie Book to Author
        placeImages.add(placeImage1);
        //place1.set(books);

        return placeImage1;
    }
}
