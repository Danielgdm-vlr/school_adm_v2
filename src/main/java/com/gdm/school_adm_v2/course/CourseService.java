package com.gdm.school_adm_v2.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course saveOrUpdate(Course course){

        return courseRepository.save(course);
    }

    public Iterable<Course> saveOrUpdateAll(Iterable<Course> courses){

        return courseRepository.saveAll(courses);
    }

    public Course getByName(String name){

        return courseRepository.getByName(name).
                orElseThrow(() -> new IllegalStateException(String.format(
                        "Course with name %s not found", name
                )));
    }

    public Iterable<Course> getAll(){

        return courseRepository.findAll();
    }

    public Iterable<CourseDTO> getCoursesInSchoolYear(Long id, String schoolYear) {

        return courseRepository.getCoursesInSchoolYear(id, schoolYear)
                .orElseThrow(() -> new IllegalStateException(String.format(
                        "Courses in " +
                                "school year %1$s and in " +
                                "school with id %2$s " +
                                "not found", schoolYear, id
                )));
    }
}
