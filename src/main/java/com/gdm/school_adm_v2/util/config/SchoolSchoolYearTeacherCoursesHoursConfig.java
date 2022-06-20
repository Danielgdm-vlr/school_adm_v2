package com.gdm.school_adm_v2.util.config;

import com.gdm.school_adm_v2.course.CourseService;
import com.gdm.school_adm_v2.course_hours.CourseHours;
import com.gdm.school_adm_v2.school.SchoolService;
import com.gdm.school_adm_v2.school_school_year_teacher_courses_hours.SchoolSchoolYearTeacherCoursesHours;
import com.gdm.school_adm_v2.school_school_year_teacher_courses_hours.SchoolSchoolYearTeacherCoursesHoursService;
import com.gdm.school_adm_v2.school_year.SchoolYearService;
import com.gdm.school_adm_v2.school_year_teacher_courses_hours.SchoolYearTeacherCoursesHours;
import com.gdm.school_adm_v2.teacher.TeacherService;
import com.gdm.school_adm_v2.teacher_courses_hours.TeacherCoursesHours;

import java.util.List;

public class SchoolSchoolYearTeacherCoursesHoursConfig {

    public static void createAndSave(
            SchoolService schoolService,
            SchoolYearService schoolYearService,
            TeacherService teacherService,
            CourseService courseService,
            SchoolSchoolYearTeacherCoursesHoursService schoolSchoolYearTeacherCoursesHoursService
    ){

        createAndSaveForSchool5(schoolService, schoolYearService, teacherService,
                courseService, schoolSchoolYearTeacherCoursesHoursService);
        createAndSaveForSchool8(schoolService, schoolYearService, teacherService,
                courseService, schoolSchoolYearTeacherCoursesHoursService);
    }

    private static void createAndSaveForSchool5(
            SchoolService schoolService,
            SchoolYearService schoolYearService,
            TeacherService teacherService,
            CourseService courseService,
            SchoolSchoolYearTeacherCoursesHoursService schoolSchoolYearTeacherCoursesHoursService
    ){

        insertDataForSchool5(
                schoolService,
                schoolYearService,
                teacherService,
                courseService,
                schoolSchoolYearTeacherCoursesHoursService
        );
    }

    private static void insertDataForSchool5(
            SchoolService schoolService,
            SchoolYearService schoolYearService,
            TeacherService teacherService,
            CourseService courseService,
            SchoolSchoolYearTeacherCoursesHoursService schoolSchoolYearTeacherCoursesHoursService
    ){

        SchoolSchoolYearTeacherCoursesHours schoolSchoolYearTeacherCoursesHoursForSchool5 =
                new SchoolSchoolYearTeacherCoursesHours();
        schoolSchoolYearTeacherCoursesHoursForSchool5.setActive(true);
        schoolSchoolYearTeacherCoursesHoursForSchool5.setSchool(schoolService.getById(1L));
        schoolSchoolYearTeacherCoursesHoursForSchool5.setSchoolYearTeacherCoursesHours(List.of(
                insertDataForSchool5Year2021_2022(schoolYearService, teacherService, courseService),
                insertDataForSchool5Year2020_2021(schoolYearService, teacherService, courseService)
        ));

        schoolSchoolYearTeacherCoursesHoursService
                .saveOrUpdate(schoolSchoolYearTeacherCoursesHoursForSchool5);
    }

    private static SchoolYearTeacherCoursesHours insertDataForSchool5Year2021_2022(
            SchoolYearService schoolYearService,
            TeacherService teacherService,
            CourseService courseService
    ){

        SchoolYearTeacherCoursesHours schoolYearTeacherCoursesHours =
                new SchoolYearTeacherCoursesHours();
        schoolYearTeacherCoursesHours.setActive(true);
        schoolYearTeacherCoursesHours.setSchoolYear(schoolYearService.getByYears("2021-2022"));
        schoolYearTeacherCoursesHours.setTeacherCoursesHours(
                getListOfTeacherCoursesHoursForSchool5Year2021_2022(teacherService, courseService)
        );

        return schoolYearTeacherCoursesHours;
    }

