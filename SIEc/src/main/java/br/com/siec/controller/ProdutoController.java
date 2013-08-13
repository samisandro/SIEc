package br.com.siec.controller;

import br.com.siec.config.jsf.ViewContext;
import br.com.siec.model.persistence.entity.Acompanhamento;
import br.com.siec.model.persistence.entity.Componente;
import br.com.siec.model.persistence.entity.Imagem;
import br.com.siec.model.persistence.entity.Preco;
import br.com.siec.model.persistence.entity.Produto;
import br.com.siec.model.persistence.util.Categorias;
import br.com.siec.api.factory.entity.ListEnum;
import br.com.siec.model.persistence.util.TipoPreco;
import br.com.siec.service.Service;
import br.com.siec.service.qualifiers.ProdutoServiceQualifier;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;

/**
 * ProdutoController
 *
 * @version 1.00 04 August 2013
 * @author Josimar Alves
 */
@ManagedBean(name = "produtoBean")
@ViewScoped
public class ProdutoController implements Serializable {

    @Inject
    @ProdutoServiceQualifier
    private Service<Produto> produtoService;
    @ManagedProperty(value = "#{imageBean}")
    private ImageController imageBean;
    @Inject
    ViewContext viewContext;
    private Produto produto;
    @Inject
    private ListEnum categorias;
    private Produto selectedProduto;
    private List<Produto> produtos;
    private String searchParam;
    private List<Produto> resultSearch;
    private Preco precoC;
    private Preco precoP;
    private Preco precoM;
    private Preco precoG;
    private Preco precoF;

    public ProdutoController() {
    }

    @PostConstruct
    public void initController() {
        this.produto = produtoService.create("Componente");
        this.produto.setCategoria(Categorias.Ingredientes);
        this.precoP = new Preco(TipoPreco.PEQUENA, 0.0);
        this.precoM = new Preco(TipoPreco.MEDIA, 0.0);
        this.precoG = new Preco(TipoPreco.GRANDE, 0.0);
        this.precoF = new Preco(TipoPreco.FAMILIA, 0.0);
    }

    public void save() {
        if (this.produtoService.save(produto)) {
            viewContext.info("msg_info_saved");
        } else {
            viewContext.info("msg_error");
        }
    }

    public void delete() {

        if (this.produtoService.delete(getSelectedProduto())) {
            this.produtos.remove(getSelectedProduto());
            viewContext.info("msg_info_deleted");
        } else {
            viewContext.info("msg_error");
        }
    }

    public void deleteSearch() {
        if (this.produtoService.delete(getSelectedProduto())) {
            this.resultSearch.remove(getSelectedProduto());
            viewContext.info("msg_info_deleted");
        } else {
            viewContext.info("msg_error");
        }
    }

    public void update() {
        if (this.produtoService.update(getProductUpdate())) {
            viewContext.info("msg_info_updated");
        } else {
            viewContext.info("msg_error");
        }
    }

    public List<Produto> getProdutos() {
        if (this.produtos == null) {
            this.produtos = produtoService.listAll();
        }

        return this.produtos;
    }

    public void search() {
        if (this.getResultSearch() != null) {
            this.getResultSearch().clear();
        }
        this.resultSearch = produtoService.findBy(this.getSearchParam(), "nome");
    }

    public String goUpdate() {
        ViewContext.setObjectInSession("updateProduct", this.getSelectedProduto());
        return "updateProduct.jsf";
    }

    public Produto getProductUpdate() {
        if (produto == null) {
            produto = ViewContext.getObjectInSession("updateProduct");
        }
        return produto;
    }

    public void changeProduto(AjaxBehaviorEvent event) {
        Produto produtoChanged;
        if ((this.produto.getCategoria().equals(Categorias.Acompanhamento))
                || (this.produto.getCategoria().equals(Categorias.Bebidas))) {
            produtoChanged = produtoService.create("Acompanhamento");
            produtoChanged.setNome(this.produto.getNome());
            this.precoC = new Preco(TipoPreco.COMUM, 0.0);
            this.produto = null;
            this.produto = produtoChanged;
        } else {
            produtoChanged = produtoService.create("Componente");
            produtoChanged.setNome(this.produto.getNome());
            this.produto = null;
            this.produto = produtoChanged;
        }
    }

    public String getImagePath() {
        return "/images/" + this.getProductUpdate().getId() + "-" + this.getProductUpdate().getCategoria();
    }

    public List<Produto> getResultSearch() {

        return this.resultSearch;
    }

    public List getCategorias() {
        return this.categorias.getCategoriasWithoutComposition();
    }

    public Produto getProduto() {
        return this.produto;
    }

    public Imagem getFotoDescricao() {
        return this.imageBean.getFotoDescricao();
    }

    public Produto getSelectedProduto() {
        return selectedProduto;
    }

    public void setSelectedProduto(Produto selectedProduto) {
        this.selectedProduto = selectedProduto;
    }

    public ImageController getImageBean() {
        return imageBean;
    }

    public void setImageBean(ImageController imageBean) {
        this.imageBean = imageBean;
    }

    public String getSearchParam() {
        return searchParam;
    }

    public void setSearchParam(String searchParam) {
        this.searchParam = searchParam;
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
}
