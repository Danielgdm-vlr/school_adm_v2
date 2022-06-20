package com.gdm.school_adm_v2.school_year;

import java.util.List;

public class SchoolYearConfig {

    public static void createSchoolYears(SchoolYearService schoolYearService){

        schoolYearService.saveOrUpdateAll(List.of(
                new SchoolYear(
                        true,
                        "2021-2022"
                ),
                new SchoolYear(
                        true,
                        "2020-2021"
                )
        ));
    }
}
