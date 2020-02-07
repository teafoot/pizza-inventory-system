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
import pizzeriatimbo_mvc.modelo.EmpleadoFormModelo;
import pizzeriatimbo_mvc.vista.EmpleadoFormVista;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author davidchenlo
 */
public class EmpleadoFormControlador {

    private final EmpleadoFormModelo modelo;
    private final EmpleadoFormVista vista;

    public EmpleadoFormControlador(EmpleadoFormModelo empleadoFormModelo, EmpleadoFormVista empleadoFormVista) {
        this.modelo = empleadoFormModelo;
        this.vista = empleadoFormVista;
        this.vista.btnIngresarAddActionListener(new ActionListenerBtnIngresar());
        this.vista.btnLimpiarIngresarAddActionListener(new ActionListenerBtnLimpiarIngresar());
        this.vista.txtFilterLeerAddDocumentListener(new DocumentListenerTxtFilterLeer());
        this.vista.btnRefreshLeerAddActionListener(new ActionListenerBtnRefreshLeer());
        this.vista.btnModificarLeerAddActionListener(new ActionListenerBtnModificarLeer());
        this.vista.btnEliminarLeerAddActionListener(new ActionListenerBtnEliminarLeer());
    }

    private void filterSearch() {
        String text = this.vista.getTxtFilterLeer().getText();
        if (text.trim().length() == 0) {
            this.vista.getTableRowSorterLeer().setRowFilter(null);
        } else {
            this.vista.getTableRowSorterLeer().setRowFilter(RowFilter.regexFilter("(?i)" + text));
        }
    }

