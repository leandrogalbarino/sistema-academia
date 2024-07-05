package com.example.util;


import java.sql.*;

public class Conexao {

    private static final String URL = "jdbc:postgresql://localhost:5432/sistema_academia";
    private static final String USER = "postgres";
    private static final String PASSWORD = "kise";
    
    private static Conexao conexao;

    public static Conexao getInstance() {
        if (conexao == null) {
            conexao = new Conexao();
        }
        return conexao;
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void desconectarBancoDados(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao encerrar a conexão com o banco de dados: " + e.getMessage());
            }
        }
    }
}
