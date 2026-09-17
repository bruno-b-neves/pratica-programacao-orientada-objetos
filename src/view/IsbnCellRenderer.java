package view;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.JTable;

/**
 * CAMADA DE APRESENTAÇÃO (View)
 * Customiza o alinhamento e a máscara visual do ISBN sem alterar a string pura gravada no repositório.
 */
public class IsbnCellRenderer extends DefaultTableCellRenderer {
    private static final long serialVersionUID = 1L;

    public IsbnCellRenderer() {
        setHorizontalAlignment(JLabel.CENTER); // Centraliza o texto na célula
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, 
            boolean isSelected, boolean hasFocus, int row, int column) {
        
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        // Aplica a máscara de apresentação visual caso o valor do banco seja um ISBN de 13 dígitos
        if (value != null) {
            String isbnPuro = value.toString();
            if (isbnPuro.length() == 13) {
                String isbnFormatado = String.format("%s-%s-%s-%s-%s",
                    isbnPuro.substring(0, 3),
                    isbnPuro.substring(3, 5),
                    isbnPuro.substring(5, 8),
                    isbnPuro.substring(8, 12),
                    isbnPuro.substring(12)
                );
                setText(isbnFormatado);
            }
        }
        return c;
    }
}