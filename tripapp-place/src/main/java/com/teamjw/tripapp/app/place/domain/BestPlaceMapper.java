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
 * 베스트 장소 맵핑 엔티티 클래스
 * DESC : 베스트 장소와 장소와의 맵핑 정보 모델
 * DATE : 2019.05.17
 * 
 * 아이디, 베스트 장소 구분, 시작일, 종료일, 순번, 장소 아이디
 * 비고 : 일단 사용 안함
 * @author teamjw - JJW
 */

@Entity
@Table(name = "trip_tbl_best_place_mapper")
@Getter
@Setter
@ToString
public class BestPlaceMapper extends BaseEntity {

	@Column(name = "start_date", length=8, nullable = false)
	@NotEmpty
	private String startDate;

	@Column(name = "end_date", length=8, nullable = false)
	@NotEmpty
	private String endDate;

	@Column(name = "seq", nullable = false)
	@NotEmpty
	private int seq;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="place_id", nullable = false)
	private Place placeId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="best_place_id", nullable = false)
	private BestPlace bestPlaceId;

}
