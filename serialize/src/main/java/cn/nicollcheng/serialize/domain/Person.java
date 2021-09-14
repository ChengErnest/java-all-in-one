package cn.nicollcheng.serialize.domain;

import java.io.Serializable;

/**
 * @author nicollcheng
 * <b>Creation Time:</b> 2021/9/14 10:47.
 */
public class Person implements Serializable {
    private static final long serialVersionUID = -2970127818883604239L;
    private String name;
    private long id;
    private String email;

    public String getName() {
        return name;
    }

    public Person setName(String name) {
        this.name = name;
        return this;
    }

    public long getId() {
        return id;
    }

    public Person setId(long id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Person setEmail(String email) {
        this.email = email;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", email='" + email + '\'' +
                '}';
    }
}
