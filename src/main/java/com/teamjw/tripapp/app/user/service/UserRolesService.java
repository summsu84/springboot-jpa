package com.teamjw.tripapp.app.user.service;

import com.teamjw.tripapp.app.user.domain.UserRoles;
import com.teamjw.tripapp.app.user.repository.UserRepository;
import com.teamjw.tripapp.app.user.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
public class UserRolesService {

    @Autowired
    UserRoleRepository userRoleRepository;
    /**
     *  롤 조회
     * @return
     */
    public List<UserRoles> getUserRoles() {
        return userRoleRepository.findAll();
    }


    public UserRoles createUserRoles(UserRoles userRole) {
        return userRoleRepository.save(userRole);

    }

}
