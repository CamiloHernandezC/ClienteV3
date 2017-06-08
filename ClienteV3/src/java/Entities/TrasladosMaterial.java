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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Kmilo
 */
@Entity
@Table(name = "traslados_material")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TrasladosMaterial.findAll", query = "SELECT t FROM TrasladosMaterial t"),
    @NamedQuery(name = "TrasladosMaterial.findByRemisionEntrada", query = "SELECT t FROM TrasladosMaterial t WHERE t.trasladosMaterialPK.remisionEntrada = :remisionEntrada"),
    @NamedQuery(name = "TrasladosMaterial.findByRemisionSalida", query = "SELECT t FROM TrasladosMaterial t WHERE t.trasladosMaterialPK.remisionSalida = :remisionSalida"),
    @NamedQuery(name = "TrasladosMaterial.findByFechaIngreso", query = "SELECT t FROM TrasladosMaterial t WHERE t.fechaIngreso = :fechaIngreso"),
    @NamedQuery(name = "TrasladosMaterial.findByFechaSalida", query = "SELECT t FROM TrasladosMaterial t WHERE t.fechaSalida = :fechaSalida")})
public class TrasladosMaterial implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TrasladosMaterialPK trasladosMaterialPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha_Ingreso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngreso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha_Salida")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSalida;
    @JoinColumn(name = "Remision_Entrada", referencedColumnName = "Id_Remision", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Remisiones remisiones;
    @JoinColumn(name = "Remision_Salida", referencedColumnName = "Id_Remision", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Remisiones remisiones1;
    @JoinColumn(name = "Sucursal_Entrada", referencedColumnName = "Id_Sucursal")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Sucursales sucursalEntrada;
    @JoinColumn(name = "Sucursal_Salida", referencedColumnName = "Id_Sucursal")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Sucursales sucursalSalida;

    public TrasladosMaterial() {
    }

    public TrasladosMaterial(TrasladosMaterialPK trasladosMaterialPK) {
        this.trasladosMaterialPK = trasladosMaterialPK;
    }

    public TrasladosMaterial(TrasladosMaterialPK trasladosMaterialPK, Date fechaIngreso, Date fechaSalida) {
        this.trasladosMaterialPK = trasladosMaterialPK;
        this.fechaIngreso = fechaIngreso;
        this.fechaSalida = fechaSalida;
    }

    public TrasladosMaterial(int remisionEntrada, int remisionSalida) {
        this.trasladosMaterialPK = new TrasladosMaterialPK(remisionEntrada, remisionSalida);
    }

    public TrasladosMaterialPK getTrasladosMaterialPK() {
        return trasladosMaterialPK;
    }

    public void setTrasladosMaterialPK(TrasladosMaterialPK trasladosMaterialPK) {
        this.trasladosMaterialPK = trasladosMaterialPK;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public Remisiones getRemisiones() {
        return remisiones;
    }

    public void setRemisiones(Remisiones remisiones) {
        this.remisiones = remisiones;
    }

    public Remisiones getRemisiones1() {
        return remisiones1;
    }

    public void setRemisiones1(Remisiones remisiones1) {
        this.remisiones1 = remisiones1;
    }

    public Sucursales getSucursalEntrada() {
        return sucursalEntrada;
    }

    public void setSucursalEntrada(Sucursales sucursalEntrada) {
        this.sucursalEntrada = sucursalEntrada;
    }

    public Sucursales getSucursalSalida() {
        return sucursalSalida;
    }

    public void setSucursalSalida(Sucursales sucursalSalida) {
        this.sucursalSalida = sucursalSalida;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (trasladosMaterialPK != null ? trasladosMaterialPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TrasladosMaterial)) {
            return false;
        }
        TrasladosMaterial other = (TrasladosMaterial) object;
        if ((this.trasladosMaterialPK == null && other.trasladosMaterialPK != null) || (this.trasladosMaterialPK != null && !this.trasladosMaterialPK.equals(other.trasladosMaterialPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.TrasladosMaterial[ trasladosMaterialPK=" + trasladosMaterialPK + " ]";
    }
    
}
