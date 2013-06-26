/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siec.test;

import br.com.siec.model.persistence.dao.IAcompanhamentoDAO;
import br.com.siec.model.persistence.dao.IComponenteDAO;
import br.com.siec.model.persistence.daoImpl.AcompanhamentoDAO;
import br.com.siec.model.persistence.daoImpl.ComponenteDAO;
import br.com.siec.model.persistence.entity.Acompanhamento;
import br.com.siec.model.persistence.entity.Componente;
import br.com.siec.model.persistence.entity.Produto;
import br.com.siec.model.persistence.util.Categorias;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author josimar
 */
public class FastTest {

    public static void main(String args[]) {

        IAcompanhamentoDAO acompanhamentoDao = new AcompanhamentoDAO();;
        IComponenteDAO componenteDao = new ComponenteDAO();

        List<Acompanhamento> acompanhamentos = acompanhamentoDao.listAll();
        List<Componente> ingredientes = componenteDao.listAll();
        System.out.println("acompanhamentos " + acompanhamentos.size());
        System.out.println("ingredientes " + ingredientes.size());
        List<Produto> produtos = new ArrayList<Produto>();

        produtos.addAll(ingredientes);
        produtos.addAll(acompanhamentos);

        for (int i = 0; i < produtos.size(); produtos.size()) {
            if (produtos.get(i).getCategoria().equals(Categorias.Composicao)) {
                produtos.remove(i);
            }

            System.out.println("Produtos " + produtos.size());

        }
    }
}
    /**
     * if(categorias.getCategorias().get(i).getClass().getName().equals("Composicao")){
     * categorias.getCategorias().remove(i); }
     */