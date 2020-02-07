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

/**
 *
 * @author davidchenlo
 */
public class EmpleadoFormModelo {

    private final MySQL mySQL;

    public EmpleadoFormModelo() {
        this.mySQL = new MySQL();
    }

    public java.sql.Date convertUtilDateToSqlDate(java.util.Date utilDate) {
        if (utilDate != null) {
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            return sqlDate;
        } else {
            return null;
        }
    }

    /**
     * @return the mySQL
     */
    public MySQL getMySQL() {
        return mySQL;
    }
}
