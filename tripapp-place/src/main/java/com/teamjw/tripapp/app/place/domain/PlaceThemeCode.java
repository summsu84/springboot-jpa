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
@Table(name = "trip_tbl_place_theme_code")
@Getter
@Setter
@ToString
public class PlaceThemeCode extends BaseEntity {

	@Column(name = "theme_korean_name", length=100)
	private String themeKoreanName;

	@Column(name = "theme_english_name", length=100)
	private String themeEnglishName;

	// Place 1:N 관계
/*	@OneToMany(mappedBy = "placeTheme", fetch = FetchType.LAZY)       // Ok
	private Set<Place> places = new HashSet<>();*/
}
