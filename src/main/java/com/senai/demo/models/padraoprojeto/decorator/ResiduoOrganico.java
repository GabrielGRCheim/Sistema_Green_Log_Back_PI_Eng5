package com.senai.demo.models.padraoprojeto.decorator;

public class ResiduoOrganico extends ResiduosDecorator {

    public ResiduoOrganico(Residuos componente) {
        super(componente);
    }

    @Override
    public String getDescricao() {
        String anterior = (componente != null) ? componente.getDescricao() : "";

        if (anterior.isBlank()) {
            return "Organico";
        }

        return anterior + ", Organico";
    }
}
