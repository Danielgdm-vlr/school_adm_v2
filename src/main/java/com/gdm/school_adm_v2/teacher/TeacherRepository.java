package com.gdm.school_adm_v2.teacher;

import com.gdm.school_adm_v2.course.Course;
import com.gdm.school_adm_v2.course_hours.CourseHoursDTO;
import com.gdm.school_adm_v2.school.School;
import com.gdm.school_adm_v2.school.SchoolDTO;
import com.gdm.school_adm_v2.school_year.SchoolYear;
import com.gdm.school_adm_v2.school_year_teacher_courses_hours.SYTCHDTO;
import com.gdm.school_adm_v2.school_year_teacher_courses_hours.SchoolYearTeacherCoursesHours;
import com.gdm.school_adm_v2.teacher_courses_hours.TeacherCoursesHours;
import com.gdm.school_adm_v2.teacher_courses_hours.TeacherCoursesHoursDTO;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherRepository extends CrudRepository<Teacher, Long> {

    Optional<Teacher> findByCnp(String cnp);

    @Query(
            name = "getByCNPInSchoolInYear",
            nativeQuery = true
    )
    Optional<TeacherDTO> getByCnp(Long id, String schoolYear, String cnp);

    @Query(
            name = "getAllInSchoolInSchoolYear",
            nativeQuery = true
    )
    Optional<Iterable<TeacherDTO>> getAllInSchoolInYear(
            Long idSchool, String schoolYear
    );

    @Query(
            name = "getTaughtCoursesAndHoursOfTeacherInSchoolInSchoolYear",
            nativeQuery = true
    )
    Optional<Iterable<CourseHoursDTO>>
        getTaughtCoursesAndHoursOfATeacherFromASelectedSchoolInASchoolYear(
            Long idSchool,
            Long idSchoolYear,
            Long idTeacher
    );

    @Query("select sy from school_year sy where sy.years = ?1")
    Optional<SchoolYear> getIdSchoolYearByYears(String years);

    @Query(
            name = "getAllCHForTeachersInSchoolInYear",
            nativeQuery = true
    )
    Optional<Iterable<CourseHoursDTO>> getAllCHForTeachersInSchoolInYear(Long idSchool, String schoolYear);

    @Query(
            name = "getNumberOfMaxTaughtHoursInYear",
            nativeQuery = true
    )
    Optional<TeacherDTO> getNumberOfMaxTaughtHoursInYear(Long idSchool, String schoolYear, String cnp);

    @Query(
            name = "getNumberOfTaughtCoursesInYear",
            nativeQuery = true
    )
    Optional<TeacherDTO> getNumberOfTaughtCoursesInYear(String schoolYear, String cnp);

    @Query(
            name = "getNumberOfHoursOverall",
            nativeQuery = true
    )
    Optional<TeacherDTO> getNumberOfHoursOverall(String schoolYear, String cnp);

    @Query(
            name = "getSchoolsWhereTeaches",
            nativeQuery = true
    )
    Optional<List<SchoolDTO>> getSchoolsWhereTeaches(String schoolYear, String cnp);

    @Query("select s from school s where s.id = ?1")
    Optional<School> getSchoolById(Long id);

    @Query(
            name = "findCHIdByTeacherCNPAndCourseName",
            nativeQuery = true
    )
    Optional<CourseHoursDTO> findCHIdByTeacherCNPAndCourseName(
            Long idSchool,
            String schoolYear,
            String cnp,
            String courseName
    );

    @Modifying
    @Query("delete from course_hours ch where ch.id = ?1")
    void deleteCHById(Long idCh);

    @Modifying
    @Query("update course_hours ch set ch.hours = ?2 where ch.id = ?1")
    void updateCHById(Long idCh, Integer numberOfHours);

    @Modifying
    @Query("update teacher_personal_details tpd set " +
            "tpd.firstName = ?2, " +
            "tpd.lastName = ?3 " +
            "where tpd.id = ?1")
    void updateTPDByCNP(Long idTpd, String firstName, String lastName);

    @Query("select tpd.id from teacher_personal_details tpd where tpd.teacher.cnp = ?1")
    Optional<Long> getTPDIdByTeacherCNP(String cnp);

    @Modifying
    @Query("delete from tch t2 where t2.id = ?1")
    void deleteTFromSchool(Long idTCH);

    @Query(
            name = "getTCHId",
            nativeQuery = true
    )
    Optional<TeacherCoursesHoursDTO> getTCHId(Long schoolId, String schoolYear, String cnp);

    @Query("select c from course c where c.name = ?1")
    Optional<Course> getCourse(String courseName);

    @Query(
            name = "getSYTCHId",
            nativeQuery = true
    )
    Optional<SYTCHDTO> getSYTCHDTO(Long idSchool, String schoolYear);


    @Query("select s from sytch s where s.id = ?1")
    Optional<SchoolYearTeacherCoursesHours> getSYTCH(Long id);

}
