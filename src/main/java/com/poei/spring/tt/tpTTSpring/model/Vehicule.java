package com.poei.spring.tt.tpTTSpring.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "managers")
public class Vehicule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 7)
    private String plate;

    @Column(length = 200)
    private String brand;

    @Column(length = 4)
    private String yearconstruction;

    @OneToOne(mappedBy = "vehicule")
    private Technician technician;
}
