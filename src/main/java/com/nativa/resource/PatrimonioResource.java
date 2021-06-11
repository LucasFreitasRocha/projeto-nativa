package com.nativa.resource;

import com.nativa.dto.in.PatrimonioDTO;
import com.nativa.model.Patrimonio;
import com.nativa.service.MarcaService;
import com.nativa.service.PatrimonioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/patrimonio")
public class PatrimonioResource {
    @Autowired private PatrimonioService service;


   @PostMapping
    public  ResponseEntity<Patrimonio> createPatrimonio(@RequestBody PatrimonioDTO patrimonioDTO){
       Patrimonio patrimonio = service.create(patrimonioDTO);
       URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(patrimonio.getId()).toUri();
       return ResponseEntity.created(uri).body(patrimonio);

   }
}
