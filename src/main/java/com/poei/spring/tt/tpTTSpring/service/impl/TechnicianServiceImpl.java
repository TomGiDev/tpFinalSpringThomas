package com.poei.spring.tt.tpTTSpring.service.impl;

import com.poei.spring.tt.tpTTSpring.exception.NotAllowedToDeleteException;
import com.poei.spring.tt.tpTTSpring.exception.UnknownResourceException;
import com.poei.spring.tt.tpTTSpring.model.Technician;
import com.poei.spring.tt.tpTTSpring.repository.TechnicianRepository;
import com.poei.spring.tt.tpTTSpring.service.TechnicianService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import java.util.List;

public class TechnicianServiceImpl implements TechnicianService {

    Logger log = LoggerFactory.getLogger(TechnicianServiceImpl.class);

    @Autowired
    private TechnicianRepository technicianRepository;

    @Override
    public List<Technician> getAll() {
        return this.technicianRepository.findAll(Sort.by("lastname").ascending());
    }

    @Override
    public Technician getById(Integer id) {
        return this.technicianRepository.findById(id)
                .orElseThrow(() -> new UnknownResourceException("No technician found for this city"));
    }

    @Override
    public Technician createTechnician(Technician technician) {
        log.debug("Attempting to save in DB...");
        return this.technicianRepository.save(technician);
    }

    @Override
    public void deleteTechnician(Integer id) {
        Technician technicianToDelete = this.getById(id);

        if (this.canDeleteTechnician(technicianToDelete)) {
            this.technicianRepository.delete(technicianToDelete);
        } else {
            throw new NotAllowedToDeleteException("The given technician still link to technicians.");
        }
    }

    @Override
    public Technician updateTechnician(Technician technician) {
        log.debug("Attempting to update technician {}", technician.getId());
        Technician existingTechnician = this.getById(technician.getId());
        existingTechnician.setFirstname(technician.getFirstname());
        existingTechnician.setLastname(technician.getLastname());
        existingTechnician.setAge(technician.getAge());
        existingTechnician.setAddress(technician.getAddress());
        existingTechnician.setManager(technician.getManager());
        existingTechnician.setVehicule(technician.getVehicule());
        existingTechnician.setWorks(technician.getWorks());

        return this.technicianRepository.save(existingTechnician);
    }

    private boolean canDeleteTechnician(Technician technician) {
        return
                (null == technician.getVehicule()) &&
                (null == technician.getAddress()) &&
                (null == technician.getWorks()) &&
                (null == technician.getManager());
    }
}
