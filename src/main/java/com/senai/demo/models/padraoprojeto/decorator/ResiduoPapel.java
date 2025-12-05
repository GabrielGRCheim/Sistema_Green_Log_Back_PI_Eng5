package com.senai.demo.models.padraoprojeto.decorator;

public class ResiduoPapel extends ResiduosDecorator {

    public ResiduoPapel(Residuos componente) {
        super(componente);
    }

    @Override
    public String getDescricao() {
        String anterior = (componente != null) ? componente.getDescricao() : "";

        if (anterior.isBlank()) {
            return "Papel";
        }

        return anterior + ", Papel";
    }
}
