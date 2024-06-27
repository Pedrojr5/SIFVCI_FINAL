package com.index.stylefahion.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.index.stylefahion.models.Provedor;

public interface repoProvedor extends JpaRepository<Provedor, Integer> {

    Provedor findProvedorByProvid(Integer provid);

}
