/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siec.controller.resource;

import br.com.siec.model.persistence.entity.Imagem;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.apache.log4j.Logger;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author josimar
 */
@ManagedBean(name = "fileUpload")
@SessionScoped
public class FileUpload implements Serializable {

    private StreamedContent imagem;
    
    private Imagem img;
    
    protected Logger logger = Logger.getLogger(FileUpload.class);

    public FileUpload() {
    }

    public void handleFileUpload(FileUploadEvent event) {
        if (logger.isDebugEnabled()) {
            logger.debug("{handleFileUpload(FileUploadEvent event)} Uploading arquive.");
        }
        try {
            System.out.println("Here");
            this.img.setArquivo(event.getFile().getContents());
            this.img.setExtensao(event.getFile().getContentType());
            this.img.setDescricao(event.getFile().getFileName());
            this.imagem = new DefaultStreamedContent(event.getFile().getInputstream(), "image/jpeg");
            if (logger.isDebugEnabled()) {
                logger.debug("{handleFileUpload()} Uploading succefull.");
            }
        } catch (IOException ex) {
            if (logger.isDebugEnabled()) {
                logger.debug("{handleFileUpload()-> Erro} Uploading error: " + ex);
            }
        }
    }

    public Imagem getFotoDescricao() {
        return this.img;
    }

    public StreamedContent getConverted() {
        if (logger.isDebugEnabled()) {
            logger.debug("{getConverted()} Convertendo Arquivo.");
        }
        try {
            InputStream imagemStream = new ByteArrayInputStream(this.getFotoDescricao().getArquivo());
            StreamedContent image = new DefaultStreamedContent(imagemStream, "image/jpeg", "imageProduct.jpg");
            if (logger.isDebugEnabled()) {
                logger.debug("{getConverted()} Conversão Completa.");
            }
            return image;

        } catch (Exception e) {
            if (logger.isDebugEnabled()) {
                logger.debug("{getConverted()-> Erro} Erro na conversão do arquivo:" + e);
            }
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
