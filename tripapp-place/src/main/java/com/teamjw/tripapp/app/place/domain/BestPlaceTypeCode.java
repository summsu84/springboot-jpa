package com.teamjw.tripapp.app.place.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 
 * 베스트 장소 구분 코드 엔티티 클래스
 * DESC : 여행지 장소 구분 코드 모델
 * DATE : 2019.05.17
 * 
 * 아이디, 구분명, 구분영문명
 * 
 * @author teamjw - JJW
 */

@Entity
@Table(name = "trip_tbl_best_place_type_code")
@Getter
@Setter
@ToString
public class BestPlaceTypeCode extends BaseEntity {

	@Column(name = "type_korean_name", length = 100)
	private String placeCodeKoreanName;

	@Column(name = "type_english_name", length = 100)
	private String placeCodeEnglishName;


	// BestPlace 1:N 관계
/*	@OneToMany(mappedBy = "placeType", fetch = FetchType.LAZY)       // Ok
	private Set<BestPlace> places = new HashSet<>();*/
}
