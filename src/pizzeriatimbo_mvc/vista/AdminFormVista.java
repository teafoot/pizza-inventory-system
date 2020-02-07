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
public class AdminFormVista extends JFrame {

    private JDesktopPane desktopPane;
    private JMenuBar menuBarz;
    private JMenu menu;
    private JMenuItem menuItemUnidad;
    private JMenuItem menuItemIngrediente;
    private JMenuItem menuItemProducto;
    private JMenuItem menuItemTamaño;
    private JMenuItem menuItemTipoProducto;
    private JMenuItem menuItemVentaProducto;
    private JMenuItem menuItemEmpleado;
    private JMenuItem menuItemTipoEmpleado;
    private JMenuItem menuItemTamañoProducto;
    private JMenuItem menuItemIngredienteProducto;
    private JMenuItem menuItemUnidadIngrediente;
    private JMenuItem menuItemEmpleadoTipoEmpleado;
    private JMenuItem menuItemCuenta;

    public AdminFormVista() {
        super("Pizzeria Timbo");
        initJFrame();
        initJMenu();
    }

    private void initJFrame() {
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setResizable(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setIconImage(new ImageIcon(this.getClass().getResource("/resources/pizza_icon.png")).getImage());
    }

    private void initJMenu() {
        this.setDesktopPane(new JDesktopPane() {
            ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("/resources/pizza_background.png"));
            Image image = imageIcon.getImage();
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 0, 0,this.getWidth(),this.getHeight(), this);
            }
        });
        this.setMenuBarz(new JMenuBar());
        this.setMenu(new JMenu());
        this.setMenuItemUnidad(new JMenuItem());
        this.setMenuItemTamaño(new JMenuItem());
        this.setMenuItemTipoProducto(new JMenuItem());
        this.setMenuItemTipoEmpleado(new JMenuItem());
        this.setMenuItemIngrediente(new JMenuItem());
        this.setMenuItemProducto(new JMenuItem());
        this.setMenuItemVentaProducto(new JMenuItem());
        this.setMenuItemEmpleado(new JMenuItem());
        this.setMenuItemTamañoProducto(new JMenuItem());
        this.setMenuItemIngredienteProducto(new JMenuItem());
        this.setMenuItemUnidadIngrediente(new JMenuItem());
        this.setMenuItemEmpleadoTipoEmpleado(new JMenuItem());
        this.setMenuItemCuenta(new JMenuItem());
        this.getMenu().setText("Administracion");
        this.getMenuItemUnidad().setText("Unidad");
        this.getMenuItemTamaño().setText("Tamaño");
        this.getMenuItemEmpleado().setText("Empleado");
        this.getMenuItemTipoEmpleado().setText("Tipo Empleado");
        this.getMenuItemIngrediente().setText("Ingrediente");
        this.getMenuItemProducto().setText("Producto");
        this.getMenuItemTipoProducto().setText("Tipo Producto");
        this.getMenuItemTamañoProducto().setText("Tamaño Producto");
        this.getMenuItemIngredienteProducto().setText("Ingrediente Producto");
        this.getMenuItemUnidadIngrediente().setText("Unidad Ingrediente");
        this.getMenuItemEmpleadoTipoEmpleado().setText("Empleado Tipo Empleado");
        this.getMenuItemVentaProducto().setText("Venta Producto");
        this.getMenuItemCuenta().setText("Cuenta");
        this.getMenu().add(this.getMenuItemEmpleado());
        this.getMenu().add(this.getMenuItemTipoEmpleado());
        this.getMenu().add(this.getMenuItemUnidad());
        this.getMenu().add(this.getMenuItemTamaño());
        this.getMenu().add(this.getMenuItemIngrediente());
        this.getMenu().add(this.getMenuItemProducto());
        this.getMenu().add(this.getMenuItemTipoProducto());
        this.getMenu().add(this.getMenuItemEmpleadoTipoEmpleado());
        this.getMenu().add(this.getMenuItemUnidadIngrediente());
        this.getMenu().add(this.getMenuItemIngredienteProducto());
        this.getMenu().add(this.getMenuItemTamañoProducto());
        this.getMenu().add(this.getMenuItemVentaProducto());
        this.getMenu().add(this.getMenuItemCuenta());
        this.getMenuBarz().add(this.getMenu());
        this.setJMenuBar(this.getMenuBarz());
        this.getDesktopPane().setBounds(0, 0, this.getWidth(), this.getHeight());
        this.setContentPane(this.getDesktopPane());
    }
    
    public void menuItemUnidadSetActionListener(ActionListener actionListener) {
        this.getMenuItemUnidad().addActionListener(actionListener);
    }
    
    public void menuItemTamañoSetActionListener(ActionListener actionListener) {
        this.getMenuItemTamaño().addActionListener(actionListener);
    }
    
    public void menuItemTipoProductoSetActionListener(ActionListener actionListener) {
        this.getMenuItemTipoProducto().addActionListener(actionListener);
    }
    
    public void menuItemTipoEmpleadoSetActionListener(ActionListener actionListener) {
        this.getMenuItemTipoEmpleado().addActionListener(actionListener);
    }
    
    public void menuItemIngredienteSetActionListener(ActionListener actionListener) {
        this.getMenuItemIngrediente().addActionListener(actionListener);
    }
    
    public void menuItemProductoSetActionListener(ActionListener actionListener) {
        this.getMenuItemProducto().addActionListener(actionListener);
    }
    
    public void menuItemVentaProductoSetActionListener(ActionListener actionListener) {
        this.getMenuItemVentaProducto().addActionListener(actionListener);
    }
    
    public void menuItemEmpleadoSetActionListener(ActionListener actionListener) {
        this.getMenuItemEmpleado().addActionListener(actionListener);
    }
    
    public void menuItemTamañoProductoSetActionListener(ActionListener actionListener) {
        this.getMenuItemTamañoProducto().addActionListener(actionListener);
    }
    
    public void menuItemIngredienteProductoSetActionListener(ActionListener actionListener) {
        this.getMenuItemIngredienteProducto().addActionListener(actionListener);
    }
    
    public void menuItemUnidadIngredienteSetActionListener(ActionListener actionListener) {
        this.getMenuItemUnidadIngrediente().addActionListener(actionListener);
    }
    
    public void menuItemEmpleadoTipoEmpleadoSetActionListener(ActionListener actionListener) {
        this.getMenuItemEmpleadoTipoEmpleado().addActionListener(actionListener);
    }
    
    public void menuItemCuentaSetActionListener(ActionListener actionListener) {
        this.getMenuItemCuenta().addActionListener(actionListener);
    }

    /**
     * @return the desktopPane
     */
    public JDesktopPane getDesktopPane() {
        return desktopPane;
    }

    /**
     * @param desktopPane the desktopPane to set
     */
    public void setDesktopPane(JDesktopPane desktopPane) {
        this.desktopPane = desktopPane;
    }

    /**
     * @return the menuBar
     */
    public JMenuBar getMenuBarz() {
        return menuBarz;
    }

    /**
     * @param menuBar the menuBar to set
     */
    public void setMenuBarz(JMenuBar menuBar) {
        this.menuBarz = menuBar;
    }

    /**
     * @return the menu
     */
    public JMenu getMenu() {
        return menu;
    }

    /**
     * @param menu the menu to set
     */
    public void setMenu(JMenu menu) {
        this.menu = menu;
    }

    /**
     * @return the menuItemUnidad
     */
    public JMenuItem getMenuItemUnidad() {
        return menuItemUnidad;
    }

    /**
     * @param menuItemUnidad the menuItemUnidad to set
     */
    public void setMenuItemUnidad(JMenuItem menuItemUnidad) {
        this.menuItemUnidad = menuItemUnidad;
    }

    /**
     * @return the menuItemIngrediente
     */
    public JMenuItem getMenuItemIngrediente() {
        return menuItemIngrediente;
    }

    /**
     * @param menuItemIngrediente the menuItemIngrediente to set
     */
    public void setMenuItemIngrediente(JMenuItem menuItemIngrediente) {
        this.menuItemIngrediente = menuItemIngrediente;
    }

    /**
     * @return the menuItemProducto
     */
    public JMenuItem getMenuItemProducto() {
        return menuItemProducto;
    }

    /**
     * @param menuItemProducto the menuItemProducto to set
     */
    public void setMenuItemProducto(JMenuItem menuItemProducto) {
        this.menuItemProducto = menuItemProducto;
    }

    /**
     * @return the menuItemTamaño
     */
    public JMenuItem getMenuItemTamaño() {
        return menuItemTamaño;
    }

    /**
     * @param menuItemTamaño the menuItemTamaño to set
     */
    public void setMenuItemTamaño(JMenuItem menuItemTamaño) {
        this.menuItemTamaño = menuItemTamaño;
    }

    /**
     * @return the menuItemTipoProducto
     */
    public JMenuItem getMenuItemTipoProducto() {
        return menuItemTipoProducto;
    }

    /**
     * @param menuItemTipoProducto the menuItemTipoProducto to set
     */
    public void setMenuItemTipoProducto(JMenuItem menuItemTipoProducto) {
        this.menuItemTipoProducto = menuItemTipoProducto;
    }

    /**
     * @return the menuItemVentaProducto
     */
    public JMenuItem getMenuItemVentaProducto() {
        return menuItemVentaProducto;
    }

    /**
     * @param menuItemVentaProducto the menuItemVentaProducto to set
     */
    public void setMenuItemVentaProducto(JMenuItem menuItemVentaProducto) {
        this.menuItemVentaProducto = menuItemVentaProducto;
    }

    /**
     * @return the menuItemEmpleado
     */
    public JMenuItem getMenuItemEmpleado() {
        return menuItemEmpleado;
    }

    /**
     * @param menuItemEmpleado the menuItemEmpleado to set
     */
    public void setMenuItemEmpleado(JMenuItem menuItemEmpleado) {
        this.menuItemEmpleado = menuItemEmpleado;
    }

    /**
     * @return the menuItemTipoEmpleado
     */
    public JMenuItem getMenuItemTipoEmpleado() {
        return menuItemTipoEmpleado;
    }

    /**
     * @param menuItemTipoEmpleado the menuItemTipoEmpleado to set
     */
    public void setMenuItemTipoEmpleado(JMenuItem menuItemTipoEmpleado) {
        this.menuItemTipoEmpleado = menuItemTipoEmpleado;
    }

    /**
     * @return the menuItemTamañoProducto
     */
    public JMenuItem getMenuItemTamañoProducto() {
        return menuItemTamañoProducto;
    }

    /**
     * @param menuItemTamañoProducto the menuItemTamañoProducto to set
     */
    public void setMenuItemTamañoProducto(JMenuItem menuItemTamañoProducto) {
        this.menuItemTamañoProducto = menuItemTamañoProducto;
    }

    /**
     * @return the menuItemIngredienteProducto
     */
    public JMenuItem getMenuItemIngredienteProducto() {
        return menuItemIngredienteProducto;
    }

    /**
     * @param menuItemIngredienteProducto the menuItemIngredienteProducto to set
     */
    public void setMenuItemIngredienteProducto(JMenuItem menuItemIngredienteProducto) {
        this.menuItemIngredienteProducto = menuItemIngredienteProducto;
    }

    /**
     * @return the menuItemUnidadIngrediente
     */
    public JMenuItem getMenuItemUnidadIngrediente() {
        return menuItemUnidadIngrediente;
    }

    /**
     * @param menuItemUnidadIngrediente the menuItemUnidadIngrediente to set
     */
    public void setMenuItemUnidadIngrediente(JMenuItem menuItemUnidadIngrediente) {
        this.menuItemUnidadIngrediente = menuItemUnidadIngrediente;
    }

    /**
     * @return the menuItemEmpleadoTipoEmpleado
     */
    public JMenuItem getMenuItemEmpleadoTipoEmpleado() {
        return menuItemEmpleadoTipoEmpleado;
    }

    /**
     * @param menuItemEmpleadoTipoEmpleado the menuItemEmpleadoTipoEmpleado to set
     */
    public void setMenuItemEmpleadoTipoEmpleado(JMenuItem menuItemEmpleadoTipoEmpleado) {
        this.menuItemEmpleadoTipoEmpleado = menuItemEmpleadoTipoEmpleado;
    }

    /**
     * @return the menuItemCuenta
     */
    public JMenuItem getMenuItemCuenta() {
        return menuItemCuenta;
    }

    /**
     * @param menuItemCuenta the menuItemCuenta to set
     */
    public void setMenuItemCuenta(JMenuItem menuItemCuenta) {
        this.menuItemCuenta = menuItemCuenta;
    }

        
}
