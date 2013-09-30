package br.com.siec.model.persistence.resource;

public enum TipoTelefone {

    RESIDENCIAL("Residencial"),
    PRINCIPAL("Principal"),
    COMERCIAL("Comercial");
    
    private final String descricao;

    TipoTelefone(String descricao) {
        this.descricao = descricao;
    }
    
    public String getDescricao(){
        return this.descricao;
    }
    
}