    private static List<TeacherCoursesHours> getListOfTeacherCoursesHoursForSchool5Year2021_2022(
            TeacherService teacherService,
            CourseService courseService
    )
    {

        return List.of(
                new TeacherCoursesHours(
                        true,
                        teacherService.getByCNPForConfig("6010926131083"),
                        18,
                        List.of(
                                new CourseHours(
                                        true,
                                        courseService.getByName("Engleza"),
                                        3
                                ),
                                new CourseHours(
                                        true,
                                        courseService.getByName("Germana"),
                                        3
                                ),
                                new CourseHours(
                                        true,
                                        courseService.getByName("Matematica"),
                                        3
                                )
                        )
                ),
                new TeacherCoursesHours(
                        true,
                        teacherService.getByCNPForConfig("1971118228631"),
                        18,
                        List.of(
                                new CourseHours(
                                        true,
                                        courseService.getByName("Romana"),
                                        5
                                ),
                                new CourseHours(
                                        true,
                                        courseService.getByName("Sociologie"),
                                        10
                                )
                        )
                ),
                new TeacherCoursesHours(
                        true,
                        teacherService.getByCNPForConfig("1970313118016"),
                        18,
                        List.of(
                                new CourseHours(
                                        true,
                                        courseService.getByName("TIC"),
                                        7
                                ),
                                new CourseHours(
                                        true,
                                        courseService.getByName("Informatica"),
                                        5
                                )
                        )
                ),
                new TeacherCoursesHours(
                        true,
                        teacherService.getByCNPForConfig("2891004371082"),
                        18,
                        List.of(
                                new CourseHours(
                                        true,
                                        courseService.getByName("Matematica"),
                                        14
                                )
                        )
                ),
                new TeacherCoursesHours(
                        true,
                        teacherService.getByCNPForConfig("6020430139802"),
                        18,
                        List.of(
                                new CourseHours(
                                        true,
                                        courseService.getByName("Biologie"),
                                        7
                                ),
                                new CourseHours(
                                        true,
                                        courseService.getByName("Chimie"),
                                        4
                                )
                        )
                ),
                new TeacherCoursesHours(
                        true,
                        teacherService.getByCNPForConfig("1930308426115"),
                        18,
                        List.of(
                                new CourseHours(
                                        true,
                                        courseService.getByName("Religie"),
                                        7
                                )
                        )
                ),
                new TeacherCoursesHours(
                        true,
                        teacherService.getByCNPForConfig("1920924351408"),
                        18,
                        List.of(
                                new CourseHours(
                                        true,
                                        courseService.getByName("Geografie"),
                                        10
                                )
                        )
                ),
                new TeacherCoursesHours(
                        true,
                        teacherService.getByCNPForConfig("2881110262306"),
                        18,
                        List.of(
                                new CourseHours(
                                        true,
                                        courseService.getByName("Romana"),
                                        10
                                ),
                                new CourseHours(
                                        true,
                                        courseService.getByName("Engleza"),
                                        4
                                )
                        )
                ),
                new TeacherCoursesHours(
                        true,
                        teacherService.getByCNPForConfig("2940216306251"),
                        18,
                        List.of(
                                new CourseHours(
                                        true,
                                        courseService.getByName("Chimie"),
                                        7
                                ),
                                new CourseHours(
                                        true,
                                        courseService.getByName("Fizica"),
                                        8
                                )
                        )
                ),
                new TeacherCoursesHours(
                        true,
                        teacherService.getByCNPForConfig("6031227367151"),
                        18,
                        List.of(
                                new CourseHours(
                                        true,
                                        courseService.getByName("Economie"),
                                        7
                                )
                        )
                ),
                new TeacherCoursesHours(
                        true,
                        teacherService.getByCNPForConfig("2980419035600"),
                        18,
                        List.of(
                                new CourseHours(
                                        true,
                                        courseService.getByName("Germana"),
                                        5
                                )
                        )
                )
        );
    }

    private static SchoolYearTeacherCoursesHours insertDataForSchool5Year2020_2021(
            SchoolYearService schoolYearService,
            TeacherService teacherService,
            CourseService courseService
    ){

        SchoolYearTeacherCoursesHours schoolYearTeacherCoursesHours =
                new SchoolYearTeacherCoursesHours();
        schoolYearTeacherCoursesHours.setActive(true);
        schoolYearTeacherCoursesHours.setSchoolYear(schoolYearService.getByYears("2020-2021"));
        schoolYearTeacherCoursesHours.setTeacherCoursesHours(
                getListOfTeacherCoursesHoursForSchool5Year2020_2021(teacherService, courseService)
        );

        return schoolYearTeacherCoursesHours;
    }

