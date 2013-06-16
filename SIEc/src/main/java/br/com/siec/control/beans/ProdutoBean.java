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
import java.util.List;
import javax.enterprise.inject.spi.Bean;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@ManagedBean(name = "produtoBean")
@RequestScoped
public class ProdutoBean implements Serializable {

    @Inject
    @ProdutoServiceQualifier
    private Service<Produto> produtoService;
    private Produto produto;
    private List<Produto> produtos;
    private String nome;
    private IListEnun categorias = new ListEnun();
    private String categoria = "Acompanhamento";
    private byte[] fotoDescricao;
    private double preco;
    private double precoP;
    private double precoM;
    private double precoG;
    private double precoF;

    public ProdutoBean() {
    }

    public void save() {

        ajustProduto();

        if (this.produtoService.save(produto)) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravação efetuada com sucesso", ""));

        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Ocorreu um erro durante o processo.", ""));
        }
    }

    public void delete() {
        if (this.produtoService.delete(produto)) {
        } else {
        }
    }

    public void update() {
        if (this.produtoService.update(this.produto)) {
        } else {
        }
    }

    public List<Produto> getProdutos() {
        if (this.produtos == null) {
            this.produtos = produtoService.listAll();
        }

        return this.produtos;
    }

    public String returnIndex() {
        return "/secure/admin/home.jsf";
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
                || (categoria.toString().equals("Bebibas"))) {
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
        ImageBean bean = FaceUtil.findBean("imageBean");
        this.fotoDescricao = bean.getFotoDescricao();
        return fotoDescricao;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getPrecoP() {
        return precoP;
    }

    public void setPrecoP(Double precoP) {
        this.precoP = precoP;
    }

    public double getPrecoM() {
        return precoM;
    }

    public void setPrecoM(Double precoM) {
        this.precoM = precoM;
    }

    public double getPrecoG() {
        return precoG;
    }

    public void setPrecoG(Double precoG) {
        this.precoG = precoG;
    }

    public double getPrecoF() {
        return precoF;
    }

    public void setPrecoF(Double precoF) {
        this.precoF = precoF;
    }

    public double getPreco() {
        return precoF;
    }

    public void setPreco(Double preco) {
        this.precoF = preco;
    }
}
