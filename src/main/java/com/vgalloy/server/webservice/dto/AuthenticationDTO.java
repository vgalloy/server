package com.vgalloy.server.webservice.dto;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 06/01/16.
 */
public class AuthenticationDTO {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
