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

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JDateChooserCellEditor;

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
public class EmpleadoTipoEmpleadoFormVista extends JInternalFrame {

    private JTable tableLeer;
    private DefaultTableModel tableModelLeer;
    private TableRowSorter tableRowSorterLeer;
    private JScrollPane scrollPaneTableLeer;
    private DefaultCellEditor defaultCellEditorEmpleado;
    private DefaultCellEditor defaultCellEditorTipoEmpleado;
    private TableCellEditor tableCellEditorHorasTrabajadas;
    private TableCellEditor tableCellEditorHorasPagadas;
    private TableCellRenderer tableCellRendererEmpleado;
    private TableCellRenderer tableCellRendererTipoEmpleado;
    private TableCellRenderer tableCellRendererFechaIngreso;
    private TableCellRenderer tableCellRendererFechaRetiro;
    private TableCellRenderer tableCellRendererHorasTrabajadas;
    private TableCellRenderer tableCellRendererHorasPagadas;
    private JLabel lblEmpleadoIngresar;
    private JLabel lblTipoEmpleadoIngresar;
    private JLabel lblFechaIngresoIngresar;
    private JLabel lblFechaRetiroIngresar;
    private JLabel lblHorasTrabajadasIngresar;
    private JLabel lblHorasPagadasIngresar;
    private JLabel lblBusquedaLeer;
    private JTextField txtFilterLeer;
    private JComboBox cbxEmpleadoIngresar;
    private JComboBox cbxTipoEmpleadoIngresar;
    private JComboBox cbxEmpleadoLeer;
    private JComboBox cbxTipoEmpleadoLeer;
    private JDateChooser dateChooserFechaIngresoIngresar;
    private JDateChooser dateChooserFechaRetiroIngresar;
    private JDateChooser dateChooserFechaIngresoLeer;
    private JDateChooser dateChooserFechaRetiroLeer;
    private JSpinner spinnerHorasTrabajadasIngresar;
    private JSpinner spinnerHorasPagadasIngresar;
    private JSpinner spinnerHorasTrabajadasLeer;
    private JSpinner spinnerHorasPagadasLeer;
    private JButton btnIngresar;
    private JButton btnLimpiarIngresar;
    private JButton btnRefreshIngresar;
    private JButton btnRefreshLeer;
    private JButton btnModificarLeer;
    private JButton btnEliminarLeer;
    private JTabbedPane tabbedPane;
    private JPanel panelIngresar;
    private JPanel panelLeer;

