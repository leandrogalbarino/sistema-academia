package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.example.dto.AlunoTreinoDto;
import com.example.dto.AlunoTreinoExercicioDto;
import com.example.util.Conexao;

public class AlunoTreinoDao {

    private int recuperarChave(AlunoTreinoDto alunoDto) {
        int id_treino = -1;
        String sql = "SELECT * FROM ALUNO_TREINO WHERE CPF = ? AND NOME_TREINO = ?";
        try (Connection conexao = Conexao.getInstance().getConnection();
                PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setString(1, alunoDto.getCpf());
            statement.setString(2, alunoDto.getNome_treino().toUpperCase(Locale.ENGLISH));
            try (ResultSet resultset = statement.executeQuery()) {
                if (resultset.next()) {
                    id_treino = resultset.getInt("ID_TREINO");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id_treino;
    }

    public int incluir(AlunoTreinoDto alunoDto) {
        String sql = "INSERT INTO ALUNO_TREINO(CPF, NOME_TREINO) VALUES(?, ?)";
        try (Connection conexao = Conexao.getInstance().getConnection();
                PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setString(1, alunoDto.getCpf());
            statement.setString(2, alunoDto.getNome_treino().toUpperCase(Locale.ENGLISH));
            statement.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return recuperarChave(alunoDto);
    }

    public boolean remover(Integer id) {
        String sql = "DELETE FROM ALUNO_TREINO WHERE ID_TREINO = ?";

        try (Connection conexao = Conexao.getInstance().getConnection();
                PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public AlunoTreinoDto buscarId(Integer id) {

        AlunoTreinoDto aluno = null;
        String sql = "SELECT * FROM ALUNO_TREINO WHERE ID_TREINO = ?";

        try (Connection conexao = Conexao.getInstance().getConnection();
                PreparedStatement statement = conexao.prepareStatement(sql);) {

            statement.setInt(1, id);
            try (ResultSet resultset = statement.executeQuery()) {
                if (resultset.next()) {
                    aluno = new AlunoTreinoDto();
                    aluno.setCpf(resultset.getString("CPF"));
                    aluno.setNome_treino(resultset.getString("NOME_TREINO"));
                    aluno.setId_treino(resultset.getInt("ID_TREINO"));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return aluno;
    }

    public List<AlunoTreinoDto> buscarTreinosCpf(String nome) {
        List<AlunoTreinoDto> lista_alunos = new ArrayList<AlunoTreinoDto>();
        String sql = "SELECT * FROM ALUNO_TREINO WHERE CPF = ?";

        try (Connection conexao = Conexao.getInstance().getConnection();
                PreparedStatement statement = conexao.prepareStatement(sql);) {
            statement.setString(1, nome.toUpperCase(Locale.ENGLISH));

            try (ResultSet resultset = statement.executeQuery()) {
                while (resultset.next()) {
                    AlunoTreinoDto aluno = new AlunoTreinoDto();
                    aluno.setCpf(resultset.getString("CPF"));
                    aluno.setNome_treino(resultset.getString("NOME_TREINO"));
                    aluno.setId_treino(resultset.getInt("ID_TREINO"));

                    lista_alunos.add(aluno);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista_alunos;
    }

    public boolean alterar(AlunoTreinoExercicioDto treino, int id_exercicio) {
        String sql = "UPDATE ALUNO_TREINO_EXERCICIO SET ID_EXERCICIO = ?, SERIES = ?, REP_MIN = ?, REP_MAX = ?, CARGA = ?, TEMPO_DE_DESCANSO = ? WHERE ID_TREINO = ? AND ID_EXERCICIO = ?";

        try (Connection conexao = Conexao.getInstance().getConnection();
                PreparedStatement statement = conexao.prepareStatement(sql);) {
                    
            statement.setInt(1, treino.getId_exercicio());
            statement.setInt(2, treino.getSeries());
            statement.setInt(3, treino.getMin_rep());
            statement.setInt(4, treino.getMax_rep());
            statement.setInt(5, treino.getCarga());
            statement.setInt(6, treino.getTempo_descanso());
            statement.setInt(7, treino.getId_treino());
            statement.setInt(8, treino.getId_exercicio());
            statement.setInt(8, id_exercicio);

            int linhas_afetadas = statement.executeUpdate();
            System.out.println(linhas_afetadas);
            return linhas_afetadas > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<AlunoTreinoExercicioDto> buscarTreino(int id) {
        String sql = "SELECT * FROM ALUNO_TREINO_EXERCICIO WHERE ID_TREINO = ?";
        AlunoTreinoExercicioDto treino_exercicio = null;
        List<AlunoTreinoExercicioDto> lista_exercicios = new ArrayList<AlunoTreinoExercicioDto>();

        try (Connection conexao = Conexao.getInstance().getConnection();
                PreparedStatement statement = conexao.prepareStatement(sql);) {

            statement.setInt(1, id);

            try (ResultSet resultset = statement.executeQuery()) {
                while (resultset.next()) {
                    treino_exercicio = new AlunoTreinoExercicioDto();
                    treino_exercicio.setId_treino(resultset.getInt("ID_TREINO"));
                    treino_exercicio.setId_exercicio(resultset.getInt("ID_EXERCICIO"));
                    treino_exercicio.setSeries(resultset.getInt("SERIES"));
                    treino_exercicio.setMin_rep(resultset.getInt("REP_MIN"));
                    treino_exercicio.setMax_rep(resultset.getInt("REP_MAX"));
                    treino_exercicio.setCarga(resultset.getInt("CARGA"));
                    treino_exercicio.setTempo_descanso(resultset.getInt("TEMPO_DE_DESCANSO"));
                    lista_exercicios.add(treino_exercicio);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista_exercicios;
    }

    public AlunoTreinoExercicioDto buscarExercicioId(int id_treino, int id_exercicio) {
        String sql = "SELECT * FROM ALUNO_TREINO_EXERCICIO WHERE ID_TREINO = ? AND ID_EXERCICIO = ?";
        AlunoTreinoExercicioDto treino_exercicio = null;

        try (Connection conexao = Conexao.getInstance().getConnection();
                PreparedStatement statement = conexao.prepareStatement(sql);) {

            statement.setInt(1, id_treino);
            statement.setInt(2, id_exercicio);
            try (ResultSet resultset = statement.executeQuery()) {
                if (resultset.next()) {
                    treino_exercicio = new AlunoTreinoExercicioDto();
                    treino_exercicio.setId_treino(resultset.getInt("ID_TREINO"));
                    treino_exercicio.setId_exercicio(resultset.getInt("ID_EXERCICIO"));
                    treino_exercicio.setSeries(resultset.getInt("SERIES"));
                    treino_exercicio.setMin_rep(resultset.getInt("REP_MIN"));
                    treino_exercicio.setMax_rep(resultset.getInt("REP_MAX"));
                    treino_exercicio.setCarga(resultset.getInt("CARGA"));
                    treino_exercicio.setTempo_descanso(resultset.getInt("TEMPO_DE_DESCANSO"));
                }
                return treino_exercicio;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return treino_exercicio;
        }
    }

    public boolean adicionarExercicio(AlunoTreinoExercicioDto alunoDto) {
        String sql = "INSERT INTO ALUNO_TREINO_EXERCICIO(ID_TREINO, ID_EXERCICIO, SERIES, REP_MIN, REP_MAX, CARGA, TEMPO_DE_DESCANSO) VALUES(?, ?, ?, ?, ?, ?, ?)";
        try (Connection conexao = Conexao.getInstance().getConnection();
                PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setInt(1, alunoDto.getId_treino());
            statement.setInt(2, alunoDto.getId_exercicio());
            statement.setInt(3, alunoDto.getSeries());
            statement.setInt(4, alunoDto.getMax_rep());
            statement.setInt(5, alunoDto.getMin_rep());
            statement.setInt(6, alunoDto.getCarga());
            statement.setInt(7, alunoDto.getTempo_descanso());

            int linhas_afetadas = statement.executeUpdate();
            if (linhas_afetadas > 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
}