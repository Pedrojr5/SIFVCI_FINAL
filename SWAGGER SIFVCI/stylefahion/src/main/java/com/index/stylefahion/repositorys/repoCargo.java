package com.index.stylefahion.repositorys;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.index.stylefahion.models.Cargo;

public interface repoCargo extends JpaRepository<Cargo, Integer> {

    public Cargo findCargoByCargoid(Integer cargoid);

    public Optional<Cargo> findCargoByNomcargo(String nomcargo);

}
