package com.index.stylefahion.dtos;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmpleadoDTO implements Serializable {

    private Integer empid;
    private String nombre_completo;
    private String cargo;
    private String telefono;
    private String direccion;

}
