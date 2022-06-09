package com.poei.spring.tt.tpTTSpring.mapper;

import com.poei.spring.tt.tpTTSpring.api.dto.AddressDto;
import com.poei.spring.tt.tpTTSpring.model.Address;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AddressMapper {

    AddressDto mapToDto(Address address);

    Address matToModel(AddressDto addressDto);
}