    class ActionListenerBtnIngresar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            final String sql = "insert into empleado(nombre_completo_empleado,fecha_nacimiento_empleado,celula_identidad_empleado,numero_celular_empleado) values(?,?,?,?)";
            try {
                modelo.getMySQL().connect();
                modelo.getMySQL().setPreparedStatement(modelo.getMySQL().getConnection().prepareStatement(sql));
                modelo.getMySQL().getPreparedStatement().setString(1, vista.getTxtNombreCompletoIngresar().getText());
                modelo.getMySQL().getPreparedStatement().setDate(2, modelo.convertUtilDateToSqlDate(vista.getDateChooserIngresar().getDate()));
                modelo.getMySQL().getPreparedStatement().setString(3, vista.getTxtCelulaIdentidadIngresar().getText());
                modelo.getMySQL().getPreparedStatement().setString(4, vista.getTxtNumeroCelularIngresar().getText());
                int value = modelo.getMySQL().getPreparedStatement().executeUpdate();
                if (value == 1) {
                    vista.mostrarMensaje("Registro Ingresado");
                }
                modelo.getMySQL().getPreparedStatement().close();
                modelo.getMySQL().disconnect();
            } catch (SQLException ex) {
                ex.getStackTrace();
            }
        }
    }

    class ActionListenerBtnLimpiarIngresar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            vista.getTxtNombreCompletoIngresar().setText("");
            vista.getDateChooserIngresar().setDate(null);
            vista.getTxtCelulaIdentidadIngresar().setText("");
            vista.getTxtNumeroCelularIngresar().setText("");
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
            String columnName, nombre_completo_empleado, celula_identidad_empleado, numero_celular_empleado;
            Date fecha_nacimiento_empleado;
            Timestamp last_update;
            int id_empleado;
            final String sql = "select * from empleado where valid=?";
            try {
                modelo.getMySQL().connect();
                modelo.getMySQL().setPreparedStatement(modelo.getMySQL().getConnection().prepareStatement(sql));
                modelo.getMySQL().getPreparedStatement().setBoolean(1, true);
                modelo.getMySQL().setResultSet(modelo.getMySQL().getPreparedStatement().executeQuery());
                modelo.getMySQL().setResultSetMetaData(modelo.getMySQL().getResultSet().getMetaData());
                vista.getTableModelLeer().setColumnCount(0);
                vista.getTableModelLeer().setRowCount(0);
                for (int i = 1; i < modelo.getMySQL().getResultSetMetaData().getColumnCount(); i++) {
                    columnName = modelo.getMySQL().getResultSetMetaData().getColumnName(i);
                    vista.getTableModelLeer().addColumn(columnName);
                }
                while (modelo.getMySQL().getResultSet().next()) {
                    id_empleado = modelo.getMySQL().getResultSet().getInt("id_empleado");
                    nombre_completo_empleado = modelo.getMySQL().getResultSet().getString("nombre_completo_empleado");
                    fecha_nacimiento_empleado = modelo.getMySQL().getResultSet().getDate("fecha_nacimiento_empleado");
                    celula_identidad_empleado = modelo.getMySQL().getResultSet().getString("celula_identidad_empleado");
                    numero_celular_empleado = modelo.getMySQL().getResultSet().getString("numero_celular_empleado");
                    last_update=modelo.getMySQL().getResultSet().getTimestamp("last_update");
                    vista.getTableModelLeer().addRow(new Object[]{id_empleado, nombre_completo_empleado, fecha_nacimiento_empleado, celula_identidad_empleado, numero_celular_empleado,last_update.toLocalDateTime()});
                }
                vista.dateChooserFechaIngresoLeerSetTableCellRenderer(new TableCellRendererDateChooserFechaIngresoLeer());
                modelo.getMySQL().getResultSet().close();
                modelo.getMySQL().getPreparedStatement().close();
                modelo.getMySQL().disconnect();
            } catch (SQLException ex) {
                ex.getStackTrace();
            }
        }
    }
    
    class TableCellRendererDateChooserFechaIngresoLeer implements TableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable jtable, Object value, boolean bln, boolean bln1, int i, int i1) {
            JDateChooser dateChooser = new JDateChooser();
            dateChooser.setDate((Date) value);
            return dateChooser;
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
                    int id_empleado = (int) vista.getTableLeer().getValueAt(row, 0);
                    int rowIndexToModel = vista.getTableLeer().convertRowIndexToModel(row);
                    final String sql = "update empleado set nombre_completo_empleado=?,fecha_nacimiento_empleado=?,celula_identidad_empleado=?,numero_celular_empleado=? where id_empleado=? and valid=?";
                    try {
                        modelo.getMySQL().connect();
                        modelo.getMySQL().setPreparedStatement(modelo.getMySQL().getConnection().prepareStatement(sql));
                        modelo.getMySQL().getPreparedStatement().setString(1, (String) vista.getTableModelLeer().getValueAt(rowIndexToModel, 1));
                        modelo.getMySQL().getPreparedStatement().setDate(2, modelo.convertUtilDateToSqlDate((Date) vista.getTableModelLeer().getValueAt(rowIndexToModel, 2)));
                        modelo.getMySQL().getPreparedStatement().setString(3, (String) vista.getTableModelLeer().getValueAt(rowIndexToModel, 3));
                        modelo.getMySQL().getPreparedStatement().setString(4, (String) vista.getTableModelLeer().getValueAt(rowIndexToModel, 4));
                        modelo.getMySQL().getPreparedStatement().setInt(5, id_empleado);
                        modelo.getMySQL().getPreparedStatement().setBoolean(6, true);
                        int value = modelo.getMySQL().getPreparedStatement().executeUpdate();
                        if (value == 1) {
                            vista.mostrarMensaje("Registro Modificado");
                        }
                        modelo.getMySQL().getPreparedStatement().close();
                        modelo.getMySQL().disconnect();
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
                    int id_empleado = (int) vista.getTableLeer().getValueAt(row, 0);
                    int rowIndexToModel = vista.getTableLeer().convertRowIndexToModel(row);
                    final String sql = "update empleado set valid=? where id_empleado=?";
                    try {
                        modelo.getMySQL().connect();
                        modelo.getMySQL().setPreparedStatement(modelo.getMySQL().getConnection().prepareStatement(sql));
                        modelo.getMySQL().getPreparedStatement().setBoolean(1, false);
                        modelo.getMySQL().getPreparedStatement().setInt(2, id_empleado);
                        int value = modelo.getMySQL().getPreparedStatement().executeUpdate();
                        if (value == 1) {
                            vista.getTableModelLeer().removeRow(rowIndexToModel);
                            vista.mostrarMensaje("Registro Eliminado");
                        }
                        modelo.getMySQL().getPreparedStatement().close();
                        modelo.getMySQL().disconnect();
                    } catch (SQLException ex) {
                        ex.getStackTrace();
                    }
                }
            }
        }
    }
}
