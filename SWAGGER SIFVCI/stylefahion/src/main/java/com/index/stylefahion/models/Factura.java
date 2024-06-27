package com.index.stylefahion.models;

import java.time.LocalDate;

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
@Table(name = "facturas")
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer facturaid;

    @Column
    private Integer productid;

    @Column
    private Double precio_product;

    @Column
    private Integer cantidad;

    @Column
    private Integer clienteid;

    @Column
    private Integer empid;

    @Column
    private Double total;

    @Column
    private LocalDate fecha;

}
