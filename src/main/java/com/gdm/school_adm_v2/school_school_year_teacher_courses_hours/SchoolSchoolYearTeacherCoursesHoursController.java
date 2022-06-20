package com.gdm.school_adm_v2.school_school_year_teacher_courses_hours;

import com.gdm.school_adm_v2.teacher_courses_hours.TeacherCoursesHoursDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/s-sytch")
public class SchoolSchoolYearTeacherCoursesHoursController {

    private final SchoolSchoolYearTeacherCoursesHoursService schoolSchoolYearTeacherCoursesHoursService;

    @Autowired
    public SchoolSchoolYearTeacherCoursesHoursController(
            SchoolSchoolYearTeacherCoursesHoursService schoolSchoolYearTeacherCoursesHoursService) {

        this.schoolSchoolYearTeacherCoursesHoursService = schoolSchoolYearTeacherCoursesHoursService;
    }

    @GetMapping("get-all-tch-for-t-in-s-in-sy/{id}/{year}")
    public Iterable<TeacherCoursesHoursDTO> getAllTCHForTeachersInSchoolInYear(
            @PathVariable("id") Long idSchool,
            @PathVariable("year") String schoolYear
    ){

        return schoolSchoolYearTeacherCoursesHoursService
                .getAllTCHForTeachersInSchoolInYear(idSchool, schoolYear);
    }

    @GetMapping("get-all-tch-for-t-in-s-in-sy/{id}/{year}/{cnp}")
    public Iterable<TeacherCoursesHoursDTO> getAllTCHForTeacherInSchoolInYear(
            @PathVariable("id") Long idSchool,
            @PathVariable("year") String schoolYear,
            @PathVariable("cnp") String cnpTeacher
    ){

        return schoolSchoolYearTeacherCoursesHoursService
                .getAllTCHForTeacherInSchoolInYear(idSchool, schoolYear, cnpTeacher);
    }
}
