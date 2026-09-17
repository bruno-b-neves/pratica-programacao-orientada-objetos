package view;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.ArrayList;

import domain.Livro;

public class LivroTableModel extends AbstractTableModel {
    private static final long serialVersionUID = 1L;

    private final String[] columns = {"Título", "Autor", "ISBN", "Categoria"};
    private final Class<?>[] tiposColunas = {String.class, String.class, String.class, String.class};
    private List<Livro> livros;

    public LivroTableModel() {
        this.livros = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return livros.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    /**
     * Define explicitamente o nome de cada coluna no cabeçalho da tabela.
     */
    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    /**
     * Define explicitamente a classe (tipo de dado) de cada coluna.
     * Permite à JTable aplicar renderizadores apropriados.
     */
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return tiposColunas[columnIndex];
    }

    /**
     * Define explicitamente a permissão de edição das células.
     * Retornar 'false' para todas as colunas garante que a tabela seja estritamente de CONSULTA.
     */
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true; // Somente leitura (não permite edição direta clicando na célula)
    }

    /**
     * Mapeia os dados do objeto Livro para cada coluna correspondente.
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Livro livro = livros.get(rowIndex);
        switch (columnIndex) {
            case 0: return livro.getTitulo();
            case 1: return livro.getAutor();
            case 2: return livro.getIsbn();
            case 3: return livro.getCategoria();
            default: return null;
        }
    }

public void carregarColecao(List<Livro> novaLista) {
        this.livros = new ArrayList<>(novaLista);
        fireTableDataChanged(); // Notifica atualização de toda a coleção
    }

    // Método para adicionar um livro à tabela e notificar a mudança
    public void adicionarLivro(Livro livro) {
        this.livros.add(livro);
        int ultimaLinha = livros.size() - 1;
        fireTableRowsInserted(ultimaLinha, ultimaLinha);
    }

    public void setLivros(List<Livro> novsLivros) {
        this.livros = new ArrayList<>(novsLivros);
        fireTableDataChanged();
    }

    public void removerLivro(int index) {
        if (index >= 0 && index < livros.size()) {
            this.livros.remove(index);
            fireTableRowsDeleted(index, index);
        }
    }
    
}
