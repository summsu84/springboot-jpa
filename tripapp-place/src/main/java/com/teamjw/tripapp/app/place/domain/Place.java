package com.teamjw.tripapp.app.place.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.*;

/**
 * 
 * 여행지 정보 엔티티 클래스
 * DESC : 여행지 정보 모델
 * DATE : 2019.01.16
 * 
 * 여행 장소 아이디, 여행장소 구분, 여행장소 한글 이름, 여행장소 영문 이름, 여행장소 한글 설명, 여행장소 영문 설명
 * 여행장소 한글주소, 여행장소 영문주소, 여행장소 전화번호, 여행장소 홈페이지, 여행장소 좌표, 조회수
 * 
 * @author teamjw - JJW
 */

@Entity
@Table(name = "trip_tbl_place")
@Getter
@Setter
@ToString
public class Place extends BaseEntity {

	@Column(name = "place_cd")
	@NotEmpty
	private String placeCd;

	@Column(name = "place_theme")
	private String placeTheme;

	@Column(name = "place_type")
	private String placeType;

	@Column(name = "place_korean_name")
	@NotEmpty
	private String placeKoreanName;

	@Column(name = "place_english_name")
	private String placeEnglishName;

	@Column(name = "place_korean_description")
	private String placeKoreanDescription;

	@Column(name = "place_english_description")
	private String placeEnglishDescription;

	@Column(name = "place_address")
	private String placeAddress;

	@Column(name = "place_phone")
	private String placePhone;

	@Column(name = "place_homepage")
	private String placeHomepage;

	@Column(name = "place_lat")
	private String placeLat;

	@Column(name = "place_lon")
	private String placeLon;

	@Column(name = "place_inqury")
	private int placeInqury;

	@Column(name = "place_like")
	private int placeLike;

	// 이미지 1:N 관계
	//@OneToMany(cascade = CascadeType.ALL, mappedBy = "place")
	// mappedBy는 관계되는 엔티티의 필드명을 사용
	@OneToMany(mappedBy = "place", fetch = FetchType.LAZY)       // Ok
	private Set<PlaceImage> placeImages = new HashSet<>();

	// 코멘트 1:N 관계
	@OneToMany(mappedBy = "place", fetch = FetchType.LAZY)       // Ok
	private Set<PlaceComment> placeComments = new HashSet<>();

}
