package com.gdm.school_adm_v2.teacher_courses_hours;

import com.gdm.school_adm_v2.course_hours.CourseHoursDTO;
import com.gdm.school_adm_v2.teacher.TeacherDTO;
import lombok.Data;

import java.util.List;

@Data
public class TeacherCoursesHoursDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String cnp;
    private String courseName;
    private int courseHours;

    private TeacherDTO teacher;
    private Integer norm;
    private List<CourseHoursDTO> coursesHours;
    private Long idSchool;
    private String schoolYear;


    public TeacherCoursesHoursDTO(String firstName, String lastName, String cnp, String courseName, int courseHours) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.cnp = cnp;
        this.courseName = courseName;
        this.courseHours = courseHours;
    }

    public TeacherCoursesHoursDTO(Long id) {
        this.id = id;
    }

    public TeacherCoursesHoursDTO(TeacherDTO teacher, List<CourseHoursDTO> coursesHours, Long idSchool, String schoolYear, Integer norm) {
        this.teacher = teacher;
        this.coursesHours = coursesHours;
        this.idSchool = idSchool;
        this.schoolYear = schoolYear;
        this.norm = norm;
    }
}
