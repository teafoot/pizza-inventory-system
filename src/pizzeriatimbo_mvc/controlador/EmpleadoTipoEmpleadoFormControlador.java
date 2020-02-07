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

import com.toedter.calendar.JDateChooser;
import pizzeriatimbo_mvc.modelo.EmpleadoTipoEmpleadoFormModelo;
import pizzeriatimbo_mvc.vista.EmpleadoTipoEmpleadoFormVista;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 *
 * @author davidchenlo
 */
public class EmpleadoTipoEmpleadoFormControlador {

    private final EmpleadoTipoEmpleadoFormModelo modelo;
    private final EmpleadoTipoEmpleadoFormVista vista;

    public EmpleadoTipoEmpleadoFormControlador(EmpleadoTipoEmpleadoFormModelo modelo, EmpleadoTipoEmpleadoFormVista vista) {
        this.modelo = modelo;
        this.vista = vista;
        this.vista.getDateChooserFechaRetiroIngresar().setDate(new Date(00000000));
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
            String nombre_completo_empleado, nombre_tipo_empleado;
            int id_empleado, id_tipo_empleado;
            final String sql1 = "select id_empleado,nombre_completo_empleado from empleado where valid=?";
            final String sql2 = "select id_tipo_empleado,nombre_tipo_empleado from tipo_empleado where valid=?";
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
                vista.getCbxTipoEmpleadoIngresar().removeAllItems();
                modelo.getMySQL1().connect();
                modelo.getMySQL1().setPreparedStatement(modelo.getMySQL1().getConnection().prepareStatement(sql2));
                modelo.getMySQL1().getPreparedStatement().setBoolean(1, true);
                modelo.getMySQL1().setResultSet(modelo.getMySQL1().getPreparedStatement().executeQuery());
                while (modelo.getMySQL1().getResultSet().next()) {
                    id_tipo_empleado = modelo.getMySQL1().getResultSet().getInt("id_tipo_empleado");
                    nombre_tipo_empleado = modelo.getMySQL1().getResultSet().getString("nombre_tipo_empleado");
                    vista.getCbxTipoEmpleadoIngresar().addItem(modelo.new TipoEmpleado(id_tipo_empleado, nombre_tipo_empleado));
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
            final String sql = "insert into empleado_tipo_empleado(id_empleado,id_tipo_empleado,fecha_ingreso,fecha_retiro,horas_trabajadas,horas_pagadas) values(?,?,?,?,?,?)";
            try {
                modelo.getMySQL1().connect();
                modelo.getMySQL1().setPreparedStatement(modelo.getMySQL1().getConnection().prepareStatement(sql));
                EmpleadoTipoEmpleadoFormModelo.Empleado id_empleado = (EmpleadoTipoEmpleadoFormModelo.Empleado) vista.getCbxEmpleadoIngresar().getSelectedItem();
                EmpleadoTipoEmpleadoFormModelo.TipoEmpleado id_tipo_empleado = (EmpleadoTipoEmpleadoFormModelo.TipoEmpleado) vista.getCbxTipoEmpleadoIngresar().getSelectedItem();
                modelo.getMySQL1().getPreparedStatement().setInt(1, id_empleado.getID());
                modelo.getMySQL1().getPreparedStatement().setInt(2, id_tipo_empleado.getID());
                modelo.getMySQL1().getPreparedStatement().setDate(3, modelo.convertUtilDateToSqlDate(vista.getDateChooserFechaIngresoIngresar().getDate()));
                modelo.getMySQL1().getPreparedStatement().setDate(4, modelo.convertUtilDateToSqlDate(vista.getDateChooserFechaRetiroIngresar().getDate()));
                modelo.getMySQL1().getPreparedStatement().setInt(5, (int) vista.getSpinnerHorasTrabajadasIngresar().getValue());
                modelo.getMySQL1().getPreparedStatement().setInt(6, (int) vista.getSpinnerHorasPagadasIngresar().getValue());
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
            vista.getCbxTipoEmpleadoIngresar().setSelectedItem(null);
            vista.getDateChooserFechaIngresoIngresar().setDate(null);
            vista.getDateChooserFechaRetiroIngresar().setDate(new Date(00000000));
            vista.getSpinnerHorasTrabajadasIngresar().setValue(0);
            vista.getSpinnerHorasPagadasIngresar().setValue(0);
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
            String columnName, nombre_completo_empleado, nombre_tipo_empleado;
            int id_empleado_tipo_empleado, id_empleado, id_tipo_empleado, horas_trabajadas, horas_pagadas;
            java.util.Date fecha_ingreso, fecha_retiro;
            Timestamp last_update;
            final String sql1 = "select id_empleado,nombre_completo_empleado from empleado where valid=?";
            final String sql2 = "select id_tipo_empleado,nombre_tipo_empleado from tipo_empleado where valid=?";
            final String sql3 = "select * from empleado_tipo_empleado where valid=?";
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
                vista.getCbxTipoEmpleadoLeer().removeAllItems();
                modelo.getMySQL1().connect();
                modelo.getMySQL1().setPreparedStatement(modelo.getMySQL1().getConnection().prepareStatement(sql2));
                modelo.getMySQL1().getPreparedStatement().setBoolean(1, true);
                modelo.getMySQL1().setResultSet(modelo.getMySQL1().getPreparedStatement().executeQuery());
                while (modelo.getMySQL1().getResultSet().next()) {
                    id_tipo_empleado = modelo.getMySQL1().getResultSet().getInt("id_tipo_empleado");
                    nombre_tipo_empleado = modelo.getMySQL1().getResultSet().getString("nombre_tipo_empleado");
                    vista.getCbxTipoEmpleadoLeer().addItem(modelo.new TipoEmpleado(id_tipo_empleado, nombre_tipo_empleado));
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
                    id_empleado_tipo_empleado = modelo.getMySQL1().getResultSet().getInt("id_empleado_tipo_empleado");
                    id_empleado = modelo.getMySQL1().getResultSet().getInt("id_empleado");
                    id_tipo_empleado = modelo.getMySQL1().getResultSet().getInt("id_tipo_empleado");
                    nombre_completo_empleado = modelo.searchFKStringEmpleado(id_empleado);
                    nombre_tipo_empleado = modelo.searchFKStringTipoEmpleado(id_tipo_empleado);
                    fecha_ingreso = modelo.getMySQL1().getResultSet().getDate("fecha_ingreso");
                    fecha_retiro = modelo.getMySQL1().getResultSet().getDate("fecha_retiro");
                    horas_trabajadas = modelo.getMySQL1().getResultSet().getInt("horas_trabajadas");
                    horas_pagadas = modelo.getMySQL1().getResultSet().getInt("horas_pagadas");
                    last_update = modelo.getMySQL1().getResultSet().getTimestamp("last_update");
                    vista.getTableModelLeer().addRow(new Object[]{id_empleado_tipo_empleado, nombre_completo_empleado, nombre_tipo_empleado, fecha_ingreso, fecha_retiro, horas_trabajadas, horas_pagadas,last_update.toLocalDateTime()});
                }
                vista.cbxEmpleadoLeerSetDefaultCellEditor(vista.getCbxEmpleadoLeer());
                vista.cbxTipoEmpleadoLeerSetDefaultCellEditor(vista.getCbxTipoEmpleadoLeer());
                vista.spinnerHorasTrabajadasLeerSetTableCellEditor(new TableCellEditorSpinnerHorasTrabajadasLeer());
                vista.spinnerHorasPagadasLeerSetTableCellEditor(new TableCellEditorSpinnerHorasPagadasLeer());
                vista.cbxEmpleadoLeerSetTableCellRenderer(new TableCellRendererCbxEmpleadoLeer());
                vista.cbxTipoEmpleadoLeerSetTableCellRenderer(new TableCellRendererCbxTipoEmpleadoLeer());
                vista.dateChooserFechaIngresoLeerSetTableCellRenderer(new TableCellRendererDateChooserFechaIngresoLeer());
                vista.dateChooserFechaRetiroLeerSetTableCellRenderer(new TableCellRendererDateChooserFechaRetiroLeer());
                vista.spinnerHorasTrabajadasLeerSetTableCellRenderer(new TableCellRendererSpinnerHorasTrabajadasLeer());
                vista.spinnerHorasPagadasLeerSetTableCellRenderer(new TableCellRendererSpinnerHorasPagadasLeer());
                modelo.getMySQL1().getResultSet().close();
                modelo.getMySQL1().getPreparedStatement().close();
                modelo.getMySQL1().disconnect();
            } catch (SQLException ex) {
                ex.getStackTrace();
            }
        }

    }

