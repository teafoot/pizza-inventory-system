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

import pizzeriatimbo_mvc.modelo.*;
import pizzeriatimbo_mvc.vista.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;

/**
 *
 * @author davidchenlo
 */
public class AdminFormControlador {

    private final AdminFormModelo modelo;
    private final AdminFormVista vista;

    public AdminFormControlador(AdminFormModelo adminFormModelo, AdminFormVista adminFormVista) {
        this.modelo = adminFormModelo;
        this.vista = adminFormVista;
        this.vista.menuItemEmpleadoSetActionListener(new ActionListenerMenuItemEmpleado());
        this.vista.menuItemIngredienteSetActionListener(new ActionListenerMenuItemIngrediente());
        this.vista.menuItemProductoSetActionListener(new ActionListenerMenuItemProducto());
        this.vista.menuItemTamañoSetActionListener(new ActionListenerMenuItemTamaño());
        this.vista.menuItemTipoEmpleadoSetActionListener(new ActionListenerMenuItemTipoEmpleado());
        this.vista.menuItemTipoProductoSetActionListener(new ActionListenerMenuItemTipoProducto());
        this.vista.menuItemUnidadSetActionListener(new ActionListenerMenuItemUnidad());
        this.vista.menuItemVentaProductoSetActionListener(new ActionListenerMenuItemVentaProducto());
        this.vista.menuItemTamañoProductoSetActionListener(new ActionListenerMenuItemTamañoProducto());
        this.vista.menuItemIngredienteProductoSetActionListener(new ActionListenerMenuItemIngredienteProducto());
        this.vista.menuItemUnidadIngredienteSetActionListener(new ActionListenerMenuItemUnidadIngrediente());
        this.vista.menuItemEmpleadoTipoEmpleadoSetActionListener(new ActionListenerMenuItemEmpleadoTipoEmpleado());
        this.vista.menuItemCuentaSetActionListener(new ActionListenerMenuItemCuenta());
    }

