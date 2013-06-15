package br.com.siec.control.beans;

import br.com.siec.model.persistence.entity.Produto;
import br.com.siec.model.persistence.util.Categorias;
import br.com.siec.model.persistence.util.ListEnun;
import br.com.siec.service.Service;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean(name="produtoBean")
@RequestScoped
public class ProdutoBean implements Serializable {

    private Service<Produto> produtoService;
    private Produto produto;
    private List<Produto> produtos;
    private String nome;
    private HashMap<String, Double> precos;
    private ListEnun categorias = new ListEnun();
    private String categoria;
    private byte[] fotoDescricao;
    private StreamedContent imagem;

    public ProdutoBean() {
    }
    
    public void save(){
        if((categoria.toString().equals("Acompanhamento")) || 
           (categoria.toString().equals("Bebibas"))){
            this.produto = this.produtoService.create("Acompanhamento");
        } else {
            this.produto = this.produtoService.create("Componente");
        }
        
        this.produto.setCategoria(Categorias.valueOf(categoria));
        this.produto.setImagem(fotoDescricao);
        this.produto.setNome(nome);
        
        if(this.produtoService.save(produto)){
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravação efetuada com sucesso", ""));
            
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Ocorreu um erro durante o processo.", ""));
        }
    }
    
    public void delete(){
        if(this.produtoService.delete(produto)){
            
        } else {
            
        }
    }
    
    public void update(){
        if(this.produtoService.update(this.produto)){
            
        } else {
            
        }
    }
    
    public List<Produto> getProdutos(){
        if (this.produtos == null) {
            this.produtos = produtoService.listAll();
        }

        return this.produtos;
    }
    
    public String returnIndex(){
        return "/secure/admin/home.jsf";
    }
    
    
    public void handleFileUpload(FileUploadEvent event) {
        try {
            imagem = new DefaultStreamedContent(event.getFile().getInputstream());
            byte[] foto = event.getFile().getContents();
            this.fotoDescricao = foto;
        } catch (IOException ex) {
            Logger.getLogger(ProdutoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public HashMap<String, Double> getPrecos() {
        return precos;
    }

    public void setPrecos(HashMap<String, Double> precos) {
        this.precos = precos;
    }

    public List getCategoria() {
        return this.categorias.getCategorias();
    }

    public StreamedContent getImagem() {
        return imagem;
    }

    public void setImagem(StreamedContent imagem) {
        this.imagem = imagem;
    }
}
