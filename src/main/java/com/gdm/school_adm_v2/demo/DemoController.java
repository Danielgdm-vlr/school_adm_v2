package com.gdm.school_adm_v2.demo;

import com.gdm.school_adm_v2.course_hours.CourseHoursDTO;
import com.gdm.school_adm_v2.school_school_year_teacher_courses_hours.SchoolSchoolYearTeacherCoursesHours;
import com.gdm.school_adm_v2.school_school_year_teacher_courses_hours.SchoolSchoolYearTeacherCoursesHoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/demo")
public class DemoController {

    private final SchoolSchoolYearTeacherCoursesHoursService schoolSchoolYearTeacherCoursesHoursService;

    @Autowired
    public DemoController(SchoolSchoolYearTeacherCoursesHoursService schoolSchoolYearTeacherCoursesHoursService) {
        this.schoolSchoolYearTeacherCoursesHoursService = schoolSchoolYearTeacherCoursesHoursService;
    }

    @GetMapping
    public Iterable<SchoolSchoolYearTeacherCoursesHours> getAll(){

        return schoolSchoolYearTeacherCoursesHoursService.getAll();
    }

    @GetMapping("teacher-teached-courses-in-a-school-year-in-a-school/" +
            "{idSchool}/{schoolYear}/{idTeacher}")
    public Iterable<CourseHoursDTO> getTaughtCoursesAndHoursOfATeacherFromASelectedSchoolInASchoolYear(
            @PathVariable("idSchool") Long idSchool,
            @PathVariable("schoolYear") String schoolYear,
            @PathVariable("idTeacher") Long idSchoolTeacher
    ){

        return schoolSchoolYearTeacherCoursesHoursService
                .getTaughtCoursesAndHoursOfTeacherInSchoolInASchoolYear(
                        idSchool, schoolYear, idSchoolTeacher);
    }
}
