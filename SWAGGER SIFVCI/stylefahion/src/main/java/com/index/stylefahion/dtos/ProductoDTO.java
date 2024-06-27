package com.index.stylefahion.dtos;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductoDTO implements Serializable {

    private Integer id;
    private String nom_product;
    private String categoria;
    private String descrip_product;
    private String marca;
    private Double precio_product;

}
