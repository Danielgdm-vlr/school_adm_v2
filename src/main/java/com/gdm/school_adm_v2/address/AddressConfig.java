package com.gdm.school_adm_v2.address;

import com.gdm.school_adm_v2.city.CityService;

import java.util.List;

public class AddressConfig {

    public static void saveAndCreateAddressesAndUpdateCities(
            AddressService addressService,
            CityService cityService
    ){

        addressService.saveOrUpdateAll(List.of(
                new Address(
                        true,
                        "Strada Principala",
                        "120",
                        556677,
                        cityService.getByNameWithoutDiacritics("Brasov")
                ),
                new Address(
                        true,
                        "Bulevardul Independentei",
                        "5",
                        236431,
                        cityService.getByNameWithoutDiacritics("Brasov")
                ),
                new Address(
                        true,
                        "Strada Eroilor",
                        "32",
                        557024,
                        cityService.getByNameWithoutDiacritics("Cristian")
                )
        ));
    }
}
