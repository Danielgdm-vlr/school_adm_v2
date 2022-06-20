package com.gdm.school_adm_v2.course;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {

    Optional<Course> getByName(String name);

    @Query(
            name = "getCourses",
            nativeQuery = true
    )
    Optional<Iterable<CourseDTO>> getCoursesInSchoolYear(Long id, String schoolYear);
}

