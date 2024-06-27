package com.index.stylefahion.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.index.stylefahion.models.Factura;

public interface repoFactura extends JpaRepository<Factura, Integer> {

    Factura findFacturaByFacturaid(Integer facturaid);

}
