package com.nativa.resource;

import com.nativa.dto.in.MarcaDTO;
import com.nativa.model.Marca;
import com.nativa.service.MarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Objects;

@RestController
@RequestMapping("/marca")
public class MarcaResource {
    @Autowired private MarcaService service;

    @GetMapping
    public ResponseEntity<?> index
            (@RequestParam(required = false) String name,
             Pageable paginacao)
    {
        if(Objects.isNull(name)){
            return ResponseEntity.ok(service.index(paginacao));
        }else{
            return ResponseEntity.ok(service.findByName(name));
        }
    }

    @PostMapping
    public ResponseEntity<Marca> createMarca(@RequestBody @Valid MarcaDTO marcaDTO){
        Marca marca = service.create(marcaDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(marca.getId()).toUri();
        return ResponseEntity.created(uri).body(marca);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Marca> showMarca(@PathVariable String id) {
        return ResponseEntity.ok(service.show(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateMarca(@PathVariable String id, @Valid @RequestBody MarcaDTO marcaDTO){
        service.update(id ,marcaDTO);
        return  ResponseEntity.noContent().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMarca(@PathVariable String id) {
        service.delete(id);
        return  ResponseEntity.noContent().build();
    }

}
