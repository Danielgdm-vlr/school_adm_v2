package com.gdm.school_adm_v2.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/courses")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("in-school/{id}/{schoolYear}")
    public Iterable<CourseDTO> getCoursesInSchoolYear(
            @PathVariable("id") Long id,
            @PathVariable("schoolYear") String schoolYear
    ){

        return courseService.getCoursesInSchoolYear(id, schoolYear);
    }

    @GetMapping("{name}")
    public Course getById(@PathVariable("name") String name){

        return courseService.getByName(name);
    }
}
