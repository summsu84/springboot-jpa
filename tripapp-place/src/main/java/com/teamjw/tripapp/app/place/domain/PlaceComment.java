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
	private int upperId;

	@Column(name = "message")
	private String message;

	@Column(name = "recommend")
	private int recommend;

	@Column(name = "like")
	private int like;

	@Column(name = "dislike")
	private int dislike;

	// Place와 양방향 연결, 해당 엔티티의 place_id와 place와 연결 시킨다.
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="place_id", nullable = false)
	private Place place;


	public int getUpperId() {
		return upperId;
	}

	public void setUpperId(int upperId) {
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

	public int getLike() {
		return like;
	}

	public void setLike(int like) {
		this.like = like;
	}

	public int getDislike() {
		return dislike;
	}

	public void setDislike(int dislike) {
		this.dislike = dislike;
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
