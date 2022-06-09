package com.poei.spring.tt.tpTTSpring.api.dto;

import com.poei.spring.tt.tpTTSpring.model.Technician;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ManagerDto {

    private Integer id;
    private String lastname;
    private String firstname;
    private String phonenumber;
    private String mobilenumber;
    private List<Integer> technicians;
}
