/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siec.test;

import br.com.siec.model.persistence.entity.Pessoa;
import br.com.siec.model.persistence.entity.Pj;
import br.com.siec.model.persistence.entity.Usuario;
import br.com.siec.facade.UsuarioFacade;
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

            UsuarioFacade personService =
                    context.getBean("userService", UsuarioFacade.class);

            Pessoa pessoa = new Pj();
            pessoa.setNome("Spring");
            pessoa.setEmail("spring_core@spring.source");
            Usuario user = new Usuario(pessoa);
            user.setAtivo(true);
            personService.save(user);

        } catch (Exception e) {
        }
    }
}
