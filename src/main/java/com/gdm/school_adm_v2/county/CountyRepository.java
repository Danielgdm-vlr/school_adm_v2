package com.gdm.school_adm_v2.county;

import com.gdm.school_adm_v2.city.City;
import com.gdm.school_adm_v2.school.School;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CountyRepository extends CrudRepository<County, Long> {

    Optional<County> getByNameWithoutDiacritics(String nameWithoutDiacritics);

    @Query(
            "select c from county c where c.user.id = ?1"
    )
    Optional<County> getCountyFromLoggerUser(Long id);

    @Query(
            "select c.cities from county c where c.id = ?1"
    )
    Optional<List<City>> getCities(Long id);

    @Query(
            "select s from county c " +
                    "inner join city ct on ct.county.id = c.id " +
                    "inner join address a on a.city.id = ct.id " +
                    "inner join school s on s.address.id = a.id " +
                    "where c.id = ?1"
    )
    Optional<List<School>> getAllSchools(Long id);
}
