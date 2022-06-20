package com.gdm.school_adm_v2.course_hours;

import lombok.Data;

@Data
public class CourseHoursDTO {

    private String courseName;

    private String teacherName;
    private Integer numberOfCourseHours;

    private Integer numberOfCourses;

    public CourseHoursDTO(Integer numberOfCourses) {
        this.numberOfCourses = numberOfCourses;
    }

    public CourseHoursDTO(String courseName, Integer numberOfCourseHours) {

        this.courseName = courseName;
        this.numberOfCourseHours = numberOfCourseHours;
    }

    public CourseHoursDTO(String courseName, String teacherName, Integer numberOfCourseHours) {
        this.courseName = courseName;
        this.teacherName = teacherName;
        this.numberOfCourseHours = numberOfCourseHours;
    }
}
