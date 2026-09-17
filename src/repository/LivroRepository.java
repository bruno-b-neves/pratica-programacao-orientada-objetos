package repository;

import domain.Livro;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * CAMADA DE REPOSITÓRIO (Repository)
 * Simula a persistência em memória com carga inicial de dados (Seed Data).
 */
public class LivroRepository {

	private final List<Livro> livros = new ArrayList<>();
	
	public LivroRepository() {
		carregarDadosIniciais();
	};

	private void carregarDadosIniciais() {
		livros.add(new Livro("O Senhor dos Anéis", "J.R.R. Tolkien", "978-0261102385", "Fantasia"));
		livros.add(new Livro("1984", "George Orwell", "978-0451524935", "Distopia"));
		livros.add(new Livro("O Pequeno Príncipe", "Antoine de Saint-Exupéry", "978-0156012195", "Infantil"));
		livros.add(new Livro("A Revolução dos Bichos", "George Orwell", "978-0451526342", "Fábula"));
		livros.add(new Livro("O Código Da Vinci", "Dan Brown", "978-0307474278", "Suspense"));
		livros.add(new Livro("Harry Potter e a Pedra Filosofal", "J.K. Rowling", "978-0590353427", "Fantasia"));
		livros.add(new Livro("O Hobbit", "J.R.R. Tolkien", "978-0547928227", "Fantasia"));
		livros.add(new Livro("A Menina que Roubava Livros", "Markus Zusak", "978-0375842207", "Drama"));
		livros.add(new Livro("O Alquimista", "Paulo Coelho", "978-0061122415", "Ficção"));
		livros.add(new Livro("O Diário de Anne Frank", "Anne Frank", "978-0553296983", "Biografia"));
	}

	public void salvar(Livro livro) {
		livros.add(livro);
	}

	public void remover(int index) {
		if (index >= 0 && index < livros.size()) {
			livros.remove(index);
		}
	}
	
	public List<Livro> listarTodos() {
		return Collections.unmodifiableList(livros);
	}
}
