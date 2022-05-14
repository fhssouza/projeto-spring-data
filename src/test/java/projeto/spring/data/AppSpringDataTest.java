package projeto.spring.data;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import projeto.spring.data.dao.InterfaceSpringDataUser;
import projeto.spring.data.dao.InterfaceTelefone;
import projeto.spring.data.model.Telefone;
import projeto.spring.data.model.UsuarioSpringData;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:META-INF/spring-config.xml" })
public class AppSpringDataTest {

	@Autowired
	private InterfaceSpringDataUser interfaceSpringUser;

	@Autowired
	private InterfaceTelefone interfaceTelefone;

	@Test
	public void testeInsert() {
		UsuarioSpringData usuarioSpringData = new UsuarioSpringData();
		usuarioSpringData.setEmail("fabricio@gmail.com");
		usuarioSpringData.setIdade(46);
		usuarioSpringData.setLogin("fabricio");
		usuarioSpringData.setSenha("123");
		usuarioSpringData.setNome("Fabricio Souza");

		interfaceSpringUser.save(usuarioSpringData);

		System.out.println("Usuário Cadastrado -> " + interfaceSpringUser.count());
	}

	@Test
	public void consultaPorId() {

		Optional<UsuarioSpringData> usuarioSpringData = interfaceSpringUser.findById(1L);
		System.out.println(usuarioSpringData.get().getNome());
		System.out.println(usuarioSpringData.get().getEmail());
		System.out.println(usuarioSpringData.get().getIdade());
		System.out.println(usuarioSpringData.get().getLogin());
		System.out.println(usuarioSpringData.get().getSenha());
		System.out.println(usuarioSpringData.get().getId());
		
		for (Telefone telefone : usuarioSpringData.get().getTelefones()) {
			System.out.println("----Telefone----");
			System.out.println(telefone.getUsuarioSpringData().getNome());
			System.out.println(telefone.getId());
			System.out.println(telefone.getTipo());
			System.out.println(telefone.getNumero());
		};
	}

	@Test
	public void consultarTodos() {

		Iterable<UsuarioSpringData> usuarioSpringData = interfaceSpringUser.findAll();

		for (UsuarioSpringData usuarioSpringData2 : usuarioSpringData) {
			System.out.println("Id: " + usuarioSpringData2.getId());
			System.out.println("Nome: " + usuarioSpringData2.getNome());
			System.out.println("Email: " + usuarioSpringData2.getEmail());
			System.out.println("Idade: " + usuarioSpringData2.getIdade());
			System.out.println("Login: " + usuarioSpringData2.getLogin());
			System.out.println("Senha: " + usuarioSpringData2.getSenha());
			System.out.println("----------------------------");
		}

	}

	@Test
	public void update() {
		Optional<UsuarioSpringData> UsuarioSpringData = interfaceSpringUser.findById(1L);

		UsuarioSpringData data = UsuarioSpringData.get();
		data.setNome("Fabio");

		interfaceSpringUser.save(data);
	}

	@Test
	public void delete() {
		Optional<UsuarioSpringData> UsuarioSpringData = interfaceSpringUser.findById(5L);

		interfaceSpringUser.delete(UsuarioSpringData.get());
	}

	@Test
	public void buscarPorNome() {
		List<UsuarioSpringData> list = interfaceSpringUser.buscarPorNome("Fabio");

		for (UsuarioSpringData usuarioSpringData : list) {
			System.out.println("Id: " + usuarioSpringData.getId());
			System.out.println("Nome: " + usuarioSpringData.getNome());
			System.out.println("Email: " + usuarioSpringData.getEmail());
			System.out.println("Idade: " + usuarioSpringData.getIdade());
			System.out.println("Login: " + usuarioSpringData.getLogin());
			System.out.println("Senha: " + usuarioSpringData.getSenha());
			System.out.println("----------------------------");
		}
	}

	@Test
	public void buscarPorNomeParam() {

		UsuarioSpringData usuarioSpringData = interfaceSpringUser.buscarPorNomeParam("Fabio");

		System.out.println("Id: " + usuarioSpringData.getId());
		System.out.println("Nome: " + usuarioSpringData.getNome());
		System.out.println("Email: " + usuarioSpringData.getEmail());
		System.out.println("Idade: " + usuarioSpringData.getIdade());
		System.out.println("Login: " + usuarioSpringData.getLogin());
		System.out.println("Senha: " + usuarioSpringData.getSenha());
		System.out.println("----------------------------");
	}

	@Test
	public void testeDeletePorNome() {
		interfaceSpringUser.deletePorNome("Fabricio Souza");
	}

	@Test
	public void testeUpdateEmailPorNome() {
		interfaceSpringUser.updateEmailPorNome("souza@gmail.com", "Fabio");
	}
	
	@Test
	public void testeInsertTelefone() {
		
		Optional<UsuarioSpringData> usuarioSpringData = interfaceSpringUser.findById(2L);
				
		Telefone telefone = new Telefone();
		telefone.setTipo("Casa");
		telefone.setNumero("918888-8888");
		telefone.setUsuarioSpringData(usuarioSpringData.get());
		
		interfaceTelefone.save(telefone);
	}
}
