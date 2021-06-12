package com.nativa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nativa.dto.in.MarcaDTO;
import com.nativa.exceptions.BadRequestException;
import com.nativa.exceptions.ObjectNotFoundException;
import com.nativa.model.Marca;
import com.nativa.repository.MarcaRepository;

@Service
public class MarcaService {

    @Autowired private MarcaRepository marcaRepository;

  
    public Marca create(MarcaDTO marcaDTO) {
        verificaNome(marcaDTO.getName());
        Marca marca = new Marca(marcaDTO);
        return marcaRepository.save(marca);
    }

    private void verificaNome(String name) {
        Optional<Marca> optMarca = marcaRepository.findByName(name);
        if(optMarca.isPresent()){
            throw  new BadRequestException("Já existe uma marca com esse nome:  " + name);
        }
    }

    public Page<Marca> index(Pageable paginacao) {
        return marcaRepository.findAll(paginacao);
    }

    public List<Marca> findByName(String name) {
        return marcaRepository.findByNameContaining(name.toLowerCase());
    }

    public Marca show(String id) {
        Optional<Marca> optMarca = marcaRepository.findById(id);
        return optMarca.orElseThrow(() -> new ObjectNotFoundException(
                "Marca não encontrada com esse id: " + id));
    }

    public void update(String id, MarcaDTO marcaDTO) {
        verificaNome(marcaDTO.getName());
        Marca marca =  show(id);
        marca.setName(marcaDTO.getName().toLowerCase());
        marcaRepository.save(marca);

    }

    public void delete(String id) {
        Marca marca = show(id);
        marcaRepository.delete(marca);
    }
}
