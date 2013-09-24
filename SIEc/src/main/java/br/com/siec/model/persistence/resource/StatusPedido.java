package br.com.siec.model.persistence.resource;

public enum StatusPedido {

    AguardandoPagamento("Aguardando Pagamento"),
    Faturado("Faturado"),
    SendoPreparado("SendoPreparado"),
    SaiuparaEntrega("Saiu para Entrega"),
    Entregue("Entregue");
    private final String descricao;

    StatusPedido(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return this.descricao;
    }
}
