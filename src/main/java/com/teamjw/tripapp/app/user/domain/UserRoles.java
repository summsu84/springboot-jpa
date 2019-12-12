package com.teamjw.tripapp.app.user.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.omg.CORBA.ServerRequest;

import java.io.Serializable;

import javax.persistence.*;


/**
 *
 */
@Entity
@Table(name = "trip_tbl_user_roles")
public class UserRoles extends BaseEntity{

    // Role , insertable=false, updatable=false
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    // UserId
    // Place와 양방향 연결
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable = false)
    private User user;
   /* @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id", scope= ServerRequest.class)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)*/


    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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

/*
@Entity
@Table(name = "USER_ROLE")
public class UserRole {
    @Embeddable
    public static class Id implements Serializable {
        private static final long serialVersionUID = 1322120000551624359L;

        @Column(name = "APP_USER_ID")
        protected Long userId;

        @Enumerated(EnumType.STRING)
        @Column(name = "ROLE")
        protected Role role;

        public Id() { }

        public Id(Long userId, Role role) {
            this.userId = userId;
            this.role = role;
        }
    }

    @EmbeddedId
    Id id = new Id();

    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE", insertable=false, updatable=false)
    protected Role role;

    public Role getRole() {
        return role;
    }
}
*/
