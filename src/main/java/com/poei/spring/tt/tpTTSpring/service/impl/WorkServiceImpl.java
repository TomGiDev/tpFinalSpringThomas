package com.poei.spring.tt.tpTTSpring.service.impl;

import com.poei.spring.tt.tpTTSpring.exception.NotAllowedToDeleteException;
import com.poei.spring.tt.tpTTSpring.exception.UnknownResourceException;
import com.poei.spring.tt.tpTTSpring.model.Work;
import com.poei.spring.tt.tpTTSpring.repository.WorkRepository;
import com.poei.spring.tt.tpTTSpring.service.WorkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkServiceImpl implements WorkService {
    Logger log = LoggerFactory.getLogger(WorkServiceImpl.class);

    @Autowired
    private WorkRepository workRepository;

    @Override
    public List<Work> getAll() {
        return this.workRepository.findAll(Sort.by("workname").ascending());
    }

    @Override
    public Work getById(Integer id) {
        return this.workRepository.findById(id)
                .orElseThrow(() -> new UnknownResourceException("No work found for this workname"));
    }

    @Override
    public Work createWork(Work work) {
        log.debug("Attempting to save in DB...");
        return this.workRepository.save(work);
    }

    @Override
    public void deleteWork(Integer id) {
        Work workToDelete = this.getById(id);

        if (this.canDeleteWork(workToDelete)) {
            this.workRepository.delete(workToDelete);
        } else {
            throw new NotAllowedToDeleteException("The given work still link to technicians.");
        }
    }

    @Override
    public Work updateWork(Work work) {
        log.debug("Attempting to update work {}", work.getId());
        Work existingWork = this.getById(work.getId());
        existingWork.setWorkname(work.getWorkname());
        existingWork.setWorkprice(work.getWorkprice());
        existingWork.setTechnicians(work.getTechnicians());

        return this.workRepository.save(existingWork);
    }

    private boolean canDeleteWork(Work work) {
        return (null == work.getTechnicians());
    }
}
