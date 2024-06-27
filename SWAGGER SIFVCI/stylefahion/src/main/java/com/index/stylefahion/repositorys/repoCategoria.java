package com.index.stylefahion.repositorys;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.index.stylefahion.models.Categoria;

public interface repoCategoria extends JpaRepository<Categoria, Integer> {

    Optional<Categoria> findByNomcat(String nomcat);

    Optional<Categoria> findByIdcat(Integer idcat);

    Categoria findCategoriaByIdcat(Integer idcat);

}
