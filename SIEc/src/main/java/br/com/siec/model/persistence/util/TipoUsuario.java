package br.com.siec.model.persistence.util;

public enum TipoUsuario {

    Administrador("Administrador"),
    AuxiliardeProcesso("Auxiliar de Processo"),
    Gerente("Gerente"),
    Cliente("Cliente");
    private final String descricao;

    TipoUsuario(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return this.descricao;
    }
}
