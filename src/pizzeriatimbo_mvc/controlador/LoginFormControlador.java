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

import pizzeriatimbo_mvc.modelo.AdminFormModelo;
import pizzeriatimbo_mvc.modelo.LoginFormModelo;
import pizzeriatimbo_mvc.vista.AdminFormVista;
import pizzeriatimbo_mvc.vista.LoginFormVista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 *
 * @author davidchenlo
 */
public class LoginFormControlador {

    LoginFormModelo modelo;
    LoginFormVista vista;

    public LoginFormControlador(LoginFormModelo loginFormModelo, LoginFormVista loginFormVista) {
        this.modelo = loginFormModelo;
        this.vista = loginFormVista;
        this.vista.btnLoginSetActionListener(new ActionListenerBtnLogin());
        this.vista.btnLimpiarSetActionListener(new ActionListenerBtnLimpiar());
    }

    class ActionListenerBtnLogin implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            boolean ok = false;
            String usernameInput = vista.getTxtUser().getText();
            String passwordInput = vista.getTxtPassword().getText();
            modelo.getUsername().clear();
            modelo.getPassword().clear();
            final String sql = "select * from cuenta where valid=?";
            try {
                modelo.getMySQL().connect();
                modelo.getMySQL().setPreparedStatement(modelo.getMySQL().getConnection().prepareStatement(sql));
                modelo.getMySQL().getPreparedStatement().setBoolean(1, true);
                modelo.getMySQL().setResultSet(modelo.getMySQL().getPreparedStatement().executeQuery());
                while (modelo.getMySQL().getResultSet().next()) {
                    modelo.getUsername().add(modelo.getMySQL().getResultSet().getString("nombre_usuario"));
                    modelo.getPassword().add(modelo.getMySQL().getResultSet().getString("contraseña_usuario"));
                }
                modelo.getMySQL().getResultSet().close();
                modelo.getMySQL().getPreparedStatement().close();
                modelo.getMySQL().disconnect();
            } catch (SQLException ex) {
                ex.getStackTrace();
            }
            for (int i = 0; i < modelo.getUsername().size(); i++) {
                ok = usernameInput.equals(modelo.getUsername().get(i)) && passwordInput.equals(modelo.getPassword().get(i));
                if (ok) {
                    break;
                }
            }
            if (ok == true) {
                vista.mostrarMensaje("Nombre de usuario y contraseña correcta.");
                AdminFormModelo adminFormModelo = new AdminFormModelo();
                AdminFormVista adminFormVista = new AdminFormVista();
                AdminFormControlador adminFormControlador = new AdminFormControlador(adminFormModelo, adminFormVista);
                adminFormVista.pack();
                adminFormVista.setVisible(true);
                vista.dispose();
            } else {
                vista.mostrarMensaje("Nombre de usuario y/o contraseña incorrecta.");
                vista.getTxtUser().requestFocus();
            }

        }
    }

    class ActionListenerBtnLimpiar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            vista.getTxtUser().setText("");
            vista.getTxtPassword().setText("");
        }
    }

}
