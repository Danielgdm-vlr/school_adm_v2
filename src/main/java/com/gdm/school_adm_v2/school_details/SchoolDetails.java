package com.gdm.school_adm_v2.school_details;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gdm.school_adm_v2.school.School;
import com.gdm.school_adm_v2.util.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity(name = "school_details")
@Table(name = "school_details")

@NoArgsConstructor
@Getter
@Setter
@ToString
public class SchoolDetails extends BaseEntity {

    @Column(
            name = "name",
            nullable = false
    )
    private String name;

    @Column(
            name = "telephoneNumber",
            nullable = false,
            unique = true
    )
    private String telephoneNumber;

    @Column(
            name = "emailAddress",
            nullable = false,
            unique = true
    )
    private String emailAddress;

    @OneToOne(mappedBy = "schoolDetails")
    @ToString.Exclude
    @JsonIgnore
    private School school;

    public SchoolDetails(
            boolean active,
            String name,
            String telephoneNumber,
            String emailAddress) {

        super(active);

        this.name = name;
        this.telephoneNumber = telephoneNumber;
        this.emailAddress = emailAddress;
    }
}
