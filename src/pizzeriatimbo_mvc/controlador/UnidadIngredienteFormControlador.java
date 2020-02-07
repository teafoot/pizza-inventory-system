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

import pizzeriatimbo_mvc.modelo.UnidadIngredienteFormModelo;
import pizzeriatimbo_mvc.vista.UnidadIngredienteFormVista;

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
public class UnidadIngredienteFormControlador {

    private final UnidadIngredienteFormModelo modelo;
    private final UnidadIngredienteFormVista vista;

    public UnidadIngredienteFormControlador(UnidadIngredienteFormModelo modelo, UnidadIngredienteFormVista vista) {
        this.modelo = modelo;
        this.vista = vista;
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
            String nombre_unidad, nombre_ingrediente;
            int id_unidad, id_ingrediente;
            final String sql1 = "select id_unidad,nombre_unidad from unidad where valid=?";
            final String sql2 = "select id_ingrediente,nombre_ingrediente from ingrediente where valid=?";
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
        }

    }

    class ActionListenerBtnIngresar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            final String sql = "insert into unidad_ingrediente(cantidad_unidad_ingrediente,id_unidad,id_ingrediente,costo_unidad_ingrediente) values(?,?,?,?)";
            try {
                modelo.getMySQL1().connect();
                modelo.getMySQL1().setPreparedStatement(modelo.getMySQL1().getConnection().prepareStatement(sql));
                UnidadIngredienteFormModelo.Unidad id_unidad = (UnidadIngredienteFormModelo.Unidad) vista.getCbxUnidadIngresar().getSelectedItem();
                UnidadIngredienteFormModelo.Ingrediente id_ingrediente = (UnidadIngredienteFormModelo.Ingrediente) vista.getCbxIngredienteIngresar().getSelectedItem();
                modelo.getMySQL1().getPreparedStatement().setInt(1, (int) vista.getSpinnerCantidadUnidadIngredienteIngresar().getValue());
                modelo.getMySQL1().getPreparedStatement().setInt(2, id_unidad.getID());
                modelo.getMySQL1().getPreparedStatement().setInt(3, id_ingrediente.getID());
                modelo.getMySQL1().getPreparedStatement().setInt(4, (int) vista.getSpinnerCostoUnidadIngredienteIngresar().getValue());
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
            vista.getSpinnerCantidadUnidadIngredienteIngresar().setValue(0);
            vista.getCbxUnidadIngresar().setSelectedItem(null);
            vista.getCbxIngredienteIngresar().setSelectedItem(null);
            vista.getSpinnerCostoUnidadIngredienteIngresar().setValue(0);
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
            String columnName, nombre_unidad, nombre_ingrediente;
            Timestamp last_update;
            int id_unidad_ingrediente, id_unidad, id_ingrediente, costo_unidad_ingrediente, cantidad_unidad_ingrediente;
            final String sql1 = "select id_unidad,nombre_unidad from unidad where valid=?";
            final String sql2 = "select id_ingrediente,nombre_ingrediente from ingrediente where valid=?";
            final String sql3 = "select * from unidad_ingrediente where valid=?";
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
                    id_unidad_ingrediente = modelo.getMySQL1().getResultSet().getInt("id_unidad_ingrediente");
                    cantidad_unidad_ingrediente = modelo.getMySQL1().getResultSet().getInt("cantidad_unidad_ingrediente");
                    id_unidad = modelo.getMySQL1().getResultSet().getInt("id_unidad");
                    id_ingrediente = modelo.getMySQL1().getResultSet().getInt("id_ingrediente");
                    nombre_unidad = modelo.searchFKStringUnidad(id_unidad);
                    nombre_ingrediente = modelo.searchFKStringIngrediente(id_ingrediente);
                    costo_unidad_ingrediente = modelo.getMySQL1().getResultSet().getInt("costo_unidad_ingrediente");
                    last_update=modelo.getMySQL1().getResultSet().getTimestamp("last_update");
                    vista.getTableModelLeer().addRow(new Object[]{id_unidad_ingrediente, cantidad_unidad_ingrediente, nombre_unidad, nombre_ingrediente, costo_unidad_ingrediente,last_update.toLocalDateTime()});
                }
                vista.spinnerCantidadUnidadIngredienteLeerSetTableCellEditor(new TableCellEditorSpinnerCantidadUnidadIngredienteLeer());
                vista.spinnerCostoUnidadIngredienteLeerSetTableCellEditor(new TableCellEditorSpinnerCostoUnidadIngredienteLeer());
                vista.cbxUnidadLeerSetDefaultCellEditor(vista.getCbxUnidadLeer());
                vista.cbxIngredienteLeerSetDefaultCellEditor(vista.getCbxIngredienteLeer());
                vista.spinnerCantidadUnidadIngredienteLeerSetTableCellRenderer(new TableCellRendererSpinnerCantidadUnidadIngredienteLeer());
                vista.spinnerCostoUnidadIngredienteLeerSetTableCellRenderer(new TableCellRendererSpinnerCostoUnidadIngredienteLeer());
                vista.cbxUnidadLeerSetTableCellRenderer(new TableCellRendererCbxUnidadLeer());
                vista.cbxIngredienteLeerSetTableCellRenderer(new TableCellRendererCbxIngredienteLeer());
                modelo.getMySQL1().getResultSet().close();
                modelo.getMySQL1().getPreparedStatement().close();
                modelo.getMySQL1().disconnect();
            } catch (SQLException ex) {
                ex.getStackTrace();
            }
        }

    }

    class TableCellEditorSpinnerCantidadUnidadIngredienteLeer extends AbstractCellEditor implements TableCellEditor {

        @Override
        public Object getCellEditorValue() {
            return vista.getSpinnerCantidadUnidadIngredienteLeer().getValue();
        }

        @Override
        public Component getTableCellEditorComponent(JTable jtable, Object value, boolean bln, int i, int i1) {
            vista.getSpinnerCantidadUnidadIngredienteLeer().setValue(value);
            return vista.getSpinnerCantidadUnidadIngredienteLeer();
        }

    }
    
    class TableCellEditorSpinnerCostoUnidadIngredienteLeer extends AbstractCellEditor implements TableCellEditor {

        @Override
        public Object getCellEditorValue() {
            return vista.getSpinnerCostoUnidadIngredienteLeer().getValue();
        }

        @Override
        public Component getTableCellEditorComponent(JTable jtable, Object value, boolean bln, int i, int i1) {
            vista.getSpinnerCostoUnidadIngredienteLeer().setValue(value);
            return vista.getSpinnerCostoUnidadIngredienteLeer();
        }
        
    }
    
    class TableCellRendererSpinnerCantidadUnidadIngredienteLeer implements TableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable jtable, Object value, boolean bln, boolean bln1, int i, int i1) {
            JSpinner spinner = new JSpinner();
            spinner.setValue(value);
            return spinner;
        }
        
    }
    
    class TableCellRendererSpinnerCostoUnidadIngredienteLeer implements TableCellRenderer {

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
                    int id_unidad_ingrediente = (int) vista.getTableLeer().getValueAt(row, 0);
                    final String sql = "update unidad_ingrediente set cantidad_unidad_ingrediente=?,id_unidad=?,id_ingrediente=?,costo_unidad_ingrediente=? where id_unidad_ingrediente=? and valid=?";
                    try {
                        modelo.getMySQL1().connect();
                        modelo.getMySQL1().setPreparedStatement(modelo.getMySQL1().getConnection().prepareStatement(sql));
                        String nombre_unidad = (vista.getTableLeer().getValueAt(rowIndexToModel, 2)).toString();
                        String nombre_ingrediente = (vista.getTableLeer().getValueAt(rowIndexToModel, 3)).toString();
                        int id_unidad = modelo.searchFKIntUnidad(nombre_unidad);
                        int id_ingrediente = modelo.searchFKIntIngrediente(nombre_ingrediente);
                        modelo.getMySQL1().getPreparedStatement().setInt(1, (int) vista.getTableModelLeer().getValueAt(rowIndexToModel, 1));
                        modelo.getMySQL1().getPreparedStatement().setInt(2, id_unidad);
                        modelo.getMySQL1().getPreparedStatement().setInt(3, id_ingrediente);
                        modelo.getMySQL1().getPreparedStatement().setInt(4, (int) vista.getTableModelLeer().getValueAt(rowIndexToModel, 4));
                        modelo.getMySQL1().getPreparedStatement().setInt(5, id_unidad_ingrediente);
                        modelo.getMySQL1().getPreparedStatement().setBoolean(6, true);
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
                    int id_unidad_ingrediente = (int) vista.getTableLeer().getValueAt(row, 0);
                    final String sql = "update unidad_ingrediente set valid=? where id_unidad_ingrediente=?";
                    try {
                        modelo.getMySQL1().connect();
                        modelo.getMySQL1().setPreparedStatement(modelo.getMySQL1().getConnection().prepareStatement(sql));
                        modelo.getMySQL1().getPreparedStatement().setBoolean(1, false);
                        modelo.getMySQL1().getPreparedStatement().setInt(2, id_unidad_ingrediente);
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