    private static List<TeacherCoursesHours> getListOfTeacherCoursesHoursForSchool5Year2020_2021(
            TeacherService teacherService,
            CourseService courseService
    ){

        return List.of(
                new TeacherCoursesHours(
                        true,
                        teacherService.getByCNPForConfig("6010926131083"),
                        18,
                        List.of(
                                new CourseHours(
                                        true,
                                        courseService.getByName("Germana"),
                                        12
                                )
                        )
                ),
                new TeacherCoursesHours(
                        true,
                        teacherService.getByCNPForConfig("1971118228631"),
                        18,
                        List.of(
                                new CourseHours(
                                        true,
                                        courseService.getByName("Romana"),
                                        5
                                ),
                                new CourseHours(
                                        true,
                                        courseService.getByName("Germana"),
                                        3
                                )
                        )
                ),
                new TeacherCoursesHours(
                        true,
                        teacherService.getByCNPForConfig("1940101352117"),
                        18,
                        List.of(
                                new CourseHours(
                                        true,
                                        courseService.getByName("TIC"),
                                        7
                                )
                        )
                ),
                new TeacherCoursesHours(
                        true,
                        teacherService.getByCNPForConfig("2891004371082"),
                        18,
                        List.of(
                                new CourseHours(
                                        true,
                                        courseService.getByName("Matematica"),
                                        14
                                )
                        )
                ),
                new TeacherCoursesHours(
                        true,
                        teacherService.getByCNPForConfig("1900602157811"),
                        18,
                        List.of(
                                new CourseHours(
                                        true,
                                        courseService.getByName("Chimie"),
                                        4
                                )
                        )
                ),
                new TeacherCoursesHours(
                        true,
                        teacherService.getByCNPForConfig("1930308426115"),
                        18,
                        List.of(
                                new CourseHours(
                                        true,
                                        courseService.getByName("Religie"),
                                        4
                                ),
                                new CourseHours(
                                        true,
                                        courseService.getByName("Sociologie"),
                                        3
                                )

                        )
                ),
                new TeacherCoursesHours(
                        true,
                        teacherService.getByCNPForConfig("2880825266382"),
                        18,
                        List.of(
                                new CourseHours(
                                        true,
                                        courseService.getByName("Geografie"),
                                        7
                                )
                        )
                ),
                new TeacherCoursesHours(
                        true,
                        teacherService.getByCNPForConfig("2881110262306"),
                        18,
                        List.of(
                                new CourseHours(
                                        true,
                                        courseService.getByName("Romana"),
                                        12
                                )
                        )
                ),
                new TeacherCoursesHours(
                        true,
                        teacherService.getByCNPForConfig("2940216306251"),
                        18,
                        List.of(
                                new CourseHours(
                                        true,
                                        courseService.getByName("Chimie"),
                                        7
                                )
                        )
                ),
                new TeacherCoursesHours(
                        true,
                        teacherService.getByCNPForConfig("6031227367151"),
                        18,
                        List.of(
                                new CourseHours(
                                        true,
                                        courseService.getByName("Economie"),
                                        7
                                )
                        )
                ),
                new TeacherCoursesHours(
                        true,
                        teacherService.getByCNPForConfig("2980419035600"),
                        18,
                        List.of(
                                new CourseHours(
                                        true,
                                        courseService.getByName("Germana"),
                                        5
                                ),
                                new CourseHours(
                                        true,
                                        courseService.getByName("Engleza"),
                                        2
                                )
                        )
                )
        );
    }

    public static void createAndSaveForSchool8(
            SchoolService schoolService,
            SchoolYearService schoolYearService,
            TeacherService teacherService,
            CourseService courseService,
            SchoolSchoolYearTeacherCoursesHoursService schoolSchoolYearTeacherCoursesHoursService
    ){

        insertDataForSchool8(
                schoolService,
                schoolYearService,
                teacherService,
                courseService,
                schoolSchoolYearTeacherCoursesHoursService
        );
    }

    private static void insertDataForSchool8(
            SchoolService schoolService,
            SchoolYearService schoolYearService,
            TeacherService teacherService,
            CourseService courseService,
            SchoolSchoolYearTeacherCoursesHoursService schoolSchoolYearTeacherCoursesHoursService
    ){

        SchoolSchoolYearTeacherCoursesHours schoolSchoolYearTeacherCoursesHoursForSchool8 =
                new SchoolSchoolYearTeacherCoursesHours();
        schoolSchoolYearTeacherCoursesHoursForSchool8.setActive(true);
        schoolSchoolYearTeacherCoursesHoursForSchool8.setSchool(schoolService.getById(2L));
        schoolSchoolYearTeacherCoursesHoursForSchool8.setSchoolYearTeacherCoursesHours(List.of(
                insertDataForSchool8Year2021_2022(schoolYearService, teacherService, courseService),
                insertDataForSchool8Year2020_2021(schoolYearService, teacherService, courseService)
        ));

        schoolSchoolYearTeacherCoursesHoursService
                .saveOrUpdate(schoolSchoolYearTeacherCoursesHoursForSchool8);
    }

