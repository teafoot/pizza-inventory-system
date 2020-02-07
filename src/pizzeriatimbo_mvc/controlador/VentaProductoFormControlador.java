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
package pizzeriatimbo_mvc.controlador;

import pizzeriatimbo_mvc.modelo.VentaProductoFormModelo;
import pizzeriatimbo_mvc.vista.VentaProductoFormVista;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 *
 * @author davidchenlo
 */
public class VentaProductoFormControlador {

    private final VentaProductoFormModelo modelo;
    private final VentaProductoFormVista vista;
    private boolean hayDatos;
    private int costoTotal;
    private int costoTotalSumado;

    public VentaProductoFormControlador(VentaProductoFormModelo modelo, VentaProductoFormVista vista) {
        this.modelo = modelo;
        this.vista = vista;
        this.vista.btnRefreshIngresarLeerSetActionListener(new ActionListenerBtnRefreshIngresar());
        this.vista.btnIngresarSetActionListener(new ActionListenerBtnIngresar());
        this.vista.btnLimpiarIngresarSetActionListener(new ActionListenerBtnLimpiarIngresar());
        this.vista.txtFilterLeerSetDocumentListener(new DocumentListenerTxtFilterLeer());
        this.vista.btnRefreshLeerSetActionListener(new ActionListenerBtnRefreshLeer());
        this.vista.btnModificarLeerSetActionListener(new ActionListenerBtnModificarLeer());
//        this.vista.btnCheckoutLeerSetActionListener(new ActionListenerBtnCheckoutLeer());
        this.vista.btnEliminarLeerSetActionListener(new ActionListenerBtnEliminarLeer());
    }

    private void filterSearch() {
        String text = vista.getTxtFilterLeer().getText();
        if (text.trim().length() == 0) {
            vista.getTableRowSorterLeer().setRowFilter(null);
        } else {
            vista.getTableRowSorterLeer().setRowFilter(RowFilter.regexFilter("(?i)" + text));
        }
    }

