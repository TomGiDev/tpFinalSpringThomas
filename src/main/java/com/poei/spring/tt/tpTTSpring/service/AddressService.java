package com.poei.spring.tt.tpTTSpring.service;

import com.poei.spring.tt.tpTTSpring.model.Address;

import java.util.List;

public interface AddressService {
    List<Address> getAll();

    Address getById(Integer id);

    Address createAddress(Address address);

    void deleteAddress(Integer id);

    Address updateAddress(Address address);

}
