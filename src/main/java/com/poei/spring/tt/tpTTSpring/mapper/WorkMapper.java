package com.poei.spring.tt.tpTTSpring.mapper;

import com.poei.spring.tt.tpTTSpring.api.dto.TechnicianDto;
import com.poei.spring.tt.tpTTSpring.api.dto.WorkDto;
import com.poei.spring.tt.tpTTSpring.model.Work;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface WorkMapper {
    WorkDto mapWorkToWorkDto(Work work);

    Work mapWorkDtoToWork(WorkDto workDto);

    default List<TechnicianDto> getTechnicians(Work work) {
        if (work.getTechnicians() != null) {
            return work.getTechnicians().stream()
                    .map(technician -> new TechnicianDto(
                            technician.getId(),
                            technician.getFirstname(),
                            technician.getLastname(),
                            technician.getAge(),
                            technician.getManager(),
                            technician.getAddress(),
                            technician.getWorks().stream().map(wo -> wo.getId()).toList(),
                            technician.getVehicule()

                    ))
                    .toList();
        }
        return new ArrayList<>();
    }
}
