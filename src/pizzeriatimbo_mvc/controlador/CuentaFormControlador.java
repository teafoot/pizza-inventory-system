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

import pizzeriatimbo_mvc.modelo.CuentaFormModelo;
import pizzeriatimbo_mvc.vista.CuentaFormVista;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 *
 * @author davidchenlo
 */
public class CuentaFormControlador {
    
    private final CuentaFormModelo modelo;
    private final CuentaFormVista vista;

    public CuentaFormControlador(CuentaFormModelo modelo, CuentaFormVista vista) {
        this.modelo = modelo;
        this.vista = vista;
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
            final String sql = "insert into cuenta(nombre_usuario,contraseña_usuario) values(?,?)";
            try {
                modelo.getMySQL().connect();
                modelo.getMySQL().setPreparedStatement(modelo.getMySQL().getConnection().prepareStatement(sql));
                modelo.getMySQL().getPreparedStatement().setString(1, vista.getTxtUsernameIngresar().getText());
                modelo.getMySQL().getPreparedStatement().setString(2, vista.getTxtPasswordIngresar().getText());
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
            vista.getTxtUsernameIngresar().setText("");
            vista.getTxtPasswordIngresar().setText("");
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
            String columnName, nombre_usuario, contraseña_usuario;
            Timestamp last_update;
            int id_cuenta;
            final String sql = "select * from cuenta where valid=?";
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
                    id_cuenta = modelo.getMySQL().getResultSet().getInt("id_cuenta");
                    nombre_usuario = modelo.getMySQL().getResultSet().getString("nombre_usuario");
                    contraseña_usuario = modelo.getMySQL().getResultSet().getString("contraseña_usuario");
                    last_update = modelo.getMySQL().getResultSet().getTimestamp("last_update");
                    vista.getTableModelLeer().addRow(new Object[]{id_cuenta, nombre_usuario, contraseña_usuario,last_update.toLocalDateTime()});
                }
                modelo.getMySQL().getResultSet().close();
                modelo.getMySQL().getPreparedStatement().close();
                modelo.getMySQL().disconnect();
            } catch (SQLException ex) {
                ex.getStackTrace();
            }
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
                    int id_cuenta = (int) vista.getTableLeer().getValueAt(row, 0);
                    int rowIndexToModel = vista.getTableLeer().convertRowIndexToModel(row);
                    final String sql = "update cuenta set nombre_usuario=?,contraseña_usuario=? where id_cuenta=? and valid=?";
                    try {
                        modelo.getMySQL().connect();
                        modelo.getMySQL().setPreparedStatement(modelo.getMySQL().getConnection().prepareStatement(sql));
                        modelo.getMySQL().getPreparedStatement().setString(1, (String) vista.getTableModelLeer().getValueAt(rowIndexToModel, 1));
                        modelo.getMySQL().getPreparedStatement().setString(2, (String) vista.getTableModelLeer().getValueAt(rowIndexToModel, 2));
                        modelo.getMySQL().getPreparedStatement().setInt(3, id_cuenta);
                        modelo.getMySQL().getPreparedStatement().setBoolean(4, true);
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
                    int id_cuenta = (int) vista.getTableLeer().getValueAt(row, 0);
                    int rowIndexToModel = vista.getTableLeer().convertRowIndexToModel(row);
                    final String sql = "update cuenta set valid=? where id_cuenta=?";
                    try {
                        modelo.getMySQL().connect();
                        modelo.getMySQL().setPreparedStatement(modelo.getMySQL().getConnection().prepareStatement(sql));
                        modelo.getMySQL().getPreparedStatement().setBoolean(1, false);
                        modelo.getMySQL().getPreparedStatement().setInt(2, id_cuenta);
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
