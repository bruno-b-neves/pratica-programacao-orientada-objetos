package view;

import controller.LivroController;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;


/**
 * CAMADA DE APRESENTAÇÃO (View)
 * Fluxo: Captura as entradas do usuário e repassa os dados brutos ao LivroController.
 * Trata o retorno exibindo caixas de diálogo de sucesso ou aviso.
 * Gerencia a navegação por CardLayout, barra de menus e exibição do acervo.
 */
public class BibliotecaFrame extends JFrame {

	private static final 	long serialVersionUID = 1L;
	
	private 				LivroController controller;
	private 				CardLayout cardLayout;
	private 				JPanel pnlCartoes;
	
	private 				FormLivroPanel formLivroPanel;
	private 				LivroTableModel tableModel;
	
	/**
	 * Create the frame.
	 */
	public BibliotecaFrame(LivroController controller) {
		
		this.controller = controller;
		
		setTitle				("Biblioteca - Esquina da Comunidade");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds				(100, 100, 640, 520);
		
		// Instancia o formulário extraído e o aplica na janela
		formLivroPanel = 		new FormLivroPanel();
		formLivroPanel.setBorder(new EmptyBorder(30, 30, 30, 30));
		setContentPane			(formLivroPanel);
		
		configurarMenu();
		inicializarComponentes();
		configurarEventos();
	}

	private void configurarMenu() {
		JMenuBar menuBar = new JMenuBar();

		// Menu Navegação
		JMenu menuNavegacao = 		new JMenu("Navegação");
		JMenuItem itemInicio = 		new JMenuItem("Início");
		JMenuItem itemCadastro = 	new JMenuItem("Cadastrar Livros");
		JMenuItem itemConsulta = 	new JMenuItem("Consultar Acervo");

		itemInicio.addActionListener	(e -> cardLayout.show(pnlCartoes, "INICIO"));
		itemCadastro.addActionListener	(e -> cardLayout.show(pnlCartoes, "CADASTRO"));
		itemConsulta.addActionListener	(e -> {
			atualizarTabela();
			cardLayout.show(pnlCartoes, "CONSULTA");
		});

		menuNavegacao.add(itemInicio);
		menuNavegacao.add(itemCadastro);
		menuNavegacao.add(itemConsulta);

		// Menu Sistema
		JMenu menuSistema = new JMenu("Sistema");
		JMenuItem itemSair = new JMenuItem("Sair");
		itemSair.addActionListener(e -> System.exit(0));
		menuSistema.add(itemSair);

		menuBar.add(menuNavegacao);
		menuBar.add(menuSistema);

		setJMenuBar(menuBar);
	}

	private void inicializarComponentes() {
		cardLayout = new CardLayout();
		pnlCartoes = new JPanel(cardLayout);
		tableModel = new LivroTableModel();

		formLivroPanel = new FormLivroPanel();

		pnlCartoes.add(criarPainelInicio(), "INICIO");
		pnlCartoes.add(formLivroPanel, "CADASTRO");
		pnlCartoes.add(criarPainelConsulta(), "CONSULTA");

		setContentPane(pnlCartoes);
	}

	private JPanel criarPainelInicio() {
		JPanel pnl = new JPanel(new GridBagLayout());
		JLabel lblBemVindo = new JLabel("<html><center><h2>Biblioteca Esquina da Comunidade</h2><p>Utilize o menu superior para cadastrar novos livros ou consultar o acervo.</p></center></html>");
		lblBemVindo.setHorizontalAlignment(SwingConstants.CENTER);
		pnl.add(lblBemVindo);
		return pnl;
	}

	private JPanel criarPainelConsulta() {
		JPanel pnl = new JPanel(new BorderLayout(15, 15));
		pnl.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		JTable tabela = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(tabela);

		JLabel lblTitulo = new JLabel("Acervo de Livros Cadastrados");
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 14));

		pnl.add(lblTitulo, BorderLayout.NORTH);
		pnl.add(scrollPane, BorderLayout.CENTER);

		return pnl;
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

	private void atualizarTabela() {
		tableModel.setLivros(controller.listarLivros());
	}

}
