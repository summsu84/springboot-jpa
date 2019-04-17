package com.teamjw.tripapp.app.user.domain;

import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

/**
 * 
 * 소셜 유저 엔티티 클래스
 * DESC : 소셜 로그인 사용자 정보 모델
 * DATE : 2019.01.16
 * 
 * User모델, 소셜 구분, 리프레쉬토큰
 * 
 * @author teamjw - JJW
 * 
 */

@Entity
@Table (name = "tbl_social_user")
@Getter
@Setter
public class SocialUser extends BaseEntity {

	@Column (name = "social_type")
	private String socialType;

	@Column (name = "refresh_token")
	private String refreshToken;

	@Column (name = "social_user_id")
	private String socialUserId;

	@Column (name = "user_type")
	private String userType;

	@Column(name = "valid_start_date")
	private LocalDate validStartDate;

	@Column(name = "valid_end_date")
	private LocalDate validEndDate;
}
