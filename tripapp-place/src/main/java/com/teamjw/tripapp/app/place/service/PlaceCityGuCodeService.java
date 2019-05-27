package com.teamjw.tripapp.app.place.service;

import com.teamjw.tripapp.app.place.domain.PlaceCityCode;
import com.teamjw.tripapp.app.place.domain.PlaceCityGuCode;
import com.teamjw.tripapp.app.place.domain.PlaceCountryCode;
import com.teamjw.tripapp.app.place.repository.PlaceCityCodeRepository;
import com.teamjw.tripapp.app.place.repository.PlaceCityGuCodeRepository;
import com.teamjw.tripapp.app.place.repository.PlaceCountryCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 *
 * 여행지 정보 도시 코드 서비스 클래스
 * DESC : 여행지 정보 도시 코드 서비스
 * DATE : 2019.04.17
 *
 * 여행지 전체 조회, ID 기반 조회, 생성, 수정, 삭제 서비스
 *
 * @place teamjw - JJW
 */

@Service
public class PlaceCityGuCodeService {

    @Autowired
    PlaceCityGuCodeRepository placeCityGuCodeRepository;
    @Autowired
    PlaceCityCodeRepository placeCityCodeRepository;



    public Optional<PlaceCityGuCode> getPlaceCountryCode(String countryCode) {
        if (!placeCityGuCodeRepository.existsById(countryCode)) {
            throw new ResourceNotFoundException("PlaceCountryCode with id " + countryCode + " not found");
        }
        return placeCityGuCodeRepository.findById(countryCode);
    }

    public List<PlaceCityGuCode> getPlaceCityGuCode() {
        return placeCityGuCodeRepository.findAll();
    }

    /**
     *  국가코드, 지역 코드가 있는 경우 Gu 코드를 등록한다.
     * @param countrycode
     * @param citycode
     * @param placeCityGuCode
     * @return
     */
    public PlaceCityGuCode createPlaceCityGuCodeByCountryCodeAndCityCode(String countrycode, String citycode, PlaceCityGuCode placeCityGuCode) {
        Optional<PlaceCityCode> byId = placeCityCodeRepository.findById(citycode);
        if (!byId.isPresent()) {
            throw new ResourceNotFoundException("PlaceCountry with id " + countrycode + " does not exist");
        }
        PlaceCityCode placeCityCode = byId.get();

        //tie PlaceCounty to PlaceCity
        placeCityGuCode.setPlaceCityCode(placeCityCode);

        PlaceCityGuCode placeCityGuCode1 = placeCityGuCodeRepository.save(placeCityGuCode);

        return placeCityGuCode1;
    }

    public List<PlaceCityGuCode> getPlaceCityGuCodeByCountryCodeAndCityCode(String countrycode, String citycode) {
        return placeCityGuCodeRepository.findAll();
    }

    public Optional<PlaceCityGuCode> getPlaceCityGuCodeByCountryCodeAndCityCodeAndGuCode(String countrycode, String citycode, String code) {
        return placeCityGuCodeRepository.findById(code);
    }

    public ResponseEntity<Object> deletePlaceCityGuCodeByCityGuCode(String countrycode, String citycode, String code) {
        return null;
    }
}
