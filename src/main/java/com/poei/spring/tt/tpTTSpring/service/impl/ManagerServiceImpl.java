package com.poei.spring.tt.tpTTSpring.service.impl;


import com.poei.spring.tt.tpTTSpring.exception.NotAllowedToDeleteException;
import com.poei.spring.tt.tpTTSpring.exception.UnknownRessourceException;
import com.poei.spring.tt.tpTTSpring.model.Manager;
import com.poei.spring.tt.tpTTSpring.repository.ManagerRepository;
import com.poei.spring.tt.tpTTSpring.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerRepository managerRepository;

    @Override
    public List<Manager> getAll() {
        return this.managerRepository.findAll(Sort.by("lastname").ascending());
    }

    @Override
    public Manager getById(Integer id) {
        return this.managerRepository.findById(id).orElseThrow(() -> new UnknownRessourceException("No manager found for this ID"));
    }

    @Override
    public Manager createManager(Manager manager) {
        return this.managerRepository.save(manager);
    }

    @Override
    public Manager updateManager(Manager manager) {

        Manager managerToUpdate = this.getById(manager.getId());

        managerToUpdate.setLastname(manager.getLastname());
        managerToUpdate.setFirstname(manager.getFirstname());
        managerToUpdate.setPhonenumber(manager.getPhonenumber());
        managerToUpdate.setMobilenumber(manager.getMobilenumber());
        managerToUpdate.setTechnicians(manager.getTechnicians());

        return this.managerRepository.save(managerToUpdate);
    }

    @Override
    public void deleteManager(Integer id) {
        Manager managerToDelete = this.getById(id);
        if (this.canDeleteManager(managerToDelete)) {
            this.managerRepository.delete(managerToDelete);
        } else {
            throw new NotAllowedToDeleteException("The given manager has technicians");
        }
    }

    private boolean canDeleteManager(Manager manager) {
        return (null == manager.getTechnicians() || manager.getTechnicians().isEmpty());
    }
}
