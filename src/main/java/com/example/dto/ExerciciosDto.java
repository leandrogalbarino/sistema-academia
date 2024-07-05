package com.example.dto;

public class ExerciciosDto {
    private int numero;
    private String nome;
    private String musculosAtivos;

    public String getMusculosAtivos() {
        return musculosAtivos;
    }

    public String getNome() {
        return nome;
    }

    public int getNumero() {
        return numero;
    }
    
    public void setMusculosAtivos(String musculosAtivos) {
        this.musculosAtivos = musculosAtivos;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void setNumero(int numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return " " + numero +
                " - " + nome +
                " - " + musculosAtivos;
    }
    
}
