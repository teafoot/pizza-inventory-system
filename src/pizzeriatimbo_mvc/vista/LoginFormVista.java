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
package pizzeriatimbo_mvc.vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 *
 * @author davidchenlo
 */
public class LoginFormVista extends JFrame {
    private JButton btnLogin;
    private JButton btnLimpiar;
    private JPanel panel;
    private JTextField txtUser;
    private JPasswordField txtPassword;
    private JLabel lblLogin;
    private JLabel lblUser;
    private JLabel lblPassword;
    
    public LoginFormVista() {
        initJFrame();
        initComponents();
    }

    private void initJFrame() {
        this.setTitle("Autenticacion de Usuario");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setIconImage(new ImageIcon(this.getClass().getResource("/resources/key_icon.png")).getImage());
    }

    private void initComponents() {
        JPanel innerPanel1 = new JPanel();
        this.setBtnLogin(new JButton());
        this.setBtnLimpiar(new JButton());
        this.setPanel(new JPanel());
        this.setTxtUser(new JTextField());
        this.setTxtPassword(new JPasswordField());
        this.setLblLogin(new JLabel());
        this.setLblUser(new JLabel());
        this.setLblPassword(new JLabel());
        this.getLblLogin().setText("Ingrese el nombre de usuario y la contraseña correspondiente.");
        this.getLblUser().setText("Nombre de Usuario: ");
        this.getLblPassword().setText("Contraseña: ");
        this.getBtnLogin().setText("Ingresar");
        this.getBtnLimpiar().setText("Limpiar");
        this.getPanel().setLayout(new BorderLayout());
        innerPanel1.setLayout(new GridLayout(3, 2));
        this.getPanel().add(this.getLblLogin(), BorderLayout.NORTH);
        innerPanel1.add(getLblUser());
        innerPanel1.add(getTxtUser());
        innerPanel1.add(getLblPassword());
        innerPanel1.add(getTxtPassword());
        innerPanel1.add(getBtnLogin());
        innerPanel1.add(getBtnLimpiar());
        this.getPanel().add(innerPanel1, BorderLayout.CENTER);
        this.getContentPane().add(this.getPanel());
    }
    
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }
    
    public void btnLoginSetActionListener(ActionListener actionListener) {
        this.getBtnLogin().addActionListener(actionListener);
    }
    
    public void btnLimpiarSetActionListener(ActionListener actionListener) {
        this.getBtnLimpiar().addActionListener(actionListener);
    }

    /**
     * @return the btnLogin
     */
    public JButton getBtnLogin() {
        return btnLogin;
    }

    /**
     * @param btnLogin the btnLogin to set
     */
    public void setBtnLogin(JButton btnLogin) {
        this.btnLogin = btnLogin;
    }

    /**
     * @return the btnLimpiar
     */
    public JButton getBtnLimpiar() {
        return btnLimpiar;
    }

    /**
     * @param btnLimpiar the btnLimpiar to set
     */
    public void setBtnLimpiar(JButton btnLimpiar) {
        this.btnLimpiar = btnLimpiar;
    }

    /**
     * @return the panel
     */
    public JPanel getPanel() {
        return panel;
    }

    /**
     * @param panel the panel to set
     */
    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    /**
     * @return the txtUser
     */
    public JTextField getTxtUser() {
        return txtUser;
    }

    /**
     * @param txtUser the txtUser to set
     */
    public void setTxtUser(JTextField txtUser) {
        this.txtUser = txtUser;
    }

    /**
     * @return the txtPassword
     */
    public JPasswordField getTxtPassword() {
        return txtPassword;
    }

    /**
     * @param txtPassword the txtPassword to set
     */
    public void setTxtPassword(JPasswordField txtPassword) {
        this.txtPassword = txtPassword;
    }

    /**
     * @return the lblLogin
     */
    public JLabel getLblLogin() {
        return lblLogin;
    }

    /**
     * @param lblLogin the lblLogin to set
     */
    public void setLblLogin(JLabel lblLogin) {
        this.lblLogin = lblLogin;
    }

    /**
     * @return the lblUser
     */
    public JLabel getLblUser() {
        return lblUser;
    }

    /**
     * @param lblUser the lblUser to set
     */
    public void setLblUser(JLabel lblUser) {
        this.lblUser = lblUser;
    }

    /**
     * @return the lblPassword
     */
    public JLabel getLblPassword() {
        return lblPassword;
    }

    /**
     * @param lblPassword the lblPassword to set
     */
    public void setLblPassword(JLabel lblPassword) {
        this.lblPassword = lblPassword;
    }
}
