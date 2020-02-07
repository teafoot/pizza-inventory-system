/*
 * Copyright (C) 2017 davidchenlo
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package pizzeriatimbo_mvc.vista;

import javax.swing.*;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 *
 * @author davidchenlo
 */
public class IngredienteProductoFormVista extends JInternalFrame {

    private JTable tableLeer;
    private DefaultTableModel tableModelLeer;
    private TableRowSorter tableRowSorterLeer;
    private JScrollPane scrollPaneTableLeer;
    private TableCellEditor tableCellEditorCantidadIngredienteProducto;
    private DefaultCellEditor defaultCellEditorIngrediente;
    private DefaultCellEditor defaultCellEditorProducto;
    private DefaultCellEditor defaultCellEditorUnidad;
    private DefaultCellEditor defaultCellEditorTamaño;
    private TableCellRenderer tableCellRendererCantidadIngredienteProducto;
    private TableCellRenderer tableCellRendererIngrediente;
    private TableCellRenderer tableCellRendererProducto;
    private TableCellRenderer tableCellRendererUnidad;
    private TableCellRenderer tableCellRendererTamaño;
    private JLabel lblIngredienteIngresar;
    private JLabel lblProductoIngresar;
    private JLabel lblUnidadIngresar;
    private JLabel lblTamañoIngresar;
    private JLabel lblCantidadIngredienteProductoIngresar;
    private JLabel lblBusquedaLeer;
    private JTextField txtFilterLeer;
    private JComboBox cbxIngredienteIngresar;
    private JComboBox cbxProductoIngresar;
    private JComboBox cbxUnidadIngresar;
    private JComboBox cbxTamañoIngresar;
    private JComboBox cbxIngredienteLeer;
    private JComboBox cbxProductoLeer;
    private JComboBox cbxUnidadLeer;
    private JComboBox cbxTamañoLeer;
    private JSpinner spinnerCantidadIngredienteProductoIngresar;
    private JSpinner spinnerCantidadIngredienteProductoLeer;
    private JButton btnIngresar;
    private JButton btnLimpiarIngresar;
    private JButton btnRefreshIngresar;
    private JButton btnRefreshLeer;
    private JButton btnModificarLeer;
    private JButton btnEliminarLeer;
    private JTabbedPane tabbedPane;
    private JPanel panelIngresar;
    private JPanel panelLeer;

    public IngredienteProductoFormVista() {
        super("Ingrediente Producto");
        initJInternalFrame();
        initJTabbedPane();
        initPanelIngresar();
        initPanelLeer();
    }

    private void initJInternalFrame() {
        this.setResizable(true);
        this.setClosable(true);
        this.setMaximizable(true);
        this.setIconifiable(true);
        this.setFrameIcon(new ImageIcon(this.getClass().getResource("/resources/pizza_icon.png")));
    }

    private void initJTabbedPane() {
        this.setTabbedPane(new JTabbedPane(JTabbedPane.TOP));
        this.getContentPane().add(this.getTabbedPane());
    }

