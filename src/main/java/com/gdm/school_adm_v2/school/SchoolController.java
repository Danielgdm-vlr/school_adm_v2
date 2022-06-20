package com.gdm.school_adm_v2.school;

import com.gdm.school_adm_v2.course.Course;
import com.gdm.school_adm_v2.course.CourseDTO;
import com.gdm.school_adm_v2.course_hours.CourseHoursDTO;
import com.gdm.school_adm_v2.teacher.TeacherDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/schools")
public class SchoolController {

    private final SchoolService schoolService;

    @Autowired
    public SchoolController(SchoolService schoolService) {

        this.schoolService = schoolService;
    }

    @GetMapping("number-of-courses/{idSchool}/{schoolYear}")
    public CourseHoursDTO getNumberOfCourses(
            @PathVariable("idSchool") Long idSchool,
            @PathVariable("schoolYear") String schoolYear
    ){

        return schoolService.getNumberOfCourses(idSchool, schoolYear);
    }

    @GetMapping("number-of-teachers/{idSchool}/{schoolYear}")
    public TeacherDTO getNumberOfTeachers(
            @PathVariable("idSchool") Long idSchool,
            @PathVariable("schoolYear") String schoolYear
    ){

        return schoolService.getNumberOfTeachers(idSchool, schoolYear);
    }

    @PatchMapping
    public Boolean update(@RequestBody SchoolDTO schoolDTO){

        return schoolService.savedOrUpdated(schoolDTO);
    }

    @GetMapping("id/{id}")
    public School getById(
            @PathVariable("id") Long id
    ){

        return schoolService.getById(id);
    }

    @GetMapping("courses/{id}/{schoolYear}")
    public Iterable<CourseDTO> getCourses(
            @PathVariable("id") Long id,
            @PathVariable("schoolYear") String schoolYear
    ){

        return schoolService.getCourses(id, schoolYear);
    }
}
