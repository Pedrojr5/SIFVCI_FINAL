package com.index.stylefahion.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.index.stylefahion.models.Cliente;

public interface repoCliente extends JpaRepository<Cliente, Integer> {

    Cliente findClienteByClienteid(Integer clienteid);

}
