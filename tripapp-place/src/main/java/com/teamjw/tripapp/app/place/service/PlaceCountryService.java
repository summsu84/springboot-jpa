package com.teamjw.tripapp.app.place.service;

import com.teamjw.tripapp.app.place.domain.PlaceCountryCode;
import com.teamjw.tripapp.app.place.domain.PlaceThemeCode;
import com.teamjw.tripapp.app.place.repository.PlaceCountryCodeRepository;
import com.teamjw.tripapp.app.place.repository.PlaceThemeCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 *
 * 여행지 정보 지역 코드 서비스 클래스
 * DESC : 여행지 정보 모델
 * DATE : 2019.04.17
 *
 * 여행지 전체 조회, ID 기반 조회, 생성, 수정, 삭제 서비스
 *
 * @place teamjw - JJW
 */

@Service
public class PlaceCountryService {

    @Autowired
    PlaceCountryCodeRepository placeCountryCodeRepository;



    public Optional<PlaceCountryCode> getPlaceCountryCode(String countryCode) {
        if (!placeCountryCodeRepository.existsById(countryCode)) {
            throw new ResourceNotFoundException("PlaceCountryCode with id " + countryCode + " not found");
        }
        return placeCountryCodeRepository.findById(countryCode);
    }
}
