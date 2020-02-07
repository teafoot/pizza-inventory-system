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
public class Empleado {

    private Integer idEmpleado;
    private String nombreCompletoEmpleado;
    private Date fechaNacimientoEmpleado;
    private String celulaIdentidadEmpleado;
    private String numeroCelularEmpleado;
    private Date lastUpdate;
    private boolean valid;
    private Collection<EmpleadoTipoEmpleado> empleadoTipoEmpleadoCollection;
    private Collection<VentaProducto> ventaProductoCollection;

    public Empleado() {
    }

    public Empleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Empleado(Integer idEmpleado, String nombreCompletoEmpleado, Date fechaNacimientoEmpleado, String celulaIdentidadEmpleado, String numeroCelularEmpleado, Date lastUpdate, boolean valid) {
        this.idEmpleado = idEmpleado;
        this.nombreCompletoEmpleado = nombreCompletoEmpleado;
        this.fechaNacimientoEmpleado = fechaNacimientoEmpleado;
        this.celulaIdentidadEmpleado = celulaIdentidadEmpleado;
        this.numeroCelularEmpleado = numeroCelularEmpleado;
        this.lastUpdate = lastUpdate;
        this.valid = valid;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombreCompletoEmpleado() {
        return nombreCompletoEmpleado;
    }

    public void setNombreCompletoEmpleado(String nombreCompletoEmpleado) {
        this.nombreCompletoEmpleado = nombreCompletoEmpleado;
    }

    public Date getFechaNacimientoEmpleado() {
        return fechaNacimientoEmpleado;
    }

    public void setFechaNacimientoEmpleado(Date fechaNacimientoEmpleado) {
        this.fechaNacimientoEmpleado = fechaNacimientoEmpleado;
    }

    public String getCelulaIdentidadEmpleado() {
        return celulaIdentidadEmpleado;
    }

    public void setCelulaIdentidadEmpleado(String celulaIdentidadEmpleado) {
        this.celulaIdentidadEmpleado = celulaIdentidadEmpleado;
    }

    public String getNumeroCelularEmpleado() {
        return numeroCelularEmpleado;
    }

    public void setNumeroCelularEmpleado(String numeroCelularEmpleado) {
        this.numeroCelularEmpleado = numeroCelularEmpleado;
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

    public Collection<EmpleadoTipoEmpleado> getEmpleadoTipoEmpleadoCollection() {
        return empleadoTipoEmpleadoCollection;
    }

    public void setEmpleadoTipoEmpleadoCollection(Collection<EmpleadoTipoEmpleado> empleadoTipoEmpleadoCollection) {
        this.empleadoTipoEmpleadoCollection = empleadoTipoEmpleadoCollection;
    }

    public Collection<VentaProducto> getVentaProductoCollection() {
        return ventaProductoCollection;
    }

    public void setVentaProductoCollection(Collection<VentaProducto> ventaProductoCollection) {
        this.ventaProductoCollection = ventaProductoCollection;
    }

}
