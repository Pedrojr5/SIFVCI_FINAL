package index.sifvci.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import index.sifvci.models.Cliente;

public interface repoCliente extends JpaRepository<Cliente, Integer> {
    Optional<Cliente> findById(Integer id);

}
