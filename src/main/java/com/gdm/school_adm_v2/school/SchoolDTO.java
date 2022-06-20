package com.gdm.school_adm_v2.school;

import com.gdm.school_adm_v2.school_details.SchoolDetailsDTO;
import lombok.Data;

@Data
public class SchoolDTO {

    private Long id;
    private SchoolDetailsDTO schoolDetails;

    public SchoolDTO(Long id) {
        this.id = id;
    }

    public SchoolDTO(Long id, SchoolDetailsDTO schoolDetails) {
        this.id = id;
        this.schoolDetails = schoolDetails;
    }
}
