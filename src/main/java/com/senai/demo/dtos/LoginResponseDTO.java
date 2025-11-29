package com.senai.demo.dtos;

public class LoginResponseDTO {
    private String mensagem;
    private boolean autenticado;

    public LoginResponseDTO(String mensagem, boolean autenticado) {
        this.mensagem = mensagem;
        this.autenticado = autenticado;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public boolean isAutenticado() {
        return autenticado;
    }

    public void setAutenticado(boolean autenticado) {
        this.autenticado = autenticado;
    }
}
