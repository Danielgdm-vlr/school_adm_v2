package com.gdm.school_adm_v2.course;

import com.gdm.school_adm_v2.course_hours.CourseHoursDTO;
import com.gdm.school_adm_v2.util.entity.BaseEntity;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "course")
@Table(name = "course")

@NamedNativeQueries({
        @NamedNativeQuery(
                name = "getNumberOfTaughtCourses",
                query = "select " +
                        "    count(distinct c.id) as numberOfCourses " +
                        "from school_sytch ssytch " +
                        "         inner join school s on s.id = ssytch.id_school and s.id = ?1 " +
                        "         inner join school_sytch_sytch sss on ssytch.id = sss.id_school_sytch " +
                        "         inner join sytch s2 on s2.id = sss.id_sytch " +
                        "         inner join school_year sy on sy.id = s2.id_school_year and sy.year = ?2 " +
                        "         inner join sytchs_tch st on s2.id = st.id_sytch " +
                        "         inner join tch t2 on t2.id = st.id_tch " +
                        "         inner join teacher t on t.id = t2.id_teacher " +
                        "         inner join teacher_personal_details tpd on tpd.id = t.id_teacher_personal_details " +
                        "         inner join tch_ch tc on t2.id = tc.id_tch " +
                        "         inner join course_hours ch on ch.id = tc.id_ch " +
                        "         inner join course c on c.id = ch.id_course;",
                resultSetMapping = "getNumberOfTaughtCoursesResultSet"
        ),
        @NamedNativeQuery(
                name = "getCourses",
                query = "select " +
                        "    distinct(c.id) as id, " +
                        "    c.name as courseName " +
                        "from school_sytch ssytch " +
                        "         inner join school s on s.id = ssytch.id_school and s.id = ?1 " +
                        "         inner join school_sytch_sytch sss on ssytch.id = sss.id_school_sytch " +
                        "         inner join sytch s2 on s2.id = sss.id_sytch " +
                        "         inner join school_year sy on sy.id = s2.id_school_year and sy.year = ?2 " +
                        "         inner join sytchs_tch st on s2.id = st.id_sytch " +
                        "         inner join tch t2 on t2.id = st.id_tch " +
                        "         inner join tch_ch tc on t2.id = tc.id_tch " +
                        "         inner join course_hours ch on ch.id = tc.id_ch " +
                        "         inner join course c on c.id = ch.id_course; ",
                resultSetMapping = "getCoursesResultSet"
        )
})
@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = "getNumberOfTaughtCoursesResultSet",
                classes = @ConstructorResult(
                        targetClass = CourseHoursDTO.class,
                        columns = {
                                @ColumnResult(name = "numberOfCourses", type = Integer.class)
                        }
                )
        ),
        @SqlResultSetMapping(
                name = "getCoursesResultSet",
                classes = @ConstructorResult(
                        targetClass = CourseDTO.class,
                        columns = {
                                @ColumnResult(name = "id", type = Long.class),
                                @ColumnResult(name = "courseName", type = String.class)
                        }
                )
        )
})

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Course extends BaseEntity {

    @Column(
            name = "name",
            unique = true
    )
    private String name;

    public Course(

            boolean active,
            String name
    ) {

        super(active);

        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Course that = (Course) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
