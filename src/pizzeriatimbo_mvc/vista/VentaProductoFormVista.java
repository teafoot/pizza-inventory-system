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
public class VentaProductoFormVista extends JInternalFrame {

    private int ultimaFila;
    private JTable tableLeer;
    private DefaultTableModel tableModelLeer;
    private TableRowSorter tableRowSorterLeer;
    private JScrollPane scrollPaneTableLeer;
    private TableCellEditor tableCellEditorCantidadProducto;
    private DefaultCellEditor defaultCellEditorEmpleado;
    private DefaultCellEditor defaultCellEditorTamaño;
    private DefaultCellEditor defaultCellEditorProducto;
    private TableCellRenderer tableCellRendererEmpleado;
    private TableCellRenderer tableCellRendererCantidadProducto;
    private TableCellRenderer tableCellRendererProducto;
    private TableCellRenderer tableCellRendererTamaño;
    private JLabel lblEmpleadoIngresar;
    private JLabel lblTamañoIngresar;
    private JLabel lblProductoIngresar;
    private JLabel lblCantidadProductoIngresar;
    private JLabel lblBusquedaLeer;
    private JTextField txtFilterLeer;
    private JComboBox cbxEmpleadoIngresar;
    private JComboBox cbxTamañoIngresar;
    private JComboBox cbxProductoIngresar;
    private JComboBox cbxEmpleadoLeer;
    private JComboBox cbxTamañoLeer;
    private JComboBox cbxProductoLeer;
    private JSpinner spinnerCantidadProductoIngresar;
    private JSpinner spinnerCantidadProductoLeer;
    private JButton btnIngresar;
    private JButton btnLimpiarIngresar;
    private JButton btnRefreshIngresar;
    private JButton btnRefreshLeer;
    private JButton btnModificarLeer;
    private JButton btnCheckoutLeer;
    private JButton btnEliminarLeer;
    private JTabbedPane tabbedPane;
    private JPanel panelIngresar;
    private JPanel panelLeer;

