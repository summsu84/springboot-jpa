package com.teamjw.tripapp.app.place.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

/**
 * 
 * 여행지 장소 구분 코드 엔티티 클래스
 * DESC : 여행지 장소 구분 코드 모델
 * DATE : 2019.04.17
 * 
 * 테마코드, 테마 이름, 사용여부, 삭제여부
 * 
 * @author teamjw - JJW
 */

@Entity
@Table(name = "trip_tbl_place_code")
@Getter
@Setter
@ToString
public class PlaceCode extends BaseDateEntity {

	@Id
	@Column(name = "place_code")
	@NotEmpty
	private String placeCode;

	@Column(name = "place_code_korean_name")
	private String placeCodeKoreanName;

	@Column(name = "place_code_english_name")
	private String placeCodeEnglishName;
}
