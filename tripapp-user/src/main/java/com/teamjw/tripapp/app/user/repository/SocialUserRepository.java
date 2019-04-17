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
package com.teamjw.tripapp.app.user.repository;

import com.teamjw.tripapp.app.user.domain.SocialUser;
import com.teamjw.tripapp.app.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *  Social User Repository
 */
public interface SocialUserRepository extends JpaRepository<SocialUser, Long> {

}
