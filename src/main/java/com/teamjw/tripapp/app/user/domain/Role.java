package com.teamjw.tripapp.app.user.domain;


/**
 *
 */
public enum Role {
    ADMIN, MEMBER, GUIDER;
    
    public String authority() {
        return "ROLE_" + this.name();
    }
}
