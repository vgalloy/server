package com.vgalloy.server.dao.model.entity;

import com.vgalloy.server.aspect.security.SecurityLevel;
import com.vgalloy.server.dao.model.Referenceable;
import org.mongojack.Id;
import org.mongojack.ObjectId;

import java.util.Objects;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 02/12/15.
 */
public class User implements Referenceable {
    @Id
    private String username;
    private String password;
    private SecurityLevel role;

    /**
     * Constructor
     */
    public User() {
        // Allow Spring to instanciate User
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getId() {
        return username;
    }

    public void setId(String id) {
        this.password = id;
    }

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

    public SecurityLevel getRole() {
        return role;
    }

    public void setRole(SecurityLevel role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}