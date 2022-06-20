package com.gdm.school_adm_v2.county;

import com.gdm.school_adm_v2.city.City;
import com.gdm.school_adm_v2.city.CityService;
import com.gdm.school_adm_v2.user.User;

import java.util.List;

import static com.gdm.school_adm_v2.util.entity.UserRole.COUNTY_ADMIN;

public class CountyConfig {

    public static void createAndSaveCounties(CountyService countyService){

        countyService.saveOrUpdateAll(List.of(
                new County(
                        true,
                        "BV",
                        "Brașov",
                        "Brasov",
                        new User(
                                true,
                                "brasov",
                                "brasov",
                                COUNTY_ADMIN.toString()
                        )
                )
        ));
    }
}
