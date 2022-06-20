package com.gdm.school_adm_v2.teacher;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gdm.school_adm_v2.course_hours.CourseHoursDTO;
import com.gdm.school_adm_v2.teacher_courses_hours.TeacherCoursesHours;
import com.gdm.school_adm_v2.teacher_personal_details.TeacherPersonalDetails;
import com.gdm.school_adm_v2.util.entity.BaseEntity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "teacher")
@Table(name = "teacher")
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "getAllInSchoolInSchoolYear",
                query = "select " +
                            "tpd.first_name as firstName, " +
                            "tpd.last_name as lastName, " +
                            "t.cnp as cnp " +
                        "from school_sytch ssytch " +
                            "inner join school s on s.id = ssytch.id_school and s.id = ?1 " +
                            "inner join school_sytch_sytch sss on ssytch.id = sss.id_school_sytch " +
                            "inner join sytch s2 on s2.id = sss.id_sytch  " +
                            "inner join school_year sy on sy.id = s2.id_school_year and sy.year = ?2 " +
                            "inner join sytchs_tch st on s2.id = st.id_sytch " +
                            "inner join tch t2 on t2.id = st.id_tch " +
                            "inner join teacher t on t.id = t2.id_teacher " +
                            "inner join teacher_personal_details tpd on tpd.id = t.id_teacher_personal_details;",
                resultSetMapping = "getAllInSchoolInSchoolYearResultSet"
        ),
        @NamedNativeQuery(
                name = "getNumberOfTeachers",
                query = "select  " +
                        "    count(distinct t.id) as numberOfTeachers " +
                        "from school_sytch ssytch  " +
                        "         inner join school s on s.id = ssytch.id_school and s.id = ?1  " +
                        "         inner join school_sytch_sytch sss on ssytch.id = sss.id_school_sytch  " +
                        "         inner join sytch s2 on s2.id = sss.id_sytch  " +
                        "         inner join school_year sy on sy.id = s2.id_school_year and sy.year = ?2  " +
                        "         inner join sytchs_tch st on s2.id = st.id_sytch  " +
                        "         inner join tch t2 on t2.id = st.id_tch  " +
                        "         inner join teacher t on t.id = t2.id_teacher  " +
                        "         inner join teacher_personal_details tpd on tpd.id = t.id_teacher_personal_details  " +
                        "         inner join tch_ch tc on t2.id = tc.id_tch  " +
                        "         inner join course_hours ch on ch.id = tc.id_ch  " +
                        "         inner join course c on c.id = ch.id_course;",
                resultSetMapping = "getNumberOfTeachersResultSet"
        ),
        @NamedNativeQuery(
                name = "getNumberOfMaxTaughtHoursInYear",
                query = "select " +
                        "    distinct(tmp.teacher_norm) as teacherNorm, " +
                        "    tmp.teacher_name as teacherName " +
                        "from ( " +
                        "         select " +
                        "             tpd.first_name as teacher_name, " +
                        "             t2.norm_number_of_hours as teacher_norm " +
                        "         from school_sytch ssytch " +
                        "                  inner join school s on s.id = ssytch.id_school and s.id = ?1 " +
                        "                  inner join school_sytch_sytch sss on ssytch.id = sss.id_school_sytch " +
                        "                  inner join sytch s2 on s2.id = sss.id_sytch " +
                        "                  inner join school_year sy on sy.id = s2.id_school_year and sy.year = ?2 " +
                        "                  inner join sytchs_tch st on s2.id = st.id_sytch " +
                        "                  inner join tch t2 on t2.id = st.id_tch " +
                        "                  inner join teacher t on t.id = t2.id_teacher and t.cnp = ?3" +
                        "                  inner join teacher_personal_details tpd on tpd.id = t.id_teacher_personal_details " +
                        "                  inner join tch_ch tc on t2.id = tc.id_tch " +
                        "                  inner join course_hours ch on ch.id = tc.id_ch " +
                        "                  inner join course c on c.id = ch.id_course " +
                        "     ) as tmp;",
                resultSetMapping = "getNumberOfMaxTaughtHoursInYearResultSet"
        ),
        @NamedNativeQuery(
                name = "getNumberOfTaughtCoursesInYear",
                query = "select " +
                        "    tmp.numberOfCourses as numberOfTaughtCoursesOverall " +
                        "from ( " +
                        "         select " +
                        "             count(c.id) as numberOfCourses " +
                        "         from school_sytch ssytch " +
                        "                  inner join school s on s.id = ssytch.id_school " +
                        "                  inner join school_sytch_sytch sss on ssytch.id = sss.id_school_sytch " +
                        "                  inner join sytch s2 on s2.id = sss.id_sytch " +
                        "                  inner join school_year sy on sy.id = s2.id_school_year and sy.year = ?1 " +
                        "                  inner join sytchs_tch st on s2.id = st.id_sytch " +
                        "                  inner join tch t2 on t2.id = st.id_tch " +
                        "                  inner join teacher t on t.id = t2.id_teacher and t.cnp = ?2 " +
                        "                  inner join teacher_personal_details tpd on tpd.id = t.id_teacher_personal_details " +
                        "                  inner join tch_ch tc on t2.id = tc.id_tch " +
                        "                  inner join course_hours ch on ch.id = tc.id_ch " +
                        "                  inner join course c on c.id = ch.id_course " +
                        "     ) as tmp;",
                resultSetMapping = "getNumberOfTaughtCoursesInYearResultSet"
        ),
        @NamedNativeQuery(
                name = "getNumberOfHoursOverall",
                query = "select " +
                        "    sum(ch.hours) as numberOfHours " +
                        "from school_sytch ssytch " +
                        "         inner join school s on s.id = ssytch.id_school " +
                        "         inner join school_sytch_sytch sss on ssytch.id = sss.id_school_sytch " +
                        "         inner join sytch s2 on s2.id = sss.id_sytch " +
                        "         inner join school_year sy on sy.id = s2.id_school_year and sy.year = ?1 " +
                        "         inner join sytchs_tch st on s2.id = st.id_sytch " +
                        "         inner join tch t2 on t2.id = st.id_tch " +
                        "         inner join teacher t on t.id = t2.id_teacher and t.cnp = ?2 " +
                        "         inner join teacher_personal_details tpd on tpd.id = t.id_teacher_personal_details " +
                        "         inner join tch_ch tc on t2.id = tc.id_tch " +
                        "         inner join course_hours ch on ch.id = tc.id_ch " +
                        "         inner join course c on c.id = ch.id_course;",
                resultSetMapping = "getNumberOfHoursOverallResultSet"
        ),
        @NamedNativeQuery(
                name = "getByCNPInSchoolInYear",
                query = "select case when exists( " +
                        "        select " +
                        "            t2.id " +
                        "        from school_sytch ssytch " +
                        "                 inner join school s on s.id = ssytch.id_school and s.id = ?1 " +
                        "                 inner join school_sytch_sytch sss on ssytch.id = sss.id_school_sytch " +
                        "                 inner join sytch s2 on s2.id = sss.id_sytch " +
                        "                 inner join school_year sy on sy.id = s2.id_school_year and sy.year = ?2 " +
                        "                 inner join sytchs_tch st on s2.id = st.id_sytch " +
                        "                 inner join tch t2 on t2.id = st.id_tch " +
                        "                 inner join teacher t on t.id = t2.id_teacher and t.cnp = ?3 " +
                        "    ) " +
                        "then true " +
                        "else false end " +
                        "as exists;",
                resultSetMapping = "getByCNPInSchoolInYearResultSet"
        )
})
@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = "getAllInSchoolInSchoolYearResultSet",
                classes = @ConstructorResult(
                        targetClass = TeacherDTO.class,
                        columns ={
                                @ColumnResult(name = "firstName", type = String.class),
                                @ColumnResult(name = "lastName", type = String.class),
                                @ColumnResult(name = "cnp", type = String.class)
                        }
                )
        ),
        @SqlResultSetMapping(
                name = "getNumberOfTeachersResultSet",
                classes = @ConstructorResult(
                        targetClass = TeacherDTO.class,
                        columns = {
                                @ColumnResult(name = "numberOfTeachers", type = Integer.class)
                        }
                )
        ),
        @SqlResultSetMapping(
                name = "getNumberOfMaxTaughtHoursInYearResultSet",
                classes = @ConstructorResult(
                        targetClass = TeacherDTO.class,
                        columns = {
                                @ColumnResult(name = "teacherNorm", type = Integer.class)
                        }
                )
        ),
        @SqlResultSetMapping(
                name = "getNumberOfTaughtCoursesInYearResultSet",
                classes = @ConstructorResult(
                        targetClass = TeacherDTO.class,
                        columns = {
                                @ColumnResult(name = "numberOfTaughtCoursesOverall", type = Integer.class)
                        }
                )
        ),
        @SqlResultSetMapping(
                name = "getNumberOfHoursOverallResultSet",
                classes = @ConstructorResult(
                        targetClass = TeacherDTO.class,
                        columns = {
                                @ColumnResult(name = "numberOfHours", type = Integer.class)
                        }
                )
        ),
        @SqlResultSetMapping(
                name = "getByCNPInSchoolInYearResultSet",
                classes = @ConstructorResult(
                        targetClass = TeacherDTO.class,
                        columns = {
                                @ColumnResult(name = "exists", type = Boolean.class)
                        }
                )
        )
})

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Teacher extends BaseEntity {

    @Column(
            name = "cnp",
            nullable = false,
            unique = true
    )
    private String cnp;

    @OneToOne(cascade = {
            CascadeType.ALL
    })
    @JoinColumn(name = "id_teacher_personal_details", referencedColumnName = "id")
    private TeacherPersonalDetails teacherPersonalDetails;

    @OneToMany(mappedBy = "teacher")
    @ToString.Exclude
    @JsonIgnore
    private List<TeacherCoursesHours> teacherCoursesHours = new ArrayList<>();

    public Teacher(
            boolean active,
            String cnp,
            TeacherPersonalDetails teacherPersonalDetails
    ) {

        super(active);

        this.cnp = cnp;
        this.teacherPersonalDetails = teacherPersonalDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Teacher teacher = (Teacher) o;
        return getId() != null && Objects.equals(getId(), teacher.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
