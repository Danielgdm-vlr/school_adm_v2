package com.gdm.school_adm_v2.school_details;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/school-details")
public class SchoolDetailsController {

    private final SchoolDetailsService schoolDetailsService;

    @Autowired
    public SchoolDetailsController(SchoolDetailsService schoolDetailsService) {
        this.schoolDetailsService = schoolDetailsService;
    }

    @GetMapping("{id}")
    public SchoolDetails findById(@PathVariable("id") Long id){

        return schoolDetailsService.findById(id);
    }
}
