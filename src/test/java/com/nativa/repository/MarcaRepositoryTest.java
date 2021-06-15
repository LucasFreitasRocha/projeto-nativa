package com.nativa.repository;

import java.util.List;
import java.util.Optional;

import com.nativa.dto.in.MarcaDTO;
import com.nativa.dto.in.PatrimonioDTO;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.nativa.model.Marca;
import com.nativa.utils.Generate;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class MarcaRepositoryTest {

	@Autowired private MarcaRepository repo;
	
	  @BeforeEach 
	    void init() {
			Marca marca = new Marca(build());
			repo.save(marca);
	    }
	  @AfterEach
	    public  void tearDown() {
		  Optional<Marca> marca = repo.findByName("teste repository");
		  if(marca.isPresent()) {
			  repo.delete(marca.get());
		  }
	    }
	
	@Test
	public void DeveriaCarregarUmaMarcaComNomeStringTeste() {
		String teste = "teste repository";
		Optional<Marca> marcaTeste = repo.findByName(teste);
		Assert.assertNotNull(marcaTeste);
		Assert.assertEquals(teste, marcaTeste.get().getName());
		
	}

	@Test
	public void deveriaTrazerTodasMarcasQueTenhaTesteNoNome() {
		String teste = "teste";
		List<Marca> marcaTeste = repo.findByNameContaining(teste);
		Assert.assertTrue(!marcaTeste.isEmpty());
		
	}

	@Test
	public void deveriaVimVazioPassandoUmNomeQueNaoexisteNoMetedoFindByName(){
	  	String teste ="nao tem essa marca";
	  	Optional<Marca> marcaTeste = repo.findByName(teste);
			Assert.assertTrue(!marcaTeste.isPresent());
	}
	@Test
	public void deveriaVimVazioPassandoUmNomeQueNaoexisteNoMetedoFindByNameContaining(){
		String teste ="nao tem essa marca";
		List<Marca> marcaTeste = repo.findByNameContaining(teste);
		Assert.assertTrue(marcaTeste.isEmpty());
	}

	public MarcaDTO build() {
		MarcaDTO marcaDTO = new MarcaDTO();
		marcaDTO.setName("teste repository");
		return  marcaDTO;
	}

}
