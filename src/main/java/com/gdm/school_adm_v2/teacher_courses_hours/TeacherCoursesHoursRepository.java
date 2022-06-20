package com.gdm.school_adm_v2.teacher_courses_hours;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherCoursesHoursRepository extends CrudRepository<TeacherCoursesHours, Long> {
}
