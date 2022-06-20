package com.gdm.school_adm_v2.teacher_courses_hours;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gdm.school_adm_v2.course_hours.CourseHours;
import com.gdm.school_adm_v2.school_year_teacher_courses_hours.SchoolYearTeacherCoursesHours;
import com.gdm.school_adm_v2.teacher.Teacher;
import com.gdm.school_adm_v2.util.entity.BaseEntity;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "tch")
@Table(name = "tch")

@NamedNativeQueries({
        @NamedNativeQuery(
                name = "getTCHId",
                query = "select " +
                        "    t2.id as id " +
                        "from school_sytch ssytch " +
                        "         inner join school s on s.id = ssytch.id_school and s.id = ?1 " +
                        "         inner join school_sytch_sytch sss on ssytch.id = sss.id_school_sytch " +
                        "         inner join sytch s2 on s2.id = sss.id_sytch " +
                        "         inner join school_year sy on sy.id = s2.id_school_year and sy.year = ?2 " +
                        "         inner join sytchs_tch st on s2.id = st.id_sytch " +
                        "         inner join tch t2 on t2.id = st.id_tch " +
                        "         inner join teacher t on t.id = t2.id_teacher and t.cnp = ?3",
                resultSetMapping = "getTCHIdResultSet"
        )
})
@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = "getTCHIdResultSet",
                classes = @ConstructorResult(
                        targetClass = TeacherCoursesHoursDTO.class,
                        columns = @ColumnResult(name = "id", type = Long.class)
                )
        )
})

@NoArgsConstructor
@Getter
@Setter
@ToString
public class TeacherCoursesHours extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "id_teacher")
    private Teacher teacher;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(name = "tch_ch",
            joinColumns = @JoinColumn(name = "id_tch"),
            inverseJoinColumns = @JoinColumn(name = "id_ch"))
    private List<CourseHours> coursesHours = new ArrayList<>();

    @Column(
            name = "norm_number_of_hours",
            nullable = false
    )
    private Integer normNumberOfHours;

    @ManyToMany(mappedBy = "teacherCoursesHours")
    @ToString.Exclude
    @JsonIgnore
    private List<SchoolYearTeacherCoursesHours> schoolYearTeacherCoursesHours = new ArrayList<>();

    public TeacherCoursesHours(
            boolean active,
            Teacher teacher,
            Integer normNumberOfHours,
            List<CourseHours> coursesHours
    ) {

        super(active);

        this.teacher = teacher;
        this.normNumberOfHours = normNumberOfHours;
        this.coursesHours = coursesHours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TeacherCoursesHours that = (TeacherCoursesHours) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
