package com.example.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import com.example.dto.AlunoPlanoDto;
import com.example.util.Conexao;

public class AlunoPlanoDao {
    AlunoDadosDao banco = new AlunoDadosDao();

    public boolean incluir(AlunoPlanoDto aluno_planoDto) {
        Connection conexao = null;
        try {
            conexao = Conexao.getInstance().getConnection();
            String sql = "INSERT INTO ALUNO_PLANO(ALUNO_CPF, ID_PLANO, DATA_INICIO, ID_CARTAO) VALUES(?, ?, ?, ?)";
            PreparedStatement statement = conexao.prepareStatement(sql);
            statement.setString(1, aluno_planoDto.getAluno_cpf());
            statement.setInt(2, aluno_planoDto.getId_plano());

            
            statement.setDate(3, Date.valueOf(aluno_planoDto.getData_inicio()));
            statement.setInt(4, aluno_planoDto.getId_cartao());
            statement.execute();
            conexao.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean remover(String cpf) {
        Connection conexao = null;
        try {
            conexao = Conexao.getInstance().getConnection();
            String sql = "DELETE FROM ALUNO_PLANO WHERE ALUNO_CPF = ?";
            PreparedStatement statement = conexao.prepareStatement(sql);
            statement.setString(1, cpf);
            statement.execute();
            conexao.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public AlunoPlanoDto buscarAlunoPlano(String cpf) {
        AlunoPlanoDto aluno = null;
        Connection conexao = null;
        try {
            conexao = Conexao.getInstance().getConnection();
            if (conexao != null) {
                String sql = "SELECT * FROM ALUNO_PLANO WHERE ALUNO_CPF = ?";
                PreparedStatement statement = conexao.prepareStatement(sql);
                statement.setString(1, cpf);
                ResultSet resultset = statement.executeQuery();
                if (resultset.next()) {
                    aluno = new AlunoPlanoDto();
                    aluno.setAluno_cpf(resultset.getString("ALUNO_CPF"));
                    Date sql_data = resultset.getDate("DATA_INICIO");
                    if (sql_data != null) {
                        LocalDate data = sql_data.toLocalDate();
                        aluno.setData_inicio(data);
                    }
                    aluno.setId_cartao(resultset.getInt("ID_CARTAO"));
                    aluno.setId_plano(resultset.getInt("ID_PLANO"));
                }
                resultset.close();
                statement.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return aluno;

    }

}
