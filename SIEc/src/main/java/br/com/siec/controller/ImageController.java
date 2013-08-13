/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siec.controller;

import br.com.siec.model.persistence.entity.Imagem;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author josimar
 */
@ManagedBean(name="imageBean")
@SessionScoped
public class ImageController implements Serializable {

    private StreamedContent imagem;
    private byte[] fotoDescricao;
    private Imagem img;

    /**
     * Creates a new instance of UtilBean
     */
    public ImageController() {
    }

    public void handleFileUpload(FileUploadEvent event) {
        try {
            System.out.println("Here!!!");
            this.imagem = new DefaultStreamedContent(event.getFile().getInputstream(), "image/jpeg");
            this.img.setArquivo(event.getFile().getContents());
            this.img.setExtensao(event.getFile().getContentType());
            this.img.setDescricao(event.getFile().getFileName());
            byte[] foto = event.getFile().getContents();
            this.fotoDescricao = foto;
            System.out.println("Upload Succefull!!!");
        } catch (IOException ex) {
            System.out.println("Here Exception!!!");
            Logger.getLogger(ProdutoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Imagem getFotoDescricao() {
        return this.img;
    }

    public StreamedContent getConverted() {
        try {
            InputStream imagemStream = new ByteArrayInputStream(this.getFotoDescricao().getArquivo());
            StreamedContent image = new DefaultStreamedContent(imagemStream, "image/jpeg", "imageProduct.jpg");
            return image;
        } catch (Exception e) {
            System.out.println("Exception"+ e);
            return new DefaultStreamedContent();
        }
    }

    public StreamedContent getImagem() {
        return this.imagem;
    }

    public void setImagem(StreamedContent imagem) {
        this.imagem = imagem;
    }
    
    
}
