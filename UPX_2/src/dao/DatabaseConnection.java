package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // Definindo a URL, usuário e senha do banco de dados
    private static final String URL = "jdbc:mysql://localhost:3306/ConsultaProcessoss"; // URL do banco 
    private static final String USER = "root"; // Nome de usuário do MySQL 
    private static final String PASSWORD = ""; // Senha do MySQL 

    // Método que retorna uma conexão com o banco de dados
    public static Connection getConnection() {
        Connection connection = null; // Inicializando a variável de conexão
        try {
            // Tentando estabelecer a conexão com o banco de dados
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexão estabelecida com sucesso!"); // Mensagem de sucesso
        } catch (SQLException e) {
            // Se der erro na conexão, mostra a mensagem do erro
            System.out.println("Erro ao conectar: " + e.getMessage());
            e.printStackTrace(); // Para obter mais detalhes sobre a exceção
        }
        return connection; // Retorna a conexão (ou null se falhar)
    }

    // Método para fechar a conexão com o banco de dados
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Conexão fechada com sucesso!");
            } catch (SQLException e) {
                System.out.println("Erro ao fechar a conexão: " + e.getMessage());
                e.printStackTrace(); // Para obter mais detalhes sobre a exceção
            }
        }
    }
}