    private static SchoolYearTeacherCoursesHours insertDataForSchool8Year2021_2022(
            SchoolYearService schoolYearService,
            TeacherService teacherService,
            CourseService courseService
    ){

        SchoolYearTeacherCoursesHours schoolYearTeacherCoursesHours =
                new SchoolYearTeacherCoursesHours();
        schoolYearTeacherCoursesHours.setActive(true);
        schoolYearTeacherCoursesHours.setSchoolYear(schoolYearService.getByYears("2021-2022"));
        schoolYearTeacherCoursesHours.setTeacherCoursesHours(
                getListOfTeacherCoursesHoursForSchool8Year2021_2022(teacherService, courseService)
        );

        return schoolYearTeacherCoursesHours;
    }

    private static List<TeacherCoursesHours> getListOfTeacherCoursesHoursForSchool8Year2021_2022(
            TeacherService teacherService,
            CourseService courseService
    ) {

        return List.of(
                new TeacherCoursesHours(
                        true,
                        teacherService.getByCNPForConfig("6010926131083"),
                        18,
                        List.of(
                                new CourseHours(
                                        true,
                                        courseService.getByName("Engleza"),
                                        3
                                ),
                                new CourseHours(
                                        true,
                                        courseService.getByName("Matematica"),
                                        3
                                )
                        )
                ),
                new TeacherCoursesHours(
                        true,
                        teacherService.getByCNPForConfig("1971118228631"),
                        16,
                        List.of(
                                new CourseHours(
                                        true,
                                        courseService.getByName("Sociologie"),
                                        10
                                )
                        )
                ),
                new TeacherCoursesHours(
                        true,
                        teacherService.getByCNPForConfig("1970313118016"),
                        18,
                        List.of(
                                new CourseHours(
                                        true,
                                        courseService.getByName("TIC"),
                                        7
                                ),
                                new CourseHours(
                                        true,
                                        courseService.getByName("Informatica"),
                                        5
                                )
                        )
                ),
                new TeacherCoursesHours(
                        true,
                        teacherService.getByCNPForConfig("2891004371082"),
                        18,
                        List.of(
                                new CourseHours(
                                        true,
                                        courseService.getByName("Matematica"),
                                        14
                                )
                        )
                ),
                new TeacherCoursesHours(
                        true,
                        teacherService.getByCNPForConfig("6020430139802"),
                        17,
                        List.of(
                                new CourseHours(
                                        true,
                                        courseService.getByName("Biologie"),
                                        7
                                ),
                                new CourseHours(
                                        true,
                                        courseService.getByName("Chimie"),
                                        4
                                )
                        )
                ),
                new TeacherCoursesHours(
                        true,
                        teacherService.getByCNPForConfig("1930308426115"),
                        17,
                        List.of(
                                new CourseHours(
                                        true,
                                        courseService.getByName("Religie"),
                                        7
                                )
                        )
                ),
                new TeacherCoursesHours(
                        true,
                        teacherService.getByCNPForConfig("1920924351408"),
                        17,
                        List.of(
                                new CourseHours(
                                        true,
                                        courseService.getByName("Geografie"),
                                        10
                                )
                        )
                ),
                new TeacherCoursesHours(
                        true,
                        teacherService.getByCNPForConfig("2881110262306"),
                        17,
                        List.of(
                                new CourseHours(
                                        true,
                                        courseService.getByName("Engleza"),
                                        4
                                )
                        )
                ),
                new TeacherCoursesHours(
                        true,
                        teacherService.getByCNPForConfig("2940216306251"),
                        18,
                        List.of(
                                new CourseHours(
                                        true,
                                        courseService.getByName("Chimie"),
                                        7
                                )
                        )
                ),
                new TeacherCoursesHours(
                        true,
                        teacherService.getByCNPForConfig("6031227367151"),
                        18,
                        List.of(
                                new CourseHours(
                                        true,
                                        courseService.getByName("Economie"),
                                        7
                                )
                        )
                ),
                new TeacherCoursesHours(
                        true,
                        teacherService.getByCNPForConfig("2980419035600"),
                        18,
                        List.of(
                                new CourseHours(
                                        true,
                                        courseService.getByName("Germana"),
                                        5
                                )
                        )
                )
        );
    }