    private void initPanelIngresar() {
        JPanel innerPanel1 = new JPanel();
        JPanel innerPanel2 = new JPanel();
        this.setPanelIngresar(new JPanel());
        this.getPanelIngresar().setLayout(new BorderLayout());
        this.setLblIngredienteIngresar(new JLabel());
        this.setLblProductoIngresar(new JLabel());
        this.setLblUnidadIngresar(new JLabel());
        this.setLblTamañoIngresar(new JLabel());
        this.setLblCantidadIngredienteProductoIngresar(new JLabel());

        this.setCbxIngredienteIngresar(new JComboBox());
        this.setCbxProductoIngresar(new JComboBox());
        this.setCbxUnidadIngresar(new JComboBox());
        this.setCbxTamañoIngresar(new JComboBox());
        this.setSpinnerCantidadIngredienteProductoIngresar(new JSpinner(new SpinnerNumberModel(0, 0, 2147483647, 1)));
        this.setBtnRefreshIngresar(new JButton());
        this.setBtnIngresar(new JButton());
        this.setBtnLimpiarIngresar(new JButton());
        this.getLblIngredienteIngresar().setText("Ingrediente: ");
        this.getLblProductoIngresar().setText("Producto: ");
        this.getLblUnidadIngresar().setText("Unidad: ");
        this.getLblTamañoIngresar().setText("Tamaño: ");
        this.getLblCantidadIngredienteProductoIngresar().setText("Cantidad Ingrediente Producto: ");
        this.getBtnRefreshIngresar().setText("Actualizar");
        this.getBtnIngresar().setText("Ingresar");
        this.getBtnLimpiarIngresar().setText("Limpiar");
        innerPanel1.setLayout(new GridLayout(1, 3, 10, 0));
        innerPanel1.add(this.getBtnRefreshIngresar());
        innerPanel1.add(this.getBtnIngresar());
        innerPanel1.add(this.getBtnLimpiarIngresar());
        innerPanel2.setLayout(new GridLayout(5, 2, 10, 0));
        innerPanel2.add(this.getLblCantidadIngredienteProductoIngresar());
        innerPanel2.add(this.getSpinnerCantidadIngredienteProductoIngresar());
        innerPanel2.add(this.getLblUnidadIngresar());
        innerPanel2.add(this.getCbxUnidadIngresar());
        innerPanel2.add(this.getLblIngredienteIngresar());
        innerPanel2.add(this.getCbxIngredienteIngresar());
        innerPanel2.add(this.getLblProductoIngresar());
        innerPanel2.add(this.getCbxProductoIngresar());
        innerPanel2.add(this.getLblTamañoIngresar());
        innerPanel2.add(this.getCbxTamañoIngresar());
        this.getPanelIngresar().add(innerPanel2, BorderLayout.CENTER);
        this.getPanelIngresar().add(innerPanel1, BorderLayout.SOUTH);
        this.getTabbedPane().addTab("Ingresar", this.getPanelIngresar());
    }

