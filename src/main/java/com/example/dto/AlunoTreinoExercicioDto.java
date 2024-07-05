package com.example.dto;

import java.time.LocalDate;
import java.util.Objects;

public class AlunoTreinoExercicioDto implements Cloneable {
    Integer id_treino;
    Integer id_exercicio;
    Integer series;
    Integer min_rep;
    Integer max_rep;
    Integer carga;
    Integer tempo_descanso;

    public Integer getId_treino() {
        return id_treino;
    }

    public void setId_treino(Integer id_treino) {
        this.id_treino = id_treino;
    }

    public Integer getId_exercicio() {
        return id_exercicio;
    }

    public void setId_exercicio(Integer id_exercicio) {
        this.id_exercicio = id_exercicio;
    }

    public Integer getCarga() {
        return carga;
    }

    public void setCarga(Integer carga) {
        this.carga = carga;
    }

    public Integer getMax_rep() {
        return max_rep;
    }

    public void setMax_rep(Integer max_rep) {
        this.max_rep = max_rep;
    }

    public Integer getMin_rep() {
        return min_rep;
    }

    public void setMin_rep(Integer min_rep) {
        this.min_rep = min_rep;
    }

    public Integer getSeries() {
        return series;
    }

    public void setSeries(Integer series) {
        this.series = series;
    }

    public Integer getTempo_descanso() {
        return tempo_descanso;
    }

    public void setTempo_descanso(Integer tempo_descanso) {
        this.tempo_descanso = tempo_descanso;
    }

    private LocalDate dataTreino;

    public LocalDate getDataTreino() {
        return dataTreino;
    }

    public void setDataTreino(LocalDate dataTreino) {
        this.dataTreino = dataTreino;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        AlunoTreinoExercicioDto that = (AlunoTreinoExercicioDto) o;
        return id_treino == that.id_treino &&
                Objects.equals(id_exercicio, that.id_exercicio) &&
                Objects.equals(series, that.series) &&
                Objects.equals(min_rep, that.min_rep) &&
                Objects.equals(max_rep, that.max_rep) &&
                Objects.equals(carga, that.carga) &&
                Objects.equals(tempo_descanso, that.tempo_descanso);
    }
    @Override
    public AlunoTreinoExercicioDto clone() {
        try {
            return (AlunoTreinoExercicioDto) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Clone não suportado", e);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_treino, id_exercicio, series, min_rep, max_rep, carga, tempo_descanso);
    }

}