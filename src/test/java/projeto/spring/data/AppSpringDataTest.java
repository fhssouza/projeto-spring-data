package projeto.spring.data;

import java.util.Iterator;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import projeto.spring.data.dao.InterfaceSpringDataUser;
import projeto.spring.data.model.UsuarioSpringData;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:META-INF/spring-config.xml" })
public class AppSpringDataTest {

	@Autowired
	private InterfaceSpringDataUser interfaceSpringUser;

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
		
		Optional<UsuarioSpringData> usuarioSpringData = interfaceSpringUser.findById(3L);
		System.out.println(usuarioSpringData.get().getNome());
		System.out.println(usuarioSpringData.get().getEmail());
		System.out.println(usuarioSpringData.get().getIdade());
		System.out.println(usuarioSpringData.get().getLogin());
		System.out.println(usuarioSpringData.get().getSenha());
		System.out.println(usuarioSpringData.get().getId());
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

}
