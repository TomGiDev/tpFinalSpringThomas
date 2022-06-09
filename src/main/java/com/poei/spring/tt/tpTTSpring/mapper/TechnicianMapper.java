package com.poei.spring.tt.tpTTSpring.mapper;

import com.poei.spring.tt.tpTTSpring.api.dto.TechnicianDto;
import com.poei.spring.tt.tpTTSpring.api.dto.WorkDto;
import com.poei.spring.tt.tpTTSpring.model.Technician;
import com.poei.spring.tt.tpTTSpring.model.Work;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TechnicianMapper {

    @Mapping(target = "works", expression = "java(getWorksId(technician))")
    TechnicianDto mapToDto(Technician technician);

    @Mapping(target = "works", expression = "java(getWorks(technicianDto))")
    Technician mapToModel(TechnicianDto technicianDto);

    default List<Integer> getWorksId(Technician technician) {
        if (technician.getWorks() != null) {
            return technician.getWorks().stream()
                    .map(Work::getId).toList();
        }
        return new ArrayList<>();
    }

    default List<Work> getWorks(TechnicianDto technicianDto) {
        if (technicianDto.getWorks() != null) {
            return technicianDto.getWorks().stream()
                    .map(Work::new)
                    .toList();
        }
        return new ArrayList<>();
    }
}
