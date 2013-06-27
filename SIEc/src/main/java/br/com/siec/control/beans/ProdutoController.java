package br.com.siec.control.beans;

import br.com.siec.control.beans.util.FaceUtil;
import br.com.siec.control.beans.util.ImageBean;
import br.com.siec.model.persistence.entity.Produto;
import br.com.siec.model.persistence.util.Categorias;
import br.com.siec.model.persistence.util.IListEnun;
import br.com.siec.model.persistence.util.ListEnun;
import br.com.siec.service.Service;
import br.com.siec.service.qualifiers.ProdutoServiceQualifier;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author josimar
 */
@ManagedBean(name = "produtoBean")
@ViewScoped
public class ProdutoController implements Serializable {

    @Inject
    @ProdutoServiceQualifier
    private Service<Produto> produtoService;
    private ImageBean imageBean;
    private Produto produto;
    private Produto selectedProduto;
    private List<Produto> produtos;
    private List<Produto> resultSearch;
    private String searchParam;
    private IListEnun categorias;
    private String categoria;
    private String nome;
    private byte[] fotoDescricao;
    private Double preco = 0.00;
    private Double precoP = 0.00;
    private Double precoM = 0.00;
    private Double precoG = 0.00;
    private Double precoF = 0.00;

    public ProdutoController() {
        this.categorias = new ListEnun();
        this.categoria = "Acompanhamento";
    }

    public void save() {

        ajustProduto();

        if (this.produtoService.save(produto)) {
            FaceUtil.addMessage("Sucesso", "Registro incluido.");
            clear();

        } else {
            FaceUtil.addMessage("Error", "Ocorreu um erro durante a operação.");
        }
    }

    public void delete() {

        if (this.produtoService.delete(getSelectedProduto())) {
            this.produtos.remove(getSelectedProduto());
            FaceUtil.addMessage("Sucesso", "Registro " + getSelectedProduto().getId() + " excluido");
        } else {
            FaceUtil.addMessage("Error", "Ocorreu um erro durante a operação.");
        }
    }

    public void deleteSearch() {

        if (this.produtoService.delete(getSelectedProduto())) {
            this.resultSearch.remove(getSelectedProduto());
            FaceUtil.addMessage("Sucesso", "Registro " + getSelectedProduto().getId() + " excluido");
        } else {
            FaceUtil.addMessage("Error", "Ocorreu um erro durante a operação.");
        }
    }

    public void update() {
        /*if (getFotoDescricao() != null) {
            getProductUpdate().setImagem(getFotoDescricao());
        }*/System.out.println("Classe Imagem: "+getProductUpdate().getImagem().getClass().getName());

        if (this.produtoService.update(getProductUpdate())) {
            FaceUtil.addMessage("Sucesso", "Registro " + getSelectedProduto().getId() + " alterado.");
        } else {
            FaceUtil.addMessage("Error", "Ocorreu um erro durante a operação.");
        }
    }

    public List<Produto> getProdutos() {
        if (this.produtos == null) {
            this.produtos = produtoService.listAll();
        }

        return this.produtos;
    }

    public List<Produto> getResultSearch() {

        return this.resultSearch;
    }

    public void search() {
        if (this.getResultSearch() != null) {
            this.getResultSearch().clear();
        }
        this.resultSearch = produtoService.findBy(this.getSearchParam(), "nome");
    }

    public String returnIndex() {
        return "/home.jsf";
    }

    public String goUpdate() {
        FaceUtil.setObjectInSession("updateProduct", this.getSelectedProduto());
        return "updateProduct.jsf";
    }

    public Produto getProductUpdate() {
        if (produto == null) {
            produto = FaceUtil.getObjectInSession("updateProduct");
        }
        return produto;
    }

    public List<String> getMapKeys() {
        List<String> ret = new ArrayList<String>();
        for (String s : getProductUpdate().getPrecos().keySet()) {
            ret.add(s);
        }
        return ret;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List getCategorias() {

        return this.categorias.getCategoriasWithoutComposition();
    }

    public Produto getProduto() {
        return produtoService.create("Componente");
    }

    public void ajustProduto() {
        if ((categoria.toString().equals("Acompanhamento"))
                || (categoria.toString().equals("Bebidas"))) {
            this.produto = this.produtoService.create("Acompanhamento");
            this.produto.addPreco("Preco", preco);
        } else {
            this.produto = this.produtoService.create("Componente");
            this.produto.addPreco("Pequena", precoP);
            this.produto.addPreco("Média", precoM);
            this.produto.addPreco("Grande", precoG);
            this.produto.addPreco("Família", precoF);
        }
        this.produto.setCategoria(Categorias.valueOf(categoria));
        this.produto.setImagem(getFotoDescricao());
        this.produto.setNome(nome);
    }

    public void clear() {
        this.categoria = "Acompanhamento";
        this.nome = "";
        this.preco = 0.00;
        this.precoP = 0.00;
        this.precoM = 0.00;
        this.precoG = 0.00;
        this.precoF = 0.00;
    }

    public boolean isMultiplePrice() {
        if ((categoria.equals("Acompanhamento")) || (categoria.equals("Bebidas"))) {
            return false;
        } else {
            return true;
        }
    }

    public String getCategoria() {
        return this.categoria;
    }

    public byte[] getFotoDescricao() {
        this.imageBean = FaceUtil.findBean("imageBean");
        this.fotoDescricao = this.imageBean.getFotoDescricao();
        return fotoDescricao;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Double getPrecoP() {
        return precoP;
    }

    public void setPrecoP(Double precoP) {
        this.precoP = precoP;
    }

    public Double getPrecoM() {
        return precoM;
    }

    public void setPrecoM(Double precoM) {
        this.precoM = precoM;
    }

    public Double getPrecoG() {
        return precoG;
    }

    public void setPrecoG(Double precoG) {
        this.precoG = precoG;
    }

    public Double getPrecoF() {
        return precoF;
    }

    public void setPrecoF(Double precoF) {
        this.precoF = precoF;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Produto getSelectedProduto() {
        return selectedProduto;
    }

    public void setSelectedProduto(Produto selectedProduto) {
        this.selectedProduto = selectedProduto;
    }

    public ImageBean getImageBean() {
        return imageBean;
    }

    public void setImageBean(ImageBean imageBean) {
        this.imageBean = imageBean;
    }

    public String getSearchParam() {
        return searchParam;
    }

    public void setSearchParam(String searchParam) {
        this.searchParam = searchParam;
    }

    public String getImagePath() {
        return "/images/" + this.getProductUpdate().getId() + "-" + this.getProductUpdate().getCategoria();
    }
}
