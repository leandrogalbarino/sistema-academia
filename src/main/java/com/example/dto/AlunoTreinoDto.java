package com.example.dto;

public class AlunoTreinoDto {
    private String cpf;
    private String nome_treino;
    private Integer id_treino;

    public String getCpf() {
        return cpf;
    }

    public Integer getId_treino() {
        return id_treino;
    }

    public String getNome_treino() {
        return nome_treino;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setId_treino(Integer id_treino) {
        this.id_treino = id_treino;
    }

    public void setNome_treino(String nome_treino) {
        this.nome_treino = nome_treino;
    }
    
}