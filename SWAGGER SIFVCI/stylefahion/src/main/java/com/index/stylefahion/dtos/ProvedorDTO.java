package com.index.stylefahion.dtos;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProvedorDTO implements Serializable {

    private Integer provid;
    private String nom_prov;
    private String tel_prov;
    private String correo_prov;

}
