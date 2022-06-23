package com.gdm.school_adm_v2.util.pdf;

import com.gdm.school_adm_v2.school.School;
import com.gdm.school_adm_v2.school.SchoolService;
import com.gdm.school_adm_v2.school_year.SchoolYear;
import com.gdm.school_adm_v2.school_year.SchoolYearService;
import com.gdm.school_adm_v2.teacher.TeacherDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/v1/pdfs")
public class PDFController {

    private final PDFService pdfService;

    @Autowired
    public PDFController(PDFService pdfService) {

        this.pdfService = pdfService;
    }

    @GetMapping(value = "reports/teachers/{idSchool}/{schoolYear}",
            produces = MediaType.APPLICATION_PDF_VALUE)
    public @ResponseBody byte[] getPDF(
            @PathVariable("idSchool") Long idSchool,
            @PathVariable("schoolYear") String schoolYearVar){

        return pdfService.getPDF(idSchool, schoolYearVar);
    }
}
