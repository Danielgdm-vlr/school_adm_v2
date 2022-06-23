package com.gdm.school_adm_v2.util.pdf;

import com.gdm.school_adm_v2.school.School;
import com.gdm.school_adm_v2.school.SchoolService;
import com.gdm.school_adm_v2.school_year.SchoolYear;
import com.gdm.school_adm_v2.school_year.SchoolYearService;
import com.gdm.school_adm_v2.teacher.TeacherDTO;
import com.gdm.school_adm_v2.teacher.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

@Service
public class PDFService {

    private final SchoolService schoolService;
    private final SchoolYearService schoolYearService;
    private final TeacherService teacherService;

    @Autowired
    public PDFService(SchoolService schoolService, SchoolYearService schoolYearService, TeacherService teacherService) {

        this.schoolService = schoolService;
        this.schoolYearService = schoolYearService;
        this.teacherService = teacherService;
    }

    public byte[] getPDF(Long idSchool, String schoolYearVar) {

        School school = schoolService.getById(idSchool);
        if(!Files.exists(Path.of(String.format(
                "D:\\Workspace\\Licenta\\pdfs\\Rapoarte\\Materii profesori\\%1$s - %2$s.pdf",
                school.getSchoolDetails().getName() + "_" + school.getId(),
                LocalDate.now())))) {
            PDFTeachersReport pdfTeachersReport = new PDFTeachersReport();
            pdfTeachersReport.setSchool(school);
            SchoolYear schoolYear = schoolYearService.getByYears(schoolYearVar);
            List<TeacherDTO> teacherDTOs =
                    (List<TeacherDTO>) teacherService.getAllCoursesInSchoolInYear(
                            school.getId(), schoolYear.getYears());
            pdfTeachersReport.setTeacherDTOs(teacherDTOs);
            pdfTeachersReport.createPDF();
        }

        try{
            String pdfName = String.format(
                    "D:\\Workspace\\Licenta\\pdfs\\Rapoarte\\Materii profesori\\%1$s - %2$s.pdf",
                    school.getSchoolDetails().getName() + "_" + school.getId(),
                    LocalDate.now());
            FileInputStream fileInputStream = new FileInputStream(pdfName);
            byte[] targetArray = new byte[fileInputStream.available()];
            fileInputStream.read(targetArray);
            return targetArray;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
