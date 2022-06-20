package com.gdm.school_adm_v2.teacher_personal_details;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gdm.school_adm_v2.teacher.Teacher;
import com.gdm.school_adm_v2.util.entity.BaseEntity;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Objects;

@Entity(name = "teacher_personal_details")
@Table(name = "teacher_personal_details")

@NoArgsConstructor
@Getter
@Setter
@ToString
public class TeacherPersonalDetails extends BaseEntity {

    @Column(
            name = "first_name",
            nullable = false
    )
    private String firstName;

    @Column(
            name = "last_name",
            nullable = false
    )
    private String lastName;

    @Column(
            name = "date_of_birth",
            nullable = false
    )
    private LocalDate dateOfBirth;

    @OneToOne(mappedBy = "teacherPersonalDetails")
    @ToString.Exclude
    @JsonIgnore
    private Teacher teacher;

    public TeacherPersonalDetails(
            boolean active,
            String firstName,
            String lastName,
            LocalDate dateOfBirth
    ) {

        super(active);

        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TeacherPersonalDetails that = (TeacherPersonalDetails) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
