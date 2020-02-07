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

import pizzeriatimbo_mvc.modelo.TamañoProductoFormModelo;
import pizzeriatimbo_mvc.vista.TamañoProductoFormVista;

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
public class TamañoProductoFormControlador {

    private final TamañoProductoFormModelo modelo;
    private final TamañoProductoFormVista vista;

    public TamañoProductoFormControlador(TamañoProductoFormModelo tamañoProductoFormModelo, TamañoProductoFormVista tamañoProductoFormVista) {
        this.modelo = tamañoProductoFormModelo;
        this.vista = tamañoProductoFormVista;
        this.vista.btnRefreshIngresarLeerSetActionListener(new ActionListenerBtnRefreshIngresar());
        this.vista.btnIngresarSetActionListener(new ActionListenerBtnIngresar());
        this.vista.btnLimpiarIngresarSetActionListener(new ActionListenerBtnLimpiarIngresar());
        this.vista.txtFilterLeerSetDocumentListener(new DocumentListenerTxtFilterLeer());
        this.vista.btnRefreshLeerSetActionListener(new ActionListenerBtnRefreshLeer());
        this.vista.btnModificarLeerSetActionListener(new ActionListenerBtnModificarLeer());
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
            String nombre_tamaño, nombre_producto;
            int id_tamaño, id_producto;
            final String sql1 = "select id_tamaño,nombre_tamaño from tamaño where valid=?";
            final String sql2 = "select id_producto,nombre_producto from producto where valid=?";
            try {
                vista.getCbxTamañoIngresar().removeAllItems();
                modelo.getMySQL1().connect();
                modelo.getMySQL1().setPreparedStatement(modelo.getMySQL1().getConnection().prepareStatement(sql1));
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
        }

    }

    class ActionListenerBtnIngresar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            final String sql = "insert into tamaño_producto(id_producto,id_tamaño,precio_producto) values(?,?,?)";
            try {
                modelo.getMySQL1().connect();
                modelo.getMySQL1().setPreparedStatement(modelo.getMySQL1().getConnection().prepareStatement(sql));
                TamañoProductoFormModelo.Tamaño id_tamaño = (TamañoProductoFormModelo.Tamaño) vista.getCbxTamañoIngresar().getSelectedItem();
                TamañoProductoFormModelo.Producto id_producto = (TamañoProductoFormModelo.Producto) vista.getCbxProductoIngresar().getSelectedItem();
                modelo.getMySQL1().getPreparedStatement().setInt(1, id_producto.getID());
                modelo.getMySQL1().getPreparedStatement().setInt(2, id_tamaño.getID());
                modelo.getMySQL1().getPreparedStatement().setInt(3, (int) vista.getSpinnerPrecioProductoIngresar().getValue());
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
            vista.getCbxTamañoIngresar().setSelectedItem(null);
            vista.getCbxProductoIngresar().setSelectedItem(null);
            vista.getSpinnerPrecioProductoIngresar().setValue(0);
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
            String columnName, nombre_tamaño, nombre_producto;
            Timestamp last_update;
            int id_tamaño_producto, id_tamaño, id_producto, precio_producto;
            final String sql1 = "select id_producto,nombre_producto from producto where valid=?";
            final String sql2 = "select id_tamaño,nombre_tamaño from tamaño where valid=?";
            final String sql3 = "select * from tamaño_producto where valid=?";
            try {
                vista.getCbxProductoLeer().removeAllItems();
                modelo.getMySQL1().connect();
                modelo.getMySQL1().setPreparedStatement(modelo.getMySQL1().getConnection().prepareStatement(sql1));
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
                modelo.getMySQL1().setPreparedStatement(modelo.getMySQL1().getConnection().prepareStatement(sql2));
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
                modelo.getMySQL1().setPreparedStatement(modelo.getMySQL1().getConnection().prepareStatement(sql3));
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
                    id_tamaño_producto = modelo.getMySQL1().getResultSet().getInt("id_tamaño_producto");
                    id_producto = modelo.getMySQL1().getResultSet().getInt("id_producto");
                    id_tamaño = modelo.getMySQL1().getResultSet().getInt("id_tamaño");
                    nombre_producto = modelo.searchFKStringProducto(id_producto);
                    nombre_tamaño = modelo.searchFKStringTamaño(id_tamaño);
                    precio_producto = modelo.getMySQL1().getResultSet().getInt("precio_producto");
                    last_update=modelo.getMySQL1().getResultSet().getTimestamp("last_update");
                    vista.getTableModelLeer().addRow(new Object[]{id_tamaño_producto, nombre_producto, nombre_tamaño, precio_producto,last_update.toLocalDateTime()});
                }
                vista.cbxProductoLeerSetDefaultCellEditor(vista.getCbxProductoLeer());
                vista.cbxProductoLeerSetTableCellRenderer(new TableCellRendererCbxProductoLeer());
                vista.cbxTamañoLeerSetDefaultCellEditor(vista.getCbxTamañoLeer());
                vista.cbxTamañoLeerSetTableCellRenderer(new TableCellRendererCbxTamañoLeer());
                vista.spinnerPrecioProductoLeerSetTableCellEditor(new TableCellEditorSpinnerPrecioProductoLeer());
                vista.spinnerPrecioProductoLeerSetTableCellRenderer(new TableCellRendererSpinnerPrecioProductoLeer());
                modelo.getMySQL1().getResultSet().close();
                modelo.getMySQL1().getPreparedStatement().close();
                modelo.getMySQL1().disconnect();
            } catch (SQLException ex) {
                ex.getStackTrace();
            }
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

