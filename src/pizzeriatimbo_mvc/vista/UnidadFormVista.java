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
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 *
 * @author davidchenlo
 */
public class UnidadFormVista extends JInternalFrame {

    private JTable tableLeer;
    private DefaultTableModel tableModelLeer;
    private TableRowSorter tableRowSorterLeer;

    private JLabel lblNombreIngresar;
    private JLabel lblDescripcionIngresar;
    private JLabel lblBusquedaLeer;
    private JTextField txtNombreIngresar;
    private JTextField txtFilterLeer;
    private JTextArea txtDescripcionIngresar;
    private JScrollPane scrollPaneTxtDescripcionIngresar;
    private JScrollPane scrollPaneTableLeer;
    private JButton btnIngresar;
    private JButton btnLimpiarIngresar;
    private JButton btnRefreshLeer;
    private JButton btnModificarLeer;
    private JButton btnEliminarLeer;
    private JTabbedPane tabbedPane;
    private JPanel panelIngresar;
    private JPanel panelLeer;

    public UnidadFormVista() {
        super("Unidad");
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
        this.setLblNombreIngresar(new JLabel());
        this.setLblDescripcionIngresar(new JLabel());
        this.setTxtNombreIngresar(new JTextField());
        this.setTxtDescripcionIngresar(new JTextArea());
        this.setScrollPaneTxtDescripcionIngresar(new JScrollPane(this.getTxtDescripcionIngresar()));
        this.setBtnIngresar(new JButton());
        this.setBtnLimpiarIngresar(new JButton());
        this.getLblNombreIngresar().setText("Nombre: ");
        this.getLblDescripcionIngresar().setText("Descripcion: ");
        this.getBtnIngresar().setText("Ingresar");
        this.getBtnLimpiarIngresar().setText("Limpiar");
        this.getTxtDescripcionIngresar().setLineWrap(true);
        innerPanel1.setLayout(new GridLayout(1, 2, 10, 0));
        innerPanel1.add(this.getBtnIngresar());
        innerPanel1.add(this.getBtnLimpiarIngresar());
        innerPanel2.setLayout(new GridLayout(2, 2, 10, 0));
        innerPanel2.add(this.getLblNombreIngresar());
        innerPanel2.add(this.getTxtNombreIngresar());
        innerPanel2.add(this.getLblDescripcionIngresar());
        innerPanel2.add(this.getScrollPaneTxtDescripcionIngresar());
        this.getPanelIngresar().add(innerPanel2, BorderLayout.CENTER);
        this.getPanelIngresar().add(innerPanel1, BorderLayout.SOUTH);
        this.getTabbedPane().addTab("Ingresar", this.getPanelIngresar());
    }

    private void initPanelLeer() {
        JPanel innerTopPanel = new JPanel();
        JPanel innerBottomPanel = new JPanel();
        this.setPanelLeer(new JPanel());
        this.getPanelLeer().setLayout(new BorderLayout());
        this.setBtnRefreshLeer(new JButton());
        this.setBtnModificar(new JButton());
        this.setBtnEliminar(new JButton());
        this.setTxtFilterLeer(new JTextField());
        this.setLblBusquedaLeer(new JLabel());
        this.setTableModelLeer(new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return !((column == 0) || (column == 3));
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
        this.setTableLeer(new JTable(this.getTableModelLeer()));
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

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }

    public int mensajeConfirmar(String mensaje) {
        return JOptionPane.showConfirmDialog(null, mensaje);
    }

    public void btnIngresarSetActionListener(ActionListener actionListener) {
        this.getBtnIngresar().addActionListener(actionListener);
    }

    public void btnLimpiarIngresarSetActionLister(ActionListener actionListener) {
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
    public JLabel getLblNombreIngresar() {
        return lblNombreIngresar;
    }

    /**
     * @param lblNombreIngresar the lblNombreIngresar to set
     */
    public void setLblNombreIngresar(JLabel lblNombreIngresar) {
        this.lblNombreIngresar = lblNombreIngresar;
    }

    /**
     * @return the lblDescripcionIngresar
     */
    public JLabel getLblDescripcionIngresar() {
        return lblDescripcionIngresar;
    }

    /**
     * @param lblDescripcionIngresar the lblDescripcionIngresar to set
     */
    public void setLblDescripcionIngresar(JLabel lblDescripcionIngresar) {
        this.lblDescripcionIngresar = lblDescripcionIngresar;
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
     * @return the txtNombreIngresar
     */
    public JTextField getTxtNombreIngresar() {
        return txtNombreIngresar;
    }

    /**
     * @param txtNombreIngresar the txtNombreIngresar to set
     */
    public void setTxtNombreIngresar(JTextField txtNombreIngresar) {
        this.txtNombreIngresar = txtNombreIngresar;
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
     * @return the txtDescripcionIngresar
     */
    public JTextArea getTxtDescripcionIngresar() {
        return txtDescripcionIngresar;
    }

    /**
     * @param txtDescripcionIngresar the txtDescripcionIngresar to set
     */
    public void setTxtDescripcionIngresar(JTextArea txtDescripcionIngresar) {
        this.txtDescripcionIngresar = txtDescripcionIngresar;
    }

    /**
     * @return the scrollPaneTxtDescripcionIngresar
     */
    public JScrollPane getScrollPaneTxtDescripcionIngresar() {
        return scrollPaneTxtDescripcionIngresar;
    }

    /**
     * @param scrollPaneTxtDescripcionIngresar the
     * scrollPaneTxtDescripcionIngresar to set
     */
    public void setScrollPaneTxtDescripcionIngresar(JScrollPane scrollPaneTxtDescripcionIngresar) {
        this.scrollPaneTxtDescripcionIngresar = scrollPaneTxtDescripcionIngresar;
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
}
