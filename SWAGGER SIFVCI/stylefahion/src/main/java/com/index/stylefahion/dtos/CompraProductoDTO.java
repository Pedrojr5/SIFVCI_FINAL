package com.index.stylefahion.dtos;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompraProductoDTO implements Serializable {

    private Integer compraid;
    private Integer productid;
    private Integer cantidad;
    private Double subtotal;

}
