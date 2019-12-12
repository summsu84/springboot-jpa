package com.teamjw.tripapp.app.user.service;

import com.teamjw.tripapp.app.user.domain.UserAuthToken;
import com.teamjw.tripapp.app.user.domain.UserRoles;
import com.teamjw.tripapp.app.user.repository.UserAuthTokenRepository;
import com.teamjw.tripapp.app.user.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * 사용자 정보 서비스 클래스
 * DESC : 사용자 정보 모델
 * DATE : 2019.04.17
 *
 * 여행지 전체 조회, ID 기반 조회, 생성, 수정, 삭제 서비스
 *
 * @place teamjw - JJW
 */

@Service
public class UserAuthTokenService {

    @Autowired
    UserAuthTokenRepository userAuthTokenRepository;
    /**
     *  롤 조회
     * @return
     */
    public List<UserAuthToken> getUserRoles() {
        return userAuthTokenRepository.findAll();
    }


    public UserAuthToken createUserRoles(UserAuthToken userAuthToken) {
        return userAuthTokenRepository.save(userAuthToken);

    }

}
