package com.gdm.school_adm_v2.school_school_year_teacher_courses_hours;

import com.gdm.school_adm_v2.course_hours.CourseHoursDTO;
import com.gdm.school_adm_v2.teacher_courses_hours.TeacherCoursesHoursDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchoolSchoolYearTeacherCoursesHoursService {

    private final SchoolSchoolYearTeacherCoursesHoursRepository schoolSchoolYearTeacherCoursesHoursRepository;

    @Autowired
    public SchoolSchoolYearTeacherCoursesHoursService(
            SchoolSchoolYearTeacherCoursesHoursRepository schoolSchoolYearTeacherCoursesHoursRepository) {

        this.schoolSchoolYearTeacherCoursesHoursRepository = schoolSchoolYearTeacherCoursesHoursRepository;
    }

    public SchoolSchoolYearTeacherCoursesHours saveOrUpdate(
            SchoolSchoolYearTeacherCoursesHours schoolSchoolYearTeacherCoursesHours
    ){

        return schoolSchoolYearTeacherCoursesHoursRepository.save(schoolSchoolYearTeacherCoursesHours);
    }

    public Iterable<SchoolSchoolYearTeacherCoursesHours> saveOrUpdateAll(
            Iterable<SchoolSchoolYearTeacherCoursesHours> schoolSchoolYearTeacherCoursesHours
    ){

        return schoolSchoolYearTeacherCoursesHoursRepository.saveAll(schoolSchoolYearTeacherCoursesHours);
    }

    public Iterable<SchoolSchoolYearTeacherCoursesHours> getAll(){

        return schoolSchoolYearTeacherCoursesHoursRepository.findAll();
    }

    public Iterable<CourseHoursDTO> getTaughtCoursesAndHoursOfTeacherInSchoolInASchoolYear(
            Long idSchool,
            String schoolYear,
            Long idTeacher
    ){

        Long idSchoolYear = schoolSchoolYearTeacherCoursesHoursRepository.getIdSchoolYear(schoolYear)
                .orElseThrow(() -> new IllegalStateException(String.format(
                        "School year with years %s was not found", schoolYear
                )));
        return schoolSchoolYearTeacherCoursesHoursRepository
                .getTaughtCoursesAndHoursOfTeacherInSchoolInASchoolYear(
                        idSchool, idSchoolYear, idTeacher)
                .orElseThrow(() -> new IllegalStateException(
                        String.format(
                                "Taught courses by teacher with id %1$s " +
                                "in school year %2$s " +
                                "in school with id %3$s " +
                                "was not found",
                                idTeacher, schoolYear, idSchool
                        )
                ));
    }

    public Iterable<TeacherCoursesHoursDTO> getAllTCHForTeachersInSchoolInYear(
            Long idSchool,
            String schoolYear
    ){

        return schoolSchoolYearTeacherCoursesHoursRepository
                .getAllTCHForTeachersInSchoolInYear(idSchool, schoolYear)
                .orElseThrow(() -> new IllegalStateException(String.format(
                        "Taught courses by teachers in " +
                                "school with id %1$s and " +
                                "school year %2$s " +
                                "not found", idSchool, schoolYear
                )));
    }

    public Iterable<TeacherCoursesHoursDTO>
        getAllTCHForTeacherInSchoolInYear(
                Long idSchool,
                String schoolYear,
                String cnpTeacher
    ) {

        return schoolSchoolYearTeacherCoursesHoursRepository
                .getAllTCHForTeacherInSchoolInYear(idSchool, schoolYear, cnpTeacher)
                .orElseThrow(() -> new IllegalStateException(String.format(
                        "Taught courses by teacher " +
                                "with id %3$s in " +
                                "school with id %1$s and " +
                                "school year %2$s " +
                                "not found", idSchool, schoolYear, cnpTeacher
                )));
    }
}
