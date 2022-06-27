package com.gdm.school_adm_v2.county;

import com.gdm.school_adm_v2.city.City;
import com.gdm.school_adm_v2.school.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public County getCountyFromLoggerUser(Long id) {

        return countyRepository.getCountyFromLoggerUser(id)
                .orElseThrow(() -> new IllegalStateException(String.format(
                        "County from " +
                                "user with id %s " +
                                "was not found", id
                )));
    }

    public List<City> getCities(Long id) {

        return countyRepository.getCities(id)
                .orElseThrow(() -> new IllegalStateException(String.format(
                        "Cities in " +
                                "county with id %s " +
                                "were not found", id
                )));
    }

    public List<School> getAllSchools(Long id) {

        return countyRepository.getAllSchools(id)
                .orElseThrow(() -> new IllegalStateException(String.format(
                        "Schools in " +
                                "county with id %s " +
                                "were not found", id
                )));
    }
}
