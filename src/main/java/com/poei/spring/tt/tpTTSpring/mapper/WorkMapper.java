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
public interface WorkMapper {
    @Mapping(target = "technicians", expression = "java(getTechniciansId(work))")
    WorkDto mapToDto(Work work);

    @Mapping(target = "technicians", expression = "java(getTechnicians(workDto))")
    Work mapToModel(WorkDto workDto);

    default List<Integer> getTechniciansId(Work work) {
        if (work.getTechnicians() != null) {
            return work.getTechnicians().stream()
                    .map(Technician::getId).toList();
        }
        return new ArrayList<>();
    }

    default List<Technician> getTechnicians(WorkDto workDto) {
        if (workDto.getTechnicians() != null) {
            return workDto.getTechnicians().stream()
                    .map(Technician::new)
                    .toList();
        }
        return new ArrayList<>();
    }
}
