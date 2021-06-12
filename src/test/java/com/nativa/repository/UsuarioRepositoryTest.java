package com.nativa.repository;

import static org.junit.Assert.*;

import com.nativa.dto.in.CadastroDTO;
import com.nativa.dto.out.UsuarioDTO;
import com.nativa.model.Marca;
import com.nativa.model.Usuario;
import org.checkerframework.checker.nullness.Opt;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UsuarioRepositoryTest {
	@Autowired private UsuarioRepository repo;

	@BeforeEach
	void init() {
		Usuario usuario = new Usuario(build());
		repo.save(usuario);
	}
	@AfterEach
	public  void tearDown() {
		Optional<Usuario> optUsuario = repo.findByEmail("teste@teste.com");
		if(optUsuario.isPresent()) {
			repo.delete(optUsuario.get());
		}
	}

	@Test
	public void deveriaRetornarUmUsuarioComEmail() {
		String email = "teste@teste.com";
		Optional<Usuario> optUsuario = repo.findByEmail(email);
		Assert.assertNotNull(optUsuario);
		Assert.assertEquals(email, optUsuario.get().getUsername());
	}

	@Test
	public void deveriaTrazerTodosUsuariosQueTenhaTesteNoNome() {
		String name = "teste";
		List<Usuario> usuarios = repo.findByNameContaining(name);
		Assert.assertTrue(!usuarios.isEmpty());

	}

	@Test
	public void deveriaVimVazioChamandoComEmailQueNaoExisteMetodoFindByEmail(){
		String email = "another@mail.com";
		Optional<Usuario> optUsuario = repo.findByEmail(email);
		Assert.assertTrue(!optUsuario.isPresent());
	}

	@Test
	public void deveriaVimVazioChamandoComNomeQueNaoExisteMetodoFindByNameContatins(){
		String name = "Enzo";
		List<Usuario> usuarios = repo.findByNameContaining(name);
		Assert.assertTrue(usuarios.isEmpty());
	}
	public CadastroDTO build(){
		CadastroDTO cadastroDto = new CadastroDTO();
		cadastroDto.setEmail("teste@teste.com");
		cadastroDto.setName("teste Repository");
		cadastroDto.setPassword("123456789");
		return  cadastroDto;
	}

}
