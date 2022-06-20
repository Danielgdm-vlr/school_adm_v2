package com.gdm.school_adm_v2.school_details;

import lombok.NonNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SchoolDetailsRepository extends CrudRepository<SchoolDetails, Long> {

    @Override
    @NonNull
    Optional<SchoolDetails> findById(@NonNull Long id);
}
