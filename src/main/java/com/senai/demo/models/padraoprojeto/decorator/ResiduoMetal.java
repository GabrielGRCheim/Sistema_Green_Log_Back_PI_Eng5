package com.senai.demo.models.padraoprojeto.decorator;

public class ResiduoMetal extends ResiduosDecorator {

    public ResiduoMetal(Residuos componente) {
        super(componente);
    }

    @Override
    public String getDescricao() {
        String anterior = (componente != null) ? componente.getDescricao() : "";

        if (anterior.isBlank()) {
            return "Metal";
        }

        return anterior + ", Metal";
    }
}
