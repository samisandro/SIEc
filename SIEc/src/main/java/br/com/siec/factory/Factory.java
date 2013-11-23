/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siec.factory;

import br.com.siec.model.persistence.entity.Acompanhamento;
import br.com.siec.model.persistence.entity.Cliente;
import br.com.siec.model.persistence.entity.Componente;
import br.com.siec.model.persistence.entity.Composicao;
import br.com.siec.model.persistence.entity.Endereco;
import br.com.siec.model.persistence.entity.Pedido;
import br.com.siec.model.persistence.entity.Pf;
import br.com.siec.model.persistence.entity.Pj;
import br.com.siec.model.persistence.entity.Preco;
import br.com.siec.model.persistence.entity.Produto;
import br.com.siec.model.persistence.entity.Telefone;
import br.com.siec.model.persistence.entity.Usuario;
import br.com.siec.factory.entity.ListEnumModel;
import br.com.siec.service.impl.ProdutoServiceImpl;
import br.com.siec.service.impl.UsuarioServiceImpl;
import br.com.siec.factory.dao.DAOFactory;
import br.com.siec.factory.entity.PessoaFactory;
import br.com.siec.factory.entity.ProdutoFactory;
import br.com.siec.factory.entity.UserFactory;
import br.com.siec.factory.service.ServiceFactory;
import br.com.siec.model.persistence.interfaces.IUsuario;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author josimar
 */
public abstract class Factory implements AbstractFactory {

    private static Factory factory;
    private EnumMap<ClassType, Object> classLibrary;

    public Factory() {
    }

    public static AbstractFactory getInstance() {
        if (factory != null) {
            return factory;
        } else {
            return new Factory() {
            };
        }
    }

    @Override
    public <T> T createObject(ClassType typeObject) {
        createEnumMaps();
        putClassLibraryMap();
        return (T) classLibrary.get(typeObject);

    }

    @Override
    public <T> T createDependObject(ClassType typeObject, List<Object> dependencies) {
        createEnumMaps();
        putClassLibraryMap();
        
        for (int i = 0; i < dependencies.size(); i++) {
            dependencies.get(i).getClass().getName();
        }
        
        return (T) classLibrary.get(typeObject);
    }

    private void createEnumMaps() {
        this.classLibrary = new EnumMap<ClassType, Object>(ClassType.class);
    }

    private void putClassLibraryMap() {

        classLibrary.put(ClassType.Acompanhamento, new Acompanhamento());
        classLibrary.put(ClassType.ClientePF, new Cliente(new Usuario(new Pf())));
        classLibrary.put(ClassType.ClientePJ, new Cliente(new Usuario(new Pj())));
        classLibrary.put(ClassType.Componente, new Componente());
        classLibrary.put(ClassType.Composite, new Composicao());
        classLibrary.put(ClassType.Endereco, new Endereco());
        classLibrary.put(ClassType.Pedido, new Pedido());
        classLibrary.put(ClassType.PJ, new Pj());
        classLibrary.put(ClassType.PF, new Pf());
        classLibrary.put(ClassType.Telefone, new Telefone());
        classLibrary.put(ClassType.Usuario, new Usuario());
        classLibrary.put(ClassType.PessoaFactory, PessoaFactory.getInstance());
        classLibrary.put(ClassType.Preco, new Preco());
        classLibrary.put(ClassType.ProdutoFactory, ProdutoFactory.getInstance());
        classLibrary.put(ClassType.UserFactory, UserFactory.getInstance());
        classLibrary.put(ClassType.DAOFactory, DAOFactory.getInstance());
        classLibrary.put(ClassType.ServiceFactory, ServiceFactory.getInstance());
        classLibrary.put(ClassType.ProdutoService, new ProdutoServiceImpl());
        classLibrary.put(ClassType.UsuarioService, new UsuarioServiceImpl());
        classLibrary.put(ClassType.Categorias, new ListEnumModel());
        classLibrary.put(ClassType.HashMap, new HashMap<String, Double>());
        classLibrary.put(ClassType.ListProduto, new ArrayList<Produto>());
        classLibrary.put(ClassType.ListEnum, new ListEnumModel());

    }
}
