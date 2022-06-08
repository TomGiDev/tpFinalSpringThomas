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
@Table(name = "adresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100)
    private Integer streenumber;

    @Column(length = 255)
    private String road;

    @Column(length = 255)
    private String city;

    @OneToOne(mappedBy = "address")
    private Technician technicians;

}
