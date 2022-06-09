package com.poei.spring.tt.tpTTSpring.repository;

import com.poei.spring.tt.tpTTSpring.model.Work;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkRepository extends JpaRepository<Work, Integer> {
}
