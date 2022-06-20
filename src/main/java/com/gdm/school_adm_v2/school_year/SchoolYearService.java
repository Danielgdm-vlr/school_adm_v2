package com.gdm.school_adm_v2.school_year;

import org.springframework.stereotype.Service;

@Service
public class SchoolYearService {

    private final SchoolYearRepository schoolYearRepository;

    public SchoolYearService(SchoolYearRepository schoolYearRepository) {

        this.schoolYearRepository = schoolYearRepository;
    }

    public SchoolYear saveOrUpdate(SchoolYear schoolYear){

        return schoolYearRepository.save(schoolYear);
    }

    public Iterable<SchoolYear> saveOrUpdateAll(Iterable<SchoolYear> schoolYears){

        return schoolYearRepository.saveAll(schoolYears);
    }

    public Iterable<SchoolYear> getAll(){

        return schoolYearRepository.findAll();
    }

    public SchoolYear getByYears(String years){

        return schoolYearRepository.getByYears(years)
                .orElseThrow(() -> new IllegalStateException(String.format(
                        "School year period of %s not found", years
                )));
    }

    public Iterable<String> getYears(){

        return schoolYearRepository.getYears()
                .orElseThrow(() -> new IllegalStateException("School years not found??"));
    }
}
