package com.poei.spring.tt.tpTTSpring.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "works")
public class Work {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 255)
    private String workname;

    @Column(length = 255)
    private String workprice;

    @ManyToMany(mappedBy = "works")
    private List<Technician> technicians;
}
