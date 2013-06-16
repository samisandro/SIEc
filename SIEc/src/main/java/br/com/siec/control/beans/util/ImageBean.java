/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siec.control.beans.util;

import br.com.siec.control.beans.ProdutoBean;
import java.io.IOException;
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
@ManagedBean(name = "imageBean")
@SessionScoped
public class ImageBean implements Serializable{

    private StreamedContent imagem;
    private byte[] fotoDescricao;

    /**
     * Creates a new instance of UtilBean
     */
    public ImageBean() {
    }

    public StreamedContent getImage() {
        return this.imagem;
    }

    public void handleFileUpload(FileUploadEvent event) {
        try {
            this.imagem = new DefaultStreamedContent(event.getFile().getInputstream(), "image/jpeg");
            byte[] foto = event.getFile().getContents();
            this.fotoDescricao = foto;
        } catch (IOException ex) {
            Logger.getLogger(ProdutoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public byte[] getFotoDescricao() {
        return this.fotoDescricao;
    }
}
