package com.gdm.school_adm_v2.school_details;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchoolDetailsService {

    private final SchoolDetailsRepository schoolDetailsRepository;

    @Autowired
    public SchoolDetailsService(SchoolDetailsRepository schoolDetailsRepository) {

        this.schoolDetailsRepository = schoolDetailsRepository;
    }

    public SchoolDetails findById(Long id){

        return schoolDetailsRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(String.format(
                        "School details with id %s not found", id
                )));
    }
}
