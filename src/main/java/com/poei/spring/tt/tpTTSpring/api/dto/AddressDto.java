package com.poei.spring.tt.tpTTSpring.api.dto;

import com.poei.spring.tt.tpTTSpring.model.Technician;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {

    private Integer id;
    private Integer streenumber;
    private String road;
    private String city;
    private Integer technicianId;
}
