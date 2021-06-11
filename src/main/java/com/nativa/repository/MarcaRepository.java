package com.nativa.repository;

import com.nativa.model.Marca;
import com.nativa.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, String> {

    Optional<Marca> findByName(String name);

    List<Marca> findByNameContaining(String name);
}
