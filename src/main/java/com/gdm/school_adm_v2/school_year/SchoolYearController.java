package com.gdm.school_adm_v2.school_year;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/school-years")
public class SchoolYearController {

    private final SchoolYearService schoolYearService;

    @Autowired
    public SchoolYearController(SchoolYearService schoolYearService) {
        this.schoolYearService = schoolYearService;
    }

    @GetMapping("periods")
    public Iterable<String> getYears(){

        return schoolYearService.getYears();
    }
}
