package com.example.dto;

public class AlunoCargaDto {
    Integer id_exercicio;
    Integer id_treino;
    Integer carga;

    public Integer getCarga() {
        return carga;
    }

    public Integer getId_exercicio() {
        return id_exercicio;
    }

    public Integer getId_treino() {
        return id_treino;
    }

    public void setCarga(Integer carga) {
        this.carga = carga;
    }
    
    public void setId_exercicio(Integer id_exercicio) {
        this.id_exercicio = id_exercicio;
    }

    public void setId_treino(Integer id_treino) {
        this.id_treino = id_treino;
    }
}
