package com.index.stylefahion.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "provedores")
public class Provedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer provid;

    @Column
    private String nom_prov;

    @Column
    private String tel_prov;

    @Column
    private String correo_prov;

    @Column
    private String direcc_prov;

}
