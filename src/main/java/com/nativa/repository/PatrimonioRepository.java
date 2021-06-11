package com.nativa.repository;

import com.nativa.model.Patrimonio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatrimonioRepository extends JpaRepository<Patrimonio, String> {

    @Query("select MAX(nTombo) from #{#entityName} ")
    Integer findLastCode();

    @Query("select p from #{#entityName} p  where p.nTombo = :nTombo")
    Optional<Patrimonio> findByNtombo(Integer nTombo);
}
