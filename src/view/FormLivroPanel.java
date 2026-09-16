package view;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class FormLivroPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private JTextField inputTitulo;
	private JTextField inputAutor;
	private JTextField inputIsbn;
	private JComboBox<String> selectCategoria;
	private JButton btnSalvar;
	private JButton btnLimpar;
	
	public FormLivroPanel() {
		setLayout(null);
		
//		Rótulos e Campos
		JLabel txtTitulo = new JLabel("Título");
		txtTitulo.setBounds(10, 15, 37, 16);
		add(txtTitulo);
		
		inputTitulo = new JTextField();
		txtTitulo.setLabelFor(inputTitulo);
		inputTitulo.setBounds(52, 10, 268, 26);
		add(inputTitulo);
		
		JLabel txtAutor = new JLabel("Autor");
		txtAutor.setBounds(10, 41, 35, 16);
		txtAutor.setHorizontalAlignment(SwingConstants.LEFT);
		add(txtAutor);
		
		inputAutor = new JTextField();
		txtAutor.setLabelFor(inputAutor);
		inputAutor.setBounds(52, 36, 268, 26);
		add(inputAutor);
		
		JLabel txtIsbn = new JLabel("ISBN");
		txtIsbn.setBounds(10, 69, 28, 16);
		add(txtIsbn);
		
		inputIsbn = new JTextField();
		txtIsbn.setLabelFor(inputIsbn);
		inputIsbn.setBounds(52, 64, 268, 26);
		add(inputIsbn);
		
		JLabel txtCategoria = new JLabel("Categoria");
		txtCategoria.setBounds(10, 97, 60, 16);
		add(txtCategoria);
		
		String[] opcoesCategorias = {"", "Ficção", "Romance", "Técnico", "Biografia"};
		selectCategoria = new JComboBox<>(opcoesCategorias);
		selectCategoria.setBounds(82, 93, 238, 27);
		add(selectCategoria);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 180, 310, 40);
		add(panel);
		
		btnLimpar = new JButton("Limpar");
		panel.add(btnLimpar);
		
		btnSalvar = new JButton("Salvar");
		panel.add(btnSalvar);
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
	}
	
	public JButton getBtnLimpar() { return btnLimpar; }
    public JButton getBtnSalvar() { return btnSalvar; }
	
}
