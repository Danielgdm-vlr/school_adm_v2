package com.gdm.school_adm_v2.school_details;

import lombok.Data;

@Data
public class SchoolDetailsDTO {
    private String name;
    private String telephoneNumber;
    private String emailAddress;

    public SchoolDetailsDTO(String name, String telephoneNumber, String emailAddress) {
        this.name = name;
        this.telephoneNumber = telephoneNumber;
        this.emailAddress = emailAddress;
    }
}
