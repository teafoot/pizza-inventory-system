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

import pizzeriatimbo_mvc.modelo.ProductoFormModelo;
import pizzeriatimbo_mvc.vista.ProductoFormVista;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
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
public class ProductoFormControlador {

    ProductoFormVista vista;
    ProductoFormModelo modelo;

    public ProductoFormControlador(ProductoFormModelo productoFormModelo, ProductoFormVista productoFormVista) {
        this.modelo = productoFormModelo;
        this.vista = productoFormVista;
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
            String nombre_tipo_producto;
            int id_tipo_producto;
            final String sql1 = "select id_tipo_producto,nombre_tipo_producto from tipo_producto where valid=?";
            try {
                vista.getCbxTipoProductoIngresar().removeAllItems();
                modelo.getMySQL1().connect();
                modelo.getMySQL1().setPreparedStatement(modelo.getMySQL1().getConnection().prepareStatement(sql1));
                modelo.getMySQL1().getPreparedStatement().setBoolean(1, true);
                modelo.getMySQL1().setResultSet(modelo.getMySQL1().getPreparedStatement().executeQuery());
                while (modelo.getMySQL1().getResultSet().next()) {
                    id_tipo_producto = modelo.getMySQL1().getResultSet().getInt("id_tipo_producto");
                    nombre_tipo_producto = modelo.getMySQL1().getResultSet().getString("nombre_tipo_producto");
                    vista.getCbxTipoProductoIngresar().addItem(modelo.new TipoProducto(id_tipo_producto, nombre_tipo_producto));
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
            ProductoFormModelo.TipoProducto id_tipo_producto = (ProductoFormModelo.TipoProducto) vista.getCbxTipoProductoIngresar().getSelectedItem();
            final String sql = "insert into producto(nombre_producto,id_tipo_producto,descripcion_producto) values(?,?,?)";
            try {
                modelo.getMySQL1().connect();
                modelo.getMySQL1().setPreparedStatement(modelo.getMySQL1().getConnection().prepareStatement(sql));
                modelo.getMySQL1().getPreparedStatement().setString(1, vista.getTxtNombreIngresar().getText());
                modelo.getMySQL1().getPreparedStatement().setInt(2, id_tipo_producto.getID());
                modelo.getMySQL1().getPreparedStatement().setString(3, vista.getTxtDescripcionIngresar().getText());
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
            vista.getTxtNombreIngresar().setText("");
            vista.getCbxTipoProductoIngresar().setSelectedItem(null);
            vista.getTxtDescripcionIngresar().setText("");
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
            String columnName, nombre_producto,nombre_tipo_producto, descripcion_producto;
            Timestamp last_update;
            int id_producto, id_tipo_producto;
            final String sql1 = "select id_tipo_producto,nombre_tipo_producto from tipo_producto where valid=?";
            final String sql2 = "select * from producto where valid=?";
            try {
                vista.getCbxTipoProductoLeer().removeAllItems();
                modelo.getMySQL1().connect();
                modelo.getMySQL1().setPreparedStatement(modelo.getMySQL1().getConnection().prepareStatement(sql1));
                modelo.getMySQL1().getPreparedStatement().setBoolean(1, true);
                modelo.getMySQL1().setResultSet(modelo.getMySQL1().getPreparedStatement().executeQuery());
                while (modelo.getMySQL1().getResultSet().next()) {
                    id_tipo_producto = modelo.getMySQL1().getResultSet().getInt("id_tipo_producto");
                    nombre_tipo_producto = modelo.getMySQL1().getResultSet().getString("nombre_tipo_producto");
                    vista.getCbxTipoProductoLeer().addItem(modelo.new TipoProducto(id_tipo_producto, nombre_tipo_producto));
                }
                modelo.getMySQL1().getResultSet().close();
                modelo.getMySQL1().getPreparedStatement().close();
                modelo.getMySQL1().disconnect();
            } catch (SQLException ex) {
                ex.getStackTrace();
            }
            try {
                modelo.getMySQL1().connect();
                modelo.getMySQL1().setPreparedStatement(modelo.getMySQL1().getConnection().prepareStatement(sql2));
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
                    id_producto = modelo.getMySQL1().getResultSet().getInt("id_producto");
                    nombre_producto = modelo.getMySQL1().getResultSet().getString("nombre_producto");
                    id_tipo_producto = modelo.getMySQL1().getResultSet().getInt("id_tipo_producto");
                    nombre_tipo_producto=modelo.searchFKStringTipoProducto(id_tipo_producto);
                    descripcion_producto = modelo.getMySQL1().getResultSet().getString("descripcion_producto");
                    last_update = modelo.getMySQL1().getResultSet().getTimestamp("last_update");
                    vista.getTableModelLeer().addRow(new Object[]{id_producto, nombre_producto, nombre_tipo_producto, descripcion_producto, last_update.toLocalDateTime()});
                }
                vista.cbxTipoProductoLeerSetDefaultCellEditor(vista.getCbxTipoProductoLeer());
                vista.cbxTipoProductoLeerSetTableCellRenderer(new TableCellRendererCbxTipoProductoLeer());
                modelo.getMySQL1().getResultSet().close();
                modelo.getMySQL1().getPreparedStatement().close();
                modelo.getMySQL1().disconnect();
            } catch (SQLException ex) {
                ex.getStackTrace();
            }
        }

    }

    class TableCellRendererCbxTipoProductoLeer implements TableCellRenderer {

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
                int confirmar = vista.mensajeConfirmar("Esta seguro que desea Modificar el registro?");
                if (JOptionPane.OK_OPTION == confirmar) {
                    int id_producto = (int) vista.getTableLeer().getValueAt(row, 0);
                    int rowIndexToModel = vista.getTableLeer().convertRowIndexToModel(row);
                    String nombre_tipo_producto = (vista.getTableLeer().getValueAt(rowIndexToModel, 2)).toString();
                    int id_tipo_producto = modelo.searchFKIntTipoProducto(nombre_tipo_producto);
                    final String sql = "update producto set nombre_producto=?,id_tipo_producto=?,descripcion_producto=? where id_producto=? and valid=?";
                    try {
                        modelo.getMySQL1().connect();
                        modelo.getMySQL1().setPreparedStatement(modelo.getMySQL1().getConnection().prepareStatement(sql));
                        modelo.getMySQL1().getPreparedStatement().setString(1, (String) vista.getTableModelLeer().getValueAt(rowIndexToModel, 1));
                        modelo.getMySQL1().getPreparedStatement().setInt(2, id_tipo_producto);
                        modelo.getMySQL1().getPreparedStatement().setString(3, (String) vista.getTableModelLeer().getValueAt(rowIndexToModel, 3));
                        modelo.getMySQL1().getPreparedStatement().setInt(4, id_producto);
                        modelo.getMySQL1().getPreparedStatement().setBoolean(5, true);
                        int value = modelo.getMySQL1().getPreparedStatement().executeUpdate();
                        if (value == 1) {
                            JOptionPane.showMessageDialog(null, "Registro Modificado");
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

    class ActionListenerBtnEliminarLeer implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            int row = vista.getTableLeer().getSelectedRow();
            if (row < 0) {
                vista.mostrarMensaje("Debe seleccionar una fila de la tabla");
            } else {
                int confirmar = vista.mensajeConfirmar("Esta seguro que desea Eliminar el registro?");
                if (JOptionPane.OK_OPTION == confirmar) {
                    int id_producto = (int) vista.getTableLeer().getValueAt(row, 0);
                    int rowIndexToModel = vista.getTableLeer().convertRowIndexToModel(row);
                    final String sql = "update producto set valid=? where id_producto=?";
                    try {
                        modelo.getMySQL1().connect();
                        modelo.getMySQL1().setPreparedStatement(modelo.getMySQL1().getConnection().prepareStatement(sql));
                        modelo.getMySQL1().getPreparedStatement().setBoolean(1, false);
                        modelo.getMySQL1().getPreparedStatement().setInt(2, id_producto);
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
