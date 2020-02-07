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

import java.util.ArrayList;

/**
 *
 * @author davidchenlo
 */
public class LoginFormModelo {

    private final MySQL mySQL;
    private ArrayList<String> username = new ArrayList<>();
    private ArrayList<String> password = new ArrayList<>();

    public LoginFormModelo() {
        this.mySQL = new MySQL();
    }

    /**
     * @return the mySQL
     */
    public MySQL getMySQL() {
        return mySQL;
    }

    /**
     * @return the username
     */
    public ArrayList<String> getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(ArrayList<String> username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public ArrayList<String> getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(ArrayList<String> password) {
        this.password = password;
    }

}
