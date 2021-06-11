package com.nativa.resource;

import com.nativa.dto.in.PatrimonioDTO;
import com.nativa.model.Patrimonio;
import com.nativa.service.MarcaService;
import com.nativa.service.PatrimonioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Objects;

@RestController
@RequestMapping("/patrimonio")
public class PatrimonioResource {
    @Autowired private PatrimonioService service;

    @GetMapping
    public ResponseEntity<?> index(@RequestParam(required = false) Integer nTombo, Pageable paginacao){
        return (Objects.isNull(nTombo))?  ResponseEntity.ok(service.index(paginacao))
                : ResponseEntity.ok( service.findByNtombo(nTombo));
    }
   @PostMapping
    public  ResponseEntity<Patrimonio> createPatrimonio( @Valid @RequestBody PatrimonioDTO patrimonioDTO){
       Patrimonio patrimonio = service.create(patrimonioDTO);
       URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(patrimonio.getId()).toUri();
       return ResponseEntity.created(uri).body(patrimonio);

   }


   @GetMapping("/{id}")
   public  ResponseEntity<Patrimonio> showPatrimonio(@PathVariable String id) {
       return ResponseEntity.ok(service.find(id));
   }

   @PutMapping("/{id}")
    public ResponseEntity<Void> updatePatrimonio(@PathVariable String id, @Valid @RequestBody PatrimonioDTO patrimonioDTO) {
        service.updatePatrimonio(id, patrimonioDTO);
       return  ResponseEntity.noContent().build();
   }

   @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatrimonio(@PathVariable String id){
        service.delete(id);
       return  ResponseEntity.noContent().build();
   }
}
