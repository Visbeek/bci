package com.bci.user.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class User {

    @Id
    private String id;
    private String name;
    private String email;
    private String password;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_USER", nullable = false, updatable = false)
    private List<Phone> phones;

    @CreationTimestamp
    private Date created;

    @UpdateTimestamp
    private Date modified;

    private Date lastLogin;
    private String token;
    private Boolean isActive = true;

}
