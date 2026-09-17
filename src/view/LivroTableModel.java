package view;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.ArrayList;

import domain.Livro;

public class LivroTableModel extends AbstractTableModel {
    private static final long serialVersionUID = 1L;

    private final String[] columns = {"Título", "Autor", "ISBN", "Categoria"};
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

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

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
    
}
