package com.index.stylefahion.dtos;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CompraDTO implements Serializable {

    private Integer compraid;
    private LocalDate fecha;
    private String provedor;
    private String producto;
    private String empleado;

}
