package com.teamjw.tripapp.app.user.service;

import com.teamjw.tripapp.app.user.domain.User;
import com.teamjw.tripapp.app.user.repository.UserRepository;
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
public class UserService {

    @Autowired
    UserRepository userRepository;
    /**
     *  사용자 조회
     * @return
     */
    public List<User> getUsers() {
        return userRepository.findAll();
    }


    public Optional<User> getUserById(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("Place with id " + userId + " not found");
        }
        return userRepository.findById(userId);
    }


    public User createUser(User place) {
        return userRepository.save(place);

    }

    public User updateUserById(Long userId, User userRequest) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("Place with id " + userId + " not found");
        }
        Optional<User> user = userRepository.findById(userId);

        if (!user.isPresent()) {
            throw new ResourceNotFoundException("Place with id " + userId + " not found");
        }

        User user1 = user.get();
/*        place1.setFirstName(placeRequest.getFirstName());
        place1.setLastName(placeRequest.getLastName());*/
        return userRepository.save(user1);

    }

    public ResponseEntity<Object> deleteUserById(long userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("Place with id " + userId + " not found");
        }

        userRepository.deleteById(userId);

        return ResponseEntity.ok().build();

    }
    public Optional<User> getByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }
}
