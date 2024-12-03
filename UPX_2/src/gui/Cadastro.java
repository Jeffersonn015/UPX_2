package gui;

import dao.DatabaseConnection;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// Classe que representa a tela de cadastro
public class Cadastro extends JFrame {
    private static final long serialVersionUID = -6214249630059393526L; // Serial ID para compatibilidade de versões
    private JTextField nameField; // Campo de texto para inserir o nome
    private JTextField emailField; // Campo de texto para inserir o email
    private JPasswordField passwordField; // Campo de senha para inserir a senha
    private JButton registerButton; // Botão para realizar o cadastro
    private JFrame parent; // Referência à janela principal que abriu esta tela

    // Construtor da classe Cadastro
    public Cadastro(JFrame parent) {
        this.setParent(parent); // Define a janela pai (caso necessário retornar)

        // Configurações da janela de cadastro
        setTitle("Cadastro");
        setSize(300, 200); // Define o tamanho da janela
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Fecha apenas esta janela ao clicar em "X"
        setLocationRelativeTo(null); // Centraliza a janela na tela

        // Cria um painel para organizar os componentes
        JPanel panel = new JPanel();
        add(panel); // Adiciona o painel à janela
        placeComponents(panel); // Configura os componentes do painel

        // Configura a ação do botão "Cadastrar"
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtém os valores inseridos nos campos de texto
                String name = nameField.getText();
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());

                // Tenta realizar o cadastro
                if (register(name, email, password)) {
                    JOptionPane.showMessageDialog(null, "Cadastro bem-sucedido!"); // Exibe mensagem de sucesso
                    dispose(); // Fecha a janela de cadastro
                    new ConsultaProtocolo(); // Abre a tela de consulta de protocolos
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar. Tente novamente."); // Mensagem de erro
                }
            }
        });
    }

    // Método para configurar os componentes no painel
    private void placeComponents(JPanel panel) {
        panel.setLayout(null); // Define o layout manual (coordenadas)

        // Rótulo e campo para o nome
        JLabel nameLabel = new JLabel("Nome:");
        nameLabel.setBounds(10, 20, 80, 25); // Define a posição e tamanho do rótulo
        panel.add(nameLabel);

        nameField = new JTextField(20);
        nameField.setBounds(100, 20, 165, 25); // Define a posição e tamanho do campo de texto
        panel.add(nameField);

        // Rótulo e campo para o email
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(10, 50, 80, 25);
        panel.add(emailLabel);

        emailField = new JTextField(20);
        emailField.setBounds(100, 50, 165, 25);
        panel.add(emailField);

        // Rótulo e campo para a senha
        JLabel passwordLabel = new JLabel("Senha:");
        passwordLabel.setBounds(10, 80, 80, 25);
        panel.add(passwordLabel);

        passwordField = new JPasswordField(20);
        passwordField.setBounds(100, 80, 165, 25);
        panel.add(passwordField);

        // Botão de cadastro
        registerButton = new JButton("Cadastrar");
        registerButton.setBounds(10, 110, 100, 25);
        panel.add(registerButton);
    }

    // Método para realizar o cadastro no banco de dados
    private boolean register(String name, String email, String password) {
        Connection connection = DatabaseConnection.getConnection(); // Obtém a conexão com o banco
        if (connection != null) {
            // SQL para inserir um novo usuário
            String sql = "INSERT INTO usuarios (nome, email, senha) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                // Preenche os parâmetros do SQL
                stmt.setString(1, name);
                stmt.setString(2, email);
                stmt.setString(3, password);

                // Executa o comando e verifica se inseriu pelo menos uma linha
                int rowsInserted = stmt.executeUpdate();
                return rowsInserted > 0; // Retorna true se o cadastro foi bem-sucedido
            } catch (SQLException e) {
                // Exibe e registra o erro
                System.out.println("Erro ao cadastrar: " + e.getMessage());
                e.printStackTrace();
            } finally {
                // Fecha a conexão com o banco de dados
                DatabaseConnection.closeConnection(connection);
            }
        }
        return false; // Retorna false caso a conexão ou o cadastro falhem
    }

    // Getter e setter para a janela pai
    public JFrame getParent() {
        return parent;
    }

    public void setParent(JFrame parent) {
        this.parent = parent;
    }
}
