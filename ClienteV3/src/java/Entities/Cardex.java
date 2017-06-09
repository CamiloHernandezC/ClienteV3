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
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
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
@Table(name = "cardex")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cardex.findAll", query = "SELECT c FROM Cardex c"),
    @NamedQuery(name = "Cardex.findByIdMovimientoMaterial", query = "SELECT c FROM Cardex c WHERE c.idMovimientoMaterial = :idMovimientoMaterial"),
    @NamedQuery(name = "Cardex.findByFechaMovimiento", query = "SELECT c FROM Cardex c WHERE c.fechaMovimiento = :fechaMovimiento"),
    @NamedQuery(name = "Cardex.findByCantida", query = "SELECT c FROM Cardex c WHERE c.cantida = :cantida"),
    @NamedQuery(name = "Cardex.findByObservacion", query = "SELECT c FROM Cardex c WHERE c.observacion = :observacion"),
    @NamedQuery(name = "Cardex.findByCantidadActual", query = "SELECT c FROM Cardex c WHERE c.cantidadActual = :cantidadActual")})
public class Cardex implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id_Movimiento_Material")
    private Integer idMovimientoMaterial;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha_Movimiento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaMovimiento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Cantida")
    private int cantida;
    @Size(max = 140)
    @Column(name = "Observacion")
    private String observacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Cantidad_Actual")
    private int cantidadActual;
    @JoinColumns({
        @JoinColumn(name = "Id_Material", referencedColumnName = "Id_Material"),
        @JoinColumn(name = "Sucursal", referencedColumnName = "Id_Sucursal")})
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MaterialesSucursal materialesSucursal;
    @JoinColumn(name = "Remision", referencedColumnName = "Id_Remision")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Remisiones remision;
    @JoinColumn(name = "Almacen", referencedColumnName = "Id_Almacen")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Almacen almacen;

    public Cardex() {
    }

    public Cardex(Integer idMovimientoMaterial) {
        this.idMovimientoMaterial = idMovimientoMaterial;
    }

    public Cardex(Integer idMovimientoMaterial, Date fechaMovimiento, int cantida, int cantidadActual) {
        this.idMovimientoMaterial = idMovimientoMaterial;
        this.fechaMovimiento = fechaMovimiento;
        this.cantida = cantida;
        this.cantidadActual = cantidadActual;
    }

    public Integer getIdMovimientoMaterial() {
        return idMovimientoMaterial;
    }

    public void setIdMovimientoMaterial(Integer idMovimientoMaterial) {
        this.idMovimientoMaterial = idMovimientoMaterial;
    }

    public Date getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(Date fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }

    public int getCantida() {
        return cantida;
    }

    public void setCantida(int cantida) {
        this.cantida = cantida;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public int getCantidadActual() {
        return cantidadActual;
    }

    public void setCantidadActual(int cantidadActual) {
        this.cantidadActual = cantidadActual;
    }

    public MaterialesSucursal getMaterialesSucursal() {
        return materialesSucursal;
    }

    public void setMaterialesSucursal(MaterialesSucursal materialesSucursal) {
        this.materialesSucursal = materialesSucursal;
    }

    public Remisiones getRemision() {
        return remision;
    }

    public void setRemision(Remisiones remision) {
        this.remision = remision;
    }

    public Almacen getAlmacen() {
        return almacen;
    }

    public void setAlmacen(Almacen almacen) {
        this.almacen = almacen;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMovimientoMaterial != null ? idMovimientoMaterial.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cardex)) {
            return false;
        }
        Cardex other = (Cardex) object;
        if ((this.idMovimientoMaterial == null && other.idMovimientoMaterial != null) || (this.idMovimientoMaterial != null && !this.idMovimientoMaterial.equals(other.idMovimientoMaterial))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Cardex[ idMovimientoMaterial=" + idMovimientoMaterial + " ]";
    }

}
