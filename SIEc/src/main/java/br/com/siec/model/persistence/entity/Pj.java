package br.com.siec.model.persistence.entity;

public class Pj extends Pessoa implements IPJ {

    private String cnpj;
    private String razaoSocial;
    private String inscricaoEstadual;

    @Override
    public String getCnpj() {
        return null;
    }

    @Override
    public void setCnpj(String cnpj) {
    }

    @Override
    public String getRazaoSocial() {
        return null;
    }

    @Override
    public void setRazaoSocial(String razaoSocial) {
    }

    @Override
    public String getInscricaoEstadual() {
        return null;
    }

    @Override
    public void setInscricaoEstadual(String inscricaoEstadual) {
    }
}
