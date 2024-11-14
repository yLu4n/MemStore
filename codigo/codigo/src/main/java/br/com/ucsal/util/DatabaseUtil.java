package br.com.ucsal.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseUtil {

    private static final String URL = "jdbc:hsqldb:mem:lojadb";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    // Método para iniciar o banco de dados em memória e inserir dados iniciais
    public static void iniciarBanco() {
        // Carrega o driver do HSQLDB
        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
        } catch (ClassNotFoundException e) {
            System.err.println("Driver HSQLDB não encontrado.");
            e.printStackTrace();
            return;
        }

        // Cria a tabela e insere dados iniciais
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {

            criarTabelaProdutos(stmt);
            inserirDadosIniciais(stmt);

            System.out.println("Banco de dados iniciado com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao iniciar o banco de dados.");
            e.printStackTrace();
        }
    }

    // Método para obter uma conexão com o banco de dados
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Método auxiliar para criar a tabela produtos
    private static void criarTabelaProdutos(Statement stmt) throws SQLException {
        String sqlCreateTable = """
            CREATE TABLE produtos (
                id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                nome VARCHAR(50),
                preco DOUBLE
            )
        """;
        stmt.executeUpdate(sqlCreateTable);
    }

    // Método auxiliar para inserir dados iniciais na tabela produtos
    private static void inserirDadosIniciais(Statement stmt) throws SQLException {
        String sqlInsertNotebook = "INSERT INTO produtos (nome, preco) VALUES ('Notebook', 3500.00)";
        String sqlInsertSmartphone = "INSERT INTO produtos (nome, preco) VALUES ('Smartphone', 1500.00)";

        stmt.executeUpdate(sqlInsertNotebook);
        stmt.executeUpdate(sqlInsertSmartphone);
    }
}