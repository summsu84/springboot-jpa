package com.teamjw.tripapp.app.user.domain;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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


    // 일반 멤버이면서, 가이드가 될 수 있다.
/*    @OneToMany
    @JoinColumn(name="APP_USER_ID", referencedColumnName="ID")
    private List<UserRoles> roles;*/
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)       // Ok
    private Set<UserRoles> roles = new HashSet<>();

    //Token 연결
/*    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserAuthToken userAuthToken;*/
    @OneToOne(mappedBy = "user")
    private UserAuthToken userAuthToken;

    /*@OneToMany(mappedBy = "place", fetch = FetchType.LAZY)       // Ok
    private Set<UserRoles> roles = new HashSet<>();*/

    public User() { }

    public User(Long id, String username, String password, Set<UserRoles> roles) {
        super(id);
        this.name = username;
        this.password = password;
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public LocalDate getValidStartDate() {
        return validStartDate;
    }

    public void setValidStartDate(LocalDate validStartDate) {
        this.validStartDate = validStartDate;
    }

    public LocalDate getValidEndDate() {
        return validEndDate;
    }

    public void setValidEndDate(LocalDate validEndDate) {
        this.validEndDate = validEndDate;
    }
    public Set<UserRoles> getRoles() {
        return roles;
    }

    public UserAuthToken getUserAuthToken() {
        return userAuthToken;
    }

    public void setUserAuthToken(UserAuthToken userAuthToken) {
        this.userAuthToken = userAuthToken;
    }
}
