package com.teamjw.tripapp.app.place.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * 여행지 국가 코드 엔티티 클래스
 * DESC : 여행지 국가 코드 코드 모델
 * DATE : 2019.04.17
 * 
 * 3자리 국가코드, 국가 한글명, 국가 영문명, 비자 사용여부, 정렬 순서
 * 
 * @author teamjw - JJW
 */

@Entity
@Table(name = "trip_tbl_place_country_code")
@Getter
@Setter
@ToString
public class PlaceCountryCode extends BaseDateEntity {

	@Id
	@Column(name = "country_code", length=3, unique=true, nullable = false)
	@NotEmpty
	private String countryCode;

	@Column(name = "country_korean_name", length=100)
	private String countryKoreanName;

	@Column(name = "country_english_name", length=100)
	private String countryEnglishName;

	@Column(name = "sort_order")
	private int sortOrder;

	@Column(name = "visa_use_yn", length = 1)
	private String visaUseYn;

	// 각 베스트 정보와 맵핑 하고자 하는 장소 정보 (이때, 각 장소는 여러개의 베스트 테이블과 연결되고, 베스트 테이블은 한개의 장소와 연결 된다)
/*	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="city_id", nullable = false)
	private PlaceCityCode cityId;*/

	// CityCode와 1:N 관계
	@OneToMany(mappedBy = "placeCountryCode", fetch = FetchType.LAZY)       // Ok
	private Set<PlaceCityCode> placeCityCodes = new HashSet<>();
}
