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
package pizzeriatimbo_mvc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Properties;
import managedatabase.ManageConnection;

/**
 *
 * @author davidchenlo
 */
public class MySQL {

    private Properties properties;
    private Connection connection;
    private final String DATABASE_URL = "jdbc:mysql://localhost:3306/pizzeria_timbo";
    private final String DATABASE_DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String USERNAME = "root";
    private final String PASSWORD = "";
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private ResultSetMetaData resultSetMetaData;

    private Properties getProperties() {
        if (properties == null) {
            properties = new Properties();
            properties.setProperty("user", USERNAME);
            properties.setProperty("password", PASSWORD);
        }
        return properties;
    }

    public Connection connect() {
        if (getConnection() == null) {
            try {
                Class.forName(DATABASE_DRIVER);
                this.setConnection(DriverManager.getConnection(DATABASE_URL, getProperties()));
            } catch (ClassNotFoundException | SQLException ex) {
                ex.getStackTrace();
            }
        }
        return getConnection();
    }

    public void disconnect() {
        if (getConnection() != null) {
            try {
                this.getConnection().close();
                this.setConnection(null);
            } catch (SQLException ex) {
                ex.getStackTrace();
            }
        }
    }

    /**
     * @return the connection
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * @param connection the connection to set
     */
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    /**
     * @return the resultSet
     */
    public ResultSet getResultSet() {
        return resultSet;
    }

    /**
     * @param resultSet the resultSet to set
     */
    public void setResultSet(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    /**
     * @return the resultSetMetaData
     */
    public ResultSetMetaData getResultSetMetaData() {
        return resultSetMetaData;
    }

    /**
     * @param resultSetMetaData the resultSetMetaData to set
     */
    public void setResultSetMetaData(ResultSetMetaData resultSetMetaData) {
        this.resultSetMetaData = resultSetMetaData;
    }

    /**
     * @return the preparedStatement
     */
    public PreparedStatement getPreparedStatement() {
        return preparedStatement;
    }

    /**
     * @param preparedStatement the preparedStatement to set
     */
    public void setPreparedStatement(PreparedStatement preparedStatement) {
        this.preparedStatement = preparedStatement;
    }
}
