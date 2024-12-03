package main;

import javax.swing.*;
import gui.*;

public class Main {
    public static void main(String[] args) {
        // Inicia a aplicação utilizando a thread de interface gráfica do Swing
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Cria e torna visível a tela de login ao iniciar o programa
                new Login().setVisible(true);
            }
        });
    }
}
