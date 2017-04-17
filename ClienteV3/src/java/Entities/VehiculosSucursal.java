/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Kmilo
 */
@Entity
@Table(name = "vehiculos_sucursal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VehiculosSucursal.findAll", query = "SELECT v FROM VehiculosSucursal v"),
    @NamedQuery(name = "VehiculosSucursal.findByPlaca", query = "SELECT v FROM VehiculosSucursal v WHERE v.vehiculosSucursalPK.placa = :placa"),
    @NamedQuery(name = "VehiculosSucursal.findBySucursal", query = "SELECT v FROM VehiculosSucursal v WHERE v.vehiculosSucursalPK.sucursal = :sucursal"),
    @NamedQuery(name = "VehiculosSucursal.findByIdExterno", query = "SELECT v FROM VehiculosSucursal v WHERE v.idExterno = :idExterno"),
    @NamedQuery(name = "VehiculosSucursal.findByIngresoAutomatico", query = "SELECT v FROM VehiculosSucursal v WHERE v.ingresoAutomatico = :ingresoAutomatico"),
    @NamedQuery(name = "VehiculosSucursal.findByFecha", query = "SELECT v FROM VehiculosSucursal v WHERE v.fecha = :fecha")})
public class VehiculosSucursal implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected VehiculosSucursalPK vehiculosSucursalPK;
    @Size(max = 32)
    @Column(name = "Id_Externo")
    private String idExterno;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Ingreso_Automatico")
    private boolean ingresoAutomatico;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @JoinColumn(name = "Usuario", referencedColumnName = "Id_Persona")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Personas usuario;
    @JoinColumn(name = "Estado", referencedColumnName = "Id_Estado")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Estados estado;
    @JoinColumn(name = "Sucursal", referencedColumnName = "Id_Sucursal", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Sucursales sucursales;
    @JoinColumn(name = "Placa", referencedColumnName = "Placa", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Vehiculos vehiculos;

    public VehiculosSucursal() {
    }

    public VehiculosSucursal(VehiculosSucursalPK vehiculosSucursalPK) {
        this.vehiculosSucursalPK = vehiculosSucursalPK;
    }

    public VehiculosSucursal(VehiculosSucursalPK vehiculosSucursalPK, boolean ingresoAutomatico, Date fecha) {
        this.vehiculosSucursalPK = vehiculosSucursalPK;
        this.ingresoAutomatico = ingresoAutomatico;
        this.fecha = fecha;
    }

    public VehiculosSucursal(String placa, int sucursal) {
        this.vehiculosSucursalPK = new VehiculosSucursalPK(placa, sucursal);
    }

    public VehiculosSucursalPK getVehiculosSucursalPK() {
        return vehiculosSucursalPK;
    }

    public void setVehiculosSucursalPK(VehiculosSucursalPK vehiculosSucursalPK) {
        this.vehiculosSucursalPK = vehiculosSucursalPK;
    }

    public String getIdExterno() {
        return idExterno;
    }

    public void setIdExterno(String idExterno) {
        this.idExterno = idExterno;
    }

    public boolean getIngresoAutomatico() {
        return ingresoAutomatico;
    }

    public void setIngresoAutomatico(boolean ingresoAutomatico) {
        this.ingresoAutomatico = ingresoAutomatico;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Personas getUsuario() {
        return usuario;
    }

    public void setUsuario(Personas usuario) {
        this.usuario = usuario;
    }

    public Estados getEstado() {
        return estado;
    }

    public void setEstado(Estados estado) {
        this.estado = estado;
    }

    public Sucursales getSucursales() {
        return sucursales;
    }

    public void setSucursales(Sucursales sucursales) {
        this.sucursales = sucursales;
    }

    public Vehiculos getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(Vehiculos vehiculos) {
        this.vehiculos = vehiculos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vehiculosSucursalPK != null ? vehiculosSucursalPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VehiculosSucursal)) {
            return false;
        }
        VehiculosSucursal other = (VehiculosSucursal) object;
        if ((this.vehiculosSucursalPK == null && other.vehiculosSucursalPK != null) || (this.vehiculosSucursalPK != null && !this.vehiculosSucursalPK.equals(other.vehiculosSucursalPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.VehiculosSucursal[ vehiculosSucursalPK=" + vehiculosSucursalPK + " ]";
    }
    
}
