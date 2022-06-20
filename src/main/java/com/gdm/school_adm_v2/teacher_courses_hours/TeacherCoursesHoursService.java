package com.gdm.school_adm_v2.teacher_courses_hours;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherCoursesHoursService {

    private final TeacherCoursesHoursRepository teacherCoursesHoursRepository;

    @Autowired
    public TeacherCoursesHoursService(TeacherCoursesHoursRepository teacherCoursesHoursRepository) {
        this.teacherCoursesHoursRepository = teacherCoursesHoursRepository;
    }
}
