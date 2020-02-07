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
public class EmpleadoTipoEmpleado {

    private Integer idEmpleadoTipoEmpleado;
    private Date fechaIngreso;
    private Date fechaRetiro;
    private int horasTrabajadas;
    private int horasPagadas;
    private Date lastUpdate;
    private boolean valid;
    private Empleado idEmpleado;
    private TipoEmpleado idTipoEmpleado;

    public EmpleadoTipoEmpleado() {
    }

    public EmpleadoTipoEmpleado(Integer idEmpleadoTipoEmpleado) {
        this.idEmpleadoTipoEmpleado = idEmpleadoTipoEmpleado;
    }

    public EmpleadoTipoEmpleado(Integer idEmpleadoTipoEmpleado, Date fechaIngreso, int horasTrabajadas, int horasPagadas, Date lastUpdate, boolean valid) {
        this.idEmpleadoTipoEmpleado = idEmpleadoTipoEmpleado;
        this.fechaIngreso = fechaIngreso;
        this.horasTrabajadas = horasTrabajadas;
        this.horasPagadas = horasPagadas;
        this.lastUpdate = lastUpdate;
        this.valid = valid;
    }

    public Integer getIdEmpleadoTipoEmpleado() {
        return idEmpleadoTipoEmpleado;
    }

    public void setIdEmpleadoTipoEmpleado(Integer idEmpleadoTipoEmpleado) {
        this.idEmpleadoTipoEmpleado = idEmpleadoTipoEmpleado;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Date getFechaRetiro() {
        return fechaRetiro;
    }

    public void setFechaRetiro(Date fechaRetiro) {
        this.fechaRetiro = fechaRetiro;
    }

    public int getHorasTrabajadas() {
        return horasTrabajadas;
    }

    public void setHorasTrabajadas(int horasTrabajadas) {
        this.horasTrabajadas = horasTrabajadas;
    }

    public int getHorasPagadas() {
        return horasPagadas;
    }

    public void setHorasPagadas(int horasPagadas) {
        this.horasPagadas = horasPagadas;
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

    public TipoEmpleado getIdTipoEmpleado() {
        return idTipoEmpleado;
    }

    public void setIdTipoEmpleado(TipoEmpleado idTipoEmpleado) {
        this.idTipoEmpleado = idTipoEmpleado;
    }

}
