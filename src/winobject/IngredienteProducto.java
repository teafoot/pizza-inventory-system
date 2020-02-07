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

public class IngredienteProducto{

   
    private Integer idIngredienteProducto;
    private int cantidadIngredienteProducto;
    private Date lastUpdate;
    private boolean valid;
    private Unidad idUnidad;
    private Ingrediente idIngrediente;
    private Producto idProducto;
    private Tamaño idTamaño;

    public IngredienteProducto() {
    }

    public IngredienteProducto(Integer idIngredienteProducto) {
        this.idIngredienteProducto = idIngredienteProducto;
    }

    public IngredienteProducto(Integer idIngredienteProducto, int cantidadIngredienteProducto, Date lastUpdate, boolean valid) {
        this.idIngredienteProducto = idIngredienteProducto;
        this.cantidadIngredienteProducto = cantidadIngredienteProducto;
        this.lastUpdate = lastUpdate;
        this.valid = valid;
    }

    public Integer getIdIngredienteProducto() {
        return idIngredienteProducto;
    }

    public void setIdIngredienteProducto(Integer idIngredienteProducto) {
        this.idIngredienteProducto = idIngredienteProducto;
    }

    public int getCantidadIngredienteProducto() {
        return cantidadIngredienteProducto;
    }

    public void setCantidadIngredienteProducto(int cantidadIngredienteProducto) {
        this.cantidadIngredienteProducto = cantidadIngredienteProducto;
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

    public Unidad getIdUnidad() {
        return idUnidad;
    }

    public void setIdUnidad(Unidad idUnidad) {
        this.idUnidad = idUnidad;
    }

    public Ingrediente getIdIngrediente() {
        return idIngrediente;
    }

    public void setIdIngrediente(Ingrediente idIngrediente) {
        this.idIngrediente = idIngrediente;
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
