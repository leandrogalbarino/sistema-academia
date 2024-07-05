package com.example.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.example.dto.AlunoListaPresencaDto;
import com.example.util.Conexao;

public class AlunoListaPresencaDao {

    public boolean incluir(AlunoListaPresencaDto alunoDto) {
        String sql = "INSERT INTO ALUNO_LISTA_PRESENCA(CPF, DATA_TREINO, ID_TREINO) VALUES(?, ?, ?)";
        try (Connection conexao = Conexao.getInstance().getConnection();
                PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setString(1, alunoDto.getCpf());
            statement.setDate(2, Date.valueOf(alunoDto.getData_treino()));
            statement.setInt(3, alunoDto.getId_treino());

            int linhas_afetadas = statement.executeUpdate();
            return linhas_afetadas > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Date> buscarDatasPresencaPorIntervalo(String cpf, Date dataInicio, Date dataFim) {
        List<Date> datasPresenca = new ArrayList<>();

        String sql = "SELECT DATA_TREINO FROM ALUNO_LISTA_PRESENCA WHERE CPF = ? AND DATA_TREINO BETWEEN ? AND ?";

        try (Connection conexao = Conexao.getInstance().getConnection();
             PreparedStatement statement = conexao.prepareStatement(sql)) {

            statement.setString(1, cpf);
            statement.setDate(2, dataInicio);
            statement.setDate(3, dataFim);

            try (ResultSet resultset = statement.executeQuery()) {
                while (resultset.next()) {
                    datasPresenca.add(resultset.getDate("DATA_TREINO"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return datasPresenca;
    }

    public void relatorioPresencaAlunoIntervalo(String cpf, Date dataInicio, Date dataFim) {
        List<Date> datasPresenca = buscarDatasPresencaPorIntervalo(cpf, dataInicio, dataFim);

        if (datasPresenca.isEmpty()) {
            System.out.println("O aluno não teve presença no intervalo especificado.");
        } else {
            System.out.println("Datas de presença do aluno no intervalo:");
            for (Date data : datasPresenca) {
                System.out.println(data.toString());
            }
        }
    }
    
}
