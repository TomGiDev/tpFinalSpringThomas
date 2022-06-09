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
@Table(name = "technicians")
public class Technician {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 255)
    private String firstname;

    @Column(length = 255)
    private String lastname;

    @Column(length = 100)
    private String age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id", nullable = false)
    private Manager manager;

    @OneToOne
    @JoinColumn(name = "adresse_id", referencedColumnName = "id")
    private Address address;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "technicianWork",
            joinColumns = {@JoinColumn(name = "techinician_id")},
            inverseJoinColumns = {@JoinColumn(name = "work_id")}
    )
    private List<Work> works;

    @OneToOne
    @JoinColumn(name="vehicule_id", referencedColumnName = "id")
    private Vehicule vehicule;

    public Technician(Integer technicianId) {this.id = technicianId;}
}
