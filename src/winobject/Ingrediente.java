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
public class Ingrediente{

    private Integer idIngrediente;
    private String nombreIngrediente;
    private String descripcionIngrediente;
    private Date lastUpdate;
    private boolean valid;
    private Collection<IngredienteProducto> ingredienteProductoCollection;
    private Collection<UnidadIngrediente> unidadIngredienteCollection;

    public Ingrediente() {
    }

    public Ingrediente(Integer idIngrediente) {
        this.idIngrediente = idIngrediente;
    }

    public Ingrediente(Integer idIngrediente, String nombreIngrediente, String descripcionIngrediente, Date lastUpdate, boolean valid) {
        this.idIngrediente = idIngrediente;
        this.nombreIngrediente = nombreIngrediente;
        this.descripcionIngrediente = descripcionIngrediente;
        this.lastUpdate = lastUpdate;
        this.valid = valid;
    }

    public Integer getIdIngrediente() {
        return idIngrediente;
    }

    public void setIdIngrediente(Integer idIngrediente) {
        this.idIngrediente = idIngrediente;
    }

    public String getNombreIngrediente() {
        return nombreIngrediente;
    }

    public void setNombreIngrediente(String nombreIngrediente) {
        this.nombreIngrediente = nombreIngrediente;
    }

    public String getDescripcionIngrediente() {
        return descripcionIngrediente;
    }

    public void setDescripcionIngrediente(String descripcionIngrediente) {
        this.descripcionIngrediente = descripcionIngrediente;
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

    public Collection<IngredienteProducto> getIngredienteProductoCollection() {
        return ingredienteProductoCollection;
    }

    public void setIngredienteProductoCollection(Collection<IngredienteProducto> ingredienteProductoCollection) {
        this.ingredienteProductoCollection = ingredienteProductoCollection;
    }

    public Collection<UnidadIngrediente> getUnidadIngredienteCollection() {
        return unidadIngredienteCollection;
    }

    public void setUnidadIngredienteCollection(Collection<UnidadIngrediente> unidadIngredienteCollection) {
        this.unidadIngredienteCollection = unidadIngredienteCollection;
    } 
    
}
