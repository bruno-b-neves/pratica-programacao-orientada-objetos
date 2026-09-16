package view;

import controller.LivroController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;


/**
 * CAMADA DE APRESENTAÇÃO (View)
 * Fluxo: Captura as entradas do usuário e repassa os dados brutos ao LivroController.
 * Trata o retorno exibindo caixas de diálogo de sucesso ou aviso.
 */
public class BibliotecaFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private FormLivroPanel formLivroPanel;
	private LivroController controller;
	
	/**
	 * Create the frame.
	 */
	public BibliotecaFrame(LivroController controller) {
		
		this.controller = controller;
		
		setTitle("Biblioteca - Esquina da Comunidade");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 340, 280);
		
		// Instancia o formulário extraído e o aplica na janela
		formLivroPanel = new FormLivroPanel();
		formLivroPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(formLivroPanel);
		
		
		configurarEventos();
	}

	public void configurarEventos() {
		formLivroPanel.getBtnLimpar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				formLivroPanel.limparCampos();
			}	
		});
		
		formLivroPanel.getBtnSalvar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					controller.cadastrarLivro(
	                    formLivroPanel.getTitulo(),
	                    formLivroPanel.getAutor(),
	                    formLivroPanel.getIsbn(),
	                    formLivroPanel.getCategoria()
	                );
					
					JOptionPane.showMessageDialog(BibliotecaFrame.this,
	                        "Livro cadastrado com sucesso!\n",
	                        "Sucesso", JOptionPane.INFORMATION_MESSAGE);
					
					formLivroPanel.limparCampos();
					
				} catch (IllegalArgumentException ex) {
					JOptionPane.showMessageDialog(BibliotecaFrame.this,
	                        ex.getMessage(),
	                        "Aviso de Validação", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
	}

}
