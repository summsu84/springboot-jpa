package com.teamjw.tripapp.app.user.domain;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

/**
 *
 * 유저정보 엔티티 클래스
 * DESC : 사용자 정보 모델
 * DATE : 2019.01.16
 *
 * 사용자 ID, 비밀번호, 전화번호, 아바타사진, 커버사진, 소개, 사용자 구분(1.일반, 2.가이드), 성별(1.남자, 2.여자),
 * Firebase 토큰, 디바이스 기기 ID 정보, 로그인 접근방법 정보, 엑세스 토큰, 정보 공개 범위
 *
 * @author teamjw - JJW
 *
 */


@Entity
@Table(name = "trip_tbl_user")
@Getter
@Setter
@ToString
public class User extends BaseEntity {

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @Column(name = "cover_url")
    private String coverUrl;

    @Column(name = "intro")
    private String intro;

    @Column(name = "user_type")
    private String userType;

    @Column(name = "gender")
    private String gender;

    //private String fcmToken;
    @Column(name = "device_id")
    private String deviceId;

    @Column(name = "access_token")
    private String accessToken;

    @Column(name = "valid_start_date")
    private LocalDate validStartDate;

    @Column(name = "valid_end_date")
    private LocalDate validEndDate;

}