    class ActionListenerMenuItemEmpleado implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            EmpleadoFormModelo empleadoFormModelo = new EmpleadoFormModelo();
            EmpleadoFormVista empleadoFormVista = new EmpleadoFormVista();
            EmpleadoFormControlador empleadoFormControlador = new EmpleadoFormControlador(empleadoFormModelo, empleadoFormVista);
            vista.getDesktopPane().add(empleadoFormVista);
            try {
                empleadoFormVista.setMaximum(true);
            } catch (PropertyVetoException ex) {
                ex.getStackTrace();
            }
            empleadoFormVista.pack();
            empleadoFormVista.setVisible(true);
        }

    }

    class ActionListenerMenuItemIngrediente implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            IngredienteFormModelo ingredienteFormModelo = new IngredienteFormModelo();
            IngredienteFormVista ingredienteFormVista = new IngredienteFormVista();
            IngredienteFormControlador ingredienteFormControlador = new IngredienteFormControlador(ingredienteFormModelo, ingredienteFormVista);
            vista.getDesktopPane().add(ingredienteFormVista);
            try {
                ingredienteFormVista.setMaximum(true);
            } catch (PropertyVetoException ex) {
                ex.getStackTrace();
            }
            ingredienteFormVista.pack();
            ingredienteFormVista.setVisible(true);
        }
    }

    class ActionListenerMenuItemProducto implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            ProductoFormModelo productoFormModelo = new ProductoFormModelo();
            ProductoFormVista productoFormVista = new ProductoFormVista();
            ProductoFormControlador productoFormControlador = new ProductoFormControlador(productoFormModelo, productoFormVista);
            vista.getDesktopPane().add(productoFormVista);
            try {
                productoFormVista.setMaximum(true);
            } catch (PropertyVetoException ex) {
                ex.getStackTrace();
            }
            productoFormVista.pack();
            productoFormVista.setVisible(true);
        }

    }

    class ActionListenerMenuItemTamaño implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            TamañoFormModelo tamañoFormModelo = new TamañoFormModelo();
            TamañoFormVista tamañoFormVista = new TamañoFormVista();
            TamañoFormControlador tamañoFormControlador = new TamañoFormControlador(tamañoFormModelo, tamañoFormVista);
            vista.getDesktopPane().add(tamañoFormVista);
            try {
                tamañoFormVista.setMaximum(true);
            } catch (PropertyVetoException ex) {
                ex.getStackTrace();
            }
            tamañoFormVista.pack();
            tamañoFormVista.setVisible(true);
        }

    }

    class ActionListenerMenuItemTipoEmpleado implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            TipoEmpleadoFormModelo tipoEmpleadoFormModelo = new TipoEmpleadoFormModelo();
            TipoEmpleadoFormVista tipoEmpleadoFormVista = new TipoEmpleadoFormVista();
            TipoEmpleadoFormControlador tipoEmpleadoFormControlador = new TipoEmpleadoFormControlador(tipoEmpleadoFormModelo, tipoEmpleadoFormVista);
            vista.getDesktopPane().add(tipoEmpleadoFormVista);
            try {
                tipoEmpleadoFormVista.setMaximum(true);
            } catch (PropertyVetoException ex) {
                ex.getStackTrace();
            }
            tipoEmpleadoFormVista.pack();
            tipoEmpleadoFormVista.setVisible(true);
        }

    }

    class ActionListenerMenuItemTipoProducto implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            TipoProductoFormModelo tipoProductoFormModelo = new TipoProductoFormModelo();
            TipoProductoFormVista tipoProductoFormVista = new TipoProductoFormVista();
            TipoProductoFormControlador tipoProductoFormControlador = new TipoProductoFormControlador(tipoProductoFormModelo, tipoProductoFormVista);
            vista.getDesktopPane().add(tipoProductoFormVista);
            try {
                tipoProductoFormVista.setMaximum(true);
            } catch (PropertyVetoException ex) {
                ex.getStackTrace();
            }
            tipoProductoFormVista.pack();
            tipoProductoFormVista.setVisible(true);
        }

    }

    class ActionListenerMenuItemUnidad implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            UnidadFormModelo unidadFormModelo = new UnidadFormModelo();
            UnidadFormVista unidadFormVista = new UnidadFormVista();
            UnidadFormControlador unidadFormControlador = new UnidadFormControlador(unidadFormModelo, unidadFormVista);
            vista.getDesktopPane().add(unidadFormVista);
            try {
                unidadFormVista.setMaximum(true);
            } catch (PropertyVetoException ex) {
                ex.getStackTrace();
            }
            unidadFormVista.pack();
            unidadFormVista.setVisible(true);
        }

    }

    class ActionListenerMenuItemTamañoProducto implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            TamañoProductoFormModelo tamañoProductoFormModelo = new TamañoProductoFormModelo();
            TamañoProductoFormVista tamañoProductoFormVista = new TamañoProductoFormVista();
            TamañoProductoFormControlador tamañoProductoFormControlador = new TamañoProductoFormControlador(tamañoProductoFormModelo, tamañoProductoFormVista);
            vista.getDesktopPane().add(tamañoProductoFormVista);
            try {
                tamañoProductoFormVista.setMaximum(true);
            } catch (PropertyVetoException ex) {
                ex.getStackTrace();
            }
            tamañoProductoFormVista.pack();
            tamañoProductoFormVista.setVisible(true);
        }

    }

    class ActionListenerMenuItemIngredienteProducto implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            IngredienteProductoFormModelo ingredienteProductoFormModelo = new IngredienteProductoFormModelo();
            IngredienteProductoFormVista ingredienteProductoFormVista = new IngredienteProductoFormVista();
            IngredienteProductoFormControlador ingredienteProductoFormControlador = new IngredienteProductoFormControlador(ingredienteProductoFormModelo, ingredienteProductoFormVista);
            vista.getDesktopPane().add(ingredienteProductoFormVista);
            try {
                ingredienteProductoFormVista.setMaximum(true);
            } catch (PropertyVetoException ex) {
                ex.getStackTrace();
            }
            ingredienteProductoFormVista.pack();
            ingredienteProductoFormVista.setVisible(true);
        }

    }

    class ActionListenerMenuItemUnidadIngrediente implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            UnidadIngredienteFormModelo unidadIngredienteFormModelo = new UnidadIngredienteFormModelo();
            UnidadIngredienteFormVista unidadIngredienteFormVista = new UnidadIngredienteFormVista();
            UnidadIngredienteFormControlador unidadIngredienteFormControlador = new UnidadIngredienteFormControlador(unidadIngredienteFormModelo, unidadIngredienteFormVista);
            vista.getDesktopPane().add(unidadIngredienteFormVista);
            try {
                unidadIngredienteFormVista.setMaximum(true);
            } catch (PropertyVetoException ex) {
                ex.getStackTrace();
            }
            unidadIngredienteFormVista.pack();
            unidadIngredienteFormVista.setVisible(true);
        }

    }

    class ActionListenerMenuItemVentaProducto implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            VentaProductoFormModelo ventaProductoFormModelo = new VentaProductoFormModelo();
            VentaProductoFormVista ventaProductoFormVista = new VentaProductoFormVista();
            VentaProductoFormControlador ventaProductoFormControlador = new VentaProductoFormControlador(ventaProductoFormModelo, ventaProductoFormVista);
            vista.getDesktopPane().add(ventaProductoFormVista);
            try {
                ventaProductoFormVista.setMaximum(true);
            } catch (PropertyVetoException ex) {
                ex.getStackTrace();
            }
            ventaProductoFormVista.pack();
            ventaProductoFormVista.setVisible(true);
        }

    }

    class ActionListenerMenuItemEmpleadoTipoEmpleado implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            EmpleadoTipoEmpleadoFormModelo empleadoTipoEmpleadoFormModelo = new EmpleadoTipoEmpleadoFormModelo();
            EmpleadoTipoEmpleadoFormVista empleadoTipoEmpleadoFormVista = new EmpleadoTipoEmpleadoFormVista();
            EmpleadoTipoEmpleadoFormControlador empleadoTipoEmpleadoFormControlador = new EmpleadoTipoEmpleadoFormControlador(empleadoTipoEmpleadoFormModelo, empleadoTipoEmpleadoFormVista);
            vista.getDesktopPane().add(empleadoTipoEmpleadoFormVista);
            try {
                empleadoTipoEmpleadoFormVista.setMaximum(true);
            } catch (PropertyVetoException ex) {
                ex.getStackTrace();
            }
            empleadoTipoEmpleadoFormVista.pack();
            empleadoTipoEmpleadoFormVista.setVisible(true);
        }

    }

    class ActionListenerMenuItemCuenta implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            CuentaFormModelo CuentaFormModelo = new CuentaFormModelo();
            CuentaFormVista CuentaFormVista = new CuentaFormVista();
            CuentaFormControlador cuentaFormControlador = new CuentaFormControlador(CuentaFormModelo, CuentaFormVista);
            vista.getDesktopPane().add(CuentaFormVista);
            try {
                CuentaFormVista.setMaximum(true);
            } catch (PropertyVetoException ex) {
                ex.getStackTrace();
            }
            CuentaFormVista.pack();
            CuentaFormVista.setVisible(true);
        }

    }

}
