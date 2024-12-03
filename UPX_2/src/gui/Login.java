package gui;

import dao.DatabaseConnection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends JFrame {
    private static final long serialVersionUID = 4704521761811662806L;

    // Campos de texto e botões para o formulário de login
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;

    public Login() {
        // Configurações iniciais da janela de login
        setTitle("Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Define o comportamento ao fechar a janela
        setLocationRelativeTo(null); // Centraliza a janela na tela

        // Painel que contém os componentes do formulário
        JPanel panel = new JPanel();
        add(panel); // Adiciona o painel à janela
        placeComponents(panel); // Organiza os componentes no painel

        // Configura a ação do botão de login
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtém os valores inseridos nos campos de texto
                String email = emailField.getText();
                String senha = new String(passwordField.getPassword());
                // Verifica as credenciais do usuário
                if (authenticate(email, senha)) {
                    JOptionPane.showMessageDialog(null, "Login bem-sucedido!");
                    dispose(); // Fecha a janela de login
                    new ConsultaProtocolo(); // Abre a tela de consulta de processos
                } else {
                    JOptionPane.showMessageDialog(null, "Email ou senha incorretos.");
                }
            }
        });

        // Configura a ação do botão de cadastro
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Abre a janela de cadastro e define o formulário de login como janela pai
                new Cadastro(Login.this).setVisible(true);
            }
        });
    }

    // Método que organiza os componentes no painel
    private void placeComponents(JPanel panel) {
        panel.setLayout(null); // Define layout absoluto para posicionamento manual

        // Rótulo e campo de texto para o email
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(10, 20, 80, 25); // Define posição e tamanho do rótulo
        panel.add(emailLabel);

        emailField = new JTextField(20); // Cria o campo de texto para o email
        emailField.setBounds(100, 20, 165, 25); // Define posição e tamanho do campo
        panel.add(emailField);

        // Rótulo e campo de texto para a senha
        JLabel passwordLabel = new JLabel("Senha:");
        passwordLabel.setBounds(10, 50, 80, 25); // Define posição e tamanho do rótulo
        panel.add(passwordLabel);

        passwordField = new JPasswordField(20); // Cria o campo de texto para a senha
        passwordField.setBounds(100, 50, 165, 25); // Define posição e tamanho do campo
        panel.add(passwordField);

        // Botão de login
        loginButton = new JButton("Login");
        loginButton.setBounds(10, 80, 80, 25); // Define posição e tamanho do botão
        panel.add(loginButton);

        // Botão de cadastro
        registerButton = new JButton("Cadastrar");
        registerButton.setBounds(100, 80, 100, 25); // Define posição e tamanho do botão
        panel.add(registerButton);
    }

    // Método que autentica o usuário com base nas credenciais fornecidas
    private boolean authenticate(String email, String senha) {
        Connection connection = DatabaseConnection.getConnection(); // Obtém a conexão com o banco de dados
        if (connection != null) {
            String sql = "SELECT * FROM Usuarios WHERE email = ? AND senha = ?"; // Consulta SQL para autenticação
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                // Define os parâmetros da consulta
                stmt.setString(1, email);
                stmt.setString(2, senha);
                ResultSet rs = stmt.executeQuery(); // Executa a consulta
                if (rs.next()) {
                    return true; // Retorna verdadeiro se o usuário for encontrado
                }
            } catch (SQLException e) {
                // Exibe a mensagem de erro e a pilha de exceção
                System.out.println("Erro ao autenticar: " + e.getMessage());
                e.printStackTrace();
            } finally {
                DatabaseConnection.closeConnection(connection); // Fecha a conexão com o banco de dados
            }
        }
        return false; // Retorna falso caso a autenticação falhe
    }

    public static void main(String[] args) {
        // Inicializa a interface gráfica do Swing na thread principal
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Login().setVisible(true); // Cria e exibe a janela de login
            }
        });
    }
}
