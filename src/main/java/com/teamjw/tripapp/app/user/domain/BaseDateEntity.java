/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.teamjw.tripapp.app.user.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 베이스 s 엔티티 클래스
 * DESC : ID, 생성일자, 생성자, 수정일자, 수정자 정보
 * DATE : 2019.01.16
 *
 * 아이디(PK), 생성일자, 생성자, 수정일자, 수정자
 *
 * @author teamjw - JJW
 */
@MappedSuperclass
@Getter
@Setter
public class BaseDateEntity implements Serializable {

    @CreationTimestamp
    private LocalDateTime createdTime;

    @Column(name = "create_user_name")
    private String createUserName;

    @UpdateTimestamp
    private LocalDateTime updatedTime;

    @Column(name = "update_user_name")
    private String updateUserName;

    @Column(name = "use_yn")
    private String useYn;

    @Column(name = "del_yn")
    private String delYn;

}