    public EmpleadoTipoEmpleadoFormVista() {

        super("Empleado Tipo Empleado");
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
        this.setLblTipoEmpleadoIngresar(new JLabel());
        this.setLblFechaIngresoIngresar(new JLabel());
        this.setLblFechaRetiroIngresar(new JLabel());
        this.setLblHorasTrabajadasIngresar(new JLabel());
        this.setLblHorasPagadasIngresar(new JLabel());
        this.setCbxEmpleadoIngresar(new JComboBox());
        this.setCbxTipoEmpleadoIngresar(new JComboBox());
        this.setDateChooserFechaIngresoIngresar(new JDateChooser());
        this.setDateChooserFechaRetiroIngresar(new JDateChooser());
        this.setSpinnerHorasTrabajadasIngresar(new JSpinner(new SpinnerNumberModel(0, 0, 2147483647, 1)));
        this.setSpinnerHorasPagadasIngresar(new JSpinner(new SpinnerNumberModel(0, 0, 2147483647, 1)));
        this.setBtnRefreshIngresar(new JButton());
        this.setBtnIngresar(new JButton());
        this.setBtnLimpiarIngresar(new JButton());
        this.getLblEmpleadoIngresar().setText("Empleado: ");
        this.getLblTipoEmpleadoIngresar().setText("Tipo Empleado: ");
        this.getLblFechaIngresoIngresar().setText("Fecha Ingreso: ");
        this.getLblFechaRetiroIngresar().setText("Fecha Retiro: ");
        this.getLblHorasTrabajadasIngresar().setText("Horas Trabajadas: ");
        this.getLblHorasPagadasIngresar().setText("Horas Pagadas: ");
        this.getBtnRefreshIngresar().setText("Actualizar");
        this.getBtnIngresar().setText("Ingresar");
        this.getBtnLimpiarIngresar().setText("Limpiar");
        innerPanel1.setLayout(new GridLayout(1, 3, 10, 0));
        innerPanel1.add(this.getBtnRefreshIngresar());
        innerPanel1.add(this.getBtnIngresar());
        innerPanel1.add(this.getBtnLimpiarIngresar());
        innerPanel2.setLayout(new GridLayout(6, 2, 10, 0));
        innerPanel2.add(this.getLblEmpleadoIngresar());
        innerPanel2.add(this.getCbxEmpleadoIngresar());
        innerPanel2.add(this.getLblTipoEmpleadoIngresar());
        innerPanel2.add(this.getCbxTipoEmpleadoIngresar());
        innerPanel2.add(this.getLblFechaIngresoIngresar());
        innerPanel2.add(this.getDateChooserFechaIngresoIngresar());
        innerPanel2.add(this.getLblFechaRetiroIngresar());
        innerPanel2.add(this.getDateChooserFechaRetiroIngresar());
        innerPanel2.add(this.getLblHorasTrabajadasIngresar());
        innerPanel2.add(this.getSpinnerHorasTrabajadasIngresar());
        innerPanel2.add(this.getLblHorasPagadasIngresar());
        innerPanel2.add(this.getSpinnerHorasPagadasIngresar());
        this.getPanelIngresar().add(innerPanel2, BorderLayout.CENTER);
        this.getPanelIngresar().add(innerPanel1, BorderLayout.SOUTH);
        this.getTabbedPane().addTab("Ingresar", this.getPanelIngresar());
    }

