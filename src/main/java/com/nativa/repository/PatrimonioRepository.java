package com.nativa.repository;

import com.nativa.model.Marca;
import com.nativa.model.Patrimonio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PatrimonioRepository extends JpaRepository<Patrimonio, String> {

    @Query("select MAX(nTombo) from #{#entityName} ")
    Integer findLastCode();

}
