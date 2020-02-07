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
package winobject;

import java.util.Date;

/**
 *
 * @author davidchenlo
 */

public class TamañoProducto {

    
    private Integer idTamañoProducto;
       private int precioProducto;
       private Date lastUpdate;
       private boolean valid;
       private Producto idProducto;
      private Tamaño idTamaño;

    public TamañoProducto() {
    }

    public TamañoProducto(Integer idTamañoProducto) {
        this.idTamañoProducto = idTamañoProducto;
    }

    public TamañoProducto(Integer idTamañoProducto, int precioProducto, Date lastUpdate, boolean valid) {
        this.idTamañoProducto = idTamañoProducto;
        this.precioProducto = precioProducto;
        this.lastUpdate = lastUpdate;
        this.valid = valid;
    }

    public Integer getIdTamañoProducto() {
        return idTamañoProducto;
    }

    public void setIdTamañoProducto(Integer idTamañoProducto) {
        this.idTamañoProducto = idTamañoProducto;
    }

    public int getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(int precioProducto) {
        this.precioProducto = precioProducto;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public boolean getValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public Producto getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Producto idProducto) {
        this.idProducto = idProducto;
    }

    public Tamaño getIdTamaño() {
        return idTamaño;
    }

    public void setIdTamaño(Tamaño idTamaño) {
        this.idTamaño = idTamaño;
    }   
    
}
