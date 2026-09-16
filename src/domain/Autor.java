package domain;

public class Autor {
	private String nome;
	
	public Autor(String nome) {
		if (nome == null || nome.trim().isEmpty()) {
			throw new IllegalArgumentException("O campo nome é obrigatório.");
		}
		
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return nome;
	}
}
