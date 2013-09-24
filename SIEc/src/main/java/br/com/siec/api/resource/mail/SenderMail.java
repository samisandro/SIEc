package br.com.siec.api.resource.mail;

import br.com.sendmail.modelo.Destinatarios;
import br.com.sendmail.operacoes.SendMail;

import br.com.siec.model.persistence.interfaces.IUsuario;
import javax.servlet.ServletRequest;

/**
 * @version 1.0.0
 * @author Josimar
 */
public class SenderMail {

    public static void sendMail(ServletRequest request, IUsuario user, TypeMail type) {
        try {
            SendMail email = new SendMail(request, "siec", null, type.toString());
            Destinatarios destinatarios = new Destinatarios(user.getPessoa().getEmail());
            email.setDestinatarios(destinatarios);
            email.setTokens("%usuario%;" + user.getPessoa().getNome(),
                    "%login%;"+user.getLogin(),
                    "%password%;"+user.getSenha());
            new Thread(email).start();
        } catch (Exception e) {
            System.out.println("Ocorreu um erro no envio do email" + e);
        }
    }
}
