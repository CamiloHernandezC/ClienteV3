/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author MAURICIO
 */
@Entity
@Table(name = "Vehiculos_Sucursal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VehiculosSucursal.findAll", query = "SELECT v FROM VehiculosSucursal v"),
    @NamedQuery(name = "VehiculosSucursal.findByPlaca", query = "SELECT v FROM VehiculosSucursal v WHERE v.vehiculosSucursalPK.placa = :placa"),
    @NamedQuery(name = "VehiculosSucursal.findByIdSucursal", query = "SELECT v FROM VehiculosSucursal v WHERE v.vehiculosSucursalPK.idSucursal = :idSucursal"),
    @NamedQuery(name = "VehiculosSucursal.findByIdExterno", query = "SELECT v FROM VehiculosSucursal v WHERE v.idExterno = :idExterno"),
    @NamedQuery(name = "VehiculosSucursal.findByIngresoAutomatico", query = "SELECT v FROM VehiculosSucursal v WHERE v.ingresoAutomatico = :ingresoAutomatico")})
public class VehiculosSucursal implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected VehiculosSucursalPK vehiculosSucursalPK;
    @Size(max = 40)
    @Column(name = "Id_Externo")
    private String idExterno;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Ingreso_Automatico")
    private boolean ingresoAutomatico;
    @JoinColumn(name = "Id_Estado", referencedColumnName = "Id_Estado")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EstadosCli idEstado;
    @JoinColumn(name = "Id_Sucursal", referencedColumnName = "Id_Sucursal", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private SucursalesCli sucursalesCli;
    @JoinColumn(name = "Placa", referencedColumnName = "Placa", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private VehiculosCli vehiculosCli;

    public VehiculosSucursal() {
    }

    public VehiculosSucursal(VehiculosSucursalPK vehiculosSucursalPK) {
        this.vehiculosSucursalPK = vehiculosSucursalPK;
    }

    public VehiculosSucursal(VehiculosSucursalPK vehiculosSucursalPK, boolean ingresoAutomatico) {
        this.vehiculosSucursalPK = vehiculosSucursalPK;
        this.ingresoAutomatico = ingresoAutomatico;
    }

    public VehiculosSucursal(String placa, long idSucursal) {
        this.vehiculosSucursalPK = new VehiculosSucursalPK(placa, idSucursal);
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

    public EstadosCli getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(EstadosCli idEstado) {
        this.idEstado = idEstado;
    }

    public SucursalesCli getSucursalesCli() {
        return sucursalesCli;
    }

    public void setSucursalesCli(SucursalesCli sucursalesCli) {
        this.sucursalesCli = sucursalesCli;
    }

    public VehiculosCli getVehiculosCli() {
        return vehiculosCli;
    }

    public void setVehiculosCli(VehiculosCli vehiculosCli) {
        this.vehiculosCli = vehiculosCli;
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