    class TableCellEditorSpinnerPrecioProductoLeer extends AbstractCellEditor implements TableCellEditor {

        @Override
        public Object getCellEditorValue() {
            return vista.getSpinnerPrecioProductoLeer().getValue();
        }

        @Override
        public Component getTableCellEditorComponent(JTable jtable, Object value, boolean bln, int i, int i1) {
            vista.getSpinnerPrecioProductoLeer().setValue(value);
            return vista.getSpinnerPrecioProductoLeer();
        }

    }

    class TableCellRendererSpinnerPrecioProductoLeer implements TableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable jtable, Object value, boolean bln, boolean bln1, int i, int i1) {
            JSpinner spinner = new JSpinner();
            spinner.setValue(value);
            return spinner;
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
                    int id_tamaño_producto = (int) vista.getTableLeer().getValueAt(row, 0);
                    final String sql = "update tamaño_producto set id_producto=?,id_tamaño=?,precio_producto=? where id_tamaño_producto=? and valid=?";
                    try {
                        modelo.getMySQL1().connect();
                        modelo.getMySQL1().setPreparedStatement(modelo.getMySQL1().getConnection().prepareStatement(sql));
                        String nombre_producto = (vista.getTableLeer().getValueAt(rowIndexToModel, 1)).toString();
                        String nombre_tamaño = (vista.getTableLeer().getValueAt(rowIndexToModel, 2)).toString();
                        int id_producto = modelo.searchFKIntProducto(nombre_producto);
                        int id_tamaño = modelo.searchFKIntTamaño(nombre_tamaño);
                        modelo.getMySQL1().getPreparedStatement().setInt(1, id_producto);
                        modelo.getMySQL1().getPreparedStatement().setInt(2, id_tamaño);
                        modelo.getMySQL1().getPreparedStatement().setInt(3, (int) vista.getTableModelLeer().getValueAt(rowIndexToModel, 3));
                        modelo.getMySQL1().getPreparedStatement().setInt(4, id_tamaño_producto);
                        modelo.getMySQL1().getPreparedStatement().setBoolean(5, true);
                        int value = modelo.getMySQL1().getPreparedStatement().executeUpdate();
                        if (value == 1) {
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
                    int id_tamaño_producto = (int) vista.getTableLeer().getValueAt(row, 0);
                    final String sql = "update tamaño_producto set valid=? where id_tamaño_producto=?";
                    try {
                        modelo.getMySQL1().connect();
                        modelo.getMySQL1().setPreparedStatement(modelo.getMySQL1().getConnection().prepareStatement(sql));
                        modelo.getMySQL1().getPreparedStatement().setBoolean(1, false);
                        modelo.getMySQL1().getPreparedStatement().setInt(2, id_tamaño_producto);
                        int value = modelo.getMySQL1().getPreparedStatement().executeUpdate();
                        if (value == 1) {
                            vista.getTableModelLeer().removeRow(rowIndexToModel);
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
}
