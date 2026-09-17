package view;

import javax.swing.*;
import java.awt.*;

/**
 * CAMADA DE APRESENTAÇÃO (View)
 * Demonstração controlada do Null Layout (Posicionamento Absoluto).
 * Esta classe exibe as limitações de rigidez visual ao redimensionar a janela.
 */
public class DemonstracaoNullLayoutFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    public DemonstracaoNullLayoutFrame() {
        super("Demonstração de Limitações - Null Layout");
        configurarJanela();
        inicializarComponentes();
    }

    private void configurarJanela() {
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela na tela
    }

    private void inicializarComponentes() {
        // 1. Cria o painel e desativa o gerenciador de layout
        JPanel painel = new JPanel();
        painel.setLayout(null); // Ativa o Null Layout (posicionamento absoluto)
        painel.setBackground(new Color(245, 247, 250));

        // 2. Rótulo de instrução
        JLabel lblAviso = new JLabel("<html><b>Redimensione a janela</b> para ver as falhas do Null Layout!</html>");
        lblAviso.setBounds(20, 10, 340, 30); // Coordenadas: x=20, y=10, largura=340, altura=30
        painel.add(lblAviso);

        // 3. Campo Título
        JLabel lblTitulo = new JLabel("Título:");
        lblTitulo.setBounds(20, 50, 60, 25);
        painel.add(lblTitulo);

        JTextField txtTitulo = new JTextField();
        txtTitulo.setBounds(80, 50, 270, 25); // Largura fixa: não expande se a janela crescer
        painel.add(txtTitulo);

        // 4. Campo Autor
        JLabel lblAutor = new JLabel("Autor:");
        lblAutor.setBounds(20, 90, 60, 25);
        painel.add(lblAutor);

        JTextField txtAutor = new JTextField();
        txtAutor.setBounds(80, 90, 270, 25);
        painel.add(txtAutor);

        // 5. Botão rígido no canto
        JButton btnSalvar = new JButton("Salvar (Posição Rígida)");
        btnSalvar.setBounds(80, 130, 180, 30);
        painel.add(btnSalvar);

        setContentPane(painel);
    }
}