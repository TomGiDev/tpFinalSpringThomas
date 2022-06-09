package com.poei.spring.tt.tpTTSpring.mapper;


import com.poei.spring.tt.tpTTSpring.api.dto.ManagerDto;
import com.poei.spring.tt.tpTTSpring.model.Manager;
import com.poei.spring.tt.tpTTSpring.model.Technician;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface ManagerMapper {

    @Mapping(target = "technicians", expression = "java(getTechnicians(manager))")
    ManagerDto mapToDto(Manager manager);

    default List<TechnicianDto> getTechnicians(Manager manager) {
        List<TechnicianDto> technicians = new ArrayList<>();
        if(manager.getTechnicians() != null) {
            technicians = manager.getTechnicians().stream()
                    .map(technician -> new TechnicianDto(
                            technician.getId(),
                            technician.getLastname(),
                            technician.getFirstname(),
                            technician.getAge(),
                            manager.getId(),
                            technician.getAddress().getId()

                    ))
                    .toList();
        }
        return technicians;
    }


    Manager mapToModel(ManagerDto managerDto);
}
