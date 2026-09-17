package view;

import java.util.Arrays;
import java.util.List;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.FocusTraversalPolicy;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 * CAMADA DE APRESENTAÇÃO (View)
 * Formulário com design limpo e harmonizado com a interface nativa do sistema operacional.
 */
public class FormLivroPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    // Paleta de Cores Suaves
    private static final Color COR_FUNDO = 		new Color(245, 247, 250);       
    private static final Color COR_TEXTO = 		new Color(33, 37, 41);         
    private static final Color COR_BORDA = 		new Color(206, 212, 218);     
    private static final Color COR_BTN_SALVAR = new Color(37, 99, 235);
    private static final Color COR_BTN_LIMPAR = new Color(226, 232, 240);

    // Tipografia Padronizada
    private static final Font FONTE_ROTULO = 	new Font("Arial", Font.BOLD, 12);
    private static final Font FONTE_CAMPO = 	new Font("Arial", Font.PLAIN, 12);

    private JTextField 			inputTitulo;
    private JTextField 			inputAutor;
    private JTextField 			inputIsbn;
    private JComboBox<String> 	selectCategoria;
    private JButton 			btnSalvar;
    private JButton 			btnLimpar;

    public FormLivroPanel() {
        setLayout		(new BorderLayout(15, 15));
        setBackground	(COR_FUNDO);
        setBorder       (BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Borda com espaçamento interno suave (padding) para as caixas de texto
        Border bordaCampos = BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder	(COR_BORDA, 1),
            BorderFactory.createEmptyBorder	(4, 6, 4, 6)
        );

        // --- PAINEL DO FORMULÁRIO (GridBagLayout para Responsividade) ---
        JPanel pnlForm = new JPanel(new GridBagLayout());
        pnlForm.setBackground(COR_FUNDO);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.anchor = GridBagConstraints.WEST;

        // Linha 0: Título
        adicionarRotulo ("Título", pnlForm, gbc, 0);
        inputTitulo =   new JTextField();
        adicionarCampo  (inputTitulo, bordaCampos, pnlForm, gbc, 0);

        // Linha 1: Autor
        adicionarRotulo ("Autor", pnlForm, gbc, 1);
        inputAutor = new JTextField();
        adicionarCampo  (inputAutor, bordaCampos, pnlForm, gbc, 1);

        // Linha 2: ISBN
        adicionarRotulo("ISBN", pnlForm, gbc, 2);
        inputIsbn = new JTextField();
        adicionarCampo  (inputIsbn, bordaCampos, pnlForm, gbc, 2);

        // Linha 3: Categoria
        adicionarRotulo("Categoria", pnlForm, gbc, 3);
        String[] opcoesCategorias = {"", "Ficção", "Romance", "Técnico", "Biografia"};
        selectCategoria = new JComboBox<>(opcoesCategorias);
        selectCategoria.setFont			(FONTE_CAMPO);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        pnlForm.add(selectCategoria, gbc);

        add(pnlForm, BorderLayout.CENTER);

        // --- Painel de Botões Nativos ---
        JPanel panelBotoes = new JPanel (new FlowLayout(FlowLayout.RIGHT, 10, 0));
        panelBotoes.setBackground		(COR_FUNDO);

        // Botão Secundário (Ação Neutra)
        btnLimpar = new JButton			("Limpar");
        btnLimpar.setFont				(FONTE_CAMPO);
        btnLimpar.setBackground			(COR_BTN_LIMPAR);
        btnLimpar.setMnemonic			(KeyEvent.VK_L);
        btnLimpar.setToolTipText		("Atalho: Alt + L");
        
        // Botão Principal (Destaque por Tipografia em Negrito)
        btnSalvar = new JButton			("Salvar");
        btnSalvar.setFont				(FONTE_ROTULO);
        btnSalvar.setBackground			(COR_BTN_SALVAR);
        btnSalvar.setMnemonic			(KeyEvent.VK_S);
        btnSalvar.setToolTipText		("Atalho: Alt + S");
        
        panelBotoes.add					(btnLimpar);
        panelBotoes.add					(btnSalvar);

        add                             (panelBotoes, BorderLayout.SOUTH);

        configurarOrdemDeFoco();
    }

    /**
     * Método auxiliar para criar e posicionar rótulos no GridBagLayout.
     */
    private void adicionarRotulo(String texto, JPanel painel, GridBagConstraints gbc, int linha) {
        JLabel lbl = new JLabel(texto);
        lbl.setFont(FONTE_ROTULO);
        lbl.setForeground(COR_TEXTO);
        gbc.gridx = 0;
        gbc.gridy = linha;
        gbc.weightx = 0.0; // Não expande
        gbc.fill = GridBagConstraints.NONE;
        painel.add(lbl, gbc);
    }

    /**
     * Método auxiliar para configurar e posicionar campos de texto no GridBagLayout.
     */
    private void adicionarCampo(JTextField campo, Border borda, JPanel painel, GridBagConstraints gbc, int linha) {
        campo.setFont(FONTE_CAMPO);
        campo.setBorder(borda);
        gbc.gridx = 1;
        gbc.gridy = linha;
        gbc.weightx = 1.0; // Expande horizontalmente ao esticar a janela
        gbc.fill = GridBagConstraints.HORIZONTAL;
        painel.add(campo, gbc);
    }

    private void configurarOrdemDeFoco() {
    	List<Component> ordemFoco = Arrays.asList(
                inputTitulo, inputAutor, inputIsbn, selectCategoria, btnSalvar, btnLimpar
            );

            setFocusTraversalPolicyProvider(true);
            setFocusTraversalPolicy(new FocusTraversalPolicy() {
                @Override
                public Component getComponentAfter(Container container, Component c) {
                    int idx = (ordemFoco.indexOf(c) + 1) % ordemFoco.size();
                    return ordemFoco.get(idx);
                }

                @Override
                public Component getComponentBefore(Container container, Component c) {
                    int idx = ordemFoco.indexOf(c) - 1;
                    if (idx < 0) idx = ordemFoco.size() - 1;
                    return ordemFoco.get(idx);
                }

                @Override
                public Component getFirstComponent(Container container) { return ordemFoco.get(0); }
                @Override
                public Component getLastComponent(Container container) { return ordemFoco.get(ordemFoco.size() - 1); }
                @Override
                public Component getDefaultComponent(Container container) { return ordemFoco.get(0); }
            });
    }

    public String getTitulo() { return inputTitulo.getText().trim(); }
    public String getAutor() { return inputAutor.getText().trim(); }
    public String getIsbn() { return inputIsbn.getText().trim(); }
    public String getCategoria() {
        Object item = selectCategoria.getSelectedItem();
        return item != null ? item.toString() : "";
    }

    public void limparCampos() {
        inputTitulo.setText("");
        inputAutor.setText("");
        inputIsbn.setText("");
        selectCategoria.setSelectedIndex(0);
        inputTitulo.requestFocusInWindow();
    }

    public JButton getBtnLimpar() { return btnLimpar; }
    public JButton getBtnSalvar() { return btnSalvar; }
}