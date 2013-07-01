/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siec.control.beans.util;

import br.com.siec.model.persistence.entity.Produto;
import br.com.siec.service.Service;
import br.com.siec.service.qualifiers.ProdutoServiceQualifier;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/images/*")
public class ImageServlet extends HttpServlet {

    @Inject
    @ProdutoServiceQualifier
    private Service<Produto> produtoService;
    private static final Logger LOGGER = Logger.getLogger(ImageServlet.class.getName());

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        long id = Long.parseLong(request.getParameter("id"));
        //String categoria = request.getParameter("categoria");
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Buscando Imagem.");
            LOGGER.debug("Id do Produto [" + id + "]");
            //LOGGER.debug("Categoria do Produto [" + categoria + "]");
        }
        Produto p = produtoService.findById(id);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Produto Buscado: Nome [" + p.getNome() + "]");
        }
        response.setContentType(p.getImagem().getExtensao());
        OutputStream out = response.getOutputStream();
        out.write(p.getImagem().getArquivo());
        out.close();
    }
}
