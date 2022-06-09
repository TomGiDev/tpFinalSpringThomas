package com.poei.spring.tt.tpTTSpring.service;

import com.poei.spring.tt.tpTTSpring.model.Work;

import java.util.List;

public interface WorkService {

    List<Work> getAll();

    Work getById(Integer id);

    Work createWork(Work work);

    void deleteWork(Integer id);

    Work updateWork(Work work);
}
