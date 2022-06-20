package com.gdm.school_adm_v2.course;

import java.util.List;

public class CourseConfig {

    public static void createAndSaveCourse(CourseService courseService){

        courseService.saveOrUpdateAll(List.of(
                new Course(
                        true,
                        "Matematica"),
                new Course(
                        true,
                        "Romana"
                ),
                new Course(
                        true,
                        "Fizica"
                ),
                new Course(
                        true,
                        "Informatica"
                ),
                new Course(
                        true,
                        "Engleza"
                ),
                new Course(
                        true,
                        "Chimie"
                ),
                new Course(
                        true,
                        "Educatie fizica"
                ),
                new Course(
                        true,
                        "Desen"
                ),
                new Course(
                        true,
                        "Germana"
                ),
                new Course(
                        true,
                        "TIC"
                ),
                new Course(
                        true,
                        "Economie"
                ),
                new Course(
                        true,
                        "Istorie"
                ),
                new Course(
                        true,
                        "Geografie"
                ),
                new Course(
                        true,
                        "Biologie"
                ),
                new Course(
                        true,
                        "Sociologie"
                ),
                new Course(
                        true,
                        "Educatie civica"
                ),
                new Course(
                        true,
                        "Religie"
                )
        ));
    }
}
