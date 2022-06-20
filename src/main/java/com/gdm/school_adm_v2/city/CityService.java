package com.gdm.school_adm_v2.city;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.stream.Collectors;

@Service
public class CityService {

    private final CityRepository cityRepository;

    @Autowired
    public CityService(CityRepository cityRepository) {

        this.cityRepository = cityRepository;
    }

    public Iterable<City> getAll() {

        return cityRepository.findAll();
    }

    public City saveOrUpdate(City city){

        return cityRepository.save(city);
    }

    public Iterable<City> saveOrUpdateAll(Iterable<City> cities){

        return cityRepository.saveAll(cities);
    }

    public City getByNameWithoutDiacritics(String cityNameWithoutDiacritics){

        return cityRepository.getByNameWithoutDiacritics(cityNameWithoutDiacritics)
                .orElseThrow(() -> new IllegalStateException(String.format(
                        "City with nameWithoutDiacritics %s was not found", cityNameWithoutDiacritics
                )));
    }

    public Iterable<City> getByNamesWithoutDiacritics(String... cityNames){

        /*
            iau fiecare element din sirul de nume de orase,
            pentru fiecare, creez un obiect city(getByName) - map - creeaza un stream
            streamul il bag intr-o lista
         */
        return Arrays.stream(cityNames)
                .map(this::getByNameWithoutDiacritics)
                .collect(Collectors.toList());
    }
}
