package projeto.spring.data;

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

}
