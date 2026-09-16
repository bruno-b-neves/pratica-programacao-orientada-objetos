package repository;

import domain.Livro;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LivroRepository {

	private final List<Livro> livros = new ArrayList<>();
	
	public void salvar(Livro livro) {
		livros.add(livro);
	}
	
	public List<Livro> listarTodos() {
		return Collections.unmodifiableList(livros);
	}
}
