package com.gdm.school_adm_v2.address;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {

    Optional<Address> getByStreetAndNumber(String street, String number);
}
