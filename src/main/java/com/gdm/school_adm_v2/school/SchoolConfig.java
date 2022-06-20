package com.gdm.school_adm_v2.school;

import com.gdm.school_adm_v2.address.AddressService;
import com.gdm.school_adm_v2.school_details.SchoolDetails;
import com.gdm.school_adm_v2.user.User;
import com.gdm.school_adm_v2.util.entity.UserRole;

import java.util.List;

import static com.gdm.school_adm_v2.util.entity.UserRole.SCHOOL_ADMIN;

public class SchoolConfig {

    public static void createSchoolWithSchoolDetails(
            SchoolService schoolService,
            AddressService addressService
    ){

        schoolService.saveOrUpdateAll(List.of(
                new School(
                        true,
                        new SchoolDetails(
                                true,
                                "Scoala 5",
                                "0268935671",
                                "scoala5@gmail.com"
                        ),
                        addressService.getByStreetAndNumber("Strada Principala", "120"),
                        new User(
                                true,
                                "admin5",
                                "admin5",
                                SCHOOL_ADMIN.toString()
                        )
                ),
                new School(
                        true,
                        new SchoolDetails(
                                true,
                                "Scoala 8",
                                "0268355671",
                                "scoala8@gmail.com"
                        ),
                        addressService.getByStreetAndNumber("Bulevardul Independentei", "5"),
                        new User(
                                true,
                                "admin8",
                                "admin8",
                                SCHOOL_ADMIN.toString()
                        )
                ),
                new School(
                        true,
                        new SchoolDetails(
                                true,
                                "Scoala Cristian",
                                "0268764512",
                                "scoala.cristian@gmail.com"
                        ),
                        addressService.getByStreetAndNumber("Strada Eroilor", "32"),
                        new User(
                                true,
                                "adminC",
                                "adminC",
                                SCHOOL_ADMIN.toString()
                        )
                )
        ));
    }
}
