package com.gdm.school_adm_v2.city;

import com.gdm.school_adm_v2.county.CountyService;
import com.gdm.school_adm_v2.util.entity.Environment;

import java.util.List;

public class CityConfig {

    public static void createAndSaveCitiesAndSetAndUpdateCounties(
            CityService cityService,
            CountyService countyService
    ){

        cityService.saveOrUpdateAll(List.of(
                new City(
                        true,
                        "Brașov",
                        "Brasov",
                        Environment.URBAN.toString(),
                        countyService.getByNameWithoutDiacritics("Brasov")
                ),
                new City(
                        true,
                        "Cristian",
                        "Cristian",
                        Environment.RURAL.toString(),
                        countyService.getByNameWithoutDiacritics("Brasov")
                )
        ));
    }
}
