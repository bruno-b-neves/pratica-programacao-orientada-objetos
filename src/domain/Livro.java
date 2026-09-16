package domain;


/**
 * CAMADA DE DOMÍNIO (Domain)
 * Fluxo: Contém as regras de negócio e validações dos atributos do livro.
 */
public class Livro {
	
	private String titulo;
	private Autor autor;
	private String isbn;
	private Categoria categoria;
	
	public Livro(String titulo, Autor autor, String isbn, Categoria categoria) {
		if (titulo == null || titulo.trim().isEmpty()) {
			throw new IllegalArgumentException("Campo título é obrigatório.");
		}
		
		if (autor == null) {
			throw new IllegalArgumentException("Campo autor é obrigatório.");
		}
		
		if (categoria == null) {
			throw new IllegalArgumentException("Campo categoria é obrigatório.");
		}
		
		if (!IsbnValidator.isValido(isbn)) {
            throw new IllegalArgumentException("ISBN inválido! Informe um ISBN-10 ou ISBN-13 correto (ex: 978-85-359-0277-7).");
        }
		
		this.titulo = titulo;
		this.autor = autor;
		this.isbn = isbn.replace("-", "").replace(" ", "").trim();
		this.categoria = categoria;
		
	}
	
	public Livro(String titulo, String nomeAutor, String isbn, String nomeCategoria) {
		this(titulo, new Autor(nomeAutor), isbn, new Categoria(nomeCategoria));
	}

	public String getTitulo() {
		return titulo;
	}

	public Autor getAutor() {
		return autor;
	}

	public String getIsbn() {
		return isbn;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	@Override
    public String toString() {
        return String.format("'%s' - %s (ISBN: %s) [%s]", 
            titulo, autor.getNome(), isbn, categoria.getNomeCategoria());
    }
	
	
}
