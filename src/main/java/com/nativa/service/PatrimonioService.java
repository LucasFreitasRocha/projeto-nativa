package com.nativa.service;

import com.nativa.dto.in.PatrimonioDTO;
import com.nativa.model.Patrimonio;
import com.nativa.repository.PatrimonioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class PatrimonioService {
    @Autowired private MarcaService marcaService;

    @Autowired private PatrimonioRepository patrimonioRepository;

    public Patrimonio create(PatrimonioDTO patrimonioDTO) {
        Patrimonio patrimonio = new Patrimonio(patrimonioDTO);
        patrimonio.setMarca(marcaService.show(patrimonioDTO.getMarcaId()));
        patrimonio.setnTombo(createNTombo());
        return patrimonioRepository.save(patrimonio);

    }

    private Integer createNTombo() {
        Integer nTombo = patrimonioRepository.findLastCode();
        return (Objects.isNull(nTombo)) ? 1 :  nTombo + 1;
    }

}
