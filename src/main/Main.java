package main;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import controller.LivroController;
import repository.LivroRepository;
import view.BibliotecaFrame;


/**
 * PONTO DE ENTRADA (Main)
 * Fluxo: Instancia o LivroRepository, vincula-o ao LivroController 
 * e injeta o controlador na janela principal BibliotecaFrame.
 * Configura o Look and Feel nativo e lança a aplicação na Event Dispatch Thread.
 */
public class Main {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {}
		
		SwingUtilities.invokeLater(() -> {
			LivroRepository repository = new LivroRepository();
			LivroController controller = new LivroController(repository);
			
			BibliotecaFrame frame = new BibliotecaFrame(controller);
			frame.setVisible(true);
		});
	}
}
