package com.vgalloy.server.webservice.dto;

import com.vgalloy.server.aspect.security.SecurityLevel;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 21/12/15.
 */
public class UserDto {
    private String password;
    private SecurityLevel role;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public SecurityLevel getRole() {
        return role;
    }

    public void setRole(SecurityLevel role) {
        this.role = role;
    }
}
