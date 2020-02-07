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

import pizzeriatimbo_mvc.modelo.IngredienteProductoFormModelo;
import pizzeriatimbo_mvc.vista.IngredienteProductoFormVista;

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
public class IngredienteProductoFormControlador {

    private final IngredienteProductoFormModelo modelo;
    private final IngredienteProductoFormVista vista;

    public IngredienteProductoFormControlador(IngredienteProductoFormModelo modelo, IngredienteProductoFormVista vista) {
        this.modelo = modelo;
        this.vista = vista;
        this.vista.btnRefreshIngresarSetActionListener(new ActionListenerBtnRefreshIngresar());
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
            String nombre_unidad, nombre_ingrediente, nombre_tamaño, nombre_producto;
            int id_unidad, id_ingrediente, id_tamaño, id_producto;
            final String sql1 = "select id_unidad,nombre_unidad from unidad where valid=?";
            final String sql2 = "select id_ingrediente,nombre_ingrediente from ingrediente where valid=?";
            final String sql3 = "select id_producto,nombre_producto from producto where valid=?";
            final String sql4 = "select id_tamaño,nombre_tamaño from tamaño where valid=?";
            try {
                vista.getCbxUnidadIngresar().removeAllItems();
                modelo.getMySQL1().connect();
                modelo.getMySQL1().setPreparedStatement(modelo.getMySQL1().getConnection().prepareStatement(sql1));
                modelo.getMySQL1().getPreparedStatement().setBoolean(1, true);
                modelo.getMySQL1().setResultSet(modelo.getMySQL1().getPreparedStatement().executeQuery());
                while (modelo.getMySQL1().getResultSet().next()) {
                    id_unidad = modelo.getMySQL1().getResultSet().getInt("id_unidad");
                    nombre_unidad = modelo.getMySQL1().getResultSet().getString("nombre_unidad");
                    vista.getCbxUnidadIngresar().addItem(modelo.new Unidad(id_unidad, nombre_unidad));
                }
                modelo.getMySQL1().getResultSet().close();
                modelo.getMySQL1().getPreparedStatement().close();
                modelo.getMySQL1().disconnect();
            } catch (SQLException ex) {
                ex.getStackTrace();
            }
            try {
                vista.getCbxIngredienteIngresar().removeAllItems();
                modelo.getMySQL1().connect();
                modelo.getMySQL1().setPreparedStatement(modelo.getMySQL1().getConnection().prepareStatement(sql2));
                modelo.getMySQL1().getPreparedStatement().setBoolean(1, true);
                modelo.getMySQL1().setResultSet(modelo.getMySQL1().getPreparedStatement().executeQuery());
                while (modelo.getMySQL1().getResultSet().next()) {
                    id_ingrediente = modelo.getMySQL1().getResultSet().getInt("id_ingrediente");
                    nombre_ingrediente = modelo.getMySQL1().getResultSet().getString("nombre_ingrediente");
                    vista.getCbxIngredienteIngresar().addItem(modelo.new Ingrediente(id_ingrediente, nombre_ingrediente));
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
                modelo.getMySQL1().setPreparedStatement(modelo.getMySQL1().getConnection().prepareStatement(sql3));
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
                modelo.getMySQL1().setPreparedStatement(modelo.getMySQL1().getConnection().prepareStatement(sql4));
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
            final String sql = "insert into ingrediente_producto(cantidad_ingrediente_producto,id_unidad,id_ingrediente,id_producto,id_tamaño) values(?,?,?,?,?)";
            try {
                modelo.getMySQL1().connect();
                modelo.getMySQL1().setPreparedStatement(modelo.getMySQL1().getConnection().prepareStatement(sql));
                IngredienteProductoFormModelo.Unidad id_unidad = (IngredienteProductoFormModelo.Unidad) vista.getCbxUnidadIngresar().getSelectedItem();
                IngredienteProductoFormModelo.Ingrediente id_ingrediente = (IngredienteProductoFormModelo.Ingrediente) vista.getCbxIngredienteIngresar().getSelectedItem();
                IngredienteProductoFormModelo.Producto id_producto = (IngredienteProductoFormModelo.Producto) vista.getCbxProductoIngresar().getSelectedItem();
                IngredienteProductoFormModelo.Tamaño id_tamaño = (IngredienteProductoFormModelo.Tamaño) vista.getCbxTamañoIngresar().getSelectedItem();
                modelo.getMySQL1().getPreparedStatement().setInt(1, (int) vista.getSpinnerCantidadIngredienteProductoIngresar().getValue());
                modelo.getMySQL1().getPreparedStatement().setInt(2, id_unidad.getID());
                modelo.getMySQL1().getPreparedStatement().setInt(3, id_ingrediente.getID());
                modelo.getMySQL1().getPreparedStatement().setInt(4, id_producto.getID());
                modelo.getMySQL1().getPreparedStatement().setInt(5, id_tamaño.getID());
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
            vista.getSpinnerCantidadIngredienteProductoIngresar().setValue(0);
            vista.getCbxUnidadIngresar().setSelectedItem(null);
            vista.getCbxIngredienteIngresar().setSelectedItem(null);
            vista.getCbxProductoIngresar().setSelectedItem(null);
            vista.getCbxTamañoIngresar().setSelectedItem(null);
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
            String columnName, nombre_unidad, nombre_ingrediente, nombre_tamaño, nombre_producto;
            Timestamp last_update;
            int id_ingrediente_producto, id_unidad, id_ingrediente, id_tamaño, id_producto, cantidad_ingrediente_producto;
            final String sql1 = "select id_unidad,nombre_unidad from unidad where valid=?";
            final String sql2 = "select id_ingrediente,nombre_ingrediente from ingrediente where valid=?";
            final String sql3 = "select id_producto,nombre_producto from producto where valid=?";
            final String sql4 = "select id_tamaño,nombre_tamaño from tamaño where valid=?";
            final String sql5 = "select * from ingrediente_producto where valid=?";
            try {
                vista.getCbxUnidadLeer().removeAllItems();
                modelo.getMySQL1().connect();
                modelo.getMySQL1().setPreparedStatement(modelo.getMySQL1().getConnection().prepareStatement(sql1));
                modelo.getMySQL1().getPreparedStatement().setBoolean(1, true);
                modelo.getMySQL1().setResultSet(modelo.getMySQL1().getPreparedStatement().executeQuery());
                while (modelo.getMySQL1().getResultSet().next()) {
                    id_unidad = modelo.getMySQL1().getResultSet().getInt("id_unidad");
                    nombre_unidad = modelo.getMySQL1().getResultSet().getString("nombre_unidad");
                    vista.getCbxUnidadLeer().addItem(modelo.new Unidad(id_unidad, nombre_unidad));
                }
                modelo.getMySQL1().getResultSet().close();
                modelo.getMySQL1().getPreparedStatement().close();
                modelo.getMySQL1().disconnect();
            } catch (SQLException ex) {
                ex.getStackTrace();
            }
            try {
                vista.getCbxIngredienteLeer().removeAllItems();
                modelo.getMySQL1().connect();
                modelo.getMySQL1().setPreparedStatement(modelo.getMySQL1().getConnection().prepareStatement(sql2));
                modelo.getMySQL1().getPreparedStatement().setBoolean(1, true);
                modelo.getMySQL1().setResultSet(modelo.getMySQL1().getPreparedStatement().executeQuery());
                while (modelo.getMySQL1().getResultSet().next()) {
                    id_ingrediente = modelo.getMySQL1().getResultSet().getInt("id_ingrediente");
                    nombre_ingrediente = modelo.getMySQL1().getResultSet().getString("nombre_ingrediente");
                    vista.getCbxIngredienteLeer().addItem(modelo.new Ingrediente(id_ingrediente, nombre_ingrediente));
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
                modelo.getMySQL1().setPreparedStatement(modelo.getMySQL1().getConnection().prepareStatement(sql3));
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
                modelo.getMySQL1().setPreparedStatement(modelo.getMySQL1().getConnection().prepareStatement(sql4));
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
                modelo.getMySQL1().setPreparedStatement(modelo.getMySQL1().getConnection().prepareStatement(sql5));
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
                    id_ingrediente_producto = modelo.getMySQL1().getResultSet().getInt("id_ingrediente_producto");
                    cantidad_ingrediente_producto = modelo.getMySQL1().getResultSet().getInt("cantidad_ingrediente_producto");
                    id_unidad = modelo.getMySQL1().getResultSet().getInt("id_unidad");
                    id_ingrediente = modelo.getMySQL1().getResultSet().getInt("id_ingrediente");
                    id_producto = modelo.getMySQL1().getResultSet().getInt("id_producto");
                    id_tamaño = modelo.getMySQL1().getResultSet().getInt("id_tamaño");
                    nombre_unidad = modelo.searchFKStringUnidad(id_unidad);
                    nombre_ingrediente = modelo.searchFKStringIngrediente(id_ingrediente);
                    nombre_producto = modelo.searchFKStringProducto(id_producto);
                    nombre_tamaño = modelo.searchFKStringTamaño(id_tamaño);
                    last_update=modelo.getMySQL1().getResultSet().getTimestamp("last_update");
                    vista.getTableModelLeer().addRow(new Object[]{id_ingrediente_producto, cantidad_ingrediente_producto, nombre_unidad, nombre_ingrediente, nombre_producto, nombre_tamaño,last_update.toLocalDateTime()});
                }
                vista.spinnerCantidadIngredienteProductoLeerSetTableCellEditor(new TableCellEditorSpinnerCantidadIngredienteProductoLeer());
                vista.cbxUnidadLeerSetDefaultCellEditor(vista.getCbxUnidadLeer());
                vista.cbxIngredienteLeerSetDefaultCellEditor(vista.getCbxIngredienteLeer());
                vista.cbxProductoLeerSetDefaultCellEditor(vista.getCbxProductoLeer());
                vista.cbxTamañoLeerSetDefaultCellEditor(vista.getCbxTamañoLeer());
                vista.spinnerCantidadIngredienteProductoLeerSetTableCellRenderer(new TableCellRendererSpinnerCantidadIngredienteProductoLeer());
                vista.cbxUnidadLeerSetTableCellRenderer(new TableCellRendererCbxUnidadLeer());
                vista.cbxIngredienteLeerSetTableCellRenderer(new TableCellRendererCbxIngredienteLeer());
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

    class TableCellEditorSpinnerCantidadIngredienteProductoLeer extends AbstractCellEditor implements TableCellEditor {

        @Override
        public Object getCellEditorValue() {
            return vista.getSpinnerCantidadIngredienteProductoLeer().getValue();
        }

        @Override
        public Component getTableCellEditorComponent(JTable jtable, Object value, boolean bln, int i, int i1) {
            vista.getSpinnerCantidadIngredienteProductoLeer().setValue(value);
            return vista.getSpinnerCantidadIngredienteProductoLeer();
        }

    }

    class TableCellRendererSpinnerCantidadIngredienteProductoLeer implements TableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable jtable, Object value, boolean bln, boolean bln1, int i, int i1) {
            JSpinner spinner = new JSpinner();
            spinner.setValue(value);
            return spinner;
        }

    }

    class TableCellRendererCbxUnidadLeer implements TableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable jtable, Object value, boolean bln, boolean bln1, int i, int i1) {
            JComboBox comboBox = new JComboBox();
            comboBox.addItem(value);
            comboBox.setSelectedItem(value);
            return comboBox;
        }

    }

    class TableCellRendererCbxIngredienteLeer implements TableCellRenderer {

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
                    int id_ingrediente_producto = (int) vista.getTableLeer().getValueAt(row, 0);
                    final String sql = "update ingrediente_producto set cantidad_ingrediente_producto=?,id_unidad=?,id_ingrediente=?,id_producto=?,id_tamaño=? where id_ingrediente_producto=? and valid=?";
                    try {
                        modelo.getMySQL1().connect();
                        modelo.getMySQL1().setPreparedStatement(modelo.getMySQL1().getConnection().prepareStatement(sql));
                        String nombre_unidad = (vista.getTableLeer().getValueAt(rowIndexToModel, 2)).toString();
                        String nombre_ingrediente = (vista.getTableLeer().getValueAt(rowIndexToModel, 3)).toString();
                        String nombre_producto = (vista.getTableLeer().getValueAt(rowIndexToModel, 4)).toString();
                        String nombre_tamaño = (vista.getTableLeer().getValueAt(rowIndexToModel, 5)).toString();
                        int id_unidad = modelo.searchFKIntUnidad(nombre_unidad);
                        int id_ingrediente = modelo.searchFKIntIngrediente(nombre_ingrediente);
                        int id_producto = modelo.searchFKIntProducto(nombre_producto);
                        int id_tamaño = modelo.searchFKIntTamaño(nombre_tamaño);
                        modelo.getMySQL1().getPreparedStatement().setInt(1, (int) vista.getTableModelLeer().getValueAt(rowIndexToModel, 1));
                        modelo.getMySQL1().getPreparedStatement().setInt(2, id_unidad);
                        modelo.getMySQL1().getPreparedStatement().setInt(3, id_ingrediente);
                        modelo.getMySQL1().getPreparedStatement().setInt(4, id_producto);
                        modelo.getMySQL1().getPreparedStatement().setInt(5, id_tamaño);
                        modelo.getMySQL1().getPreparedStatement().setInt(6, id_ingrediente_producto);
                        modelo.getMySQL1().getPreparedStatement().setBoolean(7, true);
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
                    int id_ingrediente_producto = (int) vista.getTableLeer().getValueAt(row, 0);
                    final String sql = "update ingrediente_producto set valid=? where id_ingrediente_producto=?";
                    try {
                        modelo.getMySQL1().connect();
                        modelo.getMySQL1().setPreparedStatement(modelo.getMySQL1().getConnection().prepareStatement(sql));
                        modelo.getMySQL1().getPreparedStatement().setBoolean(1, false);
                        modelo.getMySQL1().getPreparedStatement().setInt(2, id_ingrediente_producto);
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
