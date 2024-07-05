package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.example.dto.CartaoDto;
import com.example.dto.PlanoDto;
import com.example.util.Conexao;

public class PlanosUtils {

    public int incluir(CartaoDto cartao) {
        Connection conexao = null;
        int id_cartao = -1;
        try {
            conexao = Conexao.getInstance().getConnection();
            if (conexao != null) {

                String sql = "INSERT INTO CARTAO_DE_CREDITO(NUMERO, NOME, VALIDADE, CVV) VALUES(?, ?, ?, ?)";
                PreparedStatement statement = conexao.prepareStatement(sql);
                statement.setString(1, cartao.getNumero());
                statement.setString(2, cartao.getNome().toUpperCase(Locale.ENGLISH));
                statement.setString(3, cartao.getData_validade());
                statement.setString(4, cartao.getCvv());
                statement.executeUpdate();
                conexao.close();
                ResultSet resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    id_cartao = resultSet.getInt(1);
                }
                return id_cartao;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return id_cartao;
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return id_cartao;
    }

    public PlanoDto buscarPlano(int codigo) {
        String sql = "SELECT * FROM PLANOS WHERE codigo = ?";
        PlanoDto plano = null;

        try (Connection conexao = Conexao.getInstance().getConnection();
                PreparedStatement statement = conexao.prepareStatement(sql)) {

            statement.setInt(1, codigo);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    plano = new PlanoDto();
                    plano.setNome(resultSet.getString("nome"));
                    plano.setCodigo(resultSet.getInt("codigo"));
                    plano.setMensalidade(resultSet.getFloat("mensalidade"));
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return plano;
    }

    public List<PlanoDto> listaPlanos() {
        List<PlanoDto> listaPlanos = new ArrayList<>();
        String sql = "SELECT codigo, nome, mensalidade FROM planos";

        try (Connection conexao = Conexao.getInstance().getConnection();
                Statement statement = conexao.createStatement();) {

            try (ResultSet resultSet = statement.executeQuery(sql);) {
                while (resultSet.next()) {

                    PlanoDto plano = new PlanoDto();
                    plano.setNome(resultSet.getString("nome"));
                    plano.setCodigo(resultSet.getInt("codigo"));
                    plano.setMensalidade(resultSet.getFloat("mensalidade"));
                    listaPlanos.add(plano);
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaPlanos;
    }

    public boolean adicionarPlano(PlanoDto plano) {

        String sql = "INSERT INTO planos (codigo, nome, mensalidade) VALUES (?, ?, ?)";

        try (Connection conexao = Conexao.getInstance().getConnection();
                PreparedStatement statement = conexao.prepareStatement(sql);) {
            statement.setInt(1, plano.getCodigo());
            statement.setString(2, plano.getNome());
            statement.setDouble(3, plano.getMensalidade());
            int linhasInseridas = statement.executeUpdate();
            return linhasInseridas > 0;

        } catch (ClassNotFoundException e) {
            System.out.println("Erro ao inserir plano: " + e.getMessage());
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Erro ao inserir plano: " + e.getMessage());
        } 
        return false;
    }
}
