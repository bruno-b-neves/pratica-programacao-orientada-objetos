package domain;

/**
 * CAMADA DE DOMÍNIO
 * Classe utilitária reutilizável para validação de formatos ISBN-10 e ISBN-13.
 */
public class IsbnValidator {
	
	private IsbnValidator() {}
	
	/**
     * Valida se a string fornecida corresponde a um ISBN-10 ou ISBN-13 válido.
     * 
     * @param isbn O texto contendo o ISBN a ser validado.
     * @return true se o formato for válido, false caso contrário.
     */
	
	public static boolean isValido(String isbn) {
		if (isbn == null || isbn.trim().isEmpty()) return false;
		
		// 1. Limpa o texto removendo hífens e espaços
		String isbnLimpo = removerHifensEEspacos(isbn);
		
		// 2. Verifica a quantidade de caracteres restantes
        int tamanho = isbnLimpo.length();
        
        if (tamanho == 10) {
            return validarIsbn10(isbnLimpo);
        } else if (tamanho == 13) {
            return validarIsbn13(isbnLimpo);
        }
        
        // Se não tiver 10 nem 13 caracteres, o ISBN é inválido
        return false;
		
	}
	
	/**
     * Remove os caracteres '-' e ' ' de uma String.
     */
	private static String removerHifensEEspacos(String texto) {
		StringBuilder sb = new StringBuilder();
	        
	    	for (int i = 0; i < texto.length(); i++) {
	            char c = texto.charAt(i);
	            // Adiciona ao resultado apenas se NÃO for hífen nem espaço
	            if (c != '-' && c != ' ') {
	                sb.append(c);
	            }
	        }
	        
	    	return sb.toString();
	}
	
	/**
     * Valida as regras de um ISBN de 10 caracteres.
     * Os primeiros 9 devem ser dígitos numéricos. O último pode ser dígito ou 'X'/'x'.
     */
    private static boolean validarIsbn10(String texto) {
        // Verifica os primeiros 9 caracteres
        for (int i = 0; i < 9; i++) {
            char c = texto.charAt(i);
            if (!Character.isDigit(c)) {
                return false; // Se algum dos 9 primeiros não for número, é inválido
            }
        }

        // O décimo caractere pode ser número ou a letra 'X' (representa 10 no padrão ISBN)
        char ultimoChar = texto.charAt(9);
        boolean ehDigito = Character.isDigit(ultimoChar);
        boolean ehLetraX = (ultimoChar == 'X' || ultimoChar == 'x');

        return ehDigito || ehLetraX;
    }
    
    /**
     * Valida as regras de um ISBN de 13 caracteres.
     * Todos os 13 caracteres devem ser dígitos numéricos.
     */
    private static boolean validarIsbn13(String texto) {
        for (int i = 0; i < 13; i++) {
            char c = texto.charAt(i);
            if (!Character.isDigit(c)) {
                return false; // Se encontrar qualquer caractere que não seja número
            }
        }
        return true;
    }
}

