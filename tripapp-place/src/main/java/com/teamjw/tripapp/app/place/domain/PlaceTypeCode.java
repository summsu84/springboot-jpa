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
 * 여행지 장소 구분 코드 엔티티 클래스
 * DESC : 여행지 장소 구분 코드 모델
 * DATE : 2019.04.17
 * 
 * 테마코드, 테마 이름, 사용여부, 삭제여부
 * 
 * @author teamjw - JJW
 */

@Entity
@Table(name = "trip_tbl_place_type_code")
@Getter
@Setter
@ToString
public class PlaceTypeCode extends BaseEntity {

/*	@Id
	@Column(name = "type_code", length=8, unique=true, nullable = false)
	@NotEmpty
	private String placeCode;*/

	@Column(name = "type_korean_name", length = 100)
	private String placeCodeKoreanName;

	@Column(name = "type_english_name", length = 100)
	private String placeCodeEnglishName;

	// Place 1:N 관계
/*	@OneToMany(mappedBy = "placeType", fetch = FetchType.LAZY)       // Ok
	private Set<Place> places = new HashSet<>();*/
}
