package com.gdm.school_adm_v2.school_year_teacher_courses_hours;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gdm.school_adm_v2.school_school_year_teacher_courses_hours.SchoolSchoolYearTeacherCoursesHours;
import com.gdm.school_adm_v2.school_year.SchoolYear;
import com.gdm.school_adm_v2.teacher_courses_hours.TeacherCoursesHours;
import com.gdm.school_adm_v2.util.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "sytch")
@Table(name = "sytch")

@NamedNativeQueries({
        @NamedNativeQuery(
                name = "getSYTCHId",
                query = "select " +
                        "    s2.id as id " +
                        "from school_sytch ssytch " +
                        "         inner join school s on s.id = ssytch.id_school and s.id = ?1 " +
                        "         inner join school_sytch_sytch sss on ssytch.id = sss.id_school_sytch " +
                        "         inner join sytch s2 on s2.id = sss.id_sytch " +
                        "         inner join school_year sy on sy.id = s2.id_school_year and sy.year = ?2",
                resultSetMapping = "getSYTCHIdResultSet"
        )
})
@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = "getSYTCHIdResultSet",
                classes = @ConstructorResult(
                        targetClass = SYTCHDTO.class,
                        columns = {
                                @ColumnResult(name = "id", type = Long.class)
                        }
                )
        )
})

@NoArgsConstructor
@Getter
@Setter
@ToString
public class SchoolYearTeacherCoursesHours extends BaseEntity {

    @OneToOne(cascade = { CascadeType.MERGE })
    @JoinColumn(name = "id_school_year", referencedColumnName = "id")
    private SchoolYear schoolYear;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "sytchs_tch",
            joinColumns = @JoinColumn(name = "id_sytch"),
            inverseJoinColumns = @JoinColumn(name = "id_tch"))
    private List<TeacherCoursesHours> teacherCoursesHours = new ArrayList<>();

    @ManyToMany(mappedBy = "schoolYearTeacherCoursesHours")
    @ToString.Exclude
    @JsonIgnore
    private List<SchoolSchoolYearTeacherCoursesHours> schoolSchoolYearTeacherCoursesHours = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SchoolYearTeacherCoursesHours that = (SchoolYearTeacherCoursesHours) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
