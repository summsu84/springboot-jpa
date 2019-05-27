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

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
public class PlaceCityCodeService {

    PlaceCountryCodeRepository placeCountryCodeRepository;
    PlaceCityCodeRepository placeCityCodeRepository;
    PlaceCityGuCodeRepository placeCityGuCodeRepository;

    //생성자 Autowired
    @Autowired
    PlaceCityCodeService(PlaceCountryCodeRepository placeCountryCodeRepository, PlaceCityCodeRepository placeCityCodeRepository,  PlaceCityGuCodeRepository placeCityGuCodeRepository)
    {
        this.placeCityCodeRepository = placeCityCodeRepository;
        this.placeCountryCodeRepository = placeCountryCodeRepository;
        this.placeCityGuCodeRepository = placeCityGuCodeRepository;
    }


    /**
     *  CityCode 리스트 전체를 조회 한다.
     * @return
     */
    public List<PlaceCityCode> getPlaceCityCodeList() {
        return placeCityCodeRepository.findAll();
    }

    /**
     *  코드에 따라 조회
     * @param countryCode
     * @return
     */
    public Optional<PlaceCityCode> getPlacCityCodeByCode(String countryCode) {
        if (!placeCityCodeRepository.existsById(countryCode)) {
            throw new ResourceNotFoundException("PlaceCountryCode with id " + countryCode + " not found");
        }
        return placeCityCodeRepository.findById(countryCode);
    }


    /**
     *  CityCode를 등록 한다.
     * @param placeCityCode
     * @return
     */
    public PlaceCityCode createPlaceCityCode(PlaceCityCode placeCityCode) {
        return placeCityCodeRepository.save(placeCityCode);

    }

    /**
     *  CountryCode에 따라 City코드를 등록한다.
     * @param countrycode
     * @param placeCityCode
     * @return
     */
    public PlaceCityCode createPlaceCityCodeByCountryCode(String countrycode, PlaceCityCode placeCityCode) {


        Optional<PlaceCountryCode> byId = placeCountryCodeRepository.findById(countrycode);
        if (!byId.isPresent()) {
            throw new ResourceNotFoundException("PlaceCountry with id " + countrycode + " does not exist");
        }

        PlaceCountryCode countryCode = byId.get();

        //tie PlaceCounty to PlaceCity
        placeCityCode.setPlaceCountryCode(countryCode);

        PlaceCityCode placeCityCode1 = null;
        //Gu 코드가 같이 들어온 경우
        if(placeCityCode.getPlaceCityGuCodes().size() > 0){
            Set<PlaceCityGuCode> placeCityGuCodes = placeCityCode.getPlaceCityGuCodes();
            placeCityCode.setPlaceCityGuCodes(null);

            placeCityCode1 = placeCityCodeRepository.save(placeCityCode);

            PlaceCityGuCode placeCityGuCode = placeCityGuCodes.iterator().next();
            placeCityGuCode.setPlaceCityCode(placeCityCode1);

            PlaceCityGuCode placeCityGuCode2 = placeCityGuCodeRepository.save(placeCityGuCode);

            placeCityCode1.setPlaceCityGuCodes(placeCityGuCodes);

        }else
        {
            placeCityCode1 = placeCityCodeRepository.save(placeCityCode);

        }
        return placeCityCode1;
    }



    public ResponseEntity<Object> deletePlaceCodeByCode(String code) {
        if (!placeCityCodeRepository.existsById(code)) {
            throw new ResourceNotFoundException("PlaceCityCode with id " + code + " not found");
        }

        placeCityCodeRepository.deleteById(code);

        return ResponseEntity.ok().build();
    }
}
