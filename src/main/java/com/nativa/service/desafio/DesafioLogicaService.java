package com.nativa.service.desafio;

import com.nativa.exceptions.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class DesafioLogicaService {
    public int retornarMaiorDaFamiliaN(Integer numero) {
        if(numero < 0){
            throw  new BadRequestException("Informe o numero inteiro positivo");
        }else{
            List<String> numeros = Arrays.asList(numero.toString().split(""));
            Collections.sort( numeros,  Collections.reverseOrder());
            StringBuilder s = new StringBuilder();
            numeros.forEach( n -> s.append(n));
            return  (Integer.parseInt(s.toString()) >= 100000000) ? -1 : Integer.parseInt(s.toString())  ;

        }
    }
}
