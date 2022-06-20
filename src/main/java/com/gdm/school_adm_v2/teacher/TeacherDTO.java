package com.gdm.school_adm_v2.teacher;

import com.gdm.school_adm_v2.course_hours.CourseHoursDTO;
import com.gdm.school_adm_v2.teacher_personal_details.TeacherPersonalDetailsDTO;
import lombok.Data;

import java.util.List;

@Data
public class TeacherDTO {

    private String firstName;
    private String lastName;
    private String cnp;
    private List<String> courses;
    private List<CourseHoursDTO> courseHoursDTOs;
    private Integer numberOfTeachers;
    private String courseName;
    private Integer courseHours;
    private TeacherPersonalDetailsDTO teacherPersonalDetails;
    private Long schoolId;
    private String schoolYear;

    private Boolean exists;

    public TeacherDTO(Integer numberOfTeachers) {
        this.numberOfTeachers = numberOfTeachers;
    }

    public TeacherDTO(
            String firstName,
            String lastName,
            String cnp
    ) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.cnp = cnp;
    }

    public TeacherDTO(
            String firstName,
            String lastName,
            String cnp, List<String> courses,
            List<CourseHoursDTO> courseHoursDTOs
    ) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.cnp = cnp;
        this.courses = courses;
        this.courseHoursDTOs = courseHoursDTOs;
    }

    public TeacherDTO(String cnp, String courseName, Integer courseHours) {

        this.cnp = cnp;
        this.courseName = courseName;
        this.courseHours = courseHours;
    }

    public TeacherDTO(String firstName, String lastName, String cnp, TeacherPersonalDetailsDTO teacherPersonalDetails) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.cnp = cnp;
        this.teacherPersonalDetails = teacherPersonalDetails;
    }

    public TeacherDTO(String cnp, Long schoolId, String schoolYear) {
        this.cnp = cnp;
        this.schoolId = schoolId;
        this.schoolYear = schoolYear;
    }

    public TeacherDTO(Boolean exists) {
        this.exists = exists;
    }
}
