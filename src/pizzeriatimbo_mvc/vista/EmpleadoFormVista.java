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
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 *
 * @author davidchenlo
 */
public class EmpleadoFormVista extends JInternalFrame {

    private JTable tableLeer;
    private DefaultTableModel tableModelLeer;
    private TableColumn tableColumnFechaNacimiento;
    private TableRowSorter tableRowSorterLeer;
    private JDateChooser dateChooserIngresar;
    private JDateChooser dateChooserLeer;
    private TableCellRenderer tableCellRendererFechaNacimiento;
    private JLabel lblNombreCompletoIngresar;
    private JLabel lblFechaNacimientoIngresar;
    private JLabel lblCelulaIdentidadIngresar;
    private JLabel lblNumeroCelularIngresar;
    private JLabel lblBusquedaLeer;
    private JTextField txtNombreCompletoIngresar;
    private JTextField txtCelulaIdentidadIngresar;
    private JTextField txtNumeroCelularIngresar;
    private JTextField txtFilterLeer;
    private JScrollPane scrollPaneTableLeer;
    private JButton btnIngresar;
    private JButton btnLimpiarIngresar;
    private JButton btnRefreshLeer;
    private JButton btnModificarLeer;
    private JButton btnEliminarLeer;
    private JTabbedPane tabbedPane;
    private JPanel panelIngresar;
    private JPanel panelLeer;

    public EmpleadoFormVista() {
        super("Empleado");
        initJInternalFrame();
        initJTabbedPane();
        initJPanelIngresar();
        initJPanelLeer();
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
        this.getContentPane().add(getTabbedPane());
    }

    private void initJPanelIngresar() {
        JPanel innerPanel1 = new JPanel();
        JPanel innerPanel2 = new JPanel();
        this.setPanelIngresar(new JPanel());
        this.getPanelIngresar().setLayout(new BorderLayout());
        this.setLblNombreCompletoIngresar(new JLabel());
        this.setLblFechaNacimientoIngresar(new JLabel());
        this.setLblCelulaIdentidadIngresar(new JLabel());
        this.setLblNumeroCelularIngresar(new JLabel());
        this.setTxtNombreCompletoIngresar(new JTextField());
        this.setTxtCelulaIdentidadIngresar(new JTextField());
        this.setTxtNumeroCelularIngresar(new JTextField());
        this.setBtnIngresar(new JButton());
        this.setBtnLimpiarIngresar(new JButton());
        this.setDateChooserIngresar(new JDateChooser());
        this.getLblNombreCompletoIngresar().setText("Nombre Completo: ");
        this.getLblFechaNacimientoIngresar().setText("Fecha Nacimiento: ");
        this.getLblCelulaIdentidadIngresar().setText("Celula Identidad: ");
        this.getLblNumeroCelularIngresar().setText("Numero Celular: ");
        this.getBtnIngresar().setText("Ingresar");
        this.getBtnLimpiarIngresar().setText("Limpiar");
        innerPanel1.setLayout(new GridLayout(1, 2, 10, 0));
        innerPanel1.add(this.getBtnIngresar());
        innerPanel1.add(this.getBtnLimpiarIngresar());
        innerPanel2.setLayout(new GridLayout(4, 2, 10, 0));
        innerPanel2.add(this.getLblNombreCompletoIngresar());
        innerPanel2.add(this.getTxtNombreCompletoIngresar());
        innerPanel2.add(this.getLblFechaNacimientoIngresar());
        innerPanel2.add(this.getDateChooserIngresar());
        innerPanel2.add(this.getLblCelulaIdentidadIngresar());
        innerPanel2.add(this.getTxtCelulaIdentidadIngresar());
        innerPanel2.add(this.getLblNumeroCelularIngresar());
        innerPanel2.add(this.getTxtNumeroCelularIngresar());
        this.getPanelIngresar().add(innerPanel2, BorderLayout.CENTER);
        this.getPanelIngresar().add(innerPanel1, BorderLayout.SOUTH);
        this.getTabbedPane().addTab("Ingresar", this.getPanelIngresar());
    }

