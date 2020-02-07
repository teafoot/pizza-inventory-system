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

import java.util.Collection;
import java.util.Date;

/**
 *
 * @author davidchenlo
 */

public class Tamaño {

    
    private Integer idTamaño;
    private String nombreTamaño;
    private String descripcionTamaño;
    private Date lastUpdate;
    private boolean valid;
    private Collection<TamañoProducto> tamañoProductoCollection;
    private Collection<IngredienteProducto> ingredienteProductoCollection;
    private Collection<VentaProducto> ventaProductoCollection;

    public Tamaño() {
    }

    public Tamaño(Integer idTamaño) {
        this.idTamaño = idTamaño;
    }

    public Tamaño(Integer idTamaño, String nombreTamaño, String descripcionTamaño, Date lastUpdate, boolean valid) {
        this.idTamaño = idTamaño;
        this.nombreTamaño = nombreTamaño;
        this.descripcionTamaño = descripcionTamaño;
        this.lastUpdate = lastUpdate;
        this.valid = valid;
    }

    public Integer getIdTamaño() {
        return idTamaño;
    }

    public void setIdTamaño(Integer idTamaño) {
        this.idTamaño = idTamaño;
    }

    public String getNombreTamaño() {
        return nombreTamaño;
    }

    public void setNombreTamaño(String nombreTamaño) {
        this.nombreTamaño = nombreTamaño;
    }

    public String getDescripcionTamaño() {
        return descripcionTamaño;
    }

    public void setDescripcionTamaño(String descripcionTamaño) {
        this.descripcionTamaño = descripcionTamaño;
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

    public Collection<TamañoProducto> getTamañoProductoCollection() {
        return tamañoProductoCollection;
    }

    public void setTamañoProductoCollection(Collection<TamañoProducto> tamañoProductoCollection) {
        this.tamañoProductoCollection = tamañoProductoCollection;
    }

    public Collection<IngredienteProducto> getIngredienteProductoCollection() {
        return ingredienteProductoCollection;
    }

    public void setIngredienteProductoCollection(Collection<IngredienteProducto> ingredienteProductoCollection) {
        this.ingredienteProductoCollection = ingredienteProductoCollection;
    }

    public Collection<VentaProducto> getVentaProductoCollection() {
        return ventaProductoCollection;
    }

    public void setVentaProductoCollection(Collection<VentaProducto> ventaProductoCollection) {
        this.ventaProductoCollection = ventaProductoCollection;
    }
   
}
