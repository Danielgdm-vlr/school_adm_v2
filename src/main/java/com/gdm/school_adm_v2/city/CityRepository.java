package com.gdm.school_adm_v2.city;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository extends CrudRepository<City, Long> {

    Optional<City> getByNameWithoutDiacritics(String cityWithoutDiacritics);
}
