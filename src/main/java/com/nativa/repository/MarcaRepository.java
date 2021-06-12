package com.nativa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nativa.model.Marca;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, String> {

    Optional<Marca> findByName(String name);

    List<Marca> findByNameContaining(String name);
}
