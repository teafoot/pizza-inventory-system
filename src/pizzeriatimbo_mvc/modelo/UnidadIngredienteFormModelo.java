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
public class UnidadIngredienteFormModelo {
    
    private final MySQL mySQL1;
    private MySQL mySQL2;

    public UnidadIngredienteFormModelo() {
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
