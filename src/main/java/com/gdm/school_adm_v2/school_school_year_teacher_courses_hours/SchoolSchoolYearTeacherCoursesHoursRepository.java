package com.gdm.school_adm_v2.school_school_year_teacher_courses_hours;

import com.gdm.school_adm_v2.course_hours.CourseHoursDTO;
import com.gdm.school_adm_v2.teacher_courses_hours.TeacherCoursesHoursDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SchoolSchoolYearTeacherCoursesHoursRepository
    extends CrudRepository<SchoolSchoolYearTeacherCoursesHours, Long> {

    @Query(
            name = "getTaughtCoursesAndHoursOfTeacherInSchoolInSchoolYear",
            nativeQuery = true
    )
    Optional<Iterable<CourseHoursDTO>>
        getTaughtCoursesAndHoursOfTeacherInSchoolInASchoolYear(
                    Long idSchool,
                    Long idSchoolYear,
                    Long idTeacher
    );

    @Query(
            name = "getAllTCHForTeachersInSchoolInYear",
            nativeQuery = true
    )
    Optional<Iterable<TeacherCoursesHoursDTO>> getAllTCHForTeachersInSchoolInYear(
            Long idSchool,
            String schoolYear
    );

    @Query(
            name = "getAllTCHForTeacherInSchoolInYear",
            nativeQuery = true
    )
    Optional<Iterable<TeacherCoursesHoursDTO>> getAllTCHForTeacherInSchoolInYear(
            Long idSchool,
            String schoolYear,
            String cnpTeacher
    );

    @Query("select sy.id from school_year sy where sy.years = ?1")
    Optional<Long> getIdSchoolYear(String schoolYear);
}
