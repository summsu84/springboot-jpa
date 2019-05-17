package com.teamjw.tripapp.app.place.service;

import com.teamjw.tripapp.app.place.domain.BestPlace;
import com.teamjw.tripapp.app.place.domain.BestPlaceMapper;
import com.teamjw.tripapp.app.place.repository.BestPlaceMapperRepository;
import com.teamjw.tripapp.app.place.repository.BestPlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 *
 * 베스트 여행지 정보 맵퍼 서비스 클래스
 * DESC : 여행지 정보 맵퍼 서비스
 * DATE : 2019.05.17
 *
 *
 * @place teamjw - JJW
 */

@Service
public class BestPlaceMapperService {

    @Autowired
    BestPlaceMapperRepository bestPlaceMapperRepository;


    /**
     * 베스트 장소 조회
     * @return
     */
    public List<BestPlaceMapper> getBestPlaces() {
        return bestPlaceMapperRepository.findAll();
    }


    public Optional<BestPlaceMapper> getBestPlaceById(Long id) {
        if (!bestPlaceMapperRepository.existsById(id)) {
            throw new ResourceNotFoundException("Place with id " + id + " not found");
        }
        return bestPlaceMapperRepository.findById(id);
    }

}
