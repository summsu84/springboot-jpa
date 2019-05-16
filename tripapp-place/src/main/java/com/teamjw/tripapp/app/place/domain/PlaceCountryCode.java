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
 * 여행지 테마 코드 엔티티 클래스
 * DESC : 여행지 테마 코드 모델
 * DATE : 2019.04.17
 * 
 * 테마코드, 테마 이름, 사용여부, 삭제여부
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
	@Column(name = "country_code", length=2, unique=true, nullable = false)
	@NotEmpty
	private String countryCode;

	@Column(name = "country_korean_name", length=100)
	private String countryKoreanName;

	@Column(name = "country_english_name", length=100)
	private String countryEnglishName;

	// Place 1:N 관계
/*	@OneToMany(mappedBy = "placeCountry", fetch = FetchType.LAZY)       // Ok
	private Set<Place> places = new HashSet<>();*/
}
