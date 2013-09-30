package br.com.siec.model.persistence.resource;

public enum TipoSexo {

    M("Masculino"),
    F("Feminino");    
    private final String descricao;

    TipoSexo(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return this.descricao;
    }
}
