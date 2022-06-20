package com.gdm.school_adm_v2.school;

import com.gdm.school_adm_v2.course.Course;
import com.gdm.school_adm_v2.course.CourseDTO;
import com.gdm.school_adm_v2.course_hours.CourseHoursDTO;
import com.gdm.school_adm_v2.school_details.SchoolDetails;
import com.gdm.school_adm_v2.school_details.SchoolDetailsDTO;
import com.gdm.school_adm_v2.teacher.TeacherDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SchoolService {

    private final SchoolRepository schoolRepository;

    @Autowired
    public SchoolService(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    public School saveOrUpdate(School school){

        return schoolRepository.save(school);
    }

    public Iterable<School> saveOrUpdateAll(Iterable<School> schools){

        return schoolRepository.saveAll(schools);
    }

    public Iterable<School> getAll(){

        return schoolRepository.findAll();
    }

    public School getById(Long id){

        return schoolRepository.getById(id)
                .orElseThrow(() -> new IllegalStateException(String.format(
                        "School with id %s not found", id
                )));
    }

    public CourseHoursDTO getNumberOfCourses(Long idSchool, String schoolYear) {

        return schoolRepository.getNumberOfCourses(idSchool, schoolYear)
                .orElseThrow(() -> new IllegalStateException(String.format(
                        "No courses were found for " +
                                "school with id %1$s " +
                                "and school year %2$s", idSchool, schoolYear
                )));
    }

    public TeacherDTO getNumberOfTeachers(Long idSchool, String schoolYear) {

        return schoolRepository.getNumberOfTeachers(idSchool, schoolYear)
                .orElseThrow(() -> new IllegalStateException(String.format(
                        "No teachers were found for " +
                                "school with id %1$s " +
                                "and school year %2$s", idSchool, schoolYear
                )));
    }

    public School saveOrUpdate(SchoolDTO schoolDTO){

        School school = getSchoolFromDTO(schoolDTO);
        school.setSchoolDetails(
                getSchoolDetailsFromDTO(schoolDTO.getId(), schoolDTO.getSchoolDetails()));
        return schoolRepository.save(school);
    }

    public Boolean savedOrUpdated(SchoolDTO schoolDTO){

        try {
            School school = saveOrUpdate(schoolDTO);
            return school.getId().equals(schoolDTO.getId()) &&
                    school.getSchoolDetails().getName().equals(schoolDTO.getSchoolDetails().getName()) &&
                    school.getSchoolDetails().getTelephoneNumber().equals(
                            schoolDTO.getSchoolDetails().getTelephoneNumber()) &&
                    school.getSchoolDetails().getEmailAddress().equals(
                            schoolDTO.getSchoolDetails().getEmailAddress());
        }catch (Exception e){
            return false;
        }
    }

    private School getSchoolFromDTO(SchoolDTO schoolDTO) {

        return schoolRepository.findById(schoolDTO.getId())
                .orElseThrow(() -> new IllegalStateException(String.format(
                        "School with id %s not found", schoolDTO.getId()
                )));
    }

    private SchoolDetails getSchoolDetailsFromDTO(Long id, SchoolDetailsDTO schoolDetailsDTO){

        SchoolDetails schoolDetails = schoolRepository.findSchoolDetailsBySchoolId(id)
                .orElseThrow(() -> new IllegalStateException(String.format(
                        "SchoolDetails with school id %s not found", id
        )));

        schoolDetails.setName(schoolDetailsDTO.getName());
        schoolDetails.setTelephoneNumber(schoolDetailsDTO.getTelephoneNumber());
        schoolDetails.setEmailAddress(schoolDetailsDTO.getEmailAddress());

        return schoolDetails;
    }

    public Iterable<CourseDTO> getCourses(Long id, String schoolYear) {

        return schoolRepository.getCourses(id, schoolYear);
    }
}
