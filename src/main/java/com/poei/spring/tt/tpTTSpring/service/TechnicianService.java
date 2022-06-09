package com.poei.spring.tt.tpTTSpring.service;

import com.poei.spring.tt.tpTTSpring.model.Technician;

import java.util.List;

public interface TechnicianService {
    List<Technician> getAll();

    Technician getById(Integer id);

    Technician createTechnician(Technician technician);

    void deleteTechnician(Technician technician);

    Technician updateTechnician(Technician technician);
}
