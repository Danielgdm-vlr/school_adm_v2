package com.gdm.school_adm_v2.city;

import com.gdm.school_adm_v2.county.County;
import com.gdm.school_adm_v2.util.entity.BaseEntity;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "city")
@Table(name = "city")

@NoArgsConstructor
@Getter
@Setter
@ToString
public class City extends BaseEntity {

    @Column(
            name = "name",
            nullable = false
    )
    private String name;

    @Column(
            name = "name_without_diacritics",
            nullable = false
    )
    private String nameWithoutDiacritics;

    @Column(
            name = "environment",
            nullable = false
    )
    private String environment;

    @ManyToOne
    @JoinColumn(name="id_county")
    private County county;

    public City(
            boolean active,
            String name,
            String nameWithoutDiacritics,
            String environment,
            County county) {

        super(active);

        this.name = name;
        this.nameWithoutDiacritics = nameWithoutDiacritics;
        this.environment = environment;
        this.county = county;
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
