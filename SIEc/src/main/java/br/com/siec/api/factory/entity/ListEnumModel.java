package br.com.siec.api.factory.entity;

import br.com.siec.model.persistence.util.Categorias;
import br.com.siec.model.persistence.util.Estados;
import br.com.siec.model.persistence.util.StatusPedido;
import br.com.siec.model.persistence.util.TipoEndereco;
import br.com.siec.model.persistence.util.TipoPreco;
import br.com.siec.model.persistence.util.TipoSexo;
import br.com.siec.model.persistence.util.TipoTelefone;
import br.com.siec.model.persistence.util.TipoUsuario;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListEnumModel implements ListEnum {

    @Override
    public List getEstados() {
        List estados = new ArrayList();
        estados.addAll(Arrays.asList(Estados.values()));
        return estados;
    }

    @Override
    public List getTipoEndereco() {
        List tipoEndereco = new ArrayList();
        tipoEndereco.addAll(Arrays.asList(TipoEndereco.values()));
        return tipoEndereco;
    }

    @Override
    public List getTipoTelefone() {
        List tipoTelefone = new ArrayList();
        tipoTelefone.addAll(Arrays.asList(TipoTelefone.values()));
        return tipoTelefone;
    }

    @Override
    public List getTipoUsuario() {
        List tipos = new ArrayList();
        tipos.addAll(Arrays.asList(TipoUsuario.values()));
        return tipos;
    }

    @Override
    public List getTipoSexo() {
        List sexo = new ArrayList();
        sexo.addAll(Arrays.asList(TipoSexo.values()));
        return sexo;
    }

    @Override
    public List getStatusPedido() {
        List status = new ArrayList();
        status.addAll(Arrays.asList(StatusPedido.values()));
        return status;
    }

    @Override
    public List getTamanhoPizza() {
        List tamanhos = new ArrayList();
        tamanhos.addAll(Arrays.asList(TipoPreco.values()));
        return tamanhos;
    }

    @Override
    public List getCategorias() {
        List categorias = new ArrayList();
        categorias.addAll(Arrays.asList(Categorias.values()));
        return categorias;
    }

    @Override
    public List getCategoriasWithoutComposition() {
        List categorias = new ArrayList();
        for (int i = 0; i < Arrays.asList(Categorias.values()).size(); i++) {
            if (!Arrays.asList(Categorias.values()).get(i).name().equals(Categorias.Composicao.toString())) {
                categorias.add(Arrays.asList(Categorias.values()).get(i));
            }
        }
        return categorias;
    }
}
