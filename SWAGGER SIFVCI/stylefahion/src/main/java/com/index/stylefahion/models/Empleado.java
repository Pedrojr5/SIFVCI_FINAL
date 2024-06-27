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
@Table(name = "empleados")
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer empid;

    @Column
    private String nom_emp;

    @Column
    private String ap_emp;

    @Column
    private Integer cargoid;

    @Column
    private String tel_emp;

    @Column
    private String correo_emp;

    @Column
    private String direcc_emp;

}
