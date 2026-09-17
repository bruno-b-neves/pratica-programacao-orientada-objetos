package view;

import controller.LivroController;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;

public class BibliotecaFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private final LivroController controller;
	private CardLayout cardLayout;
	private JPanel pnlCartoes;

	private FormLivroPanel formLivroPanel;
	private LivroTableModel tableModel;
	private JTable tabela;

	public BibliotecaFrame(LivroController controller) {
		this.controller = controller;

		setTitle("Biblioteca - Esquina da Comunidade");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 680, 520);
		setLocationRelativeTo(null);

		configurarMenu();
		inicializarComponentes();
		configurarEventos();
		
		// Carrega a coleção inicial em memória ao abrir o sistema
		carregarDadosIniciais();
	}

	private void configurarMenu() {
		JMenuBar menuBar = new JMenuBar();

		JMenu menuNavegacao = new JMenu("Navegação");
		JMenuItem itemInicio = new JMenuItem("Início");
		JMenuItem itemCadastro = new JMenuItem("Cadastrar Livro");
		JMenuItem itemConsulta = new JMenuItem("Consultar Acervo");

		itemInicio.addActionListener(e -> cardLayout.show(pnlCartoes, "INICIO"));
		itemCadastro.addActionListener(e -> cardLayout.show(pnlCartoes, "CADASTRO"));
		itemConsulta.addActionListener(e -> cardLayout.show(pnlCartoes, "CONSULTA"));

		menuNavegacao.add(itemInicio);
		menuNavegacao.add(itemCadastro);
		menuNavegacao.add(itemConsulta);

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
		tableModel = new LivroTableModel(); // Instancia o modelo

		formLivroPanel = new FormLivroPanel();

		pnlCartoes.add(criarPainelInicio(), "INICIO");
		pnlCartoes.add(formLivroPanel, "CADASTRO");
		pnlCartoes.add(criarPainelConsulta(), "CONSULTA");

		setContentPane(pnlCartoes);
	}

	private JPanel criarPainelInicio() {
		JPanel pnl = new JPanel(new GridBagLayout());
		JLabel lblWelcome = new JLabel("<html><center><h2>Biblioteca Esquina da Comunidade</h2><p>Utilize o menu superior para navegar.</p></center></html>");
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		pnl.add(lblWelcome);
		return pnl;
	}

	private JPanel criarPainelConsulta() {
    JPanel pnl = new JPanel(new BorderLayout(10, 10));
    pnl.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

    tabela = new JTable(tableModel);

    // 1. CONFIGURAÇÃO DE SELEÇÃO: Restringe para seleção única de linha
    tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    tabela.setRowHeight(26); // Altura adequada para leitura

    // 2. CONFIGURAÇÃO DE LARGURA DE COLUNAS
    TableColumnModel columnModel = tabela.getColumnModel();
    columnModel.getColumn(0).setPreferredWidth(230); // Título (Espaço maior)
    columnModel.getColumn(1).setPreferredWidth(160); // Autor
    columnModel.getColumn(2).setPreferredWidth(140); // ISBN
    columnModel.getColumn(3).setPreferredWidth(110); // Categoria

    // 3. APRESENTAÇÃO DE VALORES SEM MISTURAR PERSISTÊNCIA
    // Centraliza o texto da coluna Categoria
    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    centerRenderer.setHorizontalAlignment(JLabel.CENTER);
    columnModel.getColumn(3).setCellRenderer(centerRenderer);

    // Aplica o renderizador customizado com máscara visual na coluna ISBN
    columnModel.getColumn(2).setCellRenderer(new IsbnCellRenderer());

    JScrollPane scrollPane = new JScrollPane(tabela);

    JLabel lblTitulo = new JLabel("Acervo de Livros Cadastrados");
    lblTitulo.setFont(new Font("Arial", Font.BOLD, 14));

    JButton btnExcluir = new JButton("Excluir Selecionado");
    btnExcluir.addActionListener(e -> excluirLivroSelecionado());

    JPanel pnlTopo = new JPanel(new BorderLayout());
    pnlTopo.add(lblTitulo, BorderLayout.WEST);
    pnlTopo.add(btnExcluir, BorderLayout.EAST);

    pnl.add(pnlTopo, BorderLayout.NORTH);
    pnl.add(scrollPane, BorderLayout.CENTER);

    return pnl;
}

	private void configurarEventos() {
		formLivroPanel.getBtnLimpar().addActionListener(e -> formLivroPanel.limparCampos());

		formLivroPanel.getBtnSalvar().addActionListener(e -> {
			try {
				controller.cadastrarLivro(
                    formLivroPanel.getTitulo(),
                    formLivroPanel.getAutor(),
                    formLivroPanel.getIsbn(),
                    formLivroPanel.getCategoria()
                );

				JOptionPane.showMessageDialog(BibliotecaFrame.this,
                        "Livro cadastrado com sucesso!",
                        "Sucesso", JOptionPane.INFORMATION_MESSAGE);

				formLivroPanel.limparCampos();
				
				// Atualiza o modelo notificando a mudança
				tableModel.carregarColecao(controller.listarLivros());
				cardLayout.show(pnlCartoes, "CONSULTA");

			} catch (IllegalArgumentException ex) {
				JOptionPane.showMessageDialog(BibliotecaFrame.this,
                        ex.getMessage(),
                        "Aviso de Validação", JOptionPane.WARNING_MESSAGE);
			}
		});
	}

	private void carregarDadosIniciais() {
		tableModel.carregarColecao(controller.listarLivros());
	}

	private void excluirLivroSelecionado() {
		int linhaSelecionada = tabela.getSelectedRow();
		if (linhaSelecionada >= 0) {
			controller.removerLivro(linhaSelecionada);
			tableModel.removerLivro(linhaSelecionada); // Dispara fireTableRowsDeleted
			JOptionPane.showMessageDialog(this, "Livro removido com sucesso!");
		} else {
			JOptionPane.showMessageDialog(this, "Selecione uma linha para excluir.", "Aviso", JOptionPane.WARNING_MESSAGE);
		}
	}
}