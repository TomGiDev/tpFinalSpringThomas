package com.poei.spring.tt.tpTTSpring.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehiculeDto {

    private Integer id;
    private String plate;
    private String brand;
    private String year;
    private Integer technicianId;
}
