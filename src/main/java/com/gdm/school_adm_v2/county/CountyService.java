package com.gdm.school_adm_v2.county;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountyService {

    private final CountyRepository countyRepository;

    @Autowired
    public CountyService(CountyRepository countyRepository) {
        this.countyRepository = countyRepository;
    }

    public Iterable<County> getAll(){

        return countyRepository.findAll();
    }

    public County saveOrUpdate(County county){

        return countyRepository.save(county);
    }

    public Iterable<County> saveOrUpdateAll(Iterable<County> counties){

        return countyRepository.saveAll(counties);
    }

    public County getByNameWithoutDiacritics(String nameWithoutDiacritics){

        return countyRepository.getByNameWithoutDiacritics(nameWithoutDiacritics)
                .orElseThrow(() -> new IllegalStateException(String.format(
                        "County with nameWithoutDiacritics %s was not found!", nameWithoutDiacritics
                )));
    }
}
