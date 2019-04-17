package com.teamjw.tripapp.app.place.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * 
 * 여행지 정보 이미지 엔티티 클래스
 * DESC : 여행지 정보 이미지 모델
 * DATE : 2019.01.16
 * 
 * 여행정보 엔티티 클래스, 여행지 정보 이미지 순번, 여행지 이미지 주소
 * 
 * @author teamjw - JJW
 */

@Entity
@Table(name = "trip_tbl_place_image")
@ToString
public class PlaceImage extends BaseEntity {

	@Column(name = "seq")
	private int seq;

	@Column(name = "url")
	private String url;

	// Place와 양방향 연결
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="place_id", nullable = false)
	private Place place;

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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
