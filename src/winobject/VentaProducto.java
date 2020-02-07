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
public class VentaProducto {

        private Integer idVentaProducto;
    private int cantidadProducto;
    private int costoTotal;
    private Date lastUpdate;
    private boolean valid;
    private Empleado idEmpleado;
    private Producto idProducto;
    private Tamaño idTamaño;

    public VentaProducto() {
    }

    public VentaProducto(Integer idVentaProducto) {
        this.idVentaProducto = idVentaProducto;
    }

    public VentaProducto(Integer idVentaProducto, int cantidadProducto, int costoTotal, Date lastUpdate, boolean valid) {
        this.idVentaProducto = idVentaProducto;
        this.cantidadProducto = cantidadProducto;
        this.costoTotal = costoTotal;
        this.lastUpdate = lastUpdate;
        this.valid = valid;
    }

    public Integer getIdVentaProducto() {
        return idVentaProducto;
    }

    public void setIdVentaProducto(Integer idVentaProducto) {
        this.idVentaProducto = idVentaProducto;
    }

    public int getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(int cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    public int getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(int costoTotal) {
        this.costoTotal = costoTotal;
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

    public Empleado getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Empleado idEmpleado) {
        this.idEmpleado = idEmpleado;
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
