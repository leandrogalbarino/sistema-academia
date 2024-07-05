package com.example.dto;

public class PlanoDto {
    private int codigo;
    private String nome;
    private double mensalidade;

    public int getCodigo() {
        return codigo;
    }

    public double getMensalidade() {
        return mensalidade;
    }

    public String getNome() {
        return nome;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setMensalidade(double mensalidade) {
        this.mensalidade = mensalidade;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return " " + codigo +
                " - " + nome +
                " - R$ " + String.format("%.2f", mensalidade);
    }

}