    private void initJPanelLeer() {
        JPanel innerTopPanel = new JPanel();
        JPanel innerBottomPanel = new JPanel();
        this.setPanelLeer(new JPanel());
        this.getPanelLeer().setLayout(new BorderLayout());
        this.setDateChooserLeer(new JDateChooser());
        this.setBtnRefreshLeer(new JButton());
        this.setBtnModificar(new JButton());
        this.setBtnEliminar(new JButton());
        this.setTxtFilterLeer(new JTextField());
        this.setLblBusquedaLeer(new JLabel());
        this.setTableModelLeer(new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return !((column == 0) || (column == 5));
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
                int modelColumn = this.convertColumnIndexToModel(column);
                switch (modelColumn) {
                    case 2:
                        return getTableCellRendererFechaNacimiento();
                    default:
                        return super.getCellRenderer(row, column);
                }

            }

        });
        this.setScrollPaneTableLeer(new JScrollPane(this.getTableLeer()));
        this.getTableLeer().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        this.getTableLeer().setDefaultEditor(java.util.Date.class, new JDateChooserCellEditor());
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
    
    public void dateChooserFechaIngresoLeerSetTableCellRenderer(TableCellRenderer tableCellRenderer) {
        this.setTableCellRendererFechaNacimiento(tableCellRenderer);
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }

    public int mensajeConfirmar(String mensaje) {
        return JOptionPane.showConfirmDialog(null, mensaje);
    }

    public void btnIngresarAddActionListener(ActionListener actionListener) {
        this.getBtnIngresar().addActionListener(actionListener);
    }

    public void btnLimpiarIngresarAddActionListener(ActionListener actionListener) {
        this.getBtnLimpiarIngresar().addActionListener(actionListener);
    }

    public void txtFilterLeerAddDocumentListener(DocumentListener documentListener) {
        this.getTxtFilterLeer().getDocument().addDocumentListener(documentListener);
    }

    public void btnRefreshLeerAddActionListener(ActionListener actionListener) {
        this.getBtnRefreshLeer().addActionListener(actionListener);
    }

    public void btnModificarLeerAddActionListener(ActionListener actionListener) {
        this.getBtnModificarLeer().addActionListener(actionListener);
    }

    public void btnEliminarLeerAddActionListener(ActionListener actionListener) {
        this.getBtnEliminarLeer().addActionListener(actionListener);
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
     * @return the tableColumnFechaNacimiento
     */
    public TableColumn getTableColumnFechaNacimiento() {
        return tableColumnFechaNacimiento;
    }

    /**
     * @param tableColumnFechaNacimiento the tableColumnFechaNacimiento to set
     */
    public void setTableColumnFechaNacimiento(TableColumn tableColumnFechaNacimiento) {
        this.tableColumnFechaNacimiento = tableColumnFechaNacimiento;
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
     * @return the dateChooserIngresar
     */
    public JDateChooser getDateChooserIngresar() {
        return dateChooserIngresar;
    }

    /**
     * @param dateChooserIngresar the dateChooserIngresar to set
     */
    public void setDateChooserIngresar(JDateChooser dateChooserIngresar) {
        this.dateChooserIngresar = dateChooserIngresar;
    }

    /**
     * @return the lblNombreCompletoIngresar
     */
    public JLabel getLblNombreCompletoIngresar() {
        return lblNombreCompletoIngresar;
    }

    /**
     * @param lblNombreCompletoIngresar the lblNombreCompletoIngresar to set
     */
    public void setLblNombreCompletoIngresar(JLabel lblNombreCompletoIngresar) {
        this.lblNombreCompletoIngresar = lblNombreCompletoIngresar;
    }

    /**
     * @return the lblFechaNacimientoIngresar
     */
    public JLabel getLblFechaNacimientoIngresar() {
        return lblFechaNacimientoIngresar;
    }

    /**
     * @param lblFechaNacimientoIngresar the lblFechaNacimientoIngresar to set
     */
    public void setLblFechaNacimientoIngresar(JLabel lblFechaNacimientoIngresar) {
        this.lblFechaNacimientoIngresar = lblFechaNacimientoIngresar;
    }

    /**
     * @return the lblCelulaIdentidadIngresar
     */
    public JLabel getLblCelulaIdentidadIngresar() {
        return lblCelulaIdentidadIngresar;
    }

    /**
     * @param lblCelulaIdentidadIngresar the lblCelulaIdentidadIngresar to set
     */
    public void setLblCelulaIdentidadIngresar(JLabel lblCelulaIdentidadIngresar) {
        this.lblCelulaIdentidadIngresar = lblCelulaIdentidadIngresar;
    }

    /**
     * @return the lblNumeroCelularIngresar
     */
    public JLabel getLblNumeroCelularIngresar() {
        return lblNumeroCelularIngresar;
    }

    /**
     * @param lblNumeroCelularIngresar the lblNumeroCelularIngresar to set
     */
    public void setLblNumeroCelularIngresar(JLabel lblNumeroCelularIngresar) {
        this.lblNumeroCelularIngresar = lblNumeroCelularIngresar;
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
     * @return the txtNombreCompletoIngresar
     */
    public JTextField getTxtNombreCompletoIngresar() {
        return txtNombreCompletoIngresar;
    }

    /**
     * @param txtNombreCompletoIngresar the txtNombreCompletoIngresar to set
     */
    public void setTxtNombreCompletoIngresar(JTextField txtNombreCompletoIngresar) {
        this.txtNombreCompletoIngresar = txtNombreCompletoIngresar;
    }

    /**
     * @return the txtCelulaIdentidadIngresar
     */
    public JTextField getTxtCelulaIdentidadIngresar() {
        return txtCelulaIdentidadIngresar;
    }

    /**
     * @param txtCelulaIdentidadIngresar the txtCelulaIdentidadIngresar to set
     */
    public void setTxtCelulaIdentidadIngresar(JTextField txtCelulaIdentidadIngresar) {
        this.txtCelulaIdentidadIngresar = txtCelulaIdentidadIngresar;
    }

    /**
     * @return the txtNumeroCelularIngresar
     */
    public JTextField getTxtNumeroCelularIngresar() {
        return txtNumeroCelularIngresar;
    }

    /**
     * @param txtNumeroCelularIngresar the txtNumeroCelularIngresar to set
     */
    public void setTxtNumeroCelularIngresar(JTextField txtNumeroCelularIngresar) {
        this.txtNumeroCelularIngresar = txtNumeroCelularIngresar;
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
     * @return the btnModificar
     */
    public JButton getBtnModificarLeer() {
        return btnModificarLeer;
    }

    /**
     * @param btnModificar the btnModificar to set
     */
    public void setBtnModificar(JButton btnModificar) {
        this.btnModificarLeer = btnModificar;
    }

    /**
     * @return the btnEliminar
     */
    public JButton getBtnEliminarLeer() {
        return btnEliminarLeer;
    }

    /**
     * @param btnEliminar the btnEliminar to set
     */
    public void setBtnEliminar(JButton btnEliminar) {
        this.btnEliminarLeer = btnEliminar;
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
     * @return the tableCellRendererFechaNacimiento
     */
    public TableCellRenderer getTableCellRendererFechaNacimiento() {
        return tableCellRendererFechaNacimiento;
    }

    /**
     * @param tableCellRendererFechaNacimiento the
     * tableCellRendererFechaNacimiento to set
     */
    public void setTableCellRendererFechaNacimiento(TableCellRenderer tableCellRendererFechaNacimiento) {
        this.tableCellRendererFechaNacimiento = tableCellRendererFechaNacimiento;
    }

    /**
     * @return the dateChooserLeer
     */
    public JDateChooser getDateChooserLeer() {
        return dateChooserLeer;
    }

    /**
     * @param dateChooserLeer the dateChooserLeer to set
     */
    public void setDateChooserLeer(JDateChooser dateChooserLeer) {
        this.dateChooserLeer = dateChooserLeer;
    }

}
