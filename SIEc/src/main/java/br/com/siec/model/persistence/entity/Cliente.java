/*
 * %W% %E% Josimar Alves
 *
 * Copyright (c) 2013-2014 Josimar Alves, All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Josimar Alves. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with JOSIMAR ALVES.
 *
 * JOSIMAR ALVES MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF
 * THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 * TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE, OR NON-INFRINGEMENT. JOSIMAR ALVES SHALL NOT BE LIABLE FOR
 * ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR
 * DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
 */
package br.com.siec.model.persistence.entity;

import br.com.siec.model.persistence.interfaces.IPedido;
import br.com.siec.model.persistence.interfaces.IUsuario;
import br.com.siec.model.persistence.interfaces.ICliente;
import br.com.siec.model.persistence.interfaces.IPerfil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * Cliente
 *
 * @version 1.00 May 21, 2013.
 * @author Josimar Alves
 */
@Entity
@Table(name = "TB_CLIENTE_CLT", schema = "siec")
public class Cliente implements ICliente, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CLT_CODIGO")
    private long id;
    
    @OneToOne(targetEntity = Usuario.class)
    private IUsuario usuario;
    
    @OneToOne(mappedBy = "cliente", targetEntity = Perfil.class)
    @Cascade(CascadeType.ALL)
    private IPerfil perfil;
    
    @OneToMany(mappedBy = "cliente",
            targetEntity = Pedido.class, fetch = FetchType.EAGER)
    @Cascade(CascadeType.ALL)
    private List<IPedido> pedidos = new ArrayList<IPedido>();

    public Cliente() {
    }

    public Cliente(IUsuario user) {
        this.usuario = user;
    }
    
    public void setUsuario(IUsuario user){
        this.usuario = user;
    }

    @Override
    public long getId() {
        return this.id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public IPerfil getPerfil() {
        return perfil;
    }

    @Override
    public void setPerfil(IPerfil perfil) {
        this.perfil = perfil;
    }  

    @Override
    public List<IPedido> getPedidos() {
        return this.pedidos;
    }

    @Override
    public void addPedido(IPedido pedido) {
        this.pedidos.add(pedido);
    }

    @Override
    public IUsuario getUsuario() {
        return this.usuario;
    }

    @Override
    public String toString() {
        return "Cliente{" + "id=" + id + ", usuario=" + usuario + ", pedidos=" + pedidos + '}';
    }   
}
