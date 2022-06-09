package com.poei.spring.tt.tpTTSpring.service.impl;

import com.poei.spring.tt.tpTTSpring.exception.NotAllowedToDeleteException;
import com.poei.spring.tt.tpTTSpring.exception.UnknownResourceException;
import com.poei.spring.tt.tpTTSpring.model.Vehicule;
import com.poei.spring.tt.tpTTSpring.repository.VehiculeRepository;
import com.poei.spring.tt.tpTTSpring.service.VehiculeService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehiculeServiceImpl implements VehiculeService {

    private VehiculeRepository vehiculeRepository;

    @Override
    public List<Vehicule> getAll() {
        return this.vehiculeRepository.findAll(Sort.by("plate").ascending());
    }

    @Override
    public Vehicule getById(Integer id) {
        return vehiculeRepository.findById(id).orElseThrow(() -> new UnknownResourceException("No vehicule was found for this ID"));
    }

    @Override
    public Vehicule createVehicule(Vehicule vehicule) {
        return this.vehiculeRepository.save(vehicule);
    }

    @Override
    public void deleteVehicule(Integer id) {
        Vehicule vehiculeToDelete = this.getById(id);
        if (this.canDeleteVehicule(vehiculeToDelete)) {
            this.vehiculeRepository.delete(vehiculeToDelete);
        } else {
            throw new NotAllowedToDeleteException("The given vehicule belongs to a technician");
        }
    }

    @Override
    public Vehicule updateVehicule(Vehicule vehicule) {
        Vehicule vehiculeToUpdate = this.getById(vehicule.getId());
        vehiculeToUpdate.setPlate(vehicule.getPlate());
        vehiculeToUpdate.setBrand(vehicule.getBrand());
        vehiculeToUpdate.setYearconstruction(vehicule.getYearconstruction());
        vehiculeToUpdate.setTechnician(vehicule.getTechnician());

        return this.vehiculeRepository.save(vehiculeToUpdate);
    }

    private boolean canDeleteVehicule(Vehicule vehicule) {
        return (null == vehicule.getTechnician());
    }
}
