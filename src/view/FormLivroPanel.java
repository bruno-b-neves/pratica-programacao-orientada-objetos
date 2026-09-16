package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FocusTraversalPolicy;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
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
        setLayout		(null);
        setBackground	(COR_FUNDO);

        // Borda com espaçamento interno suave (padding) para as caixas de texto
        Border bordaCampos = BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder	(COR_BORDA, 1),
            BorderFactory.createEmptyBorder	(3, 6, 3, 6)
        );

        // --- Rótulos e Campos ---
        JLabel txtTitulo = new JLabel	("Título");
        txtTitulo.setFont				(FONTE_ROTULO);
        txtTitulo.setForeground			(COR_TEXTO);
        txtTitulo.setBounds				(15, 15, 50, 20);
        add								(txtTitulo);

        inputTitulo = new JTextField();
        txtTitulo.setLabelFor			(inputTitulo);
        inputTitulo.setFont				(FONTE_CAMPO);
        inputTitulo.setBorder			(bordaCampos);
        inputTitulo.setBounds			(75, 12, 245, 26);
        add								(inputTitulo);

        JLabel txtAutor = new JLabel	("Autor");
        txtAutor.setFont				(FONTE_ROTULO);
        txtAutor.setForeground			(COR_TEXTO);
        txtAutor.setBounds				(15, 45, 50, 20);
        txtAutor.setHorizontalAlignment	(SwingConstants.LEFT);
        add								(txtAutor);

        inputAutor = new JTextField();
        txtAutor.setLabelFor			(inputAutor);
        inputAutor.setFont				(FONTE_CAMPO);
        inputAutor.setBorder			(bordaCampos);
        inputAutor.setBounds			(75, 42, 245, 26);
        add(inputAutor);

        JLabel txtIsbn = new JLabel		("ISBN");
        txtIsbn.setFont					(FONTE_ROTULO);
        txtIsbn.setForeground			(COR_TEXTO);
        txtIsbn.setBounds				(15, 75, 50, 20);
        add								(txtIsbn);

        inputIsbn = new JTextField();
        txtIsbn.setLabelFor				(inputIsbn);
        inputIsbn.setFont				(FONTE_CAMPO);
        inputIsbn.setBorder				(bordaCampos);
        inputIsbn.setBounds				(75, 72, 245, 26);
        add								(inputIsbn);

        JLabel txtCategoria = new JLabel("Categoria");
        txtCategoria.setFont			(FONTE_ROTULO);
        txtCategoria.setForeground		(COR_TEXTO);
        txtCategoria.setBounds			(15, 105, 65, 20);
        add								(txtCategoria);

        String[] opcoesCategorias = {"", "Ficção", "Romance", "Técnico", "Biografia"};
        selectCategoria = new JComboBox<>(opcoesCategorias);
        selectCategoria.setFont			(FONTE_CAMPO);
        selectCategoria.setBounds		(85, 102, 235, 26);
        add								(selectCategoria);

        // --- Painel de Botões Nativos ---
        JPanel panelBotoes = new JPanel();
        panelBotoes.setBackground		(COR_FUNDO);
        panelBotoes.setBounds			(15, 140, 305, 40);
        add								(panelBotoes);

        // Botão Secundário (Ação Neutra)
        btnLimpar = new JButton			("Limpar");
        btnLimpar.setFont				(FONTE_CAMPO);
        btnLimpar.setBackground			(COR_BTN_LIMPAR);
        btnLimpar.setMnemonic			(KeyEvent.VK_L);
        btnLimpar.setToolTipText		("Atalho: Alt + L");
        panelBotoes.add					(btnLimpar);

        // Botão Principal (Destaque por Tipografia em Negrito)
        btnSalvar = new JButton			("Salvar");
        btnSalvar.setFont				(FONTE_ROTULO);
        btnLimpar.setBackground			(COR_BTN_SALVAR);
        btnSalvar.setMnemonic			(KeyEvent.VK_S);
        btnSalvar.setToolTipText		("Atalho: Alt + S");
        panelBotoes.add					(btnSalvar);

        configurarOrdemDeFoco();
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