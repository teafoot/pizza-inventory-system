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
public class UnidadIngrediente{

    private Integer idUnidadIngrediente;
    private int cantidadUnidadIngrediente;
    private int costoUnidadIngrediente;
    private Date lastUpdate;
    private boolean valid;
    private Unidad idUnidad;
    private Ingrediente idIngrediente;

    public UnidadIngrediente() {
    }

    public UnidadIngrediente(Integer idUnidadIngrediente) {
        this.idUnidadIngrediente = idUnidadIngrediente;
    }

    public UnidadIngrediente(Integer idUnidadIngrediente, int cantidadUnidadIngrediente, int costoUnidadIngrediente, Date lastUpdate, boolean valid) {
        this.idUnidadIngrediente = idUnidadIngrediente;
        this.cantidadUnidadIngrediente = cantidadUnidadIngrediente;
        this.costoUnidadIngrediente = costoUnidadIngrediente;
        this.lastUpdate = lastUpdate;
        this.valid = valid;
    }

    public Integer getIdUnidadIngrediente() {
        return idUnidadIngrediente;
    }

    public void setIdUnidadIngrediente(Integer idUnidadIngrediente) {
        this.idUnidadIngrediente = idUnidadIngrediente;
    }

    public int getCantidadUnidadIngrediente() {
        return cantidadUnidadIngrediente;
    }

    public void setCantidadUnidadIngrediente(int cantidadUnidadIngrediente) {
        this.cantidadUnidadIngrediente = cantidadUnidadIngrediente;
    }

    public int getCostoUnidadIngrediente() {
        return costoUnidadIngrediente;
    }

    public void setCostoUnidadIngrediente(int costoUnidadIngrediente) {
        this.costoUnidadIngrediente = costoUnidadIngrediente;
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
    
}
