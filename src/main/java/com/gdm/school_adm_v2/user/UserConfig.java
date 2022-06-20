package com.gdm.school_adm_v2.user;

import static com.gdm.school_adm_v2.util.entity.UserRole.SUPER_ADMIN;

public class UserConfig {

    public static void createUser(UserService userService){

        userService.saveOrUpdate(new User(
                true,
                "super",
                "super",
                SUPER_ADMIN.toString()
        ));
    }
}
