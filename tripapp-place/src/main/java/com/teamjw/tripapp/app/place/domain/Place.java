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

	@Column(name = "place_cd", length=8, unique=true, nullable = false)
	@NotEmpty
	private String placeCd;

	@Column(name = "place_theme", length=8, unique=true, nullable = false)
	private String placeTheme;

	@Column(name = "place_type", length=8, unique=true, nullable = false)
	private String placeType;

	@Column(name = "place_korean_name", length = 255, nullable = false)
	@NotEmpty
	private String placeKoreanName;

	@Column(name = "place_english_name", length=255)
	private String placeEnglishName;

	@Column(name = "place_korean_description", length = 1000)
	private String placeKoreanDescription;

	@Column(name = "place_english_description", length = 1000)
	private String placeEnglishDescription;

	@Column(name = "place_address", length = 500)
	private String placeAddress;

	@Column(name = "place_phone", length = 20)
	private String placePhone;

	@Column(name = "place_homepage", length = 1000)
	private String placeHomepage;

	@Column(name = "place_lat", length=30)
	private String placeLat;

	@Column(name = "place_lon", length = 30)
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