    class TableCellEditorSpinnerHorasTrabajadasLeer extends AbstractCellEditor implements TableCellEditor {

        @Override
        public Object getCellEditorValue() {
            return vista.getSpinnerHorasTrabajadasLeer().getValue();
        }

        @Override
        public Component getTableCellEditorComponent(JTable jtable, Object value, boolean bln, int i, int i1) {
            vista.getSpinnerHorasTrabajadasLeer().setValue(value);
            return vista.getSpinnerHorasTrabajadasLeer();
        }

    }

    class TableCellEditorSpinnerHorasPagadasLeer extends AbstractCellEditor implements TableCellEditor {

        @Override
        public Object getCellEditorValue() {
            return vista.getSpinnerHorasPagadasLeer().getValue();
        }

        @Override
        public Component getTableCellEditorComponent(JTable jtable, Object value, boolean bln, int i, int i1) {
            vista.getSpinnerHorasPagadasLeer().setValue(value);
            return vista.getSpinnerHorasPagadasLeer();
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

    class TableCellRendererCbxTipoEmpleadoLeer implements TableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable jtable, Object value, boolean bln, boolean bln1, int i, int i1) {
            JComboBox comboBox = new JComboBox();
            comboBox.addItem(value);
            comboBox.setSelectedItem(value);
            return comboBox;
        }

    }

