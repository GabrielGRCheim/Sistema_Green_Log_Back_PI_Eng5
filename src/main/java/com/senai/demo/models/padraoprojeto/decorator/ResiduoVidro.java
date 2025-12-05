package com.senai.demo.models.padraoprojeto.decorator;

public class ResiduoVidro extends ResiduosDecorator {

    public ResiduoVidro(Residuos componente) {
        super(componente);
    }

    @Override
    public String getDescricao() {
        String anterior = (componente != null) ? componente.getDescricao() : "";

        if (anterior.isBlank()) {
            return "Vidro";
        }

        return anterior + ", Vidro";
    }
}