    public VentaProductoFormVista() {
        super("Venta Producto");
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
        this.setLblEmpleadoIngresar(new JLabel());
        this.setLblTamañoIngresar(new JLabel());
        this.setLblProductoIngresar(new JLabel());
        this.setLblCantidadProductoIngresar(new JLabel());
        this.setCbxEmpleadoIngresar(new JComboBox());
        this.setCbxTamañoIngresar(new JComboBox());
        this.setCbxProductoIngresar(new JComboBox());
        this.setSpinnerCantidadProductoIngresar(new JSpinner(new SpinnerNumberModel(0, 0, 2147483647, 1)));
        this.setBtnRefreshIngresar(new JButton());
        this.setBtnIngresar(new JButton());
        this.setBtnLimpiarIngresar(new JButton());
        this.getLblEmpleadoIngresar().setText("Empleado: ");
        this.getLblTamañoIngresar().setText("Tamaño: ");
        this.getLblProductoIngresar().setText("Producto: ");
        this.getLblCantidadProductoIngresar().setText("Cantidad Producto: ");
        this.getBtnRefreshIngresar().setText("Actualizar");
        this.getBtnIngresar().setText("Ingresar");
        this.getBtnLimpiarIngresar().setText("Limpiar");
        innerPanel1.setLayout(new GridLayout(1, 3, 10, 0));
        innerPanel1.add(this.getBtnRefreshIngresar());
        innerPanel1.add(this.getBtnIngresar());
        innerPanel1.add(this.getBtnLimpiarIngresar());
        innerPanel2.setLayout(new GridLayout(4, 2, 10, 0));
        innerPanel2.add(this.getLblEmpleadoIngresar());
        innerPanel2.add(this.getCbxEmpleadoIngresar());
        innerPanel2.add(this.getLblCantidadProductoIngresar());
        innerPanel2.add(this.getSpinnerCantidadProductoIngresar());
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
        this.setSpinnerCantidadProductoLeer(new JSpinner(new SpinnerNumberModel(0, 0, 2147483647, 1)));
        this.setCbxEmpleadoLeer(new JComboBox());
        this.setCbxTamañoLeer(new JComboBox());
        this.setCbxProductoLeer(new JComboBox());
        this.setBtnRefreshLeer(new JButton());
        this.setBtnModificarLeer(new JButton());
        this.setBtnCheckoutLeer(new JButton());
        this.setBtnEliminarLeer(new JButton());
        this.setTxtFilterLeer(new JTextField());
        this.setLblBusquedaLeer(new JLabel());
        this.setTableModelLeer(new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return !((column == 0) || (column == 5) || (column == 6) || (getUltimaFila() == row));
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
            public TableCellEditor getCellEditor(int row, int column) {
                int modelColumn = this.convertColumnIndexToModel(column);
                int modelRow = this.convertRowIndexToModel(row);
                if (modelRow==getUltimaFila()) {
                    return super.getCellEditor(row, column);
                }
                switch (modelColumn) {
                    case 1:
                        return getDefaultCellEditorEmpleado();
                    case 2:
                        return getTableCellEditorCantidadProducto();
                    case 3:
                        return getDefaultCellEditorProducto();
                    case 4:
                        return getDefaultCellEditorTamaño();
                    default:
                        return super.getCellEditor(row, column);
                }
            }

            @Override
            public TableCellRenderer getCellRenderer(int row, int column) {
                int modelColumn = this.convertColumnIndexToModel(column);
                int modelRow = this.convertRowIndexToModel(row);
                if (modelRow==getUltimaFila()) {
                    return super.getCellRenderer(row, column);
                }
                switch (modelColumn) {
                    case 1:
                        return getTableCellRendererEmpleado();
                    case 2:
                        return getTableCellRendererCantidadProducto();
                    case 3:
                        return getTableCellRendererProducto();
                    case 4:
                        return getTableCellRendererTamaño();
                    default:
                        return super.getCellRenderer(row, column);
                }
            }
        });
        this.setScrollPaneTableLeer(new JScrollPane(this.getTableLeer()));
        this.getTableLeer().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        this.setTableRowSorterLeer(new TableRowSorter(this.getTableModelLeer()));
        this.getTableLeer().setRowSorter(this.getTableRowSorterLeer());
        this.getBtnRefreshLeer().setText("Refrescar");
        this.getBtnModificarLeer().setText("Modificar");
        this.getBtnCheckoutLeer().setText("Checkout");
        this.getBtnEliminarLeer().setText("Eliminar");
        this.getLblBusquedaLeer().setText("Buscar:");
        innerTopPanel.setLayout(new GridLayout(2, 1));
        innerTopPanel.add(this.getLblBusquedaLeer());
        innerTopPanel.add(this.getTxtFilterLeer());
        innerBottomPanel.setLayout(new GridLayout(4, 1));
        innerBottomPanel.add(this.getBtnRefreshLeer());
        innerBottomPanel.add(this.getBtnModificarLeer());
        innerBottomPanel.add(this.getBtnEliminarLeer());
        innerBottomPanel.add(this.getBtnCheckoutLeer());
        this.getPanelLeer().add(innerTopPanel, BorderLayout.NORTH);
        this.getPanelLeer().add(innerBottomPanel, BorderLayout.SOUTH);
        this.getPanelLeer().add(this.getScrollPaneTableLeer(), BorderLayout.CENTER);
        this.getTabbedPane().addTab("Leer", this.getPanelLeer());
    }

    public void cbxEmpleadoLeerSetDefaultCellEditor(JComboBox cbx) {
        this.setDefaultCellEditorEmpleado(new DefaultCellEditor(cbx) {
            @Override
            public Component getTableCellEditorComponent(JTable jtable, Object value, boolean bln, int row, int column) {
                super.getTableCellEditorComponent(jtable, value, isSelected, row, column);
                cbx.setSelectedItem(null);
                return cbx;
            }
        });
    }

    public void cbxEmpleadoLeerSetTableCellRenderer(TableCellRenderer tableCellRenderer) {
        this.setTableCellRendererEmpleado(tableCellRenderer);
    }

    public void spinnerCantidadProductoLeerSetTableCellEditor(TableCellEditor tableCellEditor) {
        this.setTableCellEditorCantidadProducto(tableCellEditor);
    }

    public void spinnerCantidadProductoLeerSetTableCellRenderer(TableCellRenderer tableCellRenderer) {
        this.setTableCellRendererCantidadProducto(tableCellRenderer);
    }

    public void cbxProductoLeerSetDefaultCellEditor(JComboBox cbx) {
        this.setDefaultCellEditorProducto(new DefaultCellEditor(cbx) {
            @Override
            public Component getTableCellEditorComponent(JTable jtable, Object value, boolean bln, int row, int column) {
                super.getTableCellEditorComponent(jtable, value, isSelected, row, column);
                cbx.setSelectedItem(null);
                return cbx;
            }
        });
    }

    public void cbxProductoLeerSetTableCellRenderer(TableCellRenderer tableCellRenderer) {
        this.setTableCellRendererProducto(tableCellRenderer);
    }

    public void cbxTamañoLeerSetDefaultCellEditor(JComboBox cbx) {
        this.setDefaultCellEditorTamaño(new DefaultCellEditor(cbx) {
            @Override
            public Component getTableCellEditorComponent(JTable jtable, Object value, boolean bln, int row, int column) {
                super.getTableCellEditorComponent(jtable, value, isSelected, row, column);
                cbx.setSelectedItem(null);
                return cbx;
            }
        });
    }

    public void cbxTamañoLeerSetTableCellRenderer(TableCellRenderer tableCellRenderer) {
        this.setTableCellRendererTamaño(tableCellRenderer);
    }

    public void btnRefreshIngresarLeerSetActionListener(ActionListener actionListener) {
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
    
    public void btnCheckoutLeerSetActionListener(ActionListener actionListener) {
        this.getBtnCheckoutLeer().addActionListener(actionListener);
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
     * @return the lblNombreIngresar
     */
    public JLabel getLblTamañoIngresar() {
        return lblTamañoIngresar;
    }

    /**
     * @param lblTamañoIngresar the lblNombreIngresar to set
     */
    public void setLblTamañoIngresar(JLabel lblTamañoIngresar) {
        this.lblTamañoIngresar = lblTamañoIngresar;
    }

    /**
     * @return the lblProductoIngresar
     */
    public JLabel getLblProductoIngresar() {
        return lblProductoIngresar;
    }

    /**
     * @param lblProductoIngresar the lblDescripcionIngresar to set
     */
    public void setLblProductoIngresar(JLabel lblProductoIngresar) {
        this.lblProductoIngresar = lblProductoIngresar;
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
     * @return the cbxTamaño
     */
    public JComboBox getCbxTamañoIngresar() {
        return cbxTamañoIngresar;
    }

    /**
     * @param cbxTamañoIngresar the cbxTamaño to set
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
     * @return the spinnerCantidadProductoIngresar
     */
    public JSpinner getSpinnerCantidadProductoIngresar() {
        return spinnerCantidadProductoIngresar;
    }

    /**
     * @param spinnerCantidadProductoIngresar the spinnerIngresar to set
     */
    public void setSpinnerCantidadProductoIngresar(JSpinner spinnerCantidadProductoIngresar) {
        this.spinnerCantidadProductoIngresar = spinnerCantidadProductoIngresar;
    }

    /**
     * @return the lblCantidadProductoIngresar
     */
    public JLabel getLblCantidadProductoIngresar() {
        return lblCantidadProductoIngresar;
    }

    /**
     * @param lblCantidadProductoIngresar the lblCantidadProductoIngresar to set
     */
    public void setLblCantidadProductoIngresar(JLabel lblCantidadProductoIngresar) {
        this.lblCantidadProductoIngresar = lblCantidadProductoIngresar;
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
     * @return the lblEmpleadoIngresar
     */
    public JLabel getLblEmpleadoIngresar() {
        return lblEmpleadoIngresar;
    }

    /**
     * @param lblEmpleadoIngresar the lblEmpleadoIngresar to set
     */
    public void setLblEmpleadoIngresar(JLabel lblEmpleadoIngresar) {
        this.lblEmpleadoIngresar = lblEmpleadoIngresar;
    }

    /**
     * @return the cbxEmpleadoIngresar
     */
    public JComboBox getCbxEmpleadoIngresar() {
        return cbxEmpleadoIngresar;
    }

    /**
     * @param cbxEmpleadoIngresar the cbxEmpleadoIngresar to set
     */
    public void setCbxEmpleadoIngresar(JComboBox cbxEmpleadoIngresar) {
        this.cbxEmpleadoIngresar = cbxEmpleadoIngresar;
    }

    /**
     * @return the cbxEmpleadoLeer
     */
    public JComboBox getCbxEmpleadoLeer() {
        return cbxEmpleadoLeer;
    }

    /**
     * @param cbxEmpleadoLeer the cbxEmpleadoLeer to set
     */
    public void setCbxEmpleadoLeer(JComboBox cbxEmpleadoLeer) {
        this.cbxEmpleadoLeer = cbxEmpleadoLeer;
    }

    /**
     * @return the defaultCellEditorEmpleado
     */
    public DefaultCellEditor getDefaultCellEditorEmpleado() {
        return defaultCellEditorEmpleado;
    }

    /**
     * @param defaultCellEditorEmpleado the defaultCellEditorEmpleado to set
     */
    public void setDefaultCellEditorEmpleado(DefaultCellEditor defaultCellEditorEmpleado) {
        this.defaultCellEditorEmpleado = defaultCellEditorEmpleado;
    }

    /**
     * @return the tableCellRendererEmpleado
     */
    public TableCellRenderer getTableCellRendererEmpleado() {
        return tableCellRendererEmpleado;
    }

    /**
     * @param tableCellRendererEmpleado the tableCellRendererEmpleado to set
     */
    public void setTableCellRendererEmpleado(TableCellRenderer tableCellRendererEmpleado) {
        this.tableCellRendererEmpleado = tableCellRendererEmpleado;
    }

    /**
     * @return the tableCellRendererCantidadProducto
     */
    public TableCellRenderer getTableCellRendererCantidadProducto() {
        return tableCellRendererCantidadProducto;
    }

    /**
     * @param tableCellRendererCantidadProducto the
     * tableCellRendererCantidadProducto to set
     */
    public void setTableCellRendererCantidadProducto(TableCellRenderer tableCellRendererCantidadProducto) {
        this.tableCellRendererCantidadProducto = tableCellRendererCantidadProducto;
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
     * @return the tableCellEditorCantidadProducto
     */
    public TableCellEditor getTableCellEditorCantidadProducto() {
        return tableCellEditorCantidadProducto;
    }

    /**
     * @param tableCellEditorCantidadProducto the
     * tableCellEditorCantidadProducto to set
     */
    public void setTableCellEditorCantidadProducto(TableCellEditor tableCellEditorCantidadProducto) {
        this.tableCellEditorCantidadProducto = tableCellEditorCantidadProducto;
    }

    /**
     * @return the spinnerCantidadProductoLeer
     */
    public JSpinner getSpinnerCantidadProductoLeer() {
        return spinnerCantidadProductoLeer;
    }

    /**
     * @param spinnerCantidadProductoLeer the spinnerCantidadProductoLeer to set
     */
    public void setSpinnerCantidadProductoLeer(JSpinner spinnerCantidadProductoLeer) {
        this.spinnerCantidadProductoLeer = spinnerCantidadProductoLeer;
    }

    /**
     * @return the ultimaFila
     */
    public int getUltimaFila() {
        return ultimaFila;
    }

    /**
     * @param ultimaFila the ultimaFila to set
     */
    public void setUltimaFila(int ultimaFila) {
        this.ultimaFila = ultimaFila;
    }

    /**
     * @return the btnCheckoutLeer
     */
    public JButton getBtnCheckoutLeer() {
        return btnCheckoutLeer;
    }

    /**
     * @param btnCheckoutLeer the btnCheckoutLeer to set
     */
    public void setBtnCheckoutLeer(JButton btnCheckoutLeer) {
        this.btnCheckoutLeer = btnCheckoutLeer;
    }
}
