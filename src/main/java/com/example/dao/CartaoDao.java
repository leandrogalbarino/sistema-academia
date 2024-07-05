package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;

import java.util.Locale;

import com.example.util.Conexao;
import com.example.dto.CartaoDto;

public class CartaoDao {

    public int incluir(CartaoDto cartao) {
        Connection conexao = null;
        int id_cartao = -1;
        String sql = "INSERT INTO CARTAO_DE_CREDITO(NOME, NUMERO, VALIDADE, CVV) VALUES(?, ?, ?, ?)";
    
        try {
            conexao = Conexao.getInstance().getConnection();
            if (conexao != null) {
                try (PreparedStatement statement = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                    statement.setString(1, cartao.getNome().toUpperCase(Locale.ENGLISH));
                    statement.setString(2, cartao.getNumero());
                    statement.setString(3, cartao.getData_validade());
                    statement.setString(4, cartao.getCvv());
                    statement.executeUpdate();
                    
                    try (ResultSet resultSet = statement.getGeneratedKeys()) {
                        if (resultSet.next()) {
                            id_cartao = resultSet.getInt(1); 
                        }
                    }
                }
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
        return id_cartao;
    }

    public boolean remover(Integer id) {
        Connection conexao = null;
        try {
            conexao = Conexao.getInstance().getConnection();
            String sql = "DELETE FROM CARTAO_DE_CREDITO WHERE id_cartao = ?";
            PreparedStatement statement = conexao.prepareStatement(sql);
            statement.setInt(1, id);
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

}
