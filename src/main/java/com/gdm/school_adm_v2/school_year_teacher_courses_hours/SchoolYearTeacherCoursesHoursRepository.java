package com.gdm.school_adm_v2.school_year_teacher_courses_hours;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolYearTeacherCoursesHoursRepository
    extends CrudRepository<SchoolYearTeacherCoursesHours, Long> {
}
