package com.gdm.school_adm_v2.user;

import com.gdm.school_adm_v2.school.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveOrUpdate(User user){

        return userRepository.save(user);
    }

    public Boolean checkIfUserExists(UserDTO userDTO) {

        return userRepository.checkIfUserExists(userDTO.getUsername(), userDTO.getPassword());
    }

    public User getByUsername(String username) {

        return userRepository.getByUsername(username)
                .orElseThrow(() -> new IllegalStateException(String.format(
                        "User with username %s not found", username
                )));
    }

    public School getSchoolByUserId(Long id){

        return userRepository.getSchoolByUserId(id)
                .orElseThrow(() -> new IllegalStateException(String.format(
                        "School by user with id %s not found", id
                )));
    }
}
