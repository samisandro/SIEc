package br.com.siec.model.persistence.util;

import java.util.List;

public interface IListEnun {
 
	public List getEstados();
	public List getTipoEndereco();
	public List getTipoTelefone();
	public List getTipoUsuario();
	public List getTipoSexo();
	public List getStatusPedido();
	public List getTamanhoPizza();
	public List getCategorias();
        public List getCategoriasWithoutComposition();
}
 
