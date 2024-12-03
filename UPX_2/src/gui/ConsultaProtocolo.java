package gui;

import javax.swing.*;
import UPX02.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Classe que representa a janela de consulta de processos
public class ConsultaProtocolo extends JFrame {
    private static final long serialVersionUID = 8040271864939627561L;
    private JTextField protocoloField; // Campo de texto para o número do protocolo
    private JTextArea resultadoArea;   // Área de texto para mostrar o resultado da consulta
    private ProcessoService processoService; // Serviço que obtém detalhes dos processos
    private JButton logoutButton; // Botão para fazer logout

    public ConsultaProtocolo() {
        super("Consulta de Processos"); // Título da janela
        this.processoService = new ProcessoService(); // Instancia o serviço de processos

        setLayout(new BorderLayout()); // Define o layout principal da janela

        // Painel superior para entrada do protocolo
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout()); // Layout para organizar os componentes de entrada

        JLabel protocoloLabel = new JLabel("Número do Protocolo:"); // Rótulo para o campo de texto
        protocoloField = new JTextField(20); // Campo de texto para inserir o número do protocolo
        JButton consultarButton = new JButton("Consultar"); // Botão para realizar a consulta

        // Adiciona os componentes ao painel de entrada
        inputPanel.add(protocoloLabel);
        inputPanel.add(protocoloField);
        inputPanel.add(consultarButton);

        // Área de texto para mostrar os resultados da consulta, com barra de rolagem
        resultadoArea = new JTextArea(20, 50);
        resultadoArea.setEditable(false); // Impede a edição dos resultados
        JScrollPane scrollPane = new JScrollPane(resultadoArea); // Adiciona barra de rolagem

        // Painel inferior para o botão de logout
        JPanel logoutPanel = new JPanel();
        logoutButton = new JButton("Retornar");
        logoutPanel.add(logoutButton);

        // Adiciona o painel de entrada, a área de resultado e o painel de logout ao frame
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(logoutPanel, BorderLayout.SOUTH);

        // Adiciona ação ao botão de consulta
        consultarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String numeroProtocolo = protocoloField.getText(); // Obtém o número do protocolo
                if (!numeroProtocolo.isEmpty()) { // Verifica se o campo não está vazio
                    exibirDetalhesDoProcesso(numeroProtocolo); // Exibe os detalhes do processo
                } else {
                    // Mostra mensagem de erro se o campo estiver vazio
                    JOptionPane.showMessageDialog(ConsultaProtocolo.this, "Por favor, insira um número de protocolo.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Adiciona ação ao botão de logout
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fecha a tela de consulta de processos
                new Login().setVisible(true); // Abre a tela de login
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Define o comportamento ao fechar a janela
        pack(); // Ajusta o tamanho da janela para acomodar os componentes
        setLocationRelativeTo(null); // Centraliza a janela na tela
        setVisible(true); // Torna a janela visível
    }

    // Método para exibir os detalhes do processo com base no número do protocolo
    private void exibirDetalhesDoProcesso(String numeroProtocolo) {
        resultadoArea.setText(""); // Limpa a área de resultado

        // Obtém os detalhes do processo usando o serviço e exibe na área de resultado
        String detalhes = processoService.obterDetalhesDoProcesso(numeroProtocolo);
        resultadoArea.setText(detalhes); // Exibe os detalhes obtidos
    }

    // Método principal para iniciar a aplicação
    public static void main(String[] args) {
        // Executa a criação da janela na thread de interface gráfica
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ConsultaProtocolo(); // Cria e exibe a janela principal
            }
        });
    }
}