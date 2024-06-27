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
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer clienteid;

    @Column
    private String nom_cli;

    @Column
    private String ap_cli;

    @Column
    private Integer doc_cli;

    @Column
    private String tel_cli;

    @Column
    private String correo_cli;

    @Column
    private String direcc_cli;

    @Column
    private String ciudad;

}
