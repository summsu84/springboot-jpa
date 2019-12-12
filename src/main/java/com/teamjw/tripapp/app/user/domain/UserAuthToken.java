package com.teamjw.tripapp.app.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 
 * 사용자 토큰 관리 엔티티
 * DESC : 사용자 토큰 관리 정보 모델
 * DATE : 2019.05.22
 * 
 * Key, UserModel, UseYn, DelYn, 생성일자, 수정일자
 * 
 * @author teamjw - JJW
 * 
 */

@Entity
@Table (name = "trip_tbl_user_auth_token")

public class UserAuthToken implements Serializable {

	@Id
	private String token;

	@Column(name = "use_yn", length = 1)
	private String useYn = "Y";

	@Column(name = "del_yn", length = 1)
	private String delYn = "N";

	@CreationTimestamp
	private LocalDateTime createdTime;

	@UpdateTimestamp
	private LocalDateTime updatedTime;

/*	@OneToOne
	@MapsId
	private User user;*/
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;


	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	public String getDelYn() {
		return delYn;
	}

	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}

	public LocalDateTime getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(LocalDateTime createdTime) {
		this.createdTime = createdTime;
	}

	public LocalDateTime getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(LocalDateTime updatedTime) {
		this.updatedTime = updatedTime;
	}

	@JsonIgnore
	public User getUser() {
		return user;
	}

	@JsonIgnore
	public void setUser(User user) {
		this.user = user;
	}
}
