package GUI.Form.PanelesGUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import GUI.Styles;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import BusinessLogic.PiezaDeArteBL;
import DataAcces.DTO.PiezaDeArteDTO;
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

        btnBack.addActionListener(e -> showProductTipoPanel());
        btnADD.addActionListener(e -> {
            btnADD.setForeground(Styles.COLOR_FONT);
            btnMOD.setForeground(Styles.COLOR_FONT_BG);
            btnDEL.setForeground(Styles.COLOR_FONT_BG);
            try {
                addProduct();
            } catch (Exception ex) {
                messageLabel.setText("Ups... No se pudo agregar el producto");
                ex.printStackTrace();
            }
        });

        btnDEL.addActionListener(e -> {
            btnDEL.setForeground(Styles.COLOR_FONT);
            btnMOD.setForeground(Styles.COLOR_FONT_BG);
            btnADD.setForeground(Styles.COLOR_FONT_BG);
            try {
                deleteProduct();
            } catch (Exception ex) {
                messageLabel.setText("Ups... No se pudo eliminar el producto");
                ex.printStackTrace();
            }
        });

        btnMOD.addActionListener(e -> {
            btnMOD.setForeground(Styles.COLOR_FONT);
            btnDEL.setForeground(Styles.COLOR_FONT_BG);
            btnADD.setForeground(Styles.COLOR_FONT_BG);
            try {
                editProduct();
            } catch (Exception ex) {
                messageLabel.setText("Ups... No se pudo editar el producto");
                ex.printStackTrace();
            }
        });

        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // La celda no es editable
            }
        };

        tableModel.addColumn("Código de barras");
        tableModel.addColumn("Nombre de la Pintura");
        tableModel.addColumn("Autor ");
        tableModel.addColumn("Descripcion del producto");
        tableModel.addColumn("Precio De su Replica");
        tableModel.addColumn("Sección del producto");
        tableModel.addColumn("Categoría del producto");


        
        productTable = new JTable(tableModel);
        productTable.setPreferredScrollableViewportSize(new Dimension(500, 300));
        productTable.setSelectionForeground(Styles.COLOR_FOREGROUND);
        productTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    int selectedRow = productTable.getSelectedRow();
                    if (selectedRow != -1) {
                        String barcode = (String) tableModel.getValueAt(selectedRow, 0);
                        String nombre = (String) tableModel.getValueAt(selectedRow, 1);
                        String autor = (String) tableModel.getValueAt(selectedRow, 2);
                        String descripcion = (String) tableModel.getValueAt(selectedRow, 3);
                        double precio = (double) tableModel.getValueAt(selectedRow, 4);
                        int seccion = (int) tableModel.getValueAt(selectedRow, 5);
                        int categoria = (int) tableModel.getValueAt(selectedRow, 6);

                        barcodeField.setText(barcode);
                        nombreField.setText(nombre);
                        autorField.setText(autor);
                        descripcionField.setText(descripcion);
                        precioField.setText(String.valueOf(precio));
                        seccionField.setText(String.valueOf(seccion));
                        categoriaField.setText(String.valueOf(categoria));
                    }
                }
            }
        });
        productTable.setFillsViewportHeight(true);

        // Agregar la tabla a un JScrollPane
        JScrollPane tableScrollPane = new JScrollPane(productTable);

        // Agregar el JScrollPane al panel
        add(tableScrollPane, BorderLayout.EAST);

        // Cargar los datos de la base de datos en la tabla
        loadProductsFromDatabase();

        // Cargar los datos de la base de datos en la tabla
        loadProductsFromDatabase();

        JPanel northPanel = new JPanel();
        northPanel = paintPanel(northPanel);

        northPanel.setLayout(new FlowLayout());
        // ...

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());
        centerPanel = paintPanel(centerPanel);
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(5, 5, 5, 5); // add some padding around each component

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;

        JLabel info1 = new JLabel();
        info1.setText("Codigo de Barras:");
        info1.setForeground(Styles.COLOR_FONT);
        info1.setFont(Styles.FONT);
        centerPanel.add(info1, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        centerPanel.add(barcodeField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_END;
        JLabel info2 = new JLabel();
        info2.setText("Nombre del Producto:");
        info2.setForeground(Styles.COLOR_FONT);
        info2.setFont(Styles.FONT);
        centerPanel.add(info2, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        centerPanel.add(nombreField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.LINE_END;
        JLabel info3 = new JLabel();
        info3.setText("Precio del Producto:");
        info3.setForeground(Styles.COLOR_FONT);
        info3.setFont(Styles.FONT);

        centerPanel.add(info3, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        centerPanel.add(precioField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.LINE_END;
        JLabel info4 = new JLabel();
        info4.setText("Seccion");
        info4.setForeground(Styles.COLOR_FONT);
        info4.setFont(Styles.FONT);
        centerPanel.add(info4, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        centerPanel.add(seccionField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.anchor = GridBagConstraints.LINE_END;
        JLabel info5 = new JLabel();
        info5.setText("Categoria del producto:");
        info5.setForeground(Styles.COLOR_FONT);
        info5.setFont(Styles.FONT);
        centerPanel.add(info5, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.anchor = GridBagConstraints.LINE_END;
        JLabel info6 = new JLabel();
        info6.setText("Autor:");
        info6.setForeground(Styles.COLOR_FONT);
        info6.setFont(Styles.FONT);
        centerPanel.add(info6, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.anchor = GridBagConstraints.LINE_END;
        JLabel info7 = new JLabel();
        info7.setText("Descripcion:");
        info7.setForeground(Styles.COLOR_FONT);
        info7.setFont(Styles.FONT);
        centerPanel.add(info7, gbc);

        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        centerPanel.add(categoriaField, gbc);
        centerPanel.setBackground(Styles.COLOR_FONT_LIGHT);
        add(centerPanel, BorderLayout.CENTER);

        // ...
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new FlowLayout());

        northPanel.add(btnADD);
        northPanel.add(btnMOD);
        northPanel.add(btnDEL);
        add(northPanel, BorderLayout.NORTH);

        add(centerPanel, BorderLayout.CENTER);

        southPanel.add(btnBack);
        southPanel.add(messageLabel);
        add(southPanel, BorderLayout.SOUTH);

        // Solicita el foco en el campo de texto cuando el panel se muestra
        SwingUtilities.invokeLater(() -> barcodeField.requestFocusInWindow());
    }

    private void deleteProduct() {
        int selectedRow = productTable.getSelectedRow();
        if (selectedRow != -1) {
            String barcode = (String) tableModel.getValueAt(selectedRow, 0);
            int confirm = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que deseas eliminar este producto?",
                    "Eliminar producto", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    boolean exito = piezaDeArteBL.delete(barcode);
                    if (exito) {
                        messageLabel.setText("Producto eliminado con éxito");
                        tableModel.removeRow(selectedRow);
                    } else {
                        messageLabel.setText("No se pudo eliminar el producto");
                    }
                } catch (Exception e) {
                    messageLabel.setText("Ups... No se pudo eliminar el producto");
                    e.printStackTrace();
                }
            }
        } else {
            messageLabel.setText("Por favor, selecciona un producto para eliminar");
        }
    }

    private void loadProductsFromDatabase() {
        try {
            // Obtener los productos de la base de datos
            List<PiezaDeArteDTO> productos = piezaDeArteBL.getAll();

            // Agregar los productos a la tabla
            for (PiezaDeArteDTO producto : productos) {
                tableModel.addRow(new Object[] {
                        producto.getBarCode(),
                        producto.getNombre(),
                        producto.getAutor(),
                        producto.getDescripcion(),
                        producto.getPrecioReplica(),
                        producto.getIdSeccion(),
                        producto.getIdCategoria()
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void editProduct() {
        int selectedRow = productTable.getSelectedRow();
        if (selectedRow != -1) {
            String barcode = (String) tableModel.getValueAt(selectedRow, 0);
            String nombre = nombreField.getText();
            String autor = autorField.getText();
            String descripcion = descripcionField.getText();
            double precio = Double.parseDouble(precioField.getText());
            int seccion = Integer.parseInt(seccionField.getText());
            int categoria = Integer.parseInt(categoriaField.getText());

            try {
                boolean exito = piezaDeArteBL
                        .update(new PiezaDeArteDTO(barcode, nombre, autor, descripcion, precio, categoria, seccion));
                if (exito) {
                    messageLabel.setText("Producto editado con éxito en la base de datos");
                    tableModel.setValueAt(nombre, selectedRow, 1);
                    tableModel.setValueAt(autor, selectedRow, 2);
                    tableModel.setValueAt(descripcion, selectedRow, 2);
                    tableModel.setValueAt(precio, selectedRow, 2);
                    tableModel.setValueAt(seccion, selectedRow, 3);
                    tableModel.setValueAt(categoria, selectedRow, 4);
                }
            } catch (Exception e) {
                messageLabel.setText("Ups... No se pudo editar el producto");
                e.printStackTrace();
            }
        } else {
            messageLabel.setText("Por favor, selecciona un producto para editar");
        }
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
            boolean exito = piezaDeArteBL
                    .add(new PiezaDeArteDTO(barcode, nombre, autor, descripcion, precio, categoria, seccion));
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
