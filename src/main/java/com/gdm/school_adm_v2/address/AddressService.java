package com.gdm.school_adm_v2.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Address saveOrUpdate(Address address){

        return addressRepository.save(address);
    }

    public Iterable<Address> saveOrUpdateAll(Iterable<Address> addresses){

        return addressRepository.saveAll(addresses);
    }

    public Iterable<Address> getAll(){

        return addressRepository.findAll();
    }

    public Address getByStreetAndNumber(String street, String number){

        return addressRepository.getByStreetAndNumber(street, number)
                .orElseThrow(() -> new IllegalStateException(String.format(
                        "Adrress with street %1$s and number %2$s", street, number
                )));
    }
}
