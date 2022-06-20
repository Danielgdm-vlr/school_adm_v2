package com.gdm.school_adm_v2.teacher;

import com.gdm.school_adm_v2.course_hours.CourseHoursDTO;
import com.gdm.school_adm_v2.school.SchoolDTO;
import com.gdm.school_adm_v2.school_details.SchoolDetailsDTO;
import com.gdm.school_adm_v2.teacher_courses_hours.TeacherCoursesHours;
import com.gdm.school_adm_v2.teacher_courses_hours.TeacherCoursesHoursDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/teachers")
@Transactional(readOnly = true)
public class TeacherController {

    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("cnp/exists/{cnp}/{id}/{schoolYear}")
    public Boolean findByCNP(
            @PathVariable("cnp") String cnp,
            @PathVariable("id") Long id,
            @PathVariable("schoolYear") String schoolYear
    ){

        return teacherService.findByCNP(cnp, id, schoolYear);
    }

    @GetMapping("cnp/{cnp}/{id}/{schoolYear}")
    public Teacher getByCNP(
            @PathVariable("cnp") String cnp,
            @PathVariable("id") Long id,
            @PathVariable("schoolYear") String schoolYear
    ){

        return teacherService.getByCNP(cnp, id, schoolYear);
    }

    @GetMapping("{idSchool}/{school-year}")
    public Iterable<TeacherDTO> getAllInSchoolInYear(
            @PathVariable("idSchool") Long id,
            @PathVariable("school-year") String schoolYear
    ){

        return teacherService.getAllInSchoolInYear(id, schoolYear);
    }

    @GetMapping("in-school-in-year/{idSchool}/{schoolYear}")
    public Iterable<TeacherDTO> getAllCoursesInSchoolInYear(
            @PathVariable("idSchool") Long idSchool,
            @PathVariable("schoolYear") String schoolYear
    ){

        return teacherService.getAllCoursesInSchoolInYear(idSchool, schoolYear);
    }

    @GetMapping("courses-and-number-of-teachers/{idSchool}/{schoolYear}")
    public Iterable<CourseHoursDTO> getAllCHForTeachersInSchoolInYear(
            @PathVariable("idSchool") Long idSchool,
            @PathVariable("schoolYear") String schoolYear
    ){

        return teacherService.getAllCHForTeachersInSchoolInYear(idSchool, schoolYear);
    }

    @GetMapping("max/{idSchool}/{schoolYear}/{cnp}")
    public Integer getNormHoursForTeacher(
            @PathVariable("idSchool") Long idSchool,
            @PathVariable("cnp") String cnp,
            @PathVariable("schoolYear") String schoolYear
    ){

        return teacherService.getNumberOfMaxTaughtHoursInYear(idSchool, schoolYear, cnp);
    }

    @GetMapping("courses-number/{schoolYear}/{cnp}")
    public Integer getNumberOfTaughtCoursesInYear(
            @PathVariable("cnp") String cnp,
            @PathVariable("schoolYear") String schoolYear
    ){

        return teacherService.getNumberOfTaughtCoursesInYear(schoolYear, cnp);
    }

    @GetMapping("hours-number/{schoolYear}/{cnp}")
    public Integer getNumberOfHoursOverall(
            @PathVariable("cnp") String cnp,
            @PathVariable("schoolYear") String schoolYear
    ){

        return teacherService.getNumberOfHoursOverall(schoolYear, cnp);
    }

    @GetMapping("teaches/{schoolYear}/{cnp}")
    public List<SchoolDTO> getSchoolsWhereTeaches(
            @PathVariable("schoolYear") String schoolYear,
            @PathVariable("cnp") String cnp
    ){

        return teacherService.getSchoolWhereTeaches(schoolYear, cnp);
    }

    @DeleteMapping("teacher-course-hours")
    @Transactional
    public void deleteCourseHours(@RequestBody String jSONTeacherDTO){

        teacherService.deleteCourseHours(jSONTeacherDTO);
    }

    @PutMapping("teacher-course-hours")
    @Transactional
    public void updateCourseHours(@RequestBody String jSONTeacherDTO){

        teacherService.updateCourseHours(jSONTeacherDTO);
    }

    @PutMapping("teacher")
    @Transactional
    public void updateTeacherPD(@RequestBody String jSONTeacherDTO){

        teacherService.updatePD(jSONTeacherDTO);
    }

    @DeleteMapping("teacher")
    @Transactional
    public void deleteTeacherFromSchool(@RequestBody String jSONTeacherDTO){

        teacherService.deleteTeacherFromSchool(jSONTeacherDTO);
    }

    @PostMapping("save-tch")
    @Transactional
    public void saveTCH(@RequestBody String jSonTchDTO){

        teacherService.saveTCH(jSonTchDTO);
    }
}
