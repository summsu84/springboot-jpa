package com.teamjw.tripapp.app.place.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.ToString;

import javax.persistence.*;

/**
 * 
 * 여행지 정보 코멘트 엔티티 클래스
 * DESC : 여행지 정보 코멘트 모델
 * DATE : 2019.01.16
 * 
 * 여행정보 엔티티 클래스, 여행지 정보 이미지 순번, 여행지 이미지 주소
 *
 * @author teamjw - JJW
 */

@Entity
@Table(name = "trip_tbl_place_comment")
@ToString
public class PlaceComment extends BaseEntity {

	@Column(name = "upper_id")
	private Long upperId;

	@Column(name = "message", length=500)
	private String message;

	@Column(name = "recommend")
	private int recommend;

	//like 사용 시 오류로 인해 good으로 변경
	@Column(name = "good")
	private int good;

	@Column(name = "bad")
	private int bad;

	// Place와 양방향 연결, 해당 엔티티의 place_id와 place와 연결 시킨다.
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="place_id", nullable = false)
	private Place place;

	@Column(name = "user_id")
	private Long userId;


	public Long getUpperId() {
		return upperId;
	}

	public void setUpperId(Long upperId) {
		this.upperId = upperId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getRecommend() {
		return recommend;
	}

	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}

	public int getGood() {
		return good;
	}

	public void setGood(int good) {
		this.good = good;
	}

	public int getBad() {
		return bad;
	}

	public void setBad(int bad) {
		this.bad = bad;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@JsonIgnore
	public Place getPlace() {
		return place;
	}

	@JsonIgnore
	public void setPlace(Place place) {
		this.place = place;
	}
}