    private void initPanelLeer() {
        JPanel innerTopPanel = new JPanel();
        JPanel innerBottomPanel = new JPanel();
        this.setPanelLeer(new JPanel());
        this.getPanelLeer().setLayout(new BorderLayout());
        this.setSpinnerCantidadIngredienteProductoLeer(new JSpinner(new SpinnerNumberModel(0, 0, 2147483647, 1)));
        this.setCbxIngredienteLeer(new JComboBox());
        this.setCbxProductoLeer(new JComboBox());
        this.setCbxUnidadLeer(new JComboBox());
        this.setCbxTamañoLeer(new JComboBox());
        this.setBtnRefreshLeer(new JButton());
        this.setBtnModificarLeer(new JButton());
        this.setBtnEliminarLeer(new JButton());
        this.setTxtFilterLeer(new JTextField());
        this.setLblBusquedaLeer(new JLabel());
        this.setTableModelLeer(new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return !((column == 0) || (column == 6));
            }

            // FIX: Sorting any class type
            @Override
            public Class getColumnClass(int column) {
                Class value = Object.class;
                if (getRowCount() > 0) {
                    value = getValueAt(0, column).getClass();
                }
                return value;
            }
        });
        this.setTableLeer(new JTable(this.getTableModelLeer()) {
            @Override
            public TableCellRenderer getCellRenderer(int row, int column) {
                int modelColumn = convertColumnIndexToModel(column);
                switch (modelColumn) {
                    case 1:
                        return getTableCellRendererCantidadIngredienteProducto();
                    case 2:
                        return getTableCellRendererUnidad();
                    case 3:
                        return getTableCellRendererIngrediente();
                    case 4:
                        return getTableCellRendererProducto();
                    case 5:
                        return getTableCellRendererTamaño();
                    default:
                        return super.getCellRenderer(row, column);
                }
            }

            @Override
            public TableCellEditor getCellEditor(int row, int column) {
                int modelColumn = convertColumnIndexToModel(column);
                switch (modelColumn) {

                    case 1:
                        return getTableCellEditorCantidadIngredienteProducto();
                    case 2:
                        return getDefaultCellEditorUnidad();
                    case 3:
                        return getDefaultCellEditorIngrediente();
                    case 4:
                        return getDefaultCellEditorProducto();
                    case 5:
                        return getDefaultCellEditorTamaño();
                    default:
                        return super.getCellEditor(row, column);
                }
            }
        });
        this.setScrollPaneTableLeer(new JScrollPane(this.getTableLeer()));
        this.getTableLeer().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        this.setTableRowSorterLeer(new TableRowSorter(this.getTableModelLeer()));
        this.getTableLeer().setRowSorter(this.getTableRowSorterLeer());
        this.getBtnRefreshLeer().setText("Refrescar");
        this.getBtnModificarLeer().setText("Modificar");
        this.getBtnEliminarLeer().setText("Eliminar");
        this.getLblBusquedaLeer().setText("Buscar:");
        innerTopPanel.setLayout(new GridLayout(2, 1));
        innerTopPanel.add(this.getLblBusquedaLeer());
        innerTopPanel.add(this.getTxtFilterLeer());
        innerBottomPanel.setLayout(new GridLayout(3, 1));
        innerBottomPanel.add(this.getBtnRefreshLeer());
        innerBottomPanel.add(this.getBtnModificarLeer());
        innerBottomPanel.add(this.getBtnEliminarLeer());
        this.getPanelLeer().add(innerTopPanel, BorderLayout.NORTH);
        this.getPanelLeer().add(innerBottomPanel, BorderLayout.SOUTH);
        this.getPanelLeer().add(this.getScrollPaneTableLeer(), BorderLayout.CENTER);
        this.getTabbedPane().addTab("Leer", this.getPanelLeer());
    }

    public void spinnerCantidadIngredienteProductoLeerSetTableCellEditor(TableCellEditor tableCellEditor) {
        this.setTableCellEditorCantidadIngredienteProducto(tableCellEditor);
    }

    public void spinnerCantidadIngredienteProductoLeerSetTableCellRenderer(TableCellRenderer tableCellRenderer) {
        this.setTableCellRendererCantidadIngredienteProducto(tableCellRenderer);
    }

    public void cbxUnidadLeerSetDefaultCellEditor(JComboBox cbx) {
        this.setDefaultCellEditorUnidad(new DefaultCellEditor(cbx) {
            @Override
            public Component getTableCellEditorComponent(JTable jtable, Object value, boolean bln, int row, int column) {
                super.getTableCellEditorComponent(jtable, value, isSelected, row, column);
                JComboBox comboBox = cbx;
                comboBox.setSelectedItem(null);
                return comboBox;
            }
        });
    }

    public void cbxUnidadLeerSetTableCellRenderer(TableCellRenderer tableCellRenderer) {
        this.setTableCellRendererUnidad(tableCellRenderer);
    }

    public void cbxIngredienteLeerSetDefaultCellEditor(JComboBox cbx) {
        this.setDefaultCellEditorIngrediente(new DefaultCellEditor(cbx) {
            @Override
            public Component getTableCellEditorComponent(JTable jtable, Object value, boolean bln, int row, int column) {
                super.getTableCellEditorComponent(jtable, value, isSelected, row, column);
                JComboBox comboBox = cbx;
                comboBox.setSelectedItem(null);
                return comboBox;
            }
        });
    }

    public void cbxIngredienteLeerSetTableCellRenderer(TableCellRenderer tableCellRenderer) {
        this.setTableCellRendererIngrediente(tableCellRenderer);
    }

    public void cbxTamañoLeerSetDefaultCellEditor(JComboBox cbx) {
        this.setDefaultCellEditorTamaño(new DefaultCellEditor(cbx) {
            @Override
            public Component getTableCellEditorComponent(JTable jtable, Object value, boolean bln, int row, int column) {
                super.getTableCellEditorComponent(jtable, value, isSelected, row, column);
                JComboBox comboBox = cbx;
                comboBox.setSelectedItem(null);
                return comboBox;
            }
        });
    }

    public void cbxTamañoLeerSetTableCellRenderer(TableCellRenderer tableCellRenderer) {
        this.setTableCellRendererTamaño(tableCellRenderer);
    }

    public void cbxProductoLeerSetDefaultCellEditor(JComboBox cbx) {
        this.setDefaultCellEditorProducto(new DefaultCellEditor(cbx) {
            @Override
            public Component getTableCellEditorComponent(JTable jtable, Object value, boolean bln, int row, int column) {
                super.getTableCellEditorComponent(jtable, value, isSelected, row, column);
                JComboBox comboBox = cbx;
                comboBox.setSelectedItem(null);
                return comboBox;
            }
        });
    }

    public void cbxProductoLeerSetTableCellRenderer(TableCellRenderer tableCellRenderer) {
        this.setTableCellRendererProducto(tableCellRenderer);
    }

    public void btnRefreshIngresarSetActionListener(ActionListener actionListener) {
        this.getBtnRefreshIngresar().addActionListener(actionListener);
    }

    public void btnIngresarSetActionListener(ActionListener actionListener) {
        this.getBtnIngresar().addActionListener(actionListener);
    }

    public void btnLimpiarIngresarSetActionListener(ActionListener actionListener) {
        this.getBtnLimpiarIngresar().addActionListener(actionListener);
    }

    public void txtFilterLeerSetDocumentListener(DocumentListener documentListener) {
        this.getTxtFilterLeer().getDocument().addDocumentListener(documentListener);
    }

    public void btnRefreshLeerSetActionListener(ActionListener actionListener) {
        this.getBtnRefreshLeer().addActionListener(actionListener);
    }

    public void btnModificarLeerSetActionListener(ActionListener actionListener) {
        this.getBtnModificarLeer().addActionListener(actionListener);
    }

    public void btnEliminarLeerSetActionListener(ActionListener actionListener) {
        this.getBtnEliminarLeer().addActionListener(actionListener);
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }

    public int confirmarMensaje(String mensaje) {
        return JOptionPane.showConfirmDialog(null, mensaje);
    }

    /**
     * @return the tableLeer
     */
    public JTable getTableLeer() {
        return tableLeer;
    }

    /**
     * @param tableLeer the tableLeer to set
     */
    public void setTableLeer(JTable tableLeer) {
        this.tableLeer = tableLeer;
    }

    /**
     * @return the tableModelLeer
     */
    public DefaultTableModel getTableModelLeer() {
        return tableModelLeer;
    }

    /**
     * @param tableModelLeer the tableModelLeer to set
     */
    public void setTableModelLeer(DefaultTableModel tableModelLeer) {
        this.tableModelLeer = tableModelLeer;
    }

    /**
     * @return the tableRowSorterLeer
     */
    public TableRowSorter getTableRowSorterLeer() {
        return tableRowSorterLeer;
    }

    /**
     * @param tableRowSorterLeer the tableRowSorterLeer to set
     */
    public void setTableRowSorterLeer(TableRowSorter tableRowSorterLeer) {
        this.tableRowSorterLeer = tableRowSorterLeer;
    }

    /**
     * @return the scrollPaneTableLeer
     */
    public JScrollPane getScrollPaneTableLeer() {
        return scrollPaneTableLeer;
    }

    /**
     * @param scrollPaneTableLeer the scrollPaneTableLeer to set
     */
    public void setScrollPaneTableLeer(JScrollPane scrollPaneTableLeer) {
        this.scrollPaneTableLeer = scrollPaneTableLeer;
    }

    /**
     * @return the defaultCellEditorIngrediente
     */
    public DefaultCellEditor getDefaultCellEditorIngrediente() {
        return defaultCellEditorIngrediente;
    }

    /**
     * @param defaultCellEditorIngrediente the defaultCellEditorIngrediente to
     * set
     */
    public void setDefaultCellEditorIngrediente(DefaultCellEditor defaultCellEditorIngrediente) {
        this.defaultCellEditorIngrediente = defaultCellEditorIngrediente;
    }

    /**
     * @return the defaultCellEditorProducto
     */
    public DefaultCellEditor getDefaultCellEditorProducto() {
        return defaultCellEditorProducto;
    }

    /**
     * @param defaultCellEditorProducto the defaultCellEditorProducto to set
     */
    public void setDefaultCellEditorProducto(DefaultCellEditor defaultCellEditorProducto) {
        this.defaultCellEditorProducto = defaultCellEditorProducto;
    }

    /**
     * @return the lblIngredienteIngresar
     */
    public JLabel getLblIngredienteIngresar() {
        return lblIngredienteIngresar;
    }

    /**
     * @param lblIngredienteIngresar the lblIngredienteIngresar to set
     */
    public void setLblIngredienteIngresar(JLabel lblIngredienteIngresar) {
        this.lblIngredienteIngresar = lblIngredienteIngresar;
    }

    /**
     * @return the lblProductoIngresar
     */
    public JLabel getLblProductoIngresar() {
        return lblProductoIngresar;
    }

    /**
     * @param lblProductoIngresar the lblProductoIngresar to set
     */
    public void setLblProductoIngresar(JLabel lblProductoIngresar) {
        this.lblProductoIngresar = lblProductoIngresar;
    }

    /**
     * @return the lblTamañoIngresar
     */
    public JLabel getLblTamañoIngresar() {
        return lblTamañoIngresar;
    }

    /**
     * @param lblTamañoIngresar the lblTamañoIngresar to set
     */
    public void setLblTamañoIngresar(JLabel lblTamañoIngresar) {
        this.lblTamañoIngresar = lblTamañoIngresar;
    }

    /**
     * @return the lblBusquedaLeer
     */
    public JLabel getLblBusquedaLeer() {
        return lblBusquedaLeer;
    }

    /**
     * @param lblBusquedaLeer the lblBusquedaLeer to set
     */
    public void setLblBusquedaLeer(JLabel lblBusquedaLeer) {
        this.lblBusquedaLeer = lblBusquedaLeer;
    }

    /**
     * @return the txtFilterLeer
     */
    public JTextField getTxtFilterLeer() {
        return txtFilterLeer;
    }

    /**
     * @param txtFilterLeer the txtFilterLeer to set
     */
    public void setTxtFilterLeer(JTextField txtFilterLeer) {
        this.txtFilterLeer = txtFilterLeer;
    }

    /**
     * @return the cbxTamañoIngresar
     */
    public JComboBox getCbxTamañoIngresar() {
        return cbxTamañoIngresar;
    }

    /**
     * @param cbxTamañoIngresar the cbxTamañoIngresar to set
     */
    public void setCbxTamañoIngresar(JComboBox cbxTamañoIngresar) {
        this.cbxTamañoIngresar = cbxTamañoIngresar;
    }

    /**
     * @return the cbxProductoIngresar
     */
    public JComboBox getCbxProductoIngresar() {
        return cbxProductoIngresar;
    }

    /**
     * @param cbxProductoIngresar the cbxProductoIngresar to set
     */
    public void setCbxProductoIngresar(JComboBox cbxProductoIngresar) {
        this.cbxProductoIngresar = cbxProductoIngresar;
    }

    /**
     * @return the cbxTamañoLeer
     */
    public JComboBox getCbxTamañoLeer() {
        return cbxTamañoLeer;
    }

    /**
     * @param cbxTamañoLeer the cbxTamañoLeer to set
     */
    public void setCbxTamañoLeer(JComboBox cbxTamañoLeer) {
        this.cbxTamañoLeer = cbxTamañoLeer;
    }

    /**
     * @return the cbxProductoLeer
     */
    public JComboBox getCbxProductoLeer() {
        return cbxProductoLeer;
    }

    /**
     * @param cbxProductoLeer the cbxProductoLeer to set
     */
    public void setCbxProductoLeer(JComboBox cbxProductoLeer) {
        this.cbxProductoLeer = cbxProductoLeer;
    }

    /**
     * @return the spinnerCantidadIngredienteProductoIngresar
     */
    public JSpinner getSpinnerCantidadIngredienteProductoIngresar() {
        return spinnerCantidadIngredienteProductoIngresar;
    }

    /**
     * @param spinnerCantidadIngredienteProductoIngresar the
     * spinnerCantidadIngredienteProductoIngresar to set
     */
    public void setSpinnerCantidadIngredienteProductoIngresar(JSpinner spinnerCantidadIngredienteProductoIngresar) {
        this.spinnerCantidadIngredienteProductoIngresar = spinnerCantidadIngredienteProductoIngresar;
    }

    /**
     * @return the btnIngresar
     */
    public JButton getBtnIngresar() {
        return btnIngresar;
    }

    /**
     * @param btnIngresar the btnIngresar to set
     */
    public void setBtnIngresar(JButton btnIngresar) {
        this.btnIngresar = btnIngresar;
    }

    /**
     * @return the btnLimpiarIngresar
     */
    public JButton getBtnLimpiarIngresar() {
        return btnLimpiarIngresar;
    }

    /**
     * @param btnLimpiarIngresar the btnLimpiarIngresar to set
     */
    public void setBtnLimpiarIngresar(JButton btnLimpiarIngresar) {
        this.btnLimpiarIngresar = btnLimpiarIngresar;
    }

    /**
     * @return the btnRefreshIngresar
     */
    public JButton getBtnRefreshIngresar() {
        return btnRefreshIngresar;
    }

    /**
     * @param btnRefreshIngresar the btnRefreshIngresar to set
     */
    public void setBtnRefreshIngresar(JButton btnRefreshIngresar) {
        this.btnRefreshIngresar = btnRefreshIngresar;
    }

    /**
     * @return the btnRefreshLeer
     */
    public JButton getBtnRefreshLeer() {
        return btnRefreshLeer;
    }

    /**
     * @param btnRefreshLeer the btnRefreshLeer to set
     */
    public void setBtnRefreshLeer(JButton btnRefreshLeer) {
        this.btnRefreshLeer = btnRefreshLeer;
    }

    /**
     * @return the btnModificarLeer
     */
    public JButton getBtnModificarLeer() {
        return btnModificarLeer;
    }

    /**
     * @param btnModificarLeer the btnModificarLeer to set
     */
    public void setBtnModificarLeer(JButton btnModificarLeer) {
        this.btnModificarLeer = btnModificarLeer;
    }

    /**
     * @return the btnEliminarLeer
     */
    public JButton getBtnEliminarLeer() {
        return btnEliminarLeer;
    }

    /**
     * @param btnEliminarLeer the btnEliminarLeer to set
     */
    public void setBtnEliminarLeer(JButton btnEliminarLeer) {
        this.btnEliminarLeer = btnEliminarLeer;
    }

    /**
     * @return the tabbedPane
     */
    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

    /**
     * @param tabbedPane the tabbedPane to set
     */
    public void setTabbedPane(JTabbedPane tabbedPane) {
        this.tabbedPane = tabbedPane;
    }

    /**
     * @return the panelIngresar
     */
    public JPanel getPanelIngresar() {
        return panelIngresar;
    }

    /**
     * @param panelIngresar the panelIngresar to set
     */
    public void setPanelIngresar(JPanel panelIngresar) {
        this.panelIngresar = panelIngresar;
    }

    /**
     * @return the panelLeer
     */
    public JPanel getPanelLeer() {
        return panelLeer;
    }

    /**
     * @param panelLeer the panelLeer to set
     */
    public void setPanelLeer(JPanel panelLeer) {
        this.panelLeer = panelLeer;
    }

    /**
     * @return the defaultCellEditorUnidad
     */
    public DefaultCellEditor getDefaultCellEditorUnidad() {
        return defaultCellEditorUnidad;
    }

    /**
     * @param defaultCellEditorUnidad the defaultCellEditorUnidad to set
     */
    public void setDefaultCellEditorUnidad(DefaultCellEditor defaultCellEditorUnidad) {
        this.defaultCellEditorUnidad = defaultCellEditorUnidad;
    }

    /**
     * @return the defaultCellEditorTamaño
     */
    public DefaultCellEditor getDefaultCellEditorTamaño() {
        return defaultCellEditorTamaño;
    }

    /**
     * @param defaultCellEditorTamaño the defaultCellEditorTamaño to set
     */
    public void setDefaultCellEditorTamaño(DefaultCellEditor defaultCellEditorTamaño) {
        this.defaultCellEditorTamaño = defaultCellEditorTamaño;
    }

    /**
     * @return the lblUnidadIngresar
     */
    public JLabel getLblUnidadIngresar() {
        return lblUnidadIngresar;
    }

    /**
     * @param lblUnidadIngresar the lblUnidadIngresar to set
     */
    public void setLblUnidadIngresar(JLabel lblUnidadIngresar) {
        this.lblUnidadIngresar = lblUnidadIngresar;
    }

    /**
     * @return the lblCantidadIngredienteProductoIngresar
     */
    public JLabel getLblCantidadIngredienteProductoIngresar() {
        return lblCantidadIngredienteProductoIngresar;
    }

    /**
     * @param lblCantidadIngredienteProductoIngresar the
     * lblCantidadIngredienteProductoIngresar to set
     */
    public void setLblCantidadIngredienteProductoIngresar(JLabel lblCantidadIngredienteProductoIngresar) {
        this.lblCantidadIngredienteProductoIngresar = lblCantidadIngredienteProductoIngresar;
    }

    /**
     * @return the cbxIngredienteIngresar
     */
    public JComboBox getCbxIngredienteIngresar() {
        return cbxIngredienteIngresar;
    }

    /**
     * @param cbxIngredienteIngresar the cbxIngredienteIngresar to set
     */
    public void setCbxIngredienteIngresar(JComboBox cbxIngredienteIngresar) {
        this.cbxIngredienteIngresar = cbxIngredienteIngresar;
    }

    /**
     * @return the cbxUnidadIngresar
     */
    public JComboBox getCbxUnidadIngresar() {
        return cbxUnidadIngresar;
    }

    /**
     * @param cbxUnidadIngresar the cbxUnidadIngresar to set
     */
    public void setCbxUnidadIngresar(JComboBox cbxUnidadIngresar) {
        this.cbxUnidadIngresar = cbxUnidadIngresar;
    }

    /**
     * @return the cbxIngredienteLeer
     */
    public JComboBox getCbxIngredienteLeer() {
        return cbxIngredienteLeer;
    }

    /**
     * @param cbxIngredienteLeer the cbxIngredienteLeer to set
     */
    public void setCbxIngredienteLeer(JComboBox cbxIngredienteLeer) {
        this.cbxIngredienteLeer = cbxIngredienteLeer;
    }

    /**
     * @return the cbxUnidadLeer
     */
    public JComboBox getCbxUnidadLeer() {
        return cbxUnidadLeer;
    }

    /**
     * @param cbxUnidadLeer the cbxUnidadLeer to set
     */
    public void setCbxUnidadLeer(JComboBox cbxUnidadLeer) {
        this.cbxUnidadLeer = cbxUnidadLeer;
    }

    /**
     * @return the tableCellRendererIngrediente
     */
    public TableCellRenderer getTableCellRendererIngrediente() {
        return tableCellRendererIngrediente;
    }

    /**
     * @param tableCellRendererIngrediente the tableCellRendererIngrediente to
     * set
     */
    public void setTableCellRendererIngrediente(TableCellRenderer tableCellRendererIngrediente) {
        this.tableCellRendererIngrediente = tableCellRendererIngrediente;
    }

    /**
     * @return the tableCellRendererProducto
     */
    public TableCellRenderer getTableCellRendererProducto() {
        return tableCellRendererProducto;
    }

    /**
     * @param tableCellRendererProducto the tableCellRendererProducto to set
     */
    public void setTableCellRendererProducto(TableCellRenderer tableCellRendererProducto) {
        this.tableCellRendererProducto = tableCellRendererProducto;
    }

    /**
     * @return the tableCellRendererUnidad
     */
    public TableCellRenderer getTableCellRendererUnidad() {
        return tableCellRendererUnidad;
    }

    /**
     * @param tableCellRendererUnidad the tableCellRendererUnidad to set
     */
    public void setTableCellRendererUnidad(TableCellRenderer tableCellRendererUnidad) {
        this.tableCellRendererUnidad = tableCellRendererUnidad;
    }

    /**
     * @return the tableCellRendererTamaño
     */
    public TableCellRenderer getTableCellRendererTamaño() {
        return tableCellRendererTamaño;
    }

    /**
     * @param tableCellRendererTamaño the tableCellRendererTamaño to set
     */
    public void setTableCellRendererTamaño(TableCellRenderer tableCellRendererTamaño) {
        this.tableCellRendererTamaño = tableCellRendererTamaño;
    }

    /**
     * @return the spinnerCantidadIngredienteProductoLeer
     */
    public JSpinner getSpinnerCantidadIngredienteProductoLeer() {
        return spinnerCantidadIngredienteProductoLeer;
    }

    /**
     * @param spinnerCantidadIngredienteProductoLeer the
     * spinnerCantidadIngredienteProductoLeer to set
     */
    public void setSpinnerCantidadIngredienteProductoLeer(JSpinner spinnerCantidadIngredienteProductoLeer) {
        this.spinnerCantidadIngredienteProductoLeer = spinnerCantidadIngredienteProductoLeer;
    }

    /**
     * @return the tableCellRendererCantidadIngredienteProducto
     */
    public TableCellRenderer getTableCellRendererCantidadIngredienteProducto() {
        return tableCellRendererCantidadIngredienteProducto;
    }

    /**
     * @param tableCellRendererCantidadIngredienteProducto the
     * tableCellRendererCantidadIngredienteProducto to set
     */
    public void setTableCellRendererCantidadIngredienteProducto(TableCellRenderer tableCellRendererCantidadIngredienteProducto) {
        this.tableCellRendererCantidadIngredienteProducto = tableCellRendererCantidadIngredienteProducto;
    }

    /**
     * @return the tableCellEditorCantidadIngredienteProducto
     */
    public TableCellEditor getTableCellEditorCantidadIngredienteProducto() {
        return tableCellEditorCantidadIngredienteProducto;
    }

    /**
     * @param tableCellEditorCantidadIngredienteProducto the
     * tableCellEditorCantidadIngredienteProducto to set
     */
    public void setTableCellEditorCantidadIngredienteProducto(TableCellEditor tableCellEditorCantidadIngredienteProducto) {
        this.tableCellEditorCantidadIngredienteProducto = tableCellEditorCantidadIngredienteProducto;
    }
}
