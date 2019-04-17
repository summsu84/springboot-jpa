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
@Table(name = "trip_tbl_theme_code")
@Getter
@Setter
@ToString
public class PlaceThemeCode extends BaseDateEntity {

	@Id
	@Column(name = "theme_code")
	@NotEmpty
	private String themeCode;

	@Column(name = "theme_korean_name")
	private String themeKoreanName;

	@Column(name = "theme_english_name")
	private String themeEnglishName;
}
