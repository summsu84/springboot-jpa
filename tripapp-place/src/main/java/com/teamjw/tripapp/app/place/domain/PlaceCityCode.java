package com.teamjw.tripapp.app.place.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * 장소 도시 코드 엔티티 클래스
 * DESC : 장소 도시 코드 모델
 * DATE : 2019.04.17
 * 
 * 테마코드, 테마 이름, 사용여부, 삭제여부
 * 
 * @author teamjw - JJW
 */

@Entity
@Table(name = "trip_tbl_place_city_code")
@ToString
public class PlaceCityCode extends BaseDateEntity {

	@Id
	@Column(name = "city_code", length=2, unique=true, nullable = false)
	@NotEmpty
	private String cityCode;

	@Column(name = "city_korean_name", length=100)
	private String cityKoreanName;

	@Column(name = "city_english_name", length=100)
	private String cityEnglishName;


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="country_code", nullable = false)
	private PlaceCountryCode placeCountryCode;


	// CityGuiCode와 1:N 관계
	@OneToMany(mappedBy = "placeCityCode", fetch = FetchType.LAZY)       // Ok
	private Set<PlaceCityGuCode> placeCityGuCodes = new HashSet<>();

	// Place 1:N 관계
/*	@OneToMany(mappedBy = "placeCountry", fetch = FetchType.LAZY)       // Ok
	private Set<Place> places = new HashSet<>();*/

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCityKoreanName() {
		return cityKoreanName;
	}

	public void setCityKoreanName(String cityKoreanName) {
		this.cityKoreanName = cityKoreanName;
	}

	public String getCityEnglishName() {
		return cityEnglishName;
	}

	public void setCityEnglishName(String cityEnglishName) {
		this.cityEnglishName = cityEnglishName;
	}

	@JsonIgnore
	public PlaceCountryCode getPlaceCountryCode() {
		return placeCountryCode;
	}

	@JsonIgnore
	public void setPlaceCountryCode(PlaceCountryCode placeCountryCode) {
		this.placeCountryCode = placeCountryCode;
	}


	public Set<PlaceCityGuCode> getPlaceCityGuCodes() {
		return placeCityGuCodes;
	}


	public void setPlaceCityGuCodes(Set<PlaceCityGuCode> placeCityGuCodes) {
		this.placeCityGuCodes = placeCityGuCodes;
	}
}
