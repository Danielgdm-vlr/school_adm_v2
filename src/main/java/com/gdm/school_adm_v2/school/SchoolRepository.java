package com.gdm.school_adm_v2.school;

import com.gdm.school_adm_v2.course.Course;
import com.gdm.school_adm_v2.course.CourseDTO;
import com.gdm.school_adm_v2.course_hours.CourseHoursDTO;
import com.gdm.school_adm_v2.school_details.SchoolDetails;
import com.gdm.school_adm_v2.teacher.Teacher;
import com.gdm.school_adm_v2.teacher.TeacherDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SchoolRepository extends CrudRepository<School, Long> {

    Optional<School> getById(Long id);

    @Query(
            name = "getNumberOfTaughtCourses",
            nativeQuery = true
    )
    Optional<CourseHoursDTO> getNumberOfCourses(Long idSchool, String schoolYear);

    @Query(
            name = "getNumberOfTeachers",
            nativeQuery = true
    )
    Optional<TeacherDTO> getNumberOfTeachers(Long idSchool, String schoolYear);

    @Query("select sd from school_details sd " +
                "inner join school s on s.schoolDetails = sd and s.id = ?1")
    Optional<SchoolDetails> findSchoolDetailsBySchoolId(Long id);

    @Query(
            name = "getCourses",
            nativeQuery = true
    )
    Iterable<CourseDTO> getCourses(Long id, String schoolYear);
}
