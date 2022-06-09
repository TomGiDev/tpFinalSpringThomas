package com.poei.spring.tt.tpTTSpring.service.impl;

import com.poei.spring.tt.tpTTSpring.exception.UnknownResourceException;
import com.poei.spring.tt.tpTTSpring.model.Address;
import com.poei.spring.tt.tpTTSpring.repository.AddressRepository;
import com.poei.spring.tt.tpTTSpring.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public List<Address> getAll() {return this.addressRepository.findAll(Sort.by("city").ascending());}

    @Override
    public Address getById(Integer id) {
        return this.addressRepository.findById(id)
                .orElseThrow(() -> new UnknownResourceException("No address found for this city"));
    }
}
