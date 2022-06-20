package com.gdm.school_adm_v2.teacher;

import com.gdm.school_adm_v2.course.Course;
import com.gdm.school_adm_v2.course_hours.CourseHours;
import com.gdm.school_adm_v2.course_hours.CourseHoursDTO;
import com.gdm.school_adm_v2.school.School;
import com.gdm.school_adm_v2.school.SchoolDTO;
import com.gdm.school_adm_v2.school_details.SchoolDetailsDTO;
import com.gdm.school_adm_v2.school_school_year_teacher_courses_hours.SchoolSchoolYearTeacherCoursesHours;
import com.gdm.school_adm_v2.school_year_teacher_courses_hours.SchoolYearTeacherCoursesHours;
import com.gdm.school_adm_v2.school_year_teacher_courses_hours.SchoolYearTeacherCoursesHoursRepository;
import com.gdm.school_adm_v2.teacher_courses_hours.TeacherCoursesHours;
import com.gdm.school_adm_v2.teacher_courses_hours.TeacherCoursesHoursDTO;
import com.gdm.school_adm_v2.teacher_courses_hours.TeacherCoursesHoursRepository;
import com.gdm.school_adm_v2.teacher_personal_details.TeacherPersonalDetails;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Repository
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final TeacherCoursesHoursRepository teacherCoursesHoursRepository;
    private final SchoolYearTeacherCoursesHoursRepository schoolYearTeacherCoursesHoursRepository;

    @Autowired
    public TeacherService(
            TeacherRepository teacherRepository,
            TeacherCoursesHoursRepository teacherCoursesHoursRepository,
            SchoolYearTeacherCoursesHoursRepository schoolYearTeacherCoursesHoursRepository
    ) {

        this.teacherRepository = teacherRepository;
        this.teacherCoursesHoursRepository = teacherCoursesHoursRepository;
        this.schoolYearTeacherCoursesHoursRepository = schoolYearTeacherCoursesHoursRepository;
    }

    public Teacher saveOrUpdate(Teacher teacher){

        return teacherRepository.save(teacher);
    }

    public Iterable<Teacher> saveOrUpdateAll(Iterable<Teacher> teachers){

        return teacherRepository.saveAll(teachers);
    }

    public Iterable<Teacher> getAll(){

        return teacherRepository.findAll();
    }

    public Iterable<TeacherDTO> getAllInSchoolInYear(Long idSchool, String schoolYear){
        return teacherRepository.getAllInSchoolInYear(idSchool, schoolYear)
                .orElseThrow(() -> new IllegalStateException(String.format(
                        "Teachers in school with id %1$s and year %2$s not found", idSchool, schoolYear
                )));
    }

    public Teacher getByCNP(String cnp, Long id, String schoolYear){

        boolean temp = teacherRepository.getByCnp(id, schoolYear, cnp)
                .orElseThrow(() -> new IllegalStateException(String.format(
                        "Teacher with cnp %1$s " +
                                "in school with id %2$s " +
                                "in school year %3$s" +
                                "not found", cnp, id, schoolYear
                )))
                .getExists();
        if(temp){
            return teacherRepository.findByCnp(cnp)
                    .orElseThrow(() -> new IllegalStateException(String.format(
                            "Teacher with cnp %s not found", cnp
                    )));
        }

        throw new IllegalStateException("Teacher not found");
    }

    public Teacher getByCNPForConfig(String cnp){

        return teacherRepository.findByCnp(cnp)
                .orElseThrow(() -> new IllegalStateException(String.format(
                        "Teacher with cnp %s not found", cnp
                )));
    }

    public Boolean findByCNP(String cnp, Long id, String schoolYear){

        return teacherRepository.getByCnp(id, schoolYear, cnp)
                .orElseThrow(() -> new IllegalStateException(String.format(
                        "Teacher with cnp %1$s " +
                                "in school with id %2$s " +
                                "in school year %3$s" +
                                "not found", cnp, id, schoolYear
                )))
                .getExists();
    }

    public Iterable<TeacherDTO> getAllCoursesInSchoolInYear(
            Long idSchool,
            String schoolYear
    ){

        List<TeacherDTO> teacherDTOs = (List<TeacherDTO>) teacherRepository.getAllInSchoolInYear(idSchool, schoolYear)
                .orElseThrow(() -> new IllegalStateException(String.format(
                        "Teachers in school with id %1$s and year %2$s not found", idSchool, schoolYear
                )));

        AtomicReference<List<CourseHoursDTO>> courseHoursDTOs = new AtomicReference<>();
        AtomicReference<List<String>> courseNames = new AtomicReference<>();
        AtomicReference<Long> idTeacher = new AtomicReference<>();
        AtomicReference<Long> schoolYearTemp = new AtomicReference<>();
        teacherDTOs.forEach(teacherDTO -> {
            idTeacher.set(teacherRepository.findByCnp(teacherDTO.getCnp())
                    .orElseThrow(() -> new IllegalStateException(String.format(
                            "Teacher with cnp %s not found", teacherDTO.getCnp()
                    )))
                    .getId());
            schoolYearTemp.set(teacherRepository.getIdSchoolYearByYears(schoolYear)
                    .orElseThrow(() -> new IllegalStateException(String.format(
                            "School year by years %s not found", schoolYear
                    )))
                    .getId());
            courseHoursDTOs.set((List<CourseHoursDTO>) teacherRepository
                    .getTaughtCoursesAndHoursOfATeacherFromASelectedSchoolInASchoolYear(
                            idSchool, schoolYearTemp.get(), idTeacher.get()
                    )
                    .orElseThrow(() -> new IllegalStateException(
                            String.format(
                                    "Taught courses by teacher with id %1$s " +
                                            "in school year with id %2$s " +
                                            "in school with id %3$s " +
                                            "was not found",
                                    idTeacher.get(), schoolYearTemp.get(), idSchool
                            )
                    )));
            courseNames.set(courseHoursDTOs.get().stream()
                    .map(CourseHoursDTO::getCourseName)
                    .collect(Collectors.toList()));
            teacherDTO.setCourseHoursDTOs(courseHoursDTOs.get());
            teacherDTO.setCourses(courseNames.get());
        });

        return teacherDTOs;
    }

    public Iterable<CourseHoursDTO> getAllCHForTeachersInSchoolInYear(
            Long idSchool,
            String schoolYear
    ) {

        return teacherRepository.getAllCHForTeachersInSchoolInYear(idSchool, schoolYear)
                .orElseThrow(() -> new IllegalStateException(String.format(
                        "Courses and number of teachers in " +
                                "school with id %1$s and " +
                                "school year %2$s " +
                                "not found", idSchool, schoolYear
                )));
    }

    public Integer getNumberOfMaxTaughtHoursInYear(Long idSchool, String schoolYear, String cnp) {

        return teacherRepository.getNumberOfMaxTaughtHoursInYear(idSchool, schoolYear, cnp)
                .orElseThrow(() -> new IllegalStateException(String.format(
                        "Teacher with cnp %1$s " +
                                "and present in schoolYear %2$s " +
                                "in school with id %3$s" +
                                "not found", idSchool, schoolYear, cnp
                )))
                .getNumberOfTeachers();
    }

    public Integer getNumberOfTaughtCoursesInYear(String schoolYear, String cnp) {

        return teacherRepository.getNumberOfTaughtCoursesInYear(schoolYear, cnp)
                .orElseThrow(() -> new IllegalStateException(String.format(
                        "Teacher with cnp %1$s " +
                                "and present in schoolYear %2$s " +
                                "not found", schoolYear, cnp
                )))
                .getNumberOfTeachers();
    }

    public Integer getNumberOfHoursOverall(String schoolYear, String cnp) {

        return teacherRepository.getNumberOfHoursOverall(schoolYear, cnp)
                .orElseThrow(() -> new IllegalStateException(String.format(
                        "Teacher with cnp %1$s " +
                                "and present in schoolYear %2$s " +
                                "not found", schoolYear, cnp
                )))
                .getNumberOfTeachers();
    }

    public List<SchoolDTO> getSchoolWhereTeaches(String schoolYear, String cnp){

        List<SchoolDTO> schoolDTOs = teacherRepository.getSchoolsWhereTeaches(schoolYear, cnp)
                .orElseThrow(() -> new IllegalStateException(String.format(
                        "Schools where " +
                                "teacher with id %1$s " +
                                "and schoolYear %2$s " +
                                "not found", cnp, schoolYear
                )));

        return schoolDTOs.stream()
                .peek(schoolDTO -> {
                    School school = teacherRepository.getSchoolById(schoolDTO.getId())
                            .orElseThrow(() -> new IllegalStateException(String.format(
                                    "School with id %s not found", schoolDTO.getId()
                            )));
                    SchoolDetailsDTO schoolDetailsDTO = new SchoolDetailsDTO(
                            school.getSchoolDetails().getName(),
                            school.getSchoolDetails().getTelephoneNumber(),
                            school.getSchoolDetails().getEmailAddress()
                    );
                    schoolDTO.setSchoolDetails(schoolDetailsDTO);
                })
                .collect(Collectors.toList());
    }

    public void deleteCourseHours(String jSONTeacherDTO) {

        teacherRepository.deleteCHById(getIdCHFromTeacherDTO(jSONTeacherDTO));
    }

    public void updateCourseHours(String jSONTeacherDTO) {

        TeacherDTO teacherDTO = new Gson().fromJson(jSONTeacherDTO, TeacherDTO.class);
        teacherRepository.updateCHById(
                getIdCHFromTeacherDTO(jSONTeacherDTO), teacherDTO.getCourseHours());
    }

    public Long getIdCHFromTeacherDTO(String jSONTeacherDTO){
        Gson gson = new Gson();
        TeacherDTO teacherDTO = gson.fromJson(jSONTeacherDTO, TeacherDTO.class);

        return Long.valueOf(
                teacherRepository.findCHIdByTeacherCNPAndCourseName(
                                1L,
                                "2021-2022",
                                teacherDTO.getCnp(),
                                teacherDTO.getCourseName()
                        )
                        .orElseThrow(() -> new IllegalStateException(String.format(
                                "TCH for " +
                                        "teacher with cnp %1$s " +
                                        "and courseName %2$s " +
                                        "not found", teacherDTO.getCnp(), teacherDTO.getCourseName()
                        )))
                        .getNumberOfCourses());
    }

    public void updatePD(String jSONTeacherDTO) {

        TeacherDTO teacherDTO = new Gson().fromJson(jSONTeacherDTO, TeacherDTO.class);
        teacherRepository.updateTPDByCNP(
                teacherRepository.getTPDIdByTeacherCNP(teacherDTO.getCnp())
                        .orElseThrow(() -> new IllegalStateException(String.format(
                                "Tpd id for teacher with cnp %s was not found", teacherDTO.getCnp()
                        ))),
                teacherDTO.getTeacherPersonalDetails().getFirstName(),
                teacherDTO.getTeacherPersonalDetails().getLastName()
        );
    }

    public void deleteTeacherFromSchool(String jSONTeacherDTO) {

        TeacherDTO teacherDTO = new Gson().fromJson(jSONTeacherDTO, TeacherDTO.class);

        teacherRepository.deleteTFromSchool(
                teacherRepository.getTCHId(
                        teacherDTO.getSchoolId(),
                        teacherDTO.getSchoolYear(),
                        teacherDTO.getCnp()
                ).orElseThrow(() -> new IllegalStateException(String.format(
                        "TCH id for " +
                                "teacher with cnp %1$s " +
                                "and school with id %2$s " +
                                "and school year %3$s " +
                                "was not found", teacherDTO.getCnp(), teacherDTO.getSchoolId(), teacherDTO.getSchoolYear()
                ))).getId()
        );
    }

    public void saveTCH(String jSonTchDTO) {

        TeacherCoursesHoursDTO teacherCoursesHoursDTO =
                new Gson().fromJson(jSonTchDTO, TeacherCoursesHoursDTO.class);

        teacherRepository.save(getTeacherFromDTO(teacherCoursesHoursDTO.getTeacher()));
        Long id = teacherRepository.getSYTCHDTO(
                teacherCoursesHoursDTO.getIdSchool(),
                teacherCoursesHoursDTO.getSchoolYear()
                )
                .orElseThrow(() -> new IllegalStateException(String.format(
                        "SYTCH with " +
                                "school id %1$s and " +
                                "school year %2$s " +
                                "not found", teacherCoursesHoursDTO.getId(), teacherCoursesHoursDTO.getSchoolYear()
                )))
                .getId();
        SchoolYearTeacherCoursesHours sytch = teacherRepository.getSYTCH(id)
                .orElseThrow(() -> new IllegalStateException(String.format(
                        "Sytch with id %s not found", id
                )));
        TeacherCoursesHours teacherCoursesHours =
                teacherCoursesHoursRepository.save(getTCHFromDTO(teacherCoursesHoursDTO));
        sytch.getTeacherCoursesHours().add(teacherCoursesHours);
        schoolYearTeacherCoursesHoursRepository.save(sytch);
        teacherRepository.getSYTCH(id)
                .orElseThrow(() -> new IllegalStateException(String.format(
                        "Sytch with id %s not found", id
                )))
                .getTeacherCoursesHours().stream()
                .filter(tch -> tch.equals(teacherCoursesHours))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format(
                        "TCH in sytch with id %s not found", id
                )));
    }

    private Teacher getTeacherFromDTO(TeacherDTO teacherDTO){

        return new Teacher(
            true,
            teacherDTO.getCnp(),
            new TeacherPersonalDetails(
                    true,
                    teacherDTO.getFirstName(),
                    teacherDTO.getLastName(),
                    LocalDate.of(1970, 5, 9)
            )
        );
    }

    private TeacherCoursesHours getTCHFromDTO(TeacherCoursesHoursDTO teacherCoursesHoursDTO){

        return new TeacherCoursesHours(
                true,
                getByCNPForConfig(teacherCoursesHoursDTO.getTeacher().getCnp()),
                teacherCoursesHoursDTO.getNorm(),
                getCourseHours(teacherCoursesHoursDTO.getCoursesHours())
        );
    }

    private List<CourseHours> getCourseHours(List<CourseHoursDTO> courseHoursDTOs){

        return courseHoursDTOs.stream()
                .map(courseHoursDTO -> new CourseHours(
                        true,
                        teacherRepository.getCourse(courseHoursDTO.getCourseName())
                                .orElseThrow(() -> new IllegalStateException(String.format(
                                        "Course with name %s not found", courseHoursDTO.getCourseName()
                                ))),
                        courseHoursDTO.getNumberOfCourseHours()
                ))
                .collect(Collectors.toList());
    }
}
