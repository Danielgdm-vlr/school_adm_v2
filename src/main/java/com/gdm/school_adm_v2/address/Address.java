package com.gdm.school_adm_v2.address;

import com.gdm.school_adm_v2.city.City;
import com.gdm.school_adm_v2.util.entity.BaseEntity;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "address")
@Table(name = "address")

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Address extends BaseEntity {

    @Column(
            name = "street",
            nullable = false
    )
    private String street;

    @Column(
            name = "number",
            nullable = false
    )
    private String number;

    @Column(
            name = "zipCode",
            nullable = false,
            unique = true
    )
    private Integer zipCode;

    @ManyToOne
    @JoinColumn(name="id_city")
    private City city;

    public Address(
            boolean active,
            String street,
            String number,
            Integer zipCode,
            City city
    ) {

        super(active);

        this.street = street;
        this.number = number;
        this.zipCode = zipCode;
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Address address = (Address) o;
        return getId() != null && Objects.equals(getId(), address.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
