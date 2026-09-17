package domain;

public class Categoria {
	private String nomeCategoria;
	
	public Categoria(String categoria) {
		if (categoria == null || categoria.trim().isEmpty()) {
			throw new IllegalArgumentException("O campo categoria é obrigatório.");
		}
		
		this.nomeCategoria = categoria;
	}

	public String getNomeCategoria() {
		return nomeCategoria;
	}

	@Override
	public String toString() {
		return nomeCategoria;
	}
	
	
}
