package com.poei.spring.tt.tpTTSpring.service;

import com.poei.spring.tt.tpTTSpring.model.Vehicule;

import java.util.List;

public interface VehiculeService {

    List<Vehicule> getAll();

    Vehicule getById(Integer id);

    Vehicule createVehicule(Vehicule vehicule);

    void deleteVehicule(Integer id);

    Vehicule updateVehicule(Vehicule vehicule);
}
