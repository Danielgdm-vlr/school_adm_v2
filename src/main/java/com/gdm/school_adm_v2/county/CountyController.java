package com.gdm.school_adm_v2.county;

import com.gdm.school_adm_v2.city.City;
import com.gdm.school_adm_v2.school.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/counties")
public class CountyController {

    private final CountyService countyService;

    @Autowired
    public CountyController(CountyService countyService) {
        this.countyService = countyService;
    }

    @GetMapping("login/{id}")
    public County getCountyFromLoggerUser(
            @PathVariable("id") Long id
    ){

        return countyService.getCountyFromLoggerUser(id);
    }

    @GetMapping("cities/{id}")
    public List<City> getCities(
            @PathVariable("id") Long id
    ){

        return countyService.getCities(id);
    }

    @GetMapping("schools/{id}")
    public List<School> getAllSchools(
            @PathVariable("id") Long id
    ){

        return countyService.getAllSchools(id);
    }
}
