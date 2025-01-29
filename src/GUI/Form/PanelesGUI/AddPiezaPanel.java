package GUI.Form.PanelesGUI;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import BusinessLogic.PiezaDeArteBL;
import DataAcces.DTO.PiezaDeArteDTO;
import GUI.Styles;
import GUI.CustomerControl.PrjButton;
import GUI.CustomerControl.PrjLabel;

public class AddPiezaPanel extends JPanel {

    private JLabel messageLabel;
    private PiezaTipoPanel addPiezaTipo;
    private PiezaDeArteBL piezaDeArteBL = new PiezaDeArteBL();
    private PrjButton btnBack;

    public PrjTextBox barcodeField;
    public PrjTextBox nombreField;
    public PrjTextBox precioField;
    public PrjTextBox seccionField;
    public PrjTextBox categoriaField;

    public PrjTextBox autorField;
    public PrjTextBox descripcionField;

    private JTable productTable;
    private DefaultTableModel tableModel;

    private PrjButton btnADD;
    private PrjButton btnDEL;
    private PrjButton btnMOD;

    public AddPiezaPanel(PiezaTipoPanel addPiezaTipo) {

        this.addPiezaTipo = addPiezaTipo;
        btnBack = new PrjButton("Volver");
        btnADD = new PrjButton("Nuevo");
        btnMOD = new PrjButton("Editar");
        btnDEL = new PrjButton("Eliminar");
        btnDEL.setForeground(Styles.COLOR_FONT);
        btnADD.setForeground(Styles.COLOR_FONT);
        btnMOD.setForeground(Styles.COLOR_FONT);

        messageLabel = new PrjLabel();

        barcodeField = new PrjTextBox();
        barcodeField.setPreferredSize(new Dimension(200, 30));
        barcodeField.setBackground(Styles.COLOR_FONT_LIGHT);

        nombreField = new PrjTextBox();
        nombreField.setPreferredSize(new Dimension(200, 30));
        nombreField.setBackground(Styles.COLOR_FONT_LIGHT);
        
        autorField = new PrjTextBox();
        autorField.setPreferredSize(new Dimension(200, 30));
        autorField.setBackground(Styles.COLOR_FONT_LIGHT);

        descripcionField = new PrjTextBox();
        descripcionField.setPreferredSize(new Dimension(200, 30));
        descripcionField.setBackground(Styles.COLOR_FONT_LIGHT);

        precioField = new PrjTextBox();
        precioField.setPreferredSize(new Dimension(200, 30));
        precioField.setBackground(Styles.COLOR_FONT_LIGHT);

        seccionField = new PrjTextBox();
        seccionField.setPreferredSize(new Dimension(200, 30));
        seccionField.setBackground(Styles.COLOR_FONT_LIGHT);

        categoriaField = new PrjTextBox();
        categoriaField.setPreferredSize(new Dimension(200, 30));
        categoriaField.setBackground(Styles.COLOR_FONT_LIGHT);



        setLayout(new BorderLayout());
    }


    private void addProduct() throws Exception {
        String nombre = nombreField.getText();
        String barcode = barcodeField.getText();
        String autor = autorField.getText();
        String descripcion = descripcionField.getText();
        double precio = Double.parseDouble(precioField.getText());
        int seccion = Integer.parseInt(seccionField.getText());
        int categoria = Integer.parseInt(categoriaField.getText());

        try {
            boolean exito = piezaDeArteBL.add(new PiezaDeArteDTO(barcode, nombre, autor, descripcion, null, null, null));
            if (exito) {
                messageLabel.setText("Producto agregado con éxito en la base de datos");
            }
        } catch (Exception e) {
            messageLabel.setText("Ups... No se pudo agregar el producto");
            e.printStackTrace();
        }
        tableModel.addRow(new Object[] {
                barcode,
                nombre,
                autor,
                descripcion,
                precio,
                seccion,
                categoria
        });
    }

    private JPanel paintPanel(JPanel panel) {
        panel.setBackground(Styles.COLOR_BACKGROUND);
        return panel;
    }

        private void showProductTipoPanel() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (frame != null) {
            frame.setContentPane(addPiezaTipo);
            frame.revalidate();
            frame.repaint();
        }
    }

}
