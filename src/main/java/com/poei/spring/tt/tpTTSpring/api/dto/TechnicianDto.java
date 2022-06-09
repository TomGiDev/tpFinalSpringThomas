package com.poei.spring.tt.tpTTSpring.api.dto;

import com.poei.spring.tt.tpTTSpring.model.Address;
import com.poei.spring.tt.tpTTSpring.model.Manager;
import com.poei.spring.tt.tpTTSpring.model.Vehicule;
import com.poei.spring.tt.tpTTSpring.model.Work;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TechnicianDto {
    private Integer id;
    private String firstname;
    private String lastname;
    private String age;
    private Manager manager;
    private Address address;
    private List<Integer> works;
    private Vehicule vehicule;
}
