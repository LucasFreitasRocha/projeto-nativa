package com.nativa.resource.desafio;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nativa.service.desafio.DesafioLogicaService;

@RestController
@RequestMapping("/desafio-logica")
public class DesafioLogiaResource {

    @Autowired private DesafioLogicaService service;

    @GetMapping
    public ResponseEntity<?> retornarMaiorDaFamiliaN( @RequestParam BigInteger numero){
        System.out.println(numero.intValue());
        return ResponseEntity.ok(service.retornarMaiorDaFamiliaN(numero.intValue()));
    }
}
