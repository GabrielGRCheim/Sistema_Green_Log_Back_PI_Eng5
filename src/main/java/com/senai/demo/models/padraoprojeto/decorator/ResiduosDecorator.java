package com.senai.demo.models.padraoprojeto.decorator;

public abstract class ResiduosDecorator implements Residuos {

    protected Residuos componente;

    public ResiduosDecorator(Residuos componente) {
        this.componente = componente;
    }

    @Override
    public String getDescricao() {
        return componente.getDescricao();
    }
}
