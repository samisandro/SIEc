package br.com.siec.model.persistence.resource;

public enum TipoUsuario {

    ADMINISTRADOR("Administrador"),
    AUXILIAR("Auxiliar de Processo"),
    GERENTE("Gerente"),
    CLIENTE("Cliente");
    private final String descricao;

    TipoUsuario(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return this.descricao;
    }
}
