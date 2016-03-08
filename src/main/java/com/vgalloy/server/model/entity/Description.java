package com.vgalloy.server.model.entity;

import com.vgalloy.server.model.Referable;
import org.mongojack.Id;

import javax.persistence.Transient;
import java.util.Objects;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 07/03/16.
 */
public class Description implements Referable {

    @Id
    private String name;
    private String text;

    /**
     * Default constructor.
     */
    public Description() {
        super();
    }

    /**
     * Constructor with arguments.
     *
     * @param name The name of the character
     * @param text The description of the character
     */
    public Description(String name, String text) {
        this.name = name;
        this.text = text;
    }

    public String getName() {
        return name;
    }

    @Transient
    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String getId() {
        return name;
    }

    @Override
    public void setId(String id) {
        this.name = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Description that = (Description) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, text);
    }

    @Override
    public String toString() {
        return "Description{" +
                "name='" + name + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
