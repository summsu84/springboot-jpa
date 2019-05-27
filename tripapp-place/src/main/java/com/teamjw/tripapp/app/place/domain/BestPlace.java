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
 * 베스트 장소 정보 엔티티 클래스
 * DESC : 베스트 장소 정보 모델
 * DATE : 2019.05.17
 * 
 * 아이디, 베스트 장소 구분, 시작일, 종료일, 순번, 장소 아이디
 * 
 * @author teamjw - JJW
 */

@Entity
@Table(name = "trip_tbl_best_place")
@Getter
@Setter
@ToString
public class BestPlace extends BaseEntity {

	// 베스트 타입 코드 N : 1 관계
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="best_place_type_id", nullable = false)
	private BestPlaceTypeCode bestPlaceTypeCode;

	// 베스트 현시 시작 기간
	@Column(name = "start_date", length=8, nullable = false)
	@NotEmpty
	private String startDate;

	// 베스트 현시 종료 기간
	@Column(name = "end_date", length=8, nullable = false)
	@NotEmpty
	private String endDate;

	// 시퀀스
	@Column(name = "seq", nullable = false)
	@NotEmpty
	private int seq;

	@Column(name = "sort_order", nullable = false)
	@NotEmpty
	private int sortOrder;


	// 각 베스트 정보와 맵핑 하고자 하는 장소 정보 (이때, 각 장소는 여러개의 베스트 테이블과 연결되고, 베스트 테이블은 한개의 장소와 연결 된다)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="place_id", nullable = false)
	private Place placeId;

	/*@OneToMany(mappedBy = "bestPlaceId")
	Set<BestPlaceMapper> bestPlaceMappers;*/


	public void addBestPlaceType(BestPlaceTypeCode bestPlaceTypeCode)
	{
		this.bestPlaceTypeCode = bestPlaceTypeCode;
	}
}
