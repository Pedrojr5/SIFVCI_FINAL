package com.index.stylefahion.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.index.stylefahion.models.Compra;

public interface repoCompra extends JpaRepository<Compra, Integer> {

    Compra findCompraByCompraid(Integer compraid);

}
