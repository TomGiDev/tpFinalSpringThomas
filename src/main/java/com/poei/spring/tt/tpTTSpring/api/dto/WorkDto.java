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
public class WorkDto {
    private Integer id;
    private String workname;
    private String workprice;
    private List<Integer> technicians;
}
