package com.gdm.school_adm_v2.util.config;

import com.gdm.school_adm_v2.address.AddressConfig;
import com.gdm.school_adm_v2.address.AddressService;
import com.gdm.school_adm_v2.city.CityConfig;
import com.gdm.school_adm_v2.city.CityService;
import com.gdm.school_adm_v2.county.CountyConfig;
import com.gdm.school_adm_v2.county.CountyService;
import com.gdm.school_adm_v2.course.CourseConfig;
import com.gdm.school_adm_v2.course.CourseService;
import com.gdm.school_adm_v2.school.SchoolConfig;
import com.gdm.school_adm_v2.school.SchoolService;
import com.gdm.school_adm_v2.school_school_year_teacher_courses_hours.SchoolSchoolYearTeacherCoursesHoursService;
import com.gdm.school_adm_v2.school_year.SchoolYearConfig;
import com.gdm.school_adm_v2.school_year.SchoolYearService;
import com.gdm.school_adm_v2.teacher.TeacherConfig;
import com.gdm.school_adm_v2.teacher.TeacherService;
import com.gdm.school_adm_v2.user.UserConfig;
import com.gdm.school_adm_v2.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

    // TODO: se creeaza parintele in clasa lui de config
    //  apoi se creeaza copilul in clasa lui de config
    //  cand se creeaza copilul, obiectul se salveaza in db continand obiectul parinte (child.setParent(parent))

@Configuration
public class DatabaseConfig {

    @Bean
    CommandLineRunner commandLineRunner(
            CountyService countyService,
            CityService cityService,
            AddressService addressService,
            SchoolService schoolService,
            SchoolYearService schoolYearService,
            SchoolSchoolYearTeacherCoursesHoursService schoolSchoolYearTeacherCoursesHoursService,
            TeacherService teacherService,
            CourseService courseService,
            UserService userService
    ){

        return args -> {

            CountyConfig.createAndSaveCounties(countyService);
            CityConfig.createAndSaveCitiesAndSetAndUpdateCounties(
                    cityService,
                    countyService
            );
            AddressConfig.saveAndCreateAddressesAndUpdateCities(
                    addressService,
                    cityService
            );
            SchoolConfig.createSchoolWithSchoolDetails(
                    schoolService,
                    addressService
            );
            SchoolYearConfig.createSchoolYears(schoolYearService);
            TeacherConfig.createAndSaveTeachers(teacherService);
            CourseConfig.createAndSaveCourse(courseService);
            UserConfig.createUser(userService);

            SchoolSchoolYearTeacherCoursesHoursConfig.createAndSave(
                    schoolService,
                    schoolYearService,
                    teacherService,
                    courseService,
                    schoolSchoolYearTeacherCoursesHoursService
            );
        };
    }
}