    class ActionListenerBtnRefreshIngresar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            String nombre_completo_empleado, nombre_tamaño, nombre_producto;
            int id_empleado, id_tamaño, id_producto;
            final String sql1 = "select id_empleado,nombre_completo_empleado from empleado where valid=?";
            final String sql2 = "select id_producto,nombre_producto from producto where valid=?";
            final String sql3 = "select id_tamaño,nombre_tamaño from tamaño where valid=?";
            try {
                vista.getCbxEmpleadoIngresar().removeAllItems();
                modelo.getMySQL1().connect();
                modelo.getMySQL1().setPreparedStatement(modelo.getMySQL1().getConnection().prepareStatement(sql1));
                modelo.getMySQL1().getPreparedStatement().setBoolean(1, true);
                modelo.getMySQL1().setResultSet(modelo.getMySQL1().getPreparedStatement().executeQuery());
                while (modelo.getMySQL1().getResultSet().next()) {
                    id_empleado = modelo.getMySQL1().getResultSet().getInt("id_empleado");
                    nombre_completo_empleado = modelo.getMySQL1().getResultSet().getString("nombre_completo_empleado");
                    vista.getCbxEmpleadoIngresar().addItem(modelo.new Empleado(id_empleado, nombre_completo_empleado));
                }
                modelo.getMySQL1().getResultSet().close();
                modelo.getMySQL1().getPreparedStatement().close();
                modelo.getMySQL1().disconnect();
            } catch (SQLException ex) {
                ex.getStackTrace();
            }
            try {
                vista.getCbxProductoIngresar().removeAllItems();
                modelo.getMySQL1().connect();
                modelo.getMySQL1().setPreparedStatement(modelo.getMySQL1().getConnection().prepareStatement(sql2));
                modelo.getMySQL1().getPreparedStatement().setBoolean(1, true);
                modelo.getMySQL1().setResultSet(modelo.getMySQL1().getPreparedStatement().executeQuery());
                while (modelo.getMySQL1().getResultSet().next()) {
                    id_producto = modelo.getMySQL1().getResultSet().getInt("id_producto");
                    nombre_producto = modelo.getMySQL1().getResultSet().getString("nombre_producto");
                    vista.getCbxProductoIngresar().addItem(modelo.new Producto(id_producto, nombre_producto));
                }
                modelo.getMySQL1().getResultSet().close();
                modelo.getMySQL1().getPreparedStatement().close();
                modelo.getMySQL1().disconnect();
            } catch (SQLException ex) {
                ex.getStackTrace();
            }
            try {
                vista.getCbxTamañoIngresar().removeAllItems();
                modelo.getMySQL1().connect();
                modelo.getMySQL1().setPreparedStatement(modelo.getMySQL1().getConnection().prepareStatement(sql3));
                modelo.getMySQL1().getPreparedStatement().setBoolean(1, true);
                modelo.getMySQL1().setResultSet(modelo.getMySQL1().getPreparedStatement().executeQuery());
                while (modelo.getMySQL1().getResultSet().next()) {
                    id_tamaño = modelo.getMySQL1().getResultSet().getInt("id_tamaño");
                    nombre_tamaño = modelo.getMySQL1().getResultSet().getString("nombre_tamaño");
                    vista.getCbxTamañoIngresar().addItem(modelo.new Tamaño(id_tamaño, nombre_tamaño));
                }
                modelo.getMySQL1().getResultSet().close();
                modelo.getMySQL1().getPreparedStatement().close();
                modelo.getMySQL1().disconnect();
            } catch (SQLException ex) {
                ex.getStackTrace();
            }

        }

    }

    class ActionListenerBtnIngresar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            VentaProductoFormModelo.Empleado id_empleado = (VentaProductoFormModelo.Empleado) vista.getCbxEmpleadoIngresar().getSelectedItem();
            int cantidad_producto = (int) vista.getSpinnerCantidadProductoIngresar().getValue();
            VentaProductoFormModelo.Producto id_producto = (VentaProductoFormModelo.Producto) vista.getCbxProductoIngresar().getSelectedItem();
            VentaProductoFormModelo.Tamaño id_tamaño = (VentaProductoFormModelo.Tamaño) vista.getCbxTamañoIngresar().getSelectedItem();
            setCostoTotal(modelo.calcularCostoTotal(cantidad_producto, id_producto.getID(), id_tamaño.getID()));
            final String sql = "insert into venta_producto(id_empleado,cantidad_producto,id_producto,id_tamaño,costo_total) values(?,?,?,?,?)";
            try {
                modelo.getMySQL1().connect();
                modelo.getMySQL1().setPreparedStatement(modelo.getMySQL1().getConnection().prepareStatement(sql));
                modelo.getMySQL1().getPreparedStatement().setInt(1, id_empleado.getID());
                modelo.getMySQL1().getPreparedStatement().setInt(2, cantidad_producto);
                modelo.getMySQL1().getPreparedStatement().setInt(3, id_producto.getID());
                modelo.getMySQL1().getPreparedStatement().setInt(4, id_tamaño.getID());
                modelo.getMySQL1().getPreparedStatement().setInt(5, getCostoTotal());
                int value = modelo.getMySQL1().getPreparedStatement().executeUpdate();
                if (value == 1) {
                    vista.mostrarMensaje("Registro Ingresado");
                }
                modelo.getMySQL1().getPreparedStatement().close();
                modelo.getMySQL1().disconnect();
            } catch (SQLException | NullPointerException ex) {
                ex.getStackTrace();
            }
        }
    }

    class ActionListenerBtnLimpiarIngresar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            vista.getCbxEmpleadoIngresar().setSelectedItem(null);
            vista.getCbxProductoIngresar().setSelectedItem(null);
            vista.getCbxTamañoIngresar().setSelectedItem(null);
            vista.getSpinnerCantidadProductoIngresar().setValue(0);
        }

    }

    class DocumentListenerTxtFilterLeer implements DocumentListener {

        @Override
        public void insertUpdate(DocumentEvent de) {
            filterSearch();
        }

        @Override
        public void removeUpdate(DocumentEvent de) {
            filterSearch();
        }

        @Override
        public void changedUpdate(DocumentEvent de) {
        }

    }

    class ActionListenerBtnRefreshLeer implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            costoTotalSumado = 0;
            setHayDatos(false);
            String columnName, nombre_completo_empleado, nombre_tamaño, nombre_producto;
            Timestamp last_update = null;
            int id_venta_producto, id_empleado, id_tamaño, id_producto, cantidad_producto, costo_total;
            final String sql1 = "select id_empleado,nombre_completo_empleado from empleado where valid=?";
            final String sql2 = "select id_producto,nombre_producto from producto where valid=?";
            final String sql3 = "select id_tamaño,nombre_tamaño from tamaño where valid=?";
            final String sql4 = "select * from venta_producto where valid=?";
            try {
                vista.getCbxEmpleadoLeer().removeAllItems();
                modelo.getMySQL1().connect();
                modelo.getMySQL1().setPreparedStatement(modelo.getMySQL1().getConnection().prepareStatement(sql1));
                modelo.getMySQL1().getPreparedStatement().setBoolean(1, true);
                modelo.getMySQL1().setResultSet(modelo.getMySQL1().getPreparedStatement().executeQuery());
                while (modelo.getMySQL1().getResultSet().next()) {
                    id_empleado = modelo.getMySQL1().getResultSet().getInt("id_empleado");
                    nombre_completo_empleado = modelo.getMySQL1().getResultSet().getString("nombre_completo_empleado");
                    vista.getCbxEmpleadoLeer().addItem(modelo.new Empleado(id_empleado, nombre_completo_empleado));
                }
                modelo.getMySQL1().getResultSet().close();
                modelo.getMySQL1().getPreparedStatement().close();
                modelo.getMySQL1().disconnect();
            } catch (SQLException ex) {
                ex.getStackTrace();
            }
            try {
                vista.getCbxProductoLeer().removeAllItems();
                modelo.getMySQL1().connect();
                modelo.getMySQL1().setPreparedStatement(modelo.getMySQL1().getConnection().prepareStatement(sql2));
                modelo.getMySQL1().getPreparedStatement().setBoolean(1, true);
                modelo.getMySQL1().setResultSet(modelo.getMySQL1().getPreparedStatement().executeQuery());
                while (modelo.getMySQL1().getResultSet().next()) {
                    id_producto = modelo.getMySQL1().getResultSet().getInt("id_producto");
                    nombre_producto = modelo.getMySQL1().getResultSet().getString("nombre_producto");
                    vista.getCbxProductoLeer().addItem(modelo.new Producto(id_producto, nombre_producto));
                }
                modelo.getMySQL1().getResultSet().close();
                modelo.getMySQL1().getPreparedStatement().close();
                modelo.getMySQL1().disconnect();
            } catch (SQLException ex) {
                ex.getStackTrace();
            }
            try {
                vista.getCbxTamañoLeer().removeAllItems();
                modelo.getMySQL1().connect();
                modelo.getMySQL1().setPreparedStatement(modelo.getMySQL1().getConnection().prepareStatement(sql3));
                modelo.getMySQL1().getPreparedStatement().setBoolean(1, true);
                modelo.getMySQL1().setResultSet(modelo.getMySQL1().getPreparedStatement().executeQuery());
                while (modelo.getMySQL1().getResultSet().next()) {
                    id_tamaño = modelo.getMySQL1().getResultSet().getInt("id_tamaño");
                    nombre_tamaño = modelo.getMySQL1().getResultSet().getString("nombre_tamaño");
                    vista.getCbxTamañoLeer().addItem(modelo.new Tamaño(id_tamaño, nombre_tamaño));
                }
                modelo.getMySQL1().getResultSet().close();
                modelo.getMySQL1().getPreparedStatement().close();
                modelo.getMySQL1().disconnect();
            } catch (SQLException ex) {
                ex.getStackTrace();
            }
            try {
                modelo.getMySQL1().connect();
                modelo.getMySQL1().setPreparedStatement(modelo.getMySQL1().getConnection().prepareStatement(sql4));
                modelo.getMySQL1().getPreparedStatement().setBoolean(1, true);
                modelo.getMySQL1().setResultSet(modelo.getMySQL1().getPreparedStatement().executeQuery());
                modelo.getMySQL1().setResultSetMetaData(modelo.getMySQL1().getResultSet().getMetaData());
                vista.getTableModelLeer().setColumnCount(0);
                vista.getTableModelLeer().setRowCount(0);
                for (int i = 1; i < modelo.getMySQL1().getResultSetMetaData().getColumnCount(); i++) {
                    columnName = modelo.getMySQL1().getResultSetMetaData().getColumnName(i);
                    vista.getTableModelLeer().addColumn(columnName);
                }
                while (modelo.getMySQL1().getResultSet().next()) {
                    setHayDatos(true);
                    id_venta_producto = modelo.getMySQL1().getResultSet().getInt("id_venta_producto");
                    id_empleado = modelo.getMySQL1().getResultSet().getInt("id_empleado");
                    id_producto = modelo.getMySQL1().getResultSet().getInt("id_producto");
                    id_tamaño = modelo.getMySQL1().getResultSet().getInt("id_tamaño");
                    nombre_completo_empleado = modelo.searchFKStringEmpleado(id_empleado);
                    cantidad_producto = modelo.getMySQL1().getResultSet().getInt("cantidad_producto");
                    nombre_producto = modelo.searchFKStringProducto(id_producto);
                    nombre_tamaño = modelo.searchFKStringTamaño(id_tamaño);
                    costo_total = modelo.getMySQL1().getResultSet().getInt("costo_total");
                    setCostoTotalSumado(getCostoTotalSumado() + costo_total);
                    last_update = modelo.getMySQL1().getResultSet().getTimestamp("last_update");
                    vista.getTableModelLeer().addRow(new Object[]{id_venta_producto, nombre_completo_empleado, cantidad_producto, nombre_producto, nombre_tamaño, costo_total, last_update.toLocalDateTime()});
                }
                if (isHayDatos() == true) {
                    vista.getTableModelLeer().addRow(new Object[]{null, null, null, null, "TOTAL: ", getCostoTotalSumado(), last_update.toLocalDateTime()});
                    vista.setUltimaFila(vista.getTableModelLeer().getRowCount() - 1);
                }
                vista.cbxEmpleadoLeerSetDefaultCellEditor(vista.getCbxEmpleadoLeer());
                vista.spinnerCantidadProductoLeerSetTableCellEditor(new TableCellEditorSpinnerCantidadProductoLeer());
                vista.cbxProductoLeerSetDefaultCellEditor(vista.getCbxProductoLeer());
                vista.cbxTamañoLeerSetDefaultCellEditor(vista.getCbxTamañoLeer());
                vista.cbxEmpleadoLeerSetTableCellRenderer(new TableCellRendererCbxEmpleadoLeer());
                vista.spinnerCantidadProductoLeerSetTableCellRenderer(new TableCellRendererSpinnerCantidadProductoLeer());
                vista.cbxProductoLeerSetTableCellRenderer(new TableCellRendererCbxProductoLeer());
                vista.cbxTamañoLeerSetTableCellRenderer(new TableCellRendererCbxTamañoLeer());
                modelo.getMySQL1().getResultSet().close();
                modelo.getMySQL1().getPreparedStatement().close();
                modelo.getMySQL1().disconnect();
            } catch (SQLException ex) {
                ex.getStackTrace();
            }
        }

    }

    class TableCellEditorSpinnerCantidadProductoLeer extends AbstractCellEditor implements TableCellEditor {

        @Override
        public Object getCellEditorValue() {
            return vista.getSpinnerCantidadProductoLeer().getValue();
        }

        @Override
        public Component getTableCellEditorComponent(JTable jtable, Object value, boolean bln, int i, int i1) {
            vista.getSpinnerCantidadProductoLeer().setValue(value);
            return vista.getSpinnerCantidadProductoLeer();
        }

    }

    class TableCellRendererSpinnerCantidadProductoLeer implements TableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable jtable, Object value, boolean bln, boolean bln1, int i, int i1) {
            JSpinner spinner = new JSpinner();
            spinner.setValue(value);
            return spinner;
        }

    }

    class TableCellRendererCbxEmpleadoLeer implements TableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable jtable, Object value, boolean bln, boolean bln1, int i, int i1) {
            JComboBox comboBox = new JComboBox();
            comboBox.addItem(value);
            comboBox.setSelectedItem(value);
            return comboBox;
        }

    }

    class TableCellRendererCbxProductoLeer implements TableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable jtable, Object value, boolean bln, boolean bln1, int i, int i1) {
            JComboBox comboBox = new JComboBox();
            comboBox.addItem(value);
            comboBox.setSelectedItem(value);
            return comboBox;
        }

    }

    class TableCellRendererCbxTamañoLeer implements TableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable jtable, Object value, boolean bln, boolean bln1, int i, int i1) {
            JComboBox comboBox = new JComboBox();
            comboBox.addItem(value);
            comboBox.setSelectedItem(value);
            return comboBox;
        }

    }

    class ActionListenerBtnModificarLeer implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            int row = vista.getTableLeer().getSelectedRow();
            if (row < 0) {
                vista.mostrarMensaje("Debe seleccionar una fila de la tabla");
            } else {
                int confirmar = vista.confirmarMensaje("Esta seguro que desea Modificar el registro?");
                if (JOptionPane.OK_OPTION == confirmar) {
                    int rowIndexToModel = vista.getTableLeer().convertRowIndexToModel(row);
                    int id_venta_producto = (int) vista.getTableLeer().getValueAt(row, 0);
                    final String sql = "update venta_producto set id_empleado=?,cantidad_producto=?,id_producto=?,id_tamaño=?,costo_total=? where id_venta_producto=? and valid=?";
                    try {
                        modelo.getMySQL1().connect();
                        modelo.getMySQL1().setPreparedStatement(modelo.getMySQL1().getConnection().prepareStatement(sql));
                        String nombre_completo_empleado = (vista.getTableLeer().getValueAt(rowIndexToModel, 1)).toString();
                        String nombre_producto = (vista.getTableLeer().getValueAt(rowIndexToModel, 3)).toString();
                        String nombre_tamaño = (vista.getTableLeer().getValueAt(rowIndexToModel, 4)).toString();
                        int id_empleado = modelo.searchFKIntEmpleado(nombre_completo_empleado);
                        int cantidad_producto = (int) vista.getTableModelLeer().getValueAt(rowIndexToModel, 2);
                        int id_producto = modelo.searchFKIntProducto(nombre_producto);
                        int id_tamaño = modelo.searchFKIntTamaño(nombre_tamaño);
                        setCostoTotal(modelo.calcularCostoTotal(cantidad_producto, id_producto, id_tamaño));
                        modelo.getMySQL1().getPreparedStatement().setInt(1, id_empleado);
                        modelo.getMySQL1().getPreparedStatement().setInt(2, cantidad_producto);
                        modelo.getMySQL1().getPreparedStatement().setInt(3, id_producto);
                        modelo.getMySQL1().getPreparedStatement().setInt(4, id_tamaño);
                        modelo.getMySQL1().getPreparedStatement().setInt(5, getCostoTotal());
                        modelo.getMySQL1().getPreparedStatement().setInt(6, id_venta_producto);
                        modelo.getMySQL1().getPreparedStatement().setBoolean(7, true);
                        int value = modelo.getMySQL1().getPreparedStatement().executeUpdate();
                        if (value == 1) {
                            ActionListenerBtnRefreshLeer actionListenerBtnRefreshLeer = new ActionListenerBtnRefreshLeer();
                            actionListenerBtnRefreshLeer.actionPerformed(ae);
                            vista.mostrarMensaje("Registro Modificado");
                        }
                        modelo.getMySQL1().getPreparedStatement().close();
                        modelo.getMySQL1().disconnect();
                    } catch (SQLException | NullPointerException ex) {
                        ex.getStackTrace();
                    }
                }
            }
        }
    }

