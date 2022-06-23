package com.gdm.school_adm_v2.util.config;

import com.gdm.school_adm_v2.address.AddressConfig;
import com.gdm.school_adm_v2.address.AddressService;
import com.gdm.school_adm_v2.city.CityConfig;
import com.gdm.school_adm_v2.city.CityService;
import com.gdm.school_adm_v2.county.CountyConfig;
import com.gdm.school_adm_v2.county.CountyService;
import com.gdm.school_adm_v2.course.CourseConfig;
import com.gdm.school_adm_v2.course.CourseService;
import com.gdm.school_adm_v2.school.School;
import com.gdm.school_adm_v2.school.SchoolConfig;
import com.gdm.school_adm_v2.school.SchoolService;
import com.gdm.school_adm_v2.school_school_year_teacher_courses_hours.SchoolSchoolYearTeacherCoursesHoursService;
import com.gdm.school_adm_v2.school_year.SchoolYear;
import com.gdm.school_adm_v2.school_year.SchoolYearConfig;
import com.gdm.school_adm_v2.school_year.SchoolYearService;
import com.gdm.school_adm_v2.teacher.TeacherConfig;
import com.gdm.school_adm_v2.teacher.TeacherDTO;
import com.gdm.school_adm_v2.teacher.TeacherService;
import com.gdm.school_adm_v2.user.UserConfig;
import com.gdm.school_adm_v2.user.UserService;
import com.gdm.school_adm_v2.util.pdf.PDFTeachersReport;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

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

//            PDFTeachersReport pdfTeachersReport = new PDFTeachersReport();
//            School school = schoolService.getById(1L);
//            pdfTeachersReport.setSchool(school);
//            SchoolYear schoolYear = schoolYearService.getByYears("2021-2022");
//            List<TeacherDTO> teacherDTOs =
//                    (List<TeacherDTO>) teacherService.getAllCoursesInSchoolInYear(
//                            school.getId(), schoolYear.getYears());
//            pdfTeachersReport.setTeacherDTOs(teacherDTOs);
//            pdfTeachersReport.createPDF();
        };
    }
}
