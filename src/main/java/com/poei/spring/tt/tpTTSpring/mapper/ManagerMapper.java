package com.poei.spring.tt.tpTTSpring.mapper;


import com.poei.spring.tt.tpTTSpring.api.dto.ManagerDto;
import com.poei.spring.tt.tpTTSpring.api.dto.TechnicianDto;
import com.poei.spring.tt.tpTTSpring.model.Manager;
import com.poei.spring.tt.tpTTSpring.model.Technician;
import com.poei.spring.tt.tpTTSpring.model.Work;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface ManagerMapper {

    @Mapping(target = "technicians", expression = "java(getTechniciansId(manager))")
    ManagerDto mapToDto(Manager manager);

    default List<Integer> getTechniciansId(Manager manager) {
        if (manager.getTechnicians() != null) {
            return manager.getTechnicians().stream()
                    .map(Technician::getId).toList();
        }
        return new ArrayList<>();
    }

    default List<Technician> getTechnicians(ManagerDto managerDto) {
        if (managerDto.getTechnicians() != null) {
            return managerDto.getTechnicians().stream()
                    .map(Technician::new)
                    .toList();
        }
        return new ArrayList<>();
    }

    /*default List<TechnicianDto> getTechnicians(Manager manager) {
        List<TechnicianDto> technicians = new ArrayList<>();
        if(manager.getTechnicians() != null) {
            technicians = manager.getTechnicians().stream()
                    .map(technician -> new TechnicianDto(
                            technician.getId(),
                            technician.getLastname(),
                            technician.getFirstname(),
                            technician.getAge(),
                            technician.getManager(),
                            technician.getAddress(),
                            technician.getWorks().stream().map(w -> w.getId()).toList(),
                            technician.getVehicule()

                    ))
                    .toList();
        }
        return technicians;
    }*/

    @Mapping(target = "technicians", expression = "java(getTechnicians(managerDto))")
    Manager mapToModel(ManagerDto managerDto);
}
