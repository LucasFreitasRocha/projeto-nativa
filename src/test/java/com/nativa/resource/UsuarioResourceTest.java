/**
 * 
 */
package com.nativa.resource;

import static org.junit.Assert.*;

import com.nativa.dto.in.CadastroDTO;
import com.nativa.dto.in.LoginDTO;
import com.nativa.dto.out.TokenDto;
import com.nativa.model.Usuario;
import com.nativa.repository.UsuarioRepository;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

/**
 * @author rocha
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioResourceTest {

	@Autowired private MockMvc mockMvc;
	@Autowired private AuthResource authResource;
	@Autowired private UsuarioRepository repository;
	@Autowired private UsuarioResource resource;

	private final String email = "teste@teste.com";

	/**
	 * Test method for {@link com.nativa.resource.UsuarioResource#index(java.lang.String, java.lang.String, org.springframework.data.domain.Pageable)}.
	 */
	@Test
	public void DeveriaRetornar200PoisEstaComTokenNaRotaIndex() throws Exception {
		URI uri = new URI("/user");
		mockMvc.perform(MockMvcRequestBuilders.get(uri)
				.header("Authorization", getToken())
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(200));
	}

	@Test
	public void DeveriaRetornar403PoisNaoEstouEnviandoOTokenNaRotaIndex() throws Exception {
		URI uri = new URI("/user");

		mockMvc.perform(MockMvcRequestBuilders.get(uri)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(403));
	}

	@Test
	public void deveriaRetornar201AoCriarUsuarioNaRotaCreateUser() throws Exception{
		URI uri = new URI("/user");
		String json =  "{\"name\": \"teste\" ," +
				" \"email\" : \"teste@teste.com\" , " +
				" \"password\" : \"12345678\" }";
		mockMvc.perform(MockMvcRequestBuilders.post(uri)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(201));

		deleteUser();

	}



	@Test
	public void deveriaRetornar400AoMandarUmUsuarioComDadoErrado() throws Exception {
		URI uri = new URI("/user");
		String json =  "{\"name\": \"teste\" ," +
				" \"email\" : \"teste\" , " +
				" \"password\" : \"12345678\" }";
		mockMvc.perform(MockMvcRequestBuilders.post(uri)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));

	}

	@Test
	public void deveriaRetornar200AoBuscarUmUsuarioComIdCorreto() throws Exception {
		URI uri = new URI("/user/" + createUser().getId());
		mockMvc.perform(MockMvcRequestBuilders.get(uri)
				.header("Authorization", getToken())
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(200));
		deleteUser();
	}

	@Test
	public void deveriaRetornar404AoBuscarUmUsuarioComIdIncorreto() throws Exception {
		URI uri = new URI("/user/invalidId");
		mockMvc.perform(MockMvcRequestBuilders.get(uri)
				.header("Authorization", getToken())
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(404));

	}

	public String getToken(){
		LoginDTO loginDTO = new LoginDTO();
		loginDTO.setEmail("rochadefreitaslucas@gmail.com");
		loginDTO.setPassword("12345678");
		TokenDto tokenDto  = authResource.authenticate(loginDTO).getBody();
		return tokenDto.getTipo() + " " + tokenDto.getToken();
	}

	private Usuario createUser() {
		CadastroDTO cadastroDTO = new CadastroDTO();
		cadastroDTO.setName("teste");
		cadastroDTO.setEmail(email);
		cadastroDTO.setPassword("12345678");
		resource.createUsuario(cadastroDTO);
		return repository.findByEmail(email).get();
	}
	private void deleteUser() {
		Optional<Usuario> optUsuario = repository.findByEmail(email);
		if(optUsuario.isPresent()) {
			repository.delete(optUsuario.get());
		}
	}



}
