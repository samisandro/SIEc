/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siec.test;

import br.com.siec.model.persistence.entity.IPessoa;
import br.com.siec.model.persistence.entity.IUsuario;
import br.com.siec.model.persistence.entity.Pj;
import br.com.siec.model.persistence.entity.Usuario;
import br.com.siec.service.IUsuarioService;
import br.com.siec.service.UsuarioService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author josimar
 */
public class SpringTest {

    public static void main(String[] args) {

        try {
            ApplicationContext context =
                    new ClassPathXmlApplicationContext(
                    new String[]{"applicationContext.xml"});

            IUsuarioService personService =
                    context.getBean("userService", UsuarioService.class);

            IPessoa pessoa = new Pj();
            pessoa.setNome("Spring");
            pessoa.setEmail("spring_core@spring.source");
            IUsuario user = new Usuario(pessoa);
            user.setAtivo(true);
            user.setLembreteSenha("Testee");
            personService.save(user);

        } catch (Exception e) {
        }
    }
}