    private static SchoolYearTeacherCoursesHours insertDataForSchool8Year2020_2021(
            SchoolYearService schoolYearService,
            TeacherService teacherService,
            CourseService courseService
    ){

        SchoolYearTeacherCoursesHours schoolYearTeacherCoursesHours =
                new SchoolYearTeacherCoursesHours();
        schoolYearTeacherCoursesHours.setActive(true);
        schoolYearTeacherCoursesHours.setSchoolYear(schoolYearService.getByYears("2020-2021"));
        schoolYearTeacherCoursesHours.setTeacherCoursesHours(
                getListOfTeacherCoursesHoursForSchool8Year2020_2021(teacherService, courseService)
        );

        return schoolYearTeacherCoursesHours;
    }

    private static List<TeacherCoursesHours> getListOfTeacherCoursesHoursForSchool8Year2020_2021(
            TeacherService teacherService,
            CourseService courseService
    ){

        return List.of(
                new TeacherCoursesHours(
                        true,
                        teacherService.getByCNPForConfig("6010926131083"),
                        16,
                        List.of(
                                new CourseHours(
                                        true,
                                        courseService.getByName("Germana"),
                                        9
                                ),
                                new CourseHours(
                                        true,
                                        courseService.getByName("Engleza"),
                                        3
                                )
                        )
                ),
                new TeacherCoursesHours(
                        true,
                        teacherService.getByCNPForConfig("1971118228631"),
                        18,
                        List.of(
                                new CourseHours(
                                        true,
                                        courseService.getByName("Romana"),
                                        5
                                )
                        )
                ),
                new TeacherCoursesHours(
                        true,
                        teacherService.getByCNPForConfig("1940101352117"),
                        16,
                        List.of(
                                new CourseHours(
                                        true,
                                        courseService.getByName("TIC"),
                                        7
                                )
                        )
                ),
                new TeacherCoursesHours(
                        true,
                        teacherService.getByCNPForConfig("2891004371082"),
                        13,
                        List.of(
                                new CourseHours(
                                        true,
                                        courseService.getByName("Matematica"),
                                        14
                                )
                        )
                ),
                new TeacherCoursesHours(
                        true,
                        teacherService.getByCNPForConfig("1900602157811"),
                        17,
                        List.of(
                                new CourseHours(
                                        true,
                                        courseService.getByName("Chimie"),
                                        4
                                )
                        )
                ),
                new TeacherCoursesHours(
                        true,
                        teacherService.getByCNPForConfig("1930308426115"),
                        18,
                        List.of(
                                new CourseHours(
                                        true,
                                        courseService.getByName("Religie"),
                                        4
                                ),
                                new CourseHours(
                                        true,
                                        courseService.getByName("Sociologie"),
                                        3
                                )

                        )
                ),
                new TeacherCoursesHours(
                        true,
                        teacherService.getByCNPForConfig("2880825266382"),
                        18,
                        List.of(
                                new CourseHours(
                                        true,
                                        courseService.getByName("Geografie"),
                                        7
                                )
                        )
                ),
                new TeacherCoursesHours(
                        true,
                        teacherService.getByCNPForConfig("2881110262306"),
                        18,
                        List.of(
                                new CourseHours(
                                        true,
                                        courseService.getByName("Romana"),
                                        12
                                )
                        )
                ),
                new TeacherCoursesHours(
                        true,
                        teacherService.getByCNPForConfig("2940216306251"),
                        18,
                        List.of(
                                new CourseHours(
                                        true,
                                        courseService.getByName("Chimie"),
                                        7
                                )
                        )
                ),
                new TeacherCoursesHours(
                        true,
                        teacherService.getByCNPForConfig("6031227367151"),
                        18,
                        List.of(
                                new CourseHours(
                                        true,
                                        courseService.getByName("Economie"),
                                        7
                                )
                        )
                ),
                new TeacherCoursesHours(
                        true,
                        teacherService.getByCNPForConfig("2980419035600"),
                        16,
                        List.of(
                                new CourseHours(
                                        true,
                                        courseService.getByName("Germana"),
                                        5
                                ),
                                new CourseHours(
                                        true,
                                        courseService.getByName("Engleza"),
                                        2
                                )
                        )
                )
        );
    }
}
