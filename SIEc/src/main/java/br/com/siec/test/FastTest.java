/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siec.test;

import br.com.siec.model.persistence.util.Categorias;
import br.com.siec.model.persistence.util.IListEnun;
import br.com.siec.model.persistence.util.ListEnun;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author josimar
 */
public class FastTest {

    public static void main(String args[]) {
        List categorias = new ArrayList();
        for (int i = 0; i < Arrays.asList(Categorias.values()).size(); i++) {
            if (!Arrays.asList(Categorias.values()).get(i).name().equals(Categorias.Composicao.toString())) {
                categorias.add(Arrays.asList(Categorias.values()).get(i));
            }
        }
        System.out.println("Tamanho: "+categorias);

    }
}
/**
 * if(categorias.getCategorias().get(i).getClass().getName().equals("Composicao")){
                categorias.getCategorias().remove(i);
            }
 */