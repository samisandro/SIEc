package br.com.siec.model.persistence.entity;

import java.util.List;

public class Cliente implements ICliente {

    private IUsuario iUsuario;
    private List<IPedido> pedidos;

    public void Cliente(IUsuario user) {
    }
}