//    class ActionListenerBtnCheckoutLeer implements ActionListener {
//
//        @Override
//        public void actionPerformed(ActionEvent ae) {
//            if (isHayDatos()) {
//                int confirmar = vista.confirmarMensaje("Esta seguro que desea Checkout los registros?");
//                if (JOptionPane.OK_OPTION == confirmar) {
//                    final String sql = "insert into venta(id_empleado,cantidad_producto,id_producto,id_tamaño,costo_total) values(?,?,?,?,?)";
//                    try {
//                        modelo.getMySQL1().connect();
//                        modelo.getMySQL1().setPreparedStatement(modelo.getMySQL1().getConnection().prepareStatement(sql));
//                        int nRow = vista.getTableModelLeer().getRowCount(), nCol = vista.getTableModelLeer().getColumnCount();
//                        Object[][] tableData = new Object[nRow][nCol];
//                        for (int i = 0; i < vista.getTableModelLeer().getRowCount(); i++) {
//                            for (int j = 0; j < vista.getTableModelLeer().getColumnCount(); j++) {
//                                tableData[i][j] = vista.getTableModelLeer().getValueAt(i, j);
//                            }
//                        }
//                        int[] value = new int[vista.getTableModelLeer().getRowCount() - 1];
//                        for (int i = 0; i < vista.getTableModelLeer().getRowCount(); i++) {
//                            for (int j = 0; j < vista.getTableModelLeer().getColumnCount(); j++) {
//                                modelo.getMySQL1().getPreparedStatement().setObject(j + 1, tableData[i][j]);
//                            }
//                            value[i] = modelo.getMySQL1().getPreparedStatement().executeUpdate();
//                        }
//                        int valueCount = 0;
//                        for (int i = 0; i < vista.getTableModelLeer().getRowCount(); i++) {
//                            if (value[i] == 1) {
//                                valueCount++;
//                            }
//                        }
//                        if (valueCount == vista.getTableModelLeer().getRowCount() - 1) {
//                            vista.mostrarMensaje("Registros fueron Checkedout!");
//                            vista.getTableModelLeer().setColumnCount(0);
//                            vista.getTableModelLeer().setRowCount(0);
//                        }
//                        modelo.getMySQL1().getPreparedStatement().close();
//                        modelo.getMySQL1().disconnect();
//                    } catch (SQLException | NullPointerException ex) {
//                        ex.getStackTrace();
//                    }
//                }
//            }
//        }
//    }
    class ActionListenerBtnEliminarLeer implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            int row = vista.getTableLeer().getSelectedRow();
            if (row < 0) {
                vista.mostrarMensaje("Debe seleccionar una fila de la tabla");
            } else {
                int confirmar = vista.confirmarMensaje("Esta seguro que desea Eliminar el registro?");
                if (JOptionPane.OK_OPTION == confirmar) {
                    int rowIndexToModel = vista.getTableLeer().convertRowIndexToModel(row);
                    int id_venta_producto = (int) vista.getTableLeer().getValueAt(row, 0);
                    final String sql = "update venta_producto set valid=? where id_venta_producto=?";
                    try {
                        modelo.getMySQL1().connect();
                        modelo.getMySQL1().setPreparedStatement(modelo.getMySQL1().getConnection().prepareStatement(sql));
                        modelo.getMySQL1().getPreparedStatement().setBoolean(1, false);
                        modelo.getMySQL1().getPreparedStatement().setInt(2, id_venta_producto);
                        int value = modelo.getMySQL1().getPreparedStatement().executeUpdate();
                        if (value == 1) {
                            ActionListenerBtnRefreshLeer actionListenerBtnRefreshLeer = new ActionListenerBtnRefreshLeer();
                            vista.getTableModelLeer().removeRow(rowIndexToModel);
                            actionListenerBtnRefreshLeer.actionPerformed(ae);
                            vista.mostrarMensaje("Registro Eliminado");
                        }
                        modelo.getMySQL1().getPreparedStatement().close();
                        modelo.getMySQL1().disconnect();
                    } catch (SQLException ex) {
                        ex.getStackTrace();
                    }
                }
            }
        }
    }

    /**
     * @return the hayDatos
     */
    public boolean isHayDatos() {
        return hayDatos;
    }

    /**
     * @param hayDatos the hayDatos to set
     */
    public void setHayDatos(boolean hayDatos) {
        this.hayDatos = hayDatos;
    }

    /**
     * @return the costoTotalSumado
     */
    public int getCostoTotalSumado() {
        return costoTotalSumado;
    }

    /**
     * @param costoTotalSumado the costoTotalSumado to set
     */
    public void setCostoTotalSumado(int costoTotalSumado) {
        this.costoTotalSumado = costoTotalSumado;
    }

    /**
     * @return the costoTotal
     */
    public int getCostoTotal() {
        return costoTotal;
    }

    /**
     * @param costoTotal the costoTotal to set
     */
    public void setCostoTotal(int costoTotal) {
        this.costoTotal = costoTotal;
    }
}
