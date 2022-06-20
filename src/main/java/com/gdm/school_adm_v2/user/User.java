package com.gdm.school_adm_v2.user;

import com.gdm.school_adm_v2.util.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "user_table")
@Table(name = "user_table")

@NoArgsConstructor
@Getter
@Setter
@ToString
public class User extends BaseEntity {

    @Column(
            name = "username",
            unique = true,
            nullable = false)
    private String username;

    @Column(
            name = "password",
            nullable = false
    )
    private String password;

    @Column(
            name = "role",
            nullable = false
    )
    private String role;

    public User(
            boolean active,
            String username,
            String password,
            String role) {

        super(active);

        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
