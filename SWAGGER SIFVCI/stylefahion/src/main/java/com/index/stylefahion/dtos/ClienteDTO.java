package com.index.stylefahion.dtos;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDTO implements Serializable {

    private Integer clienteid;
    private String nombre_completo;
    private String tel_cli;
    private String direccion;

}
