package com.nativa.service;

import com.nativa.dto.in.PatrimonioDTO;
import com.nativa.exceptions.ObjectNotFoundException;
import com.nativa.model.Patrimonio;
import com.nativa.repository.PatrimonioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

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

    public Page<Patrimonio> index(Pageable paginacao) {
        return patrimonioRepository.findAll(paginacao);
    }

    public Patrimonio findByNtombo(Integer nTombo) {
        Optional<Patrimonio> optPatrimonio =  patrimonioRepository.findByNtombo(nTombo);
         return optPatrimonio.orElseThrow(() -> new ObjectNotFoundException(
                "Não tem patrimonio com esse nº de tombo: " + nTombo));
    }

    public void updatePatrimonio(String id, PatrimonioDTO patrimonioDTO) {
        Patrimonio patrimonio = find(id);
        patrimonio.setName(patrimonioDTO.getName());
        patrimonio.setDescription(patrimonioDTO.getDescription());
        patrimonioRepository.save(patrimonio);

    }

    public Patrimonio find(String id) {
        Optional<Patrimonio> optPatrimonio =  patrimonioRepository.findById(id);
        return optPatrimonio.orElseThrow(() -> new ObjectNotFoundException(
                "Não tem patrimonio com esse nº de tombo: " + id));
    }

    public void delete(String id) {
        Patrimonio patrimonio = find(id);
        patrimonioRepository.delete(patrimonio);
    }
}
