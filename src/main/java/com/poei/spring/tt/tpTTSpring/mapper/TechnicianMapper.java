package com.poei.spring.tt.tpTTSpring.mapper;

import com.poei.spring.tt.tpTTSpring.api.dto.TechnicianDto;
import com.poei.spring.tt.tpTTSpring.api.dto.WorkDto;
import com.poei.spring.tt.tpTTSpring.model.Technician;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TechnicianMapper {

    TechnicianDto mapTechnicianToTechnicianDto(Technician technician);

    Technician mapTechnicianDtoToTechnician(TechnicianDto technicianDto);

    default List<WorkDto> getWorks(Technician technician) {
        if (technician.getWorks() != null) {
            return technician.getWorks().stream()
                    .map(work -> new WorkDto(
                            work.getId(),
                            work.getWorkname(),
                            work.getWorkprice(),
                            work.getTechnicians().stream().map(tech -> tech.getId()).toList()
                    ))
                    .toList();
        }
        return new ArrayList<>();
    }
}
