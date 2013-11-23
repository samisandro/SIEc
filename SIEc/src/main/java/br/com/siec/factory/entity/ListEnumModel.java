package br.com.siec.factory.entity;

import br.com.siec.model.persistence.resource.Categorias;
import br.com.siec.model.persistence.resource.Estados;
import br.com.siec.model.persistence.resource.StatusPedido;
import br.com.siec.model.persistence.resource.TipoEndereco;
import br.com.siec.model.persistence.resource.TipoPreco;
import br.com.siec.model.persistence.resource.TipoSexo;
import br.com.siec.model.persistence.resource.TipoTelefone;
import br.com.siec.model.persistence.resource.TipoUsuario;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author Josimar
 */
public class ListEnumModel {

    public static List getEstados() {
        List estados = new ArrayList();
        estados.addAll(Arrays.asList(Estados.values()));
        return estados;
    }

    public static List getTipoEndereco() {
        List tipoEndereco = new ArrayList();
        tipoEndereco.addAll(Arrays.asList(TipoEndereco.values()));
        return tipoEndereco;
    }

    public static List getTipoTelefone() {
        List tipoTelefone = new ArrayList();
        tipoTelefone.addAll(Arrays.asList(TipoTelefone.values()));
        return tipoTelefone;
    }

    public static List<TipoUsuario> getTipoUsuario() {
        List<TipoUsuario> tipos = new ArrayList<TipoUsuario>();
        tipos.addAll(Arrays.asList(TipoUsuario.values()));        
        return tipos;
    }

    public static List getTipoSexo() {
        List sexo = new ArrayList();
        sexo.addAll(Arrays.asList(TipoSexo.values()));
        return sexo;
    }

    public static List getStatusPedido() {
        List status = new ArrayList();
        status.addAll(Arrays.asList(StatusPedido.values()));
        return status;
    }

    public static List getTamanhoPizza() {
        List tamanhos = new ArrayList();
        tamanhos.addAll(Arrays.asList(TipoPreco.values()));
        for(int i = 0; i < tamanhos.size(); i++){
            if(tamanhos.get(i).equals(TipoPreco.COMUM)){
                tamanhos.remove(i);
            }
        }
        return tamanhos;
    }

    public static List getCategorias() {
        List categorias = new ArrayList();
        categorias.addAll(Arrays.asList(Categorias.values()));
        return categorias;
    }

    public static List getCategoriasWithoutComposition() {
        List categorias = new ArrayList();
        for (int i = 0; i < Arrays.asList(Categorias.values()).size(); i++) {
            if (!Arrays.asList(Categorias.values()).get(i).name().equals(Categorias.Composicao.toString())) {
                categorias.add(Arrays.asList(Categorias.values()).get(i));
            }
        }
        return categorias;
    }
}
