package com.senai.demo.models.padraoprojeto.decorator;

public class ResiduoPlastico extends ResiduosDecorator {

    public ResiduoPlastico(Residuos componente) {
        super(componente);
    }

    @Override
    public String getDescricao() {
        String anterior = (componente != null) ? componente.getDescricao() : "";

        if (anterior.isBlank()) {
            return "Plastico";
        }

        return anterior + ", Plastico";
    }
}
