package com.poei.spring.tt.tpTTSpring.mapper;

import com.poei.spring.tt.tpTTSpring.api.dto.VehiculeDto;
import com.poei.spring.tt.tpTTSpring.model.Vehicule;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface VehiculeMapper {

    @Mapping(source = "technician.id", target = "technicianId")
    @Mapping(source = "yearconstruction", target = "year")
    VehiculeDto mapToDto(Vehicule vehicule);

    @Mapping(source = "technicianId", target = "technician.id")
    @Mapping(source = "year", target = "yearconstruction")
    Vehicule mapToModel(VehiculeDto vehiculeDto);
}
