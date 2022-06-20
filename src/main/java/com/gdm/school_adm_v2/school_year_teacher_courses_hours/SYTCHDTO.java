package com.gdm.school_adm_v2.school_year_teacher_courses_hours;

import lombok.Data;

@Data
public class SYTCHDTO {

    private Long id;

    public SYTCHDTO(Long id) {
        this.id = id;
    }
}
