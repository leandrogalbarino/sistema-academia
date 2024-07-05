package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.example.dto.AlunoCargaDto;
import com.example.dto.AlunoTreinoExercicioDto;
import com.example.util.Conexao;

public class AlunoCargaDao {

    public boolean incluir(AlunoCargaDto alunoDto) {
        String sql = "INSERT INTO ALUNO_TREINO_EXERCICIO_CARGA (ID_TREINO, ID_EXERCICIO, CARGA) VALUES(?, ?, ?)";
        try (Connection conexao = Conexao.getInstance().getConnection();
                PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setInt(1, alunoDto.getId_treino());
            statement.setInt(2, alunoDto.getId_exercicio());
            statement.setInt(3, alunoDto.getCarga());
            statement.execute();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<AlunoCargaDto> buscarEvolucaoCargaExercicio(AlunoTreinoExercicioDto aluno_carga) {
        List<AlunoCargaDto> evolucaoCarga = new ArrayList<>();

        String sql = "SELECT * FROM ALUNO_TREINO_EXERCICIO_CARGA WHERE ID_TREINO = ? AND ID_EXERCICIO = ?";

        try (Connection conexao = Conexao.getInstance().getConnection();
             PreparedStatement statement = conexao.prepareStatement(sql)) {

            statement.setInt(1, aluno_carga.getId_treino());
            statement.setInt(2, aluno_carga.getId_exercicio());

            try (ResultSet resultset = statement.executeQuery()) {
                while (resultset.next()) {
                    AlunoCargaDto carga = new AlunoCargaDto();
                    carga.setId_treino(resultset.getInt("ID_TREINO"));
                    carga.setId_exercicio(resultset.getInt("ID_EXERCICIO"));
                    carga.setCarga(resultset.getInt("CARGA"));
                    evolucaoCarga.add(carga);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return evolucaoCarga;
    }

}
