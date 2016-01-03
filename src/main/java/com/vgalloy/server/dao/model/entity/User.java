package com.vgalloy.server.dao.model.entity;

import com.vgalloy.server.aspect.security.SecurityLevel;
import com.vgalloy.server.dao.model.Referenceable;
import org.mongojack.Id;

import javax.persistence.Transient;
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
        super();
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, SecurityLevel role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    @Override
    public String getId() {
        return username;
    }

    @Override
    public void setId(String id) {
        this.username = id;
    }

    public String getUsername() {
        return username;
    }

    @Transient
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
                ", role=" + role +
                '}';
    }

}
