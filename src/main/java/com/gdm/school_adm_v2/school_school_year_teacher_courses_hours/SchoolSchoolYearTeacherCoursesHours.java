package com.gdm.school_adm_v2.school_school_year_teacher_courses_hours;

import com.gdm.school_adm_v2.course_hours.CourseHoursDTO;
import com.gdm.school_adm_v2.school.School;
import com.gdm.school_adm_v2.school_year_teacher_courses_hours.SchoolYearTeacherCoursesHours;
import com.gdm.school_adm_v2.teacher_courses_hours.TeacherCoursesHoursDTO;
import com.gdm.school_adm_v2.util.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "school_sytch")
@Table(name = "school_sytch")
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "getTaughtCoursesAndHoursOfTeacherInSchoolInSchoolYear",
                query = "select " +
                        "    c.name as courseName, " +
                        "    ch.hours as numberOfCourseHours " +
                        "from school_sytch ssytch " +
                        "    inner join school_sytch_sytch sss " +
                        "        on ssytch.id = sss.id_school_sytch " +
                        "        and ssytch.id_school = ?1 " +
                        "    inner join sytch " +
                        "        on sytch.id = sss.id_sytch " +
                        "        and sytch.id_school_year = ?2 " +
                        "    inner join sytchs_tch sytchs on sytchs.id_sytch = sytch.id " +
                        "    inner join tch tch on tch.id = sytchs.id_tch " +
                        "    inner join teacher t " +
                        "        on tch.id_teacher = t.id " +
                        "        and t.id = ?3 " +
                        "    inner join tch_ch tc on tch.id = tc.id_tch " +
                        "    inner join course_hours ch on ch.id = tc.id_ch " +
                        "    inner join course c on c.id = ch.id_course;",
                resultSetMapping = "getTaughtCoursesAndHoursOfTeacherInSchoolInSchoolYearResultSet"
        ),
        @NamedNativeQuery(
                name = "getAllTCHForTeachersInSchoolInYear",
                query = "select " +
                        "    tpd.first_name as firstName, " +
                        "    tpd.last_name as lastName, " +
                        "    t.cnp as cnp, " +
                        "    c.name as courseName, " +
                        "    ch.hours as courseHours " +
                        "from school_sytch ssytch " +
                        "   inner join school s on s.id = ssytch.id_school " +
                        "       and s.id = ?1 " +
                        "   inner join school_sytch_sytch sss on ssytch.id = sss.id_school_sytch " +
                        "   inner join sytch s2 on s2.id = sss.id_sytch " +
                        "   inner join school_year sy on sy.id = s2.id_school_year " +
                        "       and sy.year = ?2" +
                        "   inner join sytchs_tch st on s2.id = st.id_sytch " +
                        "   inner join tch t2 on t2.id = st.id_tch " +
                        "   inner join teacher t on t.id = t2.id_teacher " +
                        "   inner join teacher_personal_details tpd on tpd.id = t.id_teacher_personal_details " +
                        "   inner join tch_ch tc on t2.id = tc.id_tch " +
                        "   inner join course_hours ch on ch.id = tc.id_ch " +
                        "   inner join course c on c.id = ch.id_course;",
                resultSetMapping = "getAllTCHForTeachersInSchoolInYearResultSet"
        ),
        @NamedNativeQuery(
                name = "getAllTCHForTeacherInSchoolInYear",
                query = "select " +
                        "    tpd.first_name as firstName, " +
                        "    tpd.last_name as lastName, " +
                        "    t.cnp as cnp, " +
                        "    c.name as courseName, " +
                        "    ch.hours as courseHours " +
                        "from school_sytch ssytch " +
                        "   inner join school s on s.id = ssytch.id_school and s.id = ?1 " +
                        "   inner join school_sytch_sytch sss on ssytch.id = sss.id_school_sytch " +
                        "   inner join sytch s2 on s2.id = sss.id_sytch " +
                        "   inner join school_year sy on sy.id = s2.id_school_year and sy.year = ?2 " +
                        "   inner join sytchs_tch st on s2.id = st.id_sytch " +
                        "   inner join tch t2 on t2.id = st.id_tch " +
                        "   inner join teacher t on t.id = t2.id_teacher and t.cnp = ?3 " +
                        "   inner join teacher_personal_details tpd on tpd.id = t.id_teacher_personal_details " +
                        "   inner join tch_ch tc on t2.id = tc.id_tch " +
                        "   inner join course_hours ch on ch.id = tc.id_ch " +
                        "   inner join course c on c.id = ch.id_course;",
                resultSetMapping = "getAllTCHForTeacherInSchoolInYearResultSet"
        ),
        @NamedNativeQuery(
                name = "getAllCHForTeachersInSchoolInYear",
                query = "select " +
                        "   c.name as courseName, " +
                        "   concat(tpd.first_name, ' ', tpd.last_name) as teacherName, " +
                        "   ch.hours as hoursToTeach " +
                        "from school_sytch ssytch " +
                        "   inner join school s on s.id = ssytch.id_school and s.id = ?1 " +
                        "   inner join school_sytch_sytch sss on ssytch.id = sss.id_school_sytch " +
                        "   inner join sytch s2 on s2.id = sss.id_sytch " +
                        "   inner join school_year sy on sy.id = s2.id_school_year and sy.year = ?2 " +
                        "   inner join sytchs_tch st on s2.id = st.id_sytch " +
                        "   inner join tch t2 on t2.id = st.id_tch " +
                        "   inner join teacher t on t.id = t2.id_teacher " +
                        "   inner join teacher_personal_details tpd on tpd.id = t.id_teacher_personal_details " +
                        "   inner join tch_ch tc on t2.id = tc.id_tch " +
                        "   inner join course_hours ch on ch.id = tc.id_ch " +
                        "   inner join course c on c.id = ch.id_course;",
                resultSetMapping = "getAllCHForTeachersInSchoolInYearResultSet"
        )
})
@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = "getTaughtCoursesAndHoursOfTeacherInSchoolInSchoolYearResultSet",
                classes = @ConstructorResult(
                        targetClass = CourseHoursDTO.class,
                        columns ={
                                @ColumnResult(name = "courseName", type = String.class),
                                @ColumnResult(name = "numberOfCourseHours", type = Integer.class)
                        }
                )
        ),
        @SqlResultSetMapping(
                name = "getAllTCHForTeachersInSchoolInYearResultSet",
                classes = @ConstructorResult(
                        targetClass = TeacherCoursesHoursDTO.class,
                        columns ={
                                @ColumnResult(name = "firstName", type = String.class),
                                @ColumnResult(name = "lastName", type = String.class),
                                @ColumnResult(name = "cnp", type = String.class),
                                @ColumnResult(name = "courseName", type = String.class),
                                @ColumnResult(name = "courseHours", type = int.class)
                        }
                )
        ),
        @SqlResultSetMapping(
                name = "getAllTCHForTeacherInSchoolInYearResultSet",
                classes = @ConstructorResult(
                        targetClass = TeacherCoursesHoursDTO.class,
                        columns ={
                                @ColumnResult(name = "firstName", type = String.class),
                                @ColumnResult(name = "lastName", type = String.class),
                                @ColumnResult(name = "cnp", type = String.class),
                                @ColumnResult(name = "courseName", type = String.class),
                                @ColumnResult(name = "courseHours", type = int.class)
                        }
                )
        ),
        @SqlResultSetMapping(
                name = "getAllCHForTeachersInSchoolInYearResultSet",
                classes = @ConstructorResult(
                        targetClass = CourseHoursDTO.class,
                        columns ={
                                @ColumnResult(name = "courseName", type = String.class),
                                @ColumnResult(name = "teacherName", type = String.class),
                                @ColumnResult(name = "hoursToTeach", type = Integer.class)
                        }
                )
        )
})

@NoArgsConstructor
@Getter
@Setter
@ToString
public class SchoolSchoolYearTeacherCoursesHours extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "id_school")
    private School school;

    @ManyToMany(cascade = {
            CascadeType.PERSIST, CascadeType.MERGE
    })
    @JoinTable(name = "school_sytch_sytch",
            joinColumns = @JoinColumn(name = "id_school_sytch"),
            inverseJoinColumns = @JoinColumn(name = "id_sytch"))
    private List<SchoolYearTeacherCoursesHours> schoolYearTeacherCoursesHours = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SchoolSchoolYearTeacherCoursesHours that = (SchoolSchoolYearTeacherCoursesHours) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
