package com.index.stylefahion.dtos;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FacturaProductoDTO implements Serializable {

    private Integer facturaid;
    private Integer productid;
    private Double precio_product;
    private Integer cantidad;
    private Double subtotal;
    private LocalDate fecha;

}
