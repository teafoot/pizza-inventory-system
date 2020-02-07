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
public class TipoEmpleado {

    private Integer idTipoEmpleado;
    private String nombreTipoEmpleado;
    private int salarioTipoEmpleado;
    private String horarioTipoEmpleado;
    private String descripcionTipoEmpleado;
    private Date lastUpdate;
    private boolean valid;
    private Collection<EmpleadoTipoEmpleado> empleadoTipoEmpleadoCollection;

    public TipoEmpleado() {
    }

    public TipoEmpleado(Integer idTipoEmpleado) {
        this.idTipoEmpleado = idTipoEmpleado;
    }

    public TipoEmpleado(Integer idTipoEmpleado, String nombreTipoEmpleado, int salarioTipoEmpleado, String horarioTipoEmpleado, String descripcionTipoEmpleado, Date lastUpdate, boolean valid) {
        this.idTipoEmpleado = idTipoEmpleado;
        this.nombreTipoEmpleado = nombreTipoEmpleado;
        this.salarioTipoEmpleado = salarioTipoEmpleado;
        this.horarioTipoEmpleado = horarioTipoEmpleado;
        this.descripcionTipoEmpleado = descripcionTipoEmpleado;
        this.lastUpdate = lastUpdate;
        this.valid = valid;
    }

    public Integer getIdTipoEmpleado() {
        return idTipoEmpleado;
    }

    public void setIdTipoEmpleado(Integer idTipoEmpleado) {
        this.idTipoEmpleado = idTipoEmpleado;
    }

    public String getNombreTipoEmpleado() {
        return nombreTipoEmpleado;
    }

    public void setNombreTipoEmpleado(String nombreTipoEmpleado) {
        this.nombreTipoEmpleado = nombreTipoEmpleado;
    }

    public int getSalarioTipoEmpleado() {
        return salarioTipoEmpleado;
    }

    public void setSalarioTipoEmpleado(int salarioTipoEmpleado) {
        this.salarioTipoEmpleado = salarioTipoEmpleado;
    }

    public String getHorarioTipoEmpleado() {
        return horarioTipoEmpleado;
    }

    public void setHorarioTipoEmpleado(String horarioTipoEmpleado) {
        this.horarioTipoEmpleado = horarioTipoEmpleado;
    }

    public String getDescripcionTipoEmpleado() {
        return descripcionTipoEmpleado;
    }

    public void setDescripcionTipoEmpleado(String descripcionTipoEmpleado) {
        this.descripcionTipoEmpleado = descripcionTipoEmpleado;
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
    
}
