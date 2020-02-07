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

import pizzeriatimbo_mvc.modelo.TipoEmpleadoFormModelo;
import pizzeriatimbo_mvc.vista.TipoEmpleadoFormVista;

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
public class TipoEmpleadoFormControlador {

    private final TipoEmpleadoFormModelo modelo;
    private final TipoEmpleadoFormVista vista;

    public TipoEmpleadoFormControlador(TipoEmpleadoFormModelo tipoEmpleadoFormModelo, TipoEmpleadoFormVista tipoEmpleadoFormVista) {
        this.modelo = tipoEmpleadoFormModelo;
        this.vista = tipoEmpleadoFormVista;
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

    class ActionListenerBtnIngresar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            final String sql = "insert into tipo_empleado(nombre_tipo_empleado,salario_tipo_empleado,horario_tipo_empleado,descripcion_tipo_empleado) values(?,?,?,?)";
            try {
                modelo.getMySQL().connect();
                modelo.getMySQL().setPreparedStatement(modelo.getMySQL().getConnection().prepareStatement(sql));
                modelo.getMySQL().getPreparedStatement().setString(1, vista.getTxtNombreIngresar().getText());
                modelo.getMySQL().getPreparedStatement().setInt(2, (int) vista.getSpinnerSalarioIngresar().getValue());
                modelo.getMySQL().getPreparedStatement().setString(3, vista.getTxtHorarioIngresar().getText());
                modelo.getMySQL().getPreparedStatement().setString(4, vista.getTxtDescripcionIngresar().getText());
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
            vista.getTxtNombreIngresar().setText("");
            vista.getSpinnerSalarioIngresar().setValue(0);
            vista.getTxtHorarioIngresar().setText("");
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
            String columnName, nombre_tipo_empleado, horario_tipo_empleado, descripcion_tipo_empleado;
            Timestamp last_update;
            int id_tipo_empleado, salario_tipo_empleado;
            final String sql = "select * from tipo_empleado where valid=?";
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
                    id_tipo_empleado = modelo.getMySQL().getResultSet().getInt("id_tipo_empleado");
                    nombre_tipo_empleado = modelo.getMySQL().getResultSet().getString("nombre_tipo_empleado");
                    salario_tipo_empleado = modelo.getMySQL().getResultSet().getInt("salario_tipo_empleado");
                    horario_tipo_empleado = modelo.getMySQL().getResultSet().getString("horario_tipo_empleado");
                    descripcion_tipo_empleado = modelo.getMySQL().getResultSet().getString("descripcion_tipo_empleado");
                    last_update = modelo.getMySQL().getResultSet().getTimestamp("last_update");
                    vista.getTableModelLeer().addRow(new Object[]{id_tipo_empleado, nombre_tipo_empleado, salario_tipo_empleado, horario_tipo_empleado, descripcion_tipo_empleado, last_update.toLocalDateTime()});
                }
                vista.spinnerSalarioLeerSetTableCellEditor(new TableCellEditorSpinnerSalarioLeer());
                vista.spinnerSalarioLeerSetTableCellRenderer(new TableCellRendererSpinnerSalarioLeer());
                modelo.getMySQL().getResultSet().close();
                modelo.getMySQL().getPreparedStatement().close();
                modelo.getMySQL().disconnect();
            } catch (SQLException ex) {
                ex.getStackTrace();
            }
        }

    }
    
    class TableCellEditorSpinnerSalarioLeer extends AbstractCellEditor implements TableCellEditor {

        @Override
        public Object getCellEditorValue() {
            return vista.getSpinnerSalarioLeer().getValue();
        }

        @Override
        public Component getTableCellEditorComponent(JTable jtable, Object value, boolean bln, int i, int i1) {
            vista.getSpinnerSalarioLeer().setValue(value);
            return vista.getSpinnerSalarioLeer();
        }
        
    }

    class TableCellRendererSpinnerSalarioLeer implements TableCellRenderer {

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
                    int id_tipo_empleado = (int) vista.getTableLeer().getValueAt(row, 0);
                    int rowIndexToModel = vista.getTableLeer().convertRowIndexToModel(row);
                    final String sql = "update tipo_empleado set nombre_tipo_empleado=?,salario_tipo_empleado=?,horario_tipo_empleado=?,descripcion_tipo_empleado=? where id_tipo_empleado=? and valid=?";
                    try {
                        modelo.getMySQL().connect();
                        modelo.getMySQL().setPreparedStatement(modelo.getMySQL().getConnection().prepareStatement(sql));
                        modelo.getMySQL().getPreparedStatement().setString(1, (String) vista.getTableModelLeer().getValueAt(rowIndexToModel, 1));
                        modelo.getMySQL().getPreparedStatement().setInt(2, (int) vista.getTableModelLeer().getValueAt(rowIndexToModel, 2));
                        modelo.getMySQL().getPreparedStatement().setString(3, (String) vista.getTableModelLeer().getValueAt(rowIndexToModel, 3));
                        modelo.getMySQL().getPreparedStatement().setString(4, (String) vista.getTableModelLeer().getValueAt(rowIndexToModel, 4));
                        modelo.getMySQL().getPreparedStatement().setInt(5, id_tipo_empleado);
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
                int confirmar = vista.confirmarMensaje("Esta seguro que desea Eliminar el registro?");
                if (JOptionPane.OK_OPTION == confirmar) {
                    int id_tipo_empleado = (int) vista.getTableLeer().getValueAt(row, 0);
                    int rowIndexToModel = vista.getTableLeer().convertRowIndexToModel(row);
                    final String sql = "update tipo_empleado set valid=? where id_tipo_empleado=?";
                    try {
                        modelo.getMySQL().connect();
                        modelo.getMySQL().setPreparedStatement(modelo.getMySQL().getConnection().prepareStatement(sql));
                        modelo.getMySQL().getPreparedStatement().setBoolean(1, false);
                        modelo.getMySQL().getPreparedStatement().setInt(2, id_tipo_empleado);
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