    class TableCellRendererDateChooserFechaIngresoLeer implements TableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable jtable, Object value, boolean bln, boolean bln1, int i, int i1) {
            JDateChooser dateChooser = new JDateChooser();
            dateChooser.setDate((java.util.Date) value);
            return dateChooser;
        }

    }

    class TableCellRendererDateChooserFechaRetiroLeer implements TableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable jtable, Object value, boolean bln, boolean bln1, int i, int i1) {
            JDateChooser dateChooser = new JDateChooser();
            dateChooser.setDate((java.util.Date) value);
            return dateChooser;
        }

    }

    class TableCellRendererSpinnerHorasTrabajadasLeer implements TableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable jtable, Object value, boolean bln, boolean bln1, int i, int i1) {
            JSpinner spinner = new JSpinner();
            spinner.setValue(value);
            return spinner;
        }
    }

    class TableCellRendererSpinnerHorasPagadasLeer implements TableCellRenderer {

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
                    int id_empleado_tipo_empleado = (int) vista.getTableLeer().getValueAt(row, 0);
                    final String sql = "update empleado_tipo_empleado set id_empleado=?,id_tipo_empleado=?,fecha_ingreso=?,fecha_retiro=?,horas_trabajadas=?,horas_pagadas=? where id_empleado_tipo_empleado=? and valid=?";
                    try {
                        modelo.getMySQL1().connect();
                        modelo.getMySQL1().setPreparedStatement(modelo.getMySQL1().getConnection().prepareStatement(sql));
                        String nombre_completo_empleado = (vista.getTableLeer().getValueAt(rowIndexToModel, 1)).toString();
                        String nombre_tipo_empleado = (vista.getTableLeer().getValueAt(rowIndexToModel, 2)).toString();
                        int id_empleado = modelo.searchFKIntEmpleado(nombre_completo_empleado);
                        int id_tipo_empleado = modelo.searchFKIntTipoEmpleado(nombre_tipo_empleado);
                        modelo.getMySQL1().getPreparedStatement().setInt(1, id_empleado);
                        modelo.getMySQL1().getPreparedStatement().setInt(2, id_tipo_empleado);
                        modelo.getMySQL1().getPreparedStatement().setDate(3, modelo.convertUtilDateToSqlDate((java.util.Date) vista.getTableModelLeer().getValueAt(rowIndexToModel, 3)));
                        modelo.getMySQL1().getPreparedStatement().setDate(4, modelo.convertUtilDateToSqlDate((java.util.Date) vista.getTableModelLeer().getValueAt(rowIndexToModel, 4)));
                        modelo.getMySQL1().getPreparedStatement().setInt(5, (int) vista.getTableModelLeer().getValueAt(rowIndexToModel, 5));
                        modelo.getMySQL1().getPreparedStatement().setInt(6, (int) vista.getTableModelLeer().getValueAt(rowIndexToModel, 6));
                        modelo.getMySQL1().getPreparedStatement().setInt(7, id_empleado_tipo_empleado);
                        modelo.getMySQL1().getPreparedStatement().setBoolean(8, true);
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
                    int id_empleado_tipo_empleado = (int) vista.getTableLeer().getValueAt(row, 0);
                    final String sql = "update empleado_tipo_empleado set valid=? where id_empleado_tipo_empleado=?";
                    try {
                        modelo.getMySQL1().connect();
                        modelo.getMySQL1().setPreparedStatement(modelo.getMySQL1().getConnection().prepareStatement(sql));
                        modelo.getMySQL1().getPreparedStatement().setBoolean(1, false);
                        modelo.getMySQL1().getPreparedStatement().setInt(2, id_empleado_tipo_empleado);
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
