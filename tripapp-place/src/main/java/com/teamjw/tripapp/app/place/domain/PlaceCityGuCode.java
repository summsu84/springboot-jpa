package com.teamjw.tripapp.app.place.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

/**
 * 
 * 장소 도시 구(강남구, 구로구 등) 코드 엔티티 클래스
 * DESC : 장소 도시 구 코드 모델
 * DATE : 2019.04.17
 * 
 * 도시 구 코드, 도시 코드, 구 한글명, 구 영문명
 * 
 * @author teamjw - JJW
 */

@Entity
@Table(name = "trip_tbl_place_city_gu_code")
@ToString
public class PlaceCityGuCode extends BaseDateEntity {

	@Id
	@Column(name = "gu_code", length=5, unique=true, nullable = false)
	@NotEmpty
	private String guCode;

	@Column(name = "gu_korean_name", length=100)
	private String guKoreanName;

	@Column(name = "gu_english_name", length=100)
	private String guEnglishName;

	@ManyToOne(fetch = FetchType.LAZY)
    @NotFound(
            action = NotFoundAction.IGNORE)
	@JoinColumn(name="city_code", nullable = false)
	private PlaceCityCode placeCityCode;

	public String getGuCode() {
		return guCode;
	}

	public void setGuCode(String guCode) {
		this.guCode = guCode;
	}

	public String getGuKoreanName() {
		return guKoreanName;
	}

	public void setGuKoreanName(String guKoreanName) {
		this.guKoreanName = guKoreanName;
	}

	public String getGuEnglishName() {
		return guEnglishName;
	}

	public void setGuEnglishName(String guEnglishName) {
		this.guEnglishName = guEnglishName;
	}

	@JsonIgnore
	public PlaceCityCode getPlaceCityCode() {
		return placeCityCode;
	}

	@JsonIgnore
	public void setPlaceCityCode(PlaceCityCode placeCityCode) {
		this.placeCityCode = placeCityCode;
	}
}
