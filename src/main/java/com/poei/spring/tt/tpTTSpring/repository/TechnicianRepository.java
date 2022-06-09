package com.poei.spring.tt.tpTTSpring.repository;

import com.poei.spring.tt.tpTTSpring.model.Technician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnicianRepository extends JpaRepository<Technician, Integer> {
}