    private void initPanelLeer() {
        JPanel innerTopPanel = new JPanel();
        JPanel innerBottomPanel = new JPanel();
        this.setPanelLeer(new JPanel());
        this.getPanelLeer().setLayout(new BorderLayout());
        this.setCbxEmpleadoLeer(new JComboBox());
        this.setCbxTipoEmpleadoLeer(new JComboBox());
        this.setDateChooserFechaIngresoLeer(new JDateChooser());
        this.setDateChooserFechaRetiroLeer(new JDateChooser());
        this.setSpinnerHorasTrabajadasLeer(new JSpinner(new SpinnerNumberModel(0, 0, 2147483647, 1)));
        this.setSpinnerHorasPagadasLeer(new JSpinner(new SpinnerNumberModel(0, 0, 2147483647, 1)));
        this.setBtnRefreshLeer(new JButton());
        this.setBtnModificarLeer(new JButton());
        this.setBtnEliminarLeer(new JButton());
        this.setTxtFilterLeer(new JTextField());
        this.setLblBusquedaLeer(new JLabel());

        this.setTableModelLeer(new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return !((column == 0) || (column == 7));
            }

            // FIX: Sorting any class type
            @Override
            public Class getColumnClass(int column) {
                Class clase = Object.class;
                if (getRowCount() > 0) {
                    clase = getValueAt(0, column).getClass();
                }
                return clase;
            }

        });
        this.setTableLeer(new JTable(this.getTableModelLeer()) {
            @Override
            public TableCellRenderer getCellRenderer(int row, int column) {
                int modelColumn = convertColumnIndexToModel(column);
                switch (modelColumn) {
                    case 1:
                        return getTableCellRendererEmpleado();
                    case 2:
                        return getTableCellRendererTipoEmpleado();
                    case 3:
                        return getTableCellRendererFechaIngreso();
                    case 4:
                        return getTableCellRendererFechaRetiro();
                    case 5:
                        return getTableCellRendererHorasTrabajadas();
                    case 6:
                        return getTableCellRendererHorasPagadas();
                    default:
                        return super.getCellRenderer(row, column);
                }
            }

            @Override
            public TableCellEditor getCellEditor(int row, int column) {
                int modelColumn = convertColumnIndexToModel(column);
                switch (modelColumn) {
                    case 1:
                        return getDefaultCellEditorEmpleado();
                    case 2:
                        return getDefaultCellEditorTipoEmpleado();
                    case 5:
                        return getTableCellEditorHorasTrabajadas();
                    case 6:
                        return getTableCellEditorHorasPagadas();
                    default:
                        return super.getCellEditor(row, column);
                }
            }
        });
        this.getTableLeer().setDefaultEditor(java.util.Date.class, new JDateChooserCellEditor());
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

    public void cbxTipoEmpleadoLeerSetDefaultCellEditor(JComboBox cbx) {
        this.setDefaultCellEditorTipoEmpleado(new DefaultCellEditor(cbx) {
            @Override
            public Component getTableCellEditorComponent(JTable jtable, Object value, boolean bln, int row, int column) {
                super.getTableCellEditorComponent(jtable, value, isSelected, row, column);
                cbx.setSelectedItem(null);
                return cbx;
            }
        });
    }

    public void spinnerHorasTrabajadasLeerSetTableCellEditor(TableCellEditor tableCellEditor) {
        this.setTableCellEditorHorasTrabajadas(tableCellEditor);
    }

    public void spinnerHorasPagadasLeerSetTableCellEditor(TableCellEditor tableCellEditor) {
        this.setTableCellEditorHorasPagadas(tableCellEditor);
    }

    public void cbxEmpleadoLeerSetTableCellRenderer(TableCellRenderer tableCellRenderer) {
        this.setTableCellRendererEmpleado(tableCellRenderer);
    }

    public void cbxTipoEmpleadoLeerSetTableCellRenderer(TableCellRenderer tableCellRenderer) {
        this.setTableCellRendererTipoEmpleado(tableCellRenderer);
    }

    public void dateChooserFechaIngresoLeerSetTableCellRenderer(TableCellRenderer tableCellRenderer) {
        this.setTableCellRendererFechaIngreso(tableCellRenderer);
    }

    public void dateChooserFechaRetiroLeerSetTableCellRenderer(TableCellRenderer tableCellRenderer) {
        this.setTableCellRendererFechaRetiro(tableCellRenderer);
    }

    public void spinnerHorasTrabajadasLeerSetTableCellRenderer(TableCellRenderer tableCellRenderer) {
        this.setTableCellRendererHorasTrabajadas(tableCellRenderer);
    }

    public void spinnerHorasPagadasLeerSetTableCellRenderer(TableCellRenderer tableCellRenderer) {
        this.setTableCellRendererHorasPagadas(tableCellRenderer);
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
    public JLabel getLblEmpleadoIngresar() {
        return lblEmpleadoIngresar;
    }

    /**
     * @param lblEmpleadoIngresar the lblNombreIngresar to set
     */
    public void setLblEmpleadoIngresar(JLabel lblEmpleadoIngresar) {
        this.lblEmpleadoIngresar = lblEmpleadoIngresar;
    }

    /**
     * @return the lblTipoEmpleadoIngresar
     */
    public JLabel getLblTipoEmpleadoIngresar() {
        return lblTipoEmpleadoIngresar;
    }

    /**
     * @param lblTipoEmpleadoIngresar the lblDescripcionIngresar to set
     */
    public void setLblTipoEmpleadoIngresar(JLabel lblTipoEmpleadoIngresar) {
        this.lblTipoEmpleadoIngresar = lblTipoEmpleadoIngresar;
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
    public JComboBox getCbxEmpleadoIngresar() {
        return cbxEmpleadoIngresar;
    }

    /**
     * @param cbxEmpleadoIngresar the cbxTamaño to set
     */
    public void setCbxEmpleadoIngresar(JComboBox cbxEmpleadoIngresar) {
        this.cbxEmpleadoIngresar = cbxEmpleadoIngresar;
    }

    /**
     * @return the cbxTipoEmpleadoIngresar
     */
    public JComboBox getCbxTipoEmpleadoIngresar() {
        return cbxTipoEmpleadoIngresar;
    }

    /**
     * @param cbxTipoEmpleadoIngresar the cbxTipoEmpleadoIngresar to set
     */
    public void setCbxTipoEmpleadoIngresar(JComboBox cbxTipoEmpleadoIngresar) {
        this.cbxTipoEmpleadoIngresar = cbxTipoEmpleadoIngresar;
    }

    /**
     * @return the spinnerHorasTrabajadasIngresar
     */
    public JSpinner getSpinnerHorasTrabajadasIngresar() {
        return spinnerHorasTrabajadasIngresar;
    }

    /**
     * @param spinnerHorasTrabajadasIngresar the spinnerIngresar to set
     */
    public void setSpinnerHorasTrabajadasIngresar(JSpinner spinnerHorasTrabajadasIngresar) {
        this.spinnerHorasTrabajadasIngresar = spinnerHorasTrabajadasIngresar;
    }

    /**
     * @return the lblHorasTrabajadasIngresar
     */
    public JLabel getLblHorasTrabajadasIngresar() {
        return lblHorasTrabajadasIngresar;
    }

    /**
     * @param lblHorasTrabajadasIngresar the lblHorasTrabajadasIngresar to set
     */
    public void setLblHorasTrabajadasIngresar(JLabel lblHorasTrabajadasIngresar) {
        this.lblHorasTrabajadasIngresar = lblHorasTrabajadasIngresar;
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
     * @return the defaultCellEditorTipoEmpleado
     */
    public DefaultCellEditor getDefaultCellEditorTipoEmpleado() {
        return defaultCellEditorTipoEmpleado;
    }

    /**
     * @param defaultCellEditorTipoEmpleado the defaultCellEditorTipoEmpleado to
     * set
     */
    public void setDefaultCellEditorTipoEmpleado(DefaultCellEditor defaultCellEditorTipoEmpleado) {
        this.defaultCellEditorTipoEmpleado = defaultCellEditorTipoEmpleado;
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
     * @return the cbxTipoEmpleadoLeer
     */
    public JComboBox getCbxTipoEmpleadoLeer() {
        return cbxTipoEmpleadoLeer;
    }

    /**
     * @param cbxTipoEmpleadoLeer the cbxTipoEmpleadoLeer to set
     */
    public void setCbxTipoEmpleadoLeer(JComboBox cbxTipoEmpleadoLeer) {
        this.cbxTipoEmpleadoLeer = cbxTipoEmpleadoLeer;
    }

    /**
     * @return the lblFechaIngresoIngresar
     */
    public JLabel getLblFechaIngresoIngresar() {
        return lblFechaIngresoIngresar;
    }

    /**
     * @param lblFechaIngresoIngresar the lblFechaIngresoIngresar to set
     */
    public void setLblFechaIngresoIngresar(JLabel lblFechaIngresoIngresar) {
        this.lblFechaIngresoIngresar = lblFechaIngresoIngresar;
    }

    /**
     * @return the lblFechaRetiroIngresar
     */
    public JLabel getLblFechaRetiroIngresar() {
        return lblFechaRetiroIngresar;
    }

    /**
     * @param lblFechaRetiroIngresar the lblFechaRetiroIngresar to set
     */
    public void setLblFechaRetiroIngresar(JLabel lblFechaRetiroIngresar) {
        this.lblFechaRetiroIngresar = lblFechaRetiroIngresar;
    }

    /**
     * @return the lblHorasPagadasIngresar
     */
    public JLabel getLblHorasPagadasIngresar() {
        return lblHorasPagadasIngresar;
    }

    /**
     * @param lblHorasPagadasIngresar the lblHorasPagadasIngresar to set
     */
    public void setLblHorasPagadasIngresar(JLabel lblHorasPagadasIngresar) {
        this.lblHorasPagadasIngresar = lblHorasPagadasIngresar;
    }

    /**
     * @return the spinnerHorasPagadasIngresar
     */
    public JSpinner getSpinnerHorasPagadasIngresar() {
        return spinnerHorasPagadasIngresar;
    }

    /**
     * @param spinnerHorasPagadasIngresar the spinnerHorasPagadasIngresar to set
     */
    public void setSpinnerHorasPagadasIngresar(JSpinner spinnerHorasPagadasIngresar) {
        this.spinnerHorasPagadasIngresar = spinnerHorasPagadasIngresar;
    }

    /**
     * @return the dateChooserFechaIngresoIngresar
     */
    public JDateChooser getDateChooserFechaIngresoIngresar() {
        return dateChooserFechaIngresoIngresar;
    }

    /**
     * @param dateChooserFechaIngresoIngresar the
     * dateChooserFechaIngresoIngresar to set
     */
    public void setDateChooserFechaIngresoIngresar(JDateChooser dateChooserFechaIngresoIngresar) {
        this.dateChooserFechaIngresoIngresar = dateChooserFechaIngresoIngresar;
    }

    /**
     * @return the dateChooserFechaRetiroIngresar
     */
    public JDateChooser getDateChooserFechaRetiroIngresar() {
        return dateChooserFechaRetiroIngresar;
    }

    /**
     * @param dateChooserFechaRetiroIngresar the dateChooserFechaRetiroIngresar
     * to set
     */
    public void setDateChooserFechaRetiroIngresar(JDateChooser dateChooserFechaRetiroIngresar) {
        this.dateChooserFechaRetiroIngresar = dateChooserFechaRetiroIngresar;
    }

    /**
     * @return the dateChooserFechaIngresoLeer
     */
    public JDateChooser getDateChooserFechaIngresoLeer() {
        return dateChooserFechaIngresoLeer;
    }

    /**
     * @param dateChooserFechaIngresoLeer the dateChooserFechaIngresoLeer to set
     */
    public void setDateChooserFechaIngresoLeer(JDateChooser dateChooserFechaIngresoLeer) {
        this.dateChooserFechaIngresoLeer = dateChooserFechaIngresoLeer;
    }

    /**
     * @return the dateChooserFechaRetiroLeer
     */
    public JDateChooser getDateChooserFechaRetiroLeer() {
        return dateChooserFechaRetiroLeer;
    }

    /**
     * @param dateChooserFechaRetiroLeer the dateChooserFechaRetiroLeer to set
     */
    public void setDateChooserFechaRetiroLeer(JDateChooser dateChooserFechaRetiroLeer) {
        this.dateChooserFechaRetiroLeer = dateChooserFechaRetiroLeer;
    }

    /**
     * @return the tableCellEditorHorasTrabajadas
     */
    public TableCellEditor getTableCellEditorHorasTrabajadas() {
        return tableCellEditorHorasTrabajadas;
    }

    /**
     * @param tableCellEditorHorasTrabajadas the tableCellEditorHorasTrabajadas
     * to set
     */
    public void setTableCellEditorHorasTrabajadas(TableCellEditor tableCellEditorHorasTrabajadas) {
        this.tableCellEditorHorasTrabajadas = tableCellEditorHorasTrabajadas;
    }

    /**
     * @return the tableCellEditorHorasPagadas
     */
    public TableCellEditor getTableCellEditorHorasPagadas() {
        return tableCellEditorHorasPagadas;
    }

    /**
     * @param tableCellEditorHorasPagadas the tableCellEditorHorasPagadas to set
     */
    public void setTableCellEditorHorasPagadas(TableCellEditor tableCellEditorHorasPagadas) {
        this.tableCellEditorHorasPagadas = tableCellEditorHorasPagadas;
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
     * @return the tableCellRendererTipoEmpleado
     */
    public TableCellRenderer getTableCellRendererTipoEmpleado() {
        return tableCellRendererTipoEmpleado;
    }

    /**
     * @param tableCellRendererTipoEmpleado the tableCellRendererTipoEmpleado to
     * set
     */
    public void setTableCellRendererTipoEmpleado(TableCellRenderer tableCellRendererTipoEmpleado) {
        this.tableCellRendererTipoEmpleado = tableCellRendererTipoEmpleado;
    }

    /**
     * @return the tableCellRendererFechaIngreso
     */
    public TableCellRenderer getTableCellRendererFechaIngreso() {
        return tableCellRendererFechaIngreso;
    }

    /**
     * @param tableCellRendererFechaIngreso the tableCellRendererFechaIngreso to
     * set
     */
    public void setTableCellRendererFechaIngreso(TableCellRenderer tableCellRendererFechaIngreso) {
        this.tableCellRendererFechaIngreso = tableCellRendererFechaIngreso;
    }

    /**
     * @return the tableCellRendererFechaRetiro
     */
    public TableCellRenderer getTableCellRendererFechaRetiro() {
        return tableCellRendererFechaRetiro;
    }

    /**
     * @param tableCellRendererFechaRetiro the tableCellRendererFechaRetiro to
     * set
     */
    public void setTableCellRendererFechaRetiro(TableCellRenderer tableCellRendererFechaRetiro) {
        this.tableCellRendererFechaRetiro = tableCellRendererFechaRetiro;
    }

    /**
     * @return the tableCellRendererHorasTrabajadas
     */
    public TableCellRenderer getTableCellRendererHorasTrabajadas() {
        return tableCellRendererHorasTrabajadas;
    }

    /**
     * @param tableCellRendererHorasTrabajadas the
     * tableCellRendererHorasTrabajadas to set
     */
    public void setTableCellRendererHorasTrabajadas(TableCellRenderer tableCellRendererHorasTrabajadas) {
        this.tableCellRendererHorasTrabajadas = tableCellRendererHorasTrabajadas;
    }

    /**
     * @return the tableCellRendererHorasPagadas
     */
    public TableCellRenderer getTableCellRendererHorasPagadas() {
        return tableCellRendererHorasPagadas;
    }

    /**
     * @param tableCellRendererHorasPagadas the tableCellRendererHorasPagadas to
     * set
     */
    public void setTableCellRendererHorasPagadas(TableCellRenderer tableCellRendererHorasPagadas) {
        this.tableCellRendererHorasPagadas = tableCellRendererHorasPagadas;
    }

    /**
     * @return the spinnerHorasTrabajadasLeer
     */
    public JSpinner getSpinnerHorasTrabajadasLeer() {
        return spinnerHorasTrabajadasLeer;
    }

    /**
     * @param spinnerHorasTrabajadasLeer the spinnerHorasTrabajadasLeer to set
     */
    public void setSpinnerHorasTrabajadasLeer(JSpinner spinnerHorasTrabajadasLeer) {
        this.spinnerHorasTrabajadasLeer = spinnerHorasTrabajadasLeer;
    }

    /**
     * @return the spinnerHorasPagadasLeer
     */
    public JSpinner getSpinnerHorasPagadasLeer() {
        return spinnerHorasPagadasLeer;
    }

    /**
     * @param spinnerHorasPagadasLeer the spinnerHorasPagadasLeer to set
     */
    public void setSpinnerHorasPagadasLeer(JSpinner spinnerHorasPagadasLeer) {
        this.spinnerHorasPagadasLeer = spinnerHorasPagadasLeer;
    }
}
