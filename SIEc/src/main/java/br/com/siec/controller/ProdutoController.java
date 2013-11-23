package br.com.siec.controller;

import br.com.siec.config.jsf.ViewContext;

import br.com.siec.model.persistence.entity.Preco;
import br.com.siec.model.persistence.entity.Produto;

import br.com.siec.model.persistence.resource.Categorias;
import br.com.siec.model.persistence.resource.TipoPreco;

import br.com.siec.factory.entity.ListEnumModel;

import br.com.siec.controller.resource.FileUpload;

import br.com.siec.service.ProdutoService;
import br.com.siec.service.qualifiers.ProdutoServiceQualifier;

import java.io.Serializable;

import java.util.List;

import javax.annotation.PostConstruct;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;

import javax.inject.Inject;

/**
 * <p><b>ProdutoController:</b> Bean responsavel por todas as operações
 * relacionadas ao objeto Produto.</p>
 *
 * @version 1.00 04 August 2013
 * @author Josimar Alves
 * @observation Refatorar!!!
 */
@ManagedBean(name = "produtoBean")
@ViewScoped
public class ProdutoController implements Serializable {

    @Inject
    @ProdutoServiceQualifier
    private ProdutoService produtoFacade;
    
    @Inject
    ViewContext viewContext;
    
    private Produto produto;
    
    private List<Produto> produtos;
    private List<Produto> filteredProducts;
    
    private Preco precoC;
    private Preco precoP;
    private Preco precoM;
    private Preco precoG;
    private Preco precoF;
    
    private static final String HOME = "home";
    private static final String ALL_PRODUCT = "allProduct";
    private static final String UPDATE_PRODUCT = "updateProduct"/*?faces-redirect=true&includeViewParams=true*/;

    public ProdutoController() {
    }

    @PostConstruct
    public void init() {

        /**
         * @Refatorar
         * @Motivo: Não sobrecarregar o servidor com objetos não sessão.
         */
        produto = viewContext.getObjectInSession("produto");
        viewContext.removeObjectInSession("produto");

        if (produto == null) {
            produto = produtoFacade.create("Componente");
            produto.setCategoria(Categorias.Ingredientes);

            this.precoC = new Preco(TipoPreco.COMUM, 0.0);
            this.precoP = new Preco(TipoPreco.PEQUENA, 0.0);
            this.precoM = new Preco(TipoPreco.MEDIA, 0.0);
            this.precoG = new Preco(TipoPreco.GRANDE, 0.0);
            this.precoF = new Preco(TipoPreco.FAMILIA, 0.0);
        } else {
            prepareForUpdate();
        }
    }

    public String save() {

        ajustProduto();

        if (this.produtoFacade.save(produto)) {
            viewContext.info("msg_info_saved");
            return ALL_PRODUCT;
        } else {
            viewContext.info("msg_error");
            return HOME;
        }
    }

    public String delete() {

        if (this.produtoFacade.delete(produto)) {
            this.produtos.remove(produto);
            viewContext.info("msg_info_deleted");
            return ALL_PRODUCT+"faces-redirect=true";
        } else {
            viewContext.info("msg_error");
            return HOME;
        }
    }

    public String update() {
        produto.getPrecos().clear();
        
        ajustProduto();
        
        if (this.produtoFacade.update(produto)) {
            viewContext.info("msg_info_updated");
            return ALL_PRODUCT;
        } else {
            viewContext.info("msg_error");
            return HOME;
        }
    }

    public List<Produto> getProdutos() {
        if (this.produtos == null) {
            this.produtos = produtoFacade.listAll();
        }

        return this.produtos;
    }

    public String goUpdate() {
        viewContext.setObjectInSession("produto", produto);
        return UPDATE_PRODUCT;
    }

    public void prepareForUpdate() {

       /* if (FacesContext.getCurrentInstance().isPostback()) {
            return;
        }*/
        if (produto.getTypePrice().isMultiplePrice()) {
            this.precoP = produto.getPrecos().get(0);
            this.precoM = produto.getPrecos().get(1);
            this.precoG = produto.getPrecos().get(2);
            this.precoF = produto.getPrecos().get(3);
        } else {
            this.precoC = produto.getPrecos().get(0);
        }
    }

    public void changeProduto(AjaxBehaviorEvent event) {
        this.produto = produtoFacade.convertTo(produto, this.produto.getCategoria().toString());
    }

    public String getImagePath() {
        return "/images/?id=" + getProduto().getId();
    }

    public void ajustProduto() {
        FileUpload fileUpload = this.viewContext.findBean("fileUpload");
        this.produto.setImagem(fileUpload.getFotoDescricao());
        if (this.produto.getTypePrice().isMultiplePrice()) {
            this.produto.addPreco(precoP);
            this.produto.addPreco(precoM);
            this.produto.addPreco(precoG);
            this.produto.addPreco(precoF);
        } else {
            this.produto.addPreco(precoC);
        }
    }

    public List getCategorias() {
        return ListEnumModel.getCategoriasWithoutComposition();
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public SelectItem[] getSearchCategorias() {
        List<Categorias> data = ListEnumModel.getCategoriasWithoutComposition();
        SelectItem[] options = new SelectItem[data.size() + 1];

        options[0] = new SelectItem("", "Select");
        for (int i = 0; i < data.size(); i++) {
            options[i + 1] = new SelectItem(data.get(i).toString(), data.get(i).toString());
        }

        return options;
    }

    public Preco getPrecoC() {
        return this.precoC;
    }

    public void setPrecoC(Preco precoC) {
        this.precoC = precoC;
    }

    public Preco getPrecoP() {
        return this.precoP;
    }

    public void setPrecoP(Preco precoP) {
        this.precoP = precoP;
    }

    public Preco getPrecoM() {
        return this.precoM;
    }

    public void setPrecoM(Preco precoM) {
        this.precoM = precoM;
    }

    public Preco getPrecoG() {
        return this.precoG;
    }

    public void setPrecoG(Preco precoG) {
        this.precoG = precoG;
    }

    public Preco getPrecoF() {
        return this.precoF;
    }

    public void setPrecoF(Preco precoF) {
        this.precoF = precoF;
    }

    public List<Produto> getFilteredProducts() {
        return filteredProducts;
    }

    public void setFilteredProducts(List<Produto> filteredProducts) {
        this.filteredProducts = filteredProducts;
    }
}
