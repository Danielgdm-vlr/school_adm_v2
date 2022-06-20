package com.gdm.school_adm_v2.school_year;

import com.gdm.school_adm_v2.util.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity(name = "school_year")
@Table(name = "school_year")

@NoArgsConstructor
@Getter
@Setter
@ToString
public class SchoolYear extends BaseEntity {

    @Column(
            name = "year",
            nullable = false
    )
    private String years;

    public SchoolYear(
            boolean active,
            String years) {

        super(active);

        this.years = years;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SchoolYear that = (SchoolYear) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
