/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siec.controller;

import br.com.siec.factory.entity.ListEnumModel;

import br.com.siec.config.jsf.ViewContext;

import br.com.siec.model.persistence.entity.Componente;
import br.com.siec.model.persistence.entity.Composicao;
import br.com.siec.model.persistence.entity.Item;
import br.com.siec.model.persistence.entity.Pedido;
import br.com.siec.model.persistence.entity.Preco;
import br.com.siec.model.persistence.entity.Produto;

import br.com.siec.model.persistence.interfaces.ICliente;
import br.com.siec.model.persistence.interfaces.IComponente;

import br.com.siec.model.persistence.resource.Categorias;
import br.com.siec.model.persistence.resource.StatusPedido;
import br.com.siec.model.persistence.resource.TipoPreco;

import br.com.siec.service.PedidoService;
import br.com.siec.service.ProdutoService;

import br.com.siec.service.qualifiers.PedidoServiceQualifier;
import br.com.siec.service.qualifiers.ProdutoServiceQualifier;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import javax.faces.model.SelectItem;

import javax.inject.Inject;

/**
 *
 * @author Josimar Alves
 */
@ManagedBean(name = "carrinhoController")
@SessionScoped
public class CarrinhoController implements Serializable {

    @Inject
    private ComposicaoController pizzasController;
    
    @Inject
    @PedidoServiceQualifier
    private PedidoService pedidoService;
    
    @Inject
    @ProdutoServiceQualifier
    private ProdutoService produtoService;
    
    @Inject
    private ViewContext viewContext;
    
    private Pedido pedido;
    private Composicao pizza;
    private Componente aux;
    private Produto produto;
    
    private List<Produto> acompanhamentos;
    private List<Produto> bebidas;
    
    private TipoPreco precoPizza;
    private double valorPedido = 0.0;

    @PostConstruct
    public void init() {

        if (pizza == null) {
            pizza = (Composicao) produtoService.create("Composite");
            pizza.setCategoria(Categorias.Composicao);
            precoPizza = TipoPreco.GRANDE;
        }
        if (pedido == null) {
            pedido = new Pedido();
        }
    }

    public ComposicaoController getPizzasController() {
        return pizzasController;
    }

    public void setPizzasController(ComposicaoController pizzasController) {
        this.pizzasController = pizzasController;
    }

    public List<Produto> getAcompanhamentos() {
        if (acompanhamentos == null) {
            acompanhamentos = produtoService.filterByCategory(Categorias.Acompanhamento);
            acompanhamentos.addAll(produtoService.filterByCategory(Categorias.Bebidas));
        }
        return acompanhamentos;
    }

    public List<Produto> getBebidas() {
        if (bebidas == null) {
            bebidas = produtoService.filterByCategory(Categorias.Bebidas);
        }
        return bebidas;
    }

    public Composicao getPizza() {
        return pizza;
    }

    public void setPizza(Composicao pizza) {
        this.pizza = pizza;
    }

    public List<Item> getItems() {
        for (int i = 0; i < this.pedido.getItens().size(); i++) {
            if (this.pedido.getItens().get(i).getProduto().getCategoria().equals(Categorias.Composicao)) {
                this.pedido.getItens().remove(i);
            }
        }
        return this.pedido.getItens();
    }

    public List<SelectItem> getSizes() {
        List<SelectItem> tamanhos = new ArrayList<SelectItem>();
        for (Iterator it = ListEnumModel.getTamanhoPizza().iterator(); it.hasNext();) {
            TipoPreco size = (TipoPreco) it.next();
            tamanhos.add(new SelectItem(size, size.getDescricao()));
        }

        return tamanhos;
    }

    public String goLogin() {
        viewContext.info("msg_logar");
        return "login.jsf";
    }

    public String fechaVenda() {
        if (isCompletePizza()) {

            this.pizza.setNome("Pizza " + precoPizza.getDescricao());
            this.pizza.setDescricao("Pizza " + precoPizza.getDescricao());

            this.pizza = (Composicao) produtoService.validate(pizza);

            this.pedido.addItens(new Item(pizza, pedido, 1));
            return "myOrder";
        }

        return "";
    }

    public String finalizaVenda(ICliente cliente) {
        this.pedido.setCliente(cliente);
        this.pedido.setStatus(StatusPedido.AguardandoPagamento);
        this.pedido.setDataCompra(new Date());
        this.pedido.setValorTotal(this.pedido.calculaValorPedido(precoPizza));

        if (pedidoService.save(pedido)) {
            viewContext.info("msg_info_saved");
            return "/secure/customer/myOrders.jsf";
        } else {
            viewContext.info("msg_error");
            return "";
        }
    }

    public Double getValorPedido() {
        this.valorPedido = 0.0;
        for (Item item : pedido.getItens()) {
            this.valorPedido += item.getProduto().getPrecos().get(0).getValor() * item.getQuantidade();
        }
        for (Preco p : getPizza().getPrecos()) {
            if (p.getTipo().equals(this.precoPizza)) {
                this.valorPedido += p.getValor();
            }
        }

        this.pedido.setValorTotal(valorPedido);
        return this.valorPedido;
    }

    public TipoPreco getPrecoPizza() {
        return precoPizza;
    }

    public void setPrecoPizza(TipoPreco precoPizza) {
        this.precoPizza = precoPizza;
    }

    public Componente getAux() {
        return this.aux;
    }

    public void setAux(Componente aux) {
        this.aux = aux;
    }

    public List<Componente> getMassas() {
        return pizzasController.getMassas();
    }

    public void addUniqueIngrediente(ActionEvent actionEvent) {
        this.pizza.removeByCategory(getAux().getCategoria());
        this.pizza.addComponente(getAux());
    }

    public void addIngrediente(ActionEvent actionEvent) {
        this.pizza.removeComponente(getAux());
        this.pizza.addComponente(aux);
    }

    public void removeIngrediente(ActionEvent actionEvent) {
        this.pizza.removeComponente(aux);
    }

    public boolean renderPrice(TipoPreco tipo) {
        return tipo.equals(this.precoPizza);
    }

    public void addItem(ActionEvent actionEvent) {
        removeItem(actionEvent);
        this.pedido.addItens(new Item(produto, pedido, 1));
    }

    public void removeItem(ActionEvent actionEvent) {
        for (int i = 0; i < this.pedido.getItens().size(); i++) {
            if (this.pedido.getItens().get(i).getProduto().getId() == produto.getId()) {
                this.pedido.getItens().remove(i);
            }
        }
    }

    public Produto getProduto() {
        return this.produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public List<IComponente> getComponentes() {
        return this.pizza.getComponentes();
    }

    public boolean isCompletePizza() {
        int borda = 0;
        int massa = 0;
        int recheio = 0;

        for (IComponente componente : this.pizza.getComponentes()) {

            if (componente.getCategoria().equals(Categorias.Borda)) {
                borda++;
            }
            if (componente.getCategoria().equals(Categorias.Massa)) {
                massa++;
            }
            if (componente.getCategoria().equals(Categorias.Ingredientes)) {
                recheio++;
            }

        }

        if (massa < 1) {
            viewContext.info("msg_required_mass");
            return false;
        }
        if (borda < 1) {
            viewContext.info("msg_required_border");
            return false;
        }
        if (recheio < 3) {
            viewContext.info("msg_required_filling");
            return false;
        }

        return true;
    }
}
