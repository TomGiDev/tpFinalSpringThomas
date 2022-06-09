package com.poei.spring.tt.tpTTSpring.service.impl;

import com.poei.spring.tt.tpTTSpring.exception.NotAllowedToDeleteException;
import com.poei.spring.tt.tpTTSpring.exception.UnknownRessourceException;
import com.poei.spring.tt.tpTTSpring.model.Address;
import com.poei.spring.tt.tpTTSpring.repository.AddressRepository;
import com.poei.spring.tt.tpTTSpring.service.AddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    Logger log = LoggerFactory.getLogger(AddressServiceImpl.class);

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public List<Address> getAll() {
        return this.addressRepository.findAll(Sort.by("city").ascending());
    }

    @Override
    public Address getById(Integer id) {
        return this.addressRepository.findById(id)
                .orElseThrow(() -> new UnknownRessourceException("No address found for this city"));
    }

    @Override
    public Address createAddress(Address address) {
        log.debug("Attempting to save in DB...");
        return this.addressRepository.save(address);
    }

    @Override
    public void deleteAddress(Integer id) {
        Address addressToDelete = this.getById(id);

        if (this.canDeleteAddress(addressToDelete)) {
            this.addressRepository.delete(addressToDelete);
        } else {
            throw new NotAllowedToDeleteException("The given address still link to technicians.");
        }
    }

    @Override
    public Address updateAddress(Address address) {
        log.debug("Attempting to update address {}", address.getId());
        Address existingAddress = this.getById(address.getId());
        existingAddress.setStreenumber(address.getStreenumber());
        existingAddress.setRoad(address.getRoad());
        existingAddress.setCity(address.getCity());
        return this.addressRepository.save(existingAddress);
    }

    private boolean canDeleteAddress(Address address) {
        return (null == address.getTechnician());
    }
}
