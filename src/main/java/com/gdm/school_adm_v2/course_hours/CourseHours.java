package com.gdm.school_adm_v2.course_hours;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gdm.school_adm_v2.course.Course;
import com.gdm.school_adm_v2.teacher_courses_hours.TeacherCoursesHours;
import com.gdm.school_adm_v2.util.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "course_hours")
@Table(name = "course_hours")

@NamedNativeQueries({
        @NamedNativeQuery(
                name = "findCHIdByTeacherCNPAndCourseName",
                query = "select " +
                        "    ch.id as chId " +
                        "from school_sytch ssytch " +
                        "         inner join school s on s.id = ssytch.id_school and s.id = ?1 " +
                        "         inner join school_sytch_sytch sss on ssytch.id = sss.id_school_sytch " +
                        "         inner join sytch s2 on s2.id = sss.id_sytch " +
                        "         inner join school_year sy on sy.id = s2.id_school_year and sy.year = ?2 " +
                        "         inner join sytchs_tch st on s2.id = st.id_sytch " +
                        "         inner join tch t2 on t2.id = st.id_tch " +
                        "         inner join teacher t on t.id = t2.id_teacher and t.cnp = ?3 " +
                        "         inner join teacher_personal_details tpd on tpd.id = t.id_teacher_personal_details " +
                        "         inner join tch_ch tc on t2.id = tc.id_tch " +
                        "         inner join course_hours ch on ch.id = tc.id_ch " +
                        "         inner join course c on c.id = ch.id_course and c.name = ?4",
                resultSetMapping = "findCHIdByTeacherCNPAndCourseNameResultSet"
        )
})
@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = "findCHIdByTeacherCNPAndCourseNameResultSet",
                classes = @ConstructorResult(
                        targetClass = CourseHoursDTO.class,
                        columns = @ColumnResult(name = "chId", type = Integer.class)
                )
        )
})

@NoArgsConstructor
@Getter
@Setter
@ToString
public class CourseHours extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "id_course", referencedColumnName = "id")
    private Course course;

    @Column(name = "hours")
    private Integer hours;

    @ManyToMany(mappedBy = "coursesHours")
    @ToString.Exclude
    @JsonIgnore
    private List<TeacherCoursesHours> teacherCoursesHours = new ArrayList<>();

    public CourseHours(
            boolean active,
            Course course,
            Integer hours) {

        super(active);

        this.course = course;
        this.hours = hours;
    }
}
