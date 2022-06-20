package com.gdm.school_adm_v2.county;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountyRepository extends CrudRepository<County, Long> {

    Optional<County> getByNameWithoutDiacritics(String nameWithoutDiacritics);
}
