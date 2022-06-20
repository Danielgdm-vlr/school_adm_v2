package com.gdm.school_adm_v2.county;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gdm.school_adm_v2.city.City;
import com.gdm.school_adm_v2.user.User;
import com.gdm.school_adm_v2.util.entity.BaseEntity;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity(name = "county")
@Table(name = "county")

@NoArgsConstructor
@Getter
@Setter
@ToString
public class County extends BaseEntity {

    @Column(
            name = "code",
            unique = true,
            nullable = false
    )
    private String code;

    @Column(
            name = "name",
            unique = true,
            nullable = false
    )
    private String name;

    @Column(
            name = "name_without_diacritics",
            unique = true,
            nullable = false
    )
    private String nameWithoutDiacritics;

    @OneToMany(mappedBy="county")
    @ToString.Exclude
    @JsonIgnore
    private List<City> cities;

    @OneToOne(cascade = {
            CascadeType.MERGE,
            CascadeType.PERSIST
    })
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    @JsonIgnore
    private User user;

    public County(boolean active, String code, String name, String nameWithoutDiacritics) {
        super(active);
        this.code = code;
        this.name = name;
        this.nameWithoutDiacritics = nameWithoutDiacritics;
    }

    public County(
            boolean active,
            String code,
            String name,
            String nameWithoutDiacritics,
            User user
    ) {

        super(active);

        this.code = code;
        this.name = name;
        this.nameWithoutDiacritics = nameWithoutDiacritics;
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        County county = (County) o;
        return getId() != null && Objects.equals(getId(), county.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

