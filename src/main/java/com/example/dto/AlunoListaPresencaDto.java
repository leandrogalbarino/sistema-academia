package com.example.dto;

import java.time.LocalDate;

public class AlunoListaPresencaDto {
    private Integer id_treino;
    private String cpf;
    private LocalDate data_treino;

    public String getCpf() {
        return cpf;
    }

    public LocalDate getData_treino() {
        return data_treino;
    }

    public Integer getId_treino() {
        return id_treino;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setData_treino(LocalDate data_treino) {
        this.data_treino = data_treino;
    }

    public void setId_treino(Integer id_treino) {
        this.id_treino = id_treino;
    }

}
