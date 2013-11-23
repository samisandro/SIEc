package br.com.siec.service;

import br.com.siec.model.persistence.entity.Usuario;
import java.util.List;

/**
 * @version 1.00 August 09, 2013
 * @author Josimar
 */
public interface UsuarioService extends Service<Usuario>{
    
    public boolean desative(Usuario user);
    
    public boolean ative(Usuario user);
    
    public boolean isEmailAlredyInUse(String email);
    
    public boolean isLoginAlredyInUse(String login);
    
    public boolean authenticate(Usuario user);
    
    public List<Usuario> findBy(String param, String attribute);
    
    public List<Usuario> getLastUsers(int quantityUsers);

    public boolean isEmailValid(String email);
    
    public void recoveryPassword(String email);
}
