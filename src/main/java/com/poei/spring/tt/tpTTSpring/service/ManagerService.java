package com.poei.spring.tt.tpTTSpring.service;

import com.poei.spring.tt.tpTTSpring.model.Manager;

import java.util.List;

public interface ManagerService {

    List<Manager> getAll();

    Manager getById(Integer id);

    Manager createManager(Manager manager);

    Manager updateManager(Manager manager);

    void deleteManager(Integer id);
}
