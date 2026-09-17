package controller;

import domain.Livro;
import repository.LivroRepository;
import java.util.List;


/**
 * CAMADA DE CONTROLE (Controller)
 * Fluxo: Intermedia a comunicação entre View, Domain e Repository. Instancia a entidade Livro
 * e, caso seja válida, solicita a gravação no LivroRepository.
 */
public class LivroController {

	private final LivroRepository repository;
	
	public LivroController(LivroRepository repository) {
		this.repository = repository;
	} 
	
	public void cadastrarLivro(String titulo, String autor, String isbn, String categoria) {
		Livro novoLivro = new Livro(titulo, autor, isbn, categoria);
		repository.salvar(novoLivro);
	}

	public List<Livro> listarLivros() {
		return repository.listarTodos();
	}
}
