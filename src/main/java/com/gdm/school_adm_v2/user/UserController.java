package com.gdm.school_adm_v2.user;

import com.gdm.school_adm_v2.school.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("login")
    public Boolean checkIfUserExists(@RequestBody UserDTO userDTO) {

        return userService.checkIfUserExists(userDTO);
    }

    @GetMapping("{username}")
    public User getByUsername(@PathVariable("username") String username){
        return userService.getByUsername(username);
    }

    @GetMapping("by-user-id/{id}")
    public School getSchoolByUserId(@PathVariable("id") Long id){

        return userService.getSchoolByUserId(id);
    }
}
