package com.index.stylefahion.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.index.stylefahion.models.Producto;

public interface repoProducto extends JpaRepository<Producto, Integer> {

    Producto findProductoById(Integer id);

}
