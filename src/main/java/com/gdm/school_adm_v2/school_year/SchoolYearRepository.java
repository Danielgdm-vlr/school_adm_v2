package com.gdm.school_adm_v2.school_year;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SchoolYearRepository extends CrudRepository<SchoolYear, Long> {

    Optional<SchoolYear> getByYears(String years);

    @Query("select sy.years from school_year sy")
    Optional<Iterable<String>> getYears();
}
