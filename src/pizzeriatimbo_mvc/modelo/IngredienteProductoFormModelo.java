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
package pizzeriatimbo_mvc.modelo;

import pizzeriatimbo_mvc.MySQL;

import java.sql.SQLException;

/**
 *
 * @author davidchenlo
 */
public class IngredienteProductoFormModelo {
    private final MySQL mySQL1;
    private MySQL mySQL2;

    public IngredienteProductoFormModelo() {
        this.mySQL1 = new MySQL();
    }

    public String searchFKStringUnidad(int id_unidad) {
        String nombre_unidad = "";
        final String sql = "select nombre_unidad from unidad where id_unidad=? and valid=?";
        try {
            this.setMySQL2(new MySQL());
            this.getMySQL2().connect();
            this.getMySQL2().setPreparedStatement(this.getMySQL2().getConnection().prepareStatement(sql));
            this.getMySQL2().getPreparedStatement().setInt(1, id_unidad);
            this.getMySQL2().getPreparedStatement().setBoolean(2, true);
            this.getMySQL2().setResultSet(this.getMySQL2().getPreparedStatement().executeQuery());
            if (this.getMySQL2().getResultSet().next()) {
                nombre_unidad = this.getMySQL2().getResultSet().getString(1);
            }
            this.getMySQL2().getResultSet().close();
            this.getMySQL2().getPreparedStatement().close();
            this.getMySQL2().disconnect();
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
        return nombre_unidad;
    }

    public int searchFKIntUnidad(String nombre_unidad) {
        int id_unidad = -1;
        final String sql = "select id_unidad from unidad where nombre_unidad=? and valid=?";
        try {
            this.setMySQL2(new MySQL());
            this.getMySQL2().connect();
            this.getMySQL2().setPreparedStatement(this.getMySQL2().getConnection().prepareStatement(sql));
            this.getMySQL2().getPreparedStatement().setString(1, nombre_unidad);
            this.getMySQL2().getPreparedStatement().setBoolean(2, true);
            this.getMySQL2().setResultSet(this.getMySQL2().getPreparedStatement().executeQuery());
            if (this.getMySQL2().getResultSet().next()) {
                id_unidad = this.getMySQL2().getResultSet().getInt(1);
            }
            this.getMySQL2().getResultSet().close();
            this.getMySQL2().getPreparedStatement().close();
            this.getMySQL2().disconnect();
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
        return id_unidad;
    }
    
    public String searchFKStringIngrediente(int id_ingrediente) {
        String nombre_ingrediente = "";
        final String sql = "select nombre_ingrediente from ingrediente where id_ingrediente=? and valid=?";
        try {
            this.setMySQL2(new MySQL());
            this.getMySQL2().connect();
            this.getMySQL2().setPreparedStatement(this.getMySQL2().getConnection().prepareStatement(sql));
            this.getMySQL2().getPreparedStatement().setInt(1, id_ingrediente);
            this.getMySQL2().getPreparedStatement().setBoolean(2, true);
            this.getMySQL2().setResultSet(this.getMySQL2().getPreparedStatement().executeQuery());
            if (this.getMySQL2().getResultSet().next()) {
                nombre_ingrediente = this.getMySQL2().getResultSet().getString(1);
            }
            this.getMySQL2().getResultSet().close();
            this.getMySQL2().getPreparedStatement().close();
            this.getMySQL2().disconnect();
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
        return nombre_ingrediente;
    }

    public int searchFKIntIngrediente(String nombre_ingrediente) {
        int id_ingrediente = -1;
        final String sql = "select id_ingrediente from ingrediente where nombre_ingrediente=? and valid=?";
        try {
            this.setMySQL2(new MySQL());
            this.getMySQL2().connect();
            this.getMySQL2().setPreparedStatement(this.getMySQL2().getConnection().prepareStatement(sql));
            this.getMySQL2().getPreparedStatement().setString(1, nombre_ingrediente);
            this.getMySQL2().getPreparedStatement().setBoolean(2, true);
            this.getMySQL2().setResultSet(this.getMySQL2().getPreparedStatement().executeQuery());
            if (this.getMySQL2().getResultSet().next()) {
                id_ingrediente = this.getMySQL2().getResultSet().getInt(1);
            }
            this.getMySQL2().getResultSet().close();
            this.getMySQL2().getPreparedStatement().close();
            this.getMySQL2().disconnect();
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
        return id_ingrediente;
    }
    
    
    public String searchFKStringTamaño(int id_tamaño) {
        String nombre_tamaño = "";
        final String sql = "select nombre_tama\u00f1o from tama\u00f1o where id_tama\u00f1o=? and valid=?";
        try {
            this.setMySQL2(new MySQL());
            this.getMySQL2().connect();
            this.getMySQL2().setPreparedStatement(this.getMySQL2().getConnection().prepareStatement(sql));
            this.getMySQL2().getPreparedStatement().setInt(1, id_tamaño);
            this.getMySQL2().getPreparedStatement().setBoolean(2, true);
            this.getMySQL2().setResultSet(this.getMySQL2().getPreparedStatement().executeQuery());
            if (this.getMySQL2().getResultSet().next()) {
                nombre_tamaño = this.getMySQL2().getResultSet().getString(1);
            }
            this.getMySQL2().getResultSet().close();
            this.getMySQL2().getPreparedStatement().close();
            this.getMySQL2().disconnect();
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
        return nombre_tamaño;
    }

    public int searchFKIntTamaño(String nombre_tamaño) {
        int id_tamaño = -1;
        final String sql = "select id_tama\u00f1o from tama\u00f1o where nombre_tama\u00f1o=? and valid=?";
        try {
            this.setMySQL2(new MySQL());
            this.getMySQL2().connect();
            this.getMySQL2().setPreparedStatement(this.getMySQL2().getConnection().prepareStatement(sql));
            this.getMySQL2().getPreparedStatement().setString(1, nombre_tamaño);
            this.getMySQL2().getPreparedStatement().setBoolean(2, true);
            this.getMySQL2().setResultSet(this.getMySQL2().getPreparedStatement().executeQuery());
            if (this.getMySQL2().getResultSet().next()) {
                id_tamaño = this.getMySQL2().getResultSet().getInt(1);
            }
            this.getMySQL2().getResultSet().close();
            this.getMySQL2().getPreparedStatement().close();
            this.getMySQL2().disconnect();
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
        return id_tamaño;
    }

    public int searchFKIntProducto(String nombre_producto) {
        int id_producto = -1;
        final String sql = "select id_producto from producto where nombre_producto=? and valid=?";
        try {
            this.setMySQL2(new MySQL());
            this.getMySQL2().connect();
            this.getMySQL2().setPreparedStatement(this.getMySQL2().getConnection().prepareStatement(sql));
            this.getMySQL2().getPreparedStatement().setString(1, nombre_producto);
            this.getMySQL2().getPreparedStatement().setBoolean(2, true);
            this.getMySQL2().setResultSet(this.getMySQL2().getPreparedStatement().executeQuery());
            if (this.getMySQL2().getResultSet().next()) {
                id_producto = this.getMySQL2().getResultSet().getInt(1);
            }
            this.getMySQL2().getResultSet().close();
            this.getMySQL2().getPreparedStatement().close();
            this.getMySQL2().disconnect();
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
        return id_producto;
    }

    public String searchFKStringProducto(int id_producto) {
        String nombre_producto = "";
        final String sql = "select nombre_producto from producto where id_producto=? and valid=?";
        try {
            this.setMySQL2(new MySQL());
            this.getMySQL2().connect();
            this.getMySQL2().setPreparedStatement(this.getMySQL2().getConnection().prepareStatement(sql));
            this.getMySQL2().getPreparedStatement().setInt(1, id_producto);
            this.getMySQL2().getPreparedStatement().setBoolean(2, true);
            this.getMySQL2().setResultSet(this.getMySQL2().getPreparedStatement().executeQuery());
            if (this.getMySQL2().getResultSet().next()) {
                nombre_producto = this.getMySQL2().getResultSet().getString(1);
            }
            this.getMySQL2().getResultSet().close();
            this.getMySQL2().getPreparedStatement().close();
            this.getMySQL2().disconnect();
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
        return nombre_producto;
    }
    
    public class Unidad {

        private final String nombre;
        private final int id;

        public Unidad(int id, String nombre) {
            this.id = id;
            this.nombre = nombre;
        }

        public int getID() {
            return id;
        }

        @Override
        public String toString() {
            return nombre;
        }
    }
    
    public class Ingrediente {

        private final String nombre;
        private final int id;

        public Ingrediente(int id, String nombre) {
            this.id = id;
            this.nombre = nombre;
        }

        public int getID() {
            return id;
        }

        @Override
        public String toString() {
            return nombre;
        }
    }

    public class Tamaño {

        private final String nombre;
        private final int id;

        public Tamaño(int id, String nombre) {
            this.id = id;
            this.nombre = nombre;
        }

        public int getID() {
            return id;
        }

        @Override
        public String toString() {
            return nombre;
        }
    }

    public class Producto {

        private final String nombre;
        private final int id;

        public Producto(int id, String nombre) {
            this.id = id;
            this.nombre = nombre;
        }

        public int getID() {
            return id;
        }

        @Override
        public String toString() {
            return nombre;
        }
    }

    /**
     * @return the mySQL1
     */
    public MySQL getMySQL1() {
        return mySQL1;
    }

    /**
     * @param mySQL2 the mySQL2 to set
     */
    public void setMySQL2(MySQL mySQL2) {
        this.mySQL2 = mySQL2;
    }

    /**
     * @return the mySQL2
     */
    public MySQL getMySQL2() {
        return mySQL2;
    }
}
