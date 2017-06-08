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
@Table(name = "mov_remisiones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MovRemisiones.findAll", query = "SELECT m FROM MovRemisiones m"),
    @NamedQuery(name = "MovRemisiones.findByIdMovimiento", query = "SELECT m FROM MovRemisiones m WHERE m.idMovimiento = :idMovimiento"),
    @NamedQuery(name = "MovRemisiones.findByContenedoresRevisados", query = "SELECT m FROM MovRemisiones m WHERE m.contenedoresRevisados = :contenedoresRevisados"),
    @NamedQuery(name = "MovRemisiones.findByUnidadesRevisadas", query = "SELECT m FROM MovRemisiones m WHERE m.unidadesRevisadas = :unidadesRevisadas"),
    @NamedQuery(name = "MovRemisiones.findByFechaMovimiento", query = "SELECT m FROM MovRemisiones m WHERE m.fechaMovimiento = :fechaMovimiento")})
public class MovRemisiones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id_Movimiento")
    private Integer idMovimiento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Contenedores_Revisados")
    private int contenedoresRevisados;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Unidades_Revisadas")
    private int unidadesRevisadas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha_Movimiento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaMovimiento;
    @JoinColumn(name = "Remision", referencedColumnName = "Id_Remision")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Remisiones remision;
    @JoinColumn(name = "Visto_Bueno", referencedColumnName = "Id_Persona")
    @ManyToOne(fetch = FetchType.LAZY)
    private Personas vistoBueno;
    @JoinColumn(name = "Mov_Persona", referencedColumnName = "Id_Mov_Persona")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MovPersonas movPersona;
    @JoinColumn(name = "Sucursal", referencedColumnName = "Id_Sucursal")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Sucursales sucursal;

    public MovRemisiones() {
    }

    public MovRemisiones(Integer idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public MovRemisiones(Integer idMovimiento, int contenedoresRevisados, int unidadesRevisadas, Date fechaMovimiento) {
        this.idMovimiento = idMovimiento;
        this.contenedoresRevisados = contenedoresRevisados;
        this.unidadesRevisadas = unidadesRevisadas;
        this.fechaMovimiento = fechaMovimiento;
    }

    public Integer getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(Integer idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public int getContenedoresRevisados() {
        return contenedoresRevisados;
    }

    public void setContenedoresRevisados(int contenedoresRevisados) {
        this.contenedoresRevisados = contenedoresRevisados;
    }

    public int getUnidadesRevisadas() {
        return unidadesRevisadas;
    }

    public void setUnidadesRevisadas(int unidadesRevisadas) {
        this.unidadesRevisadas = unidadesRevisadas;
    }

    public Date getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(Date fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }

    public Remisiones getRemision() {
        return remision;
    }

    public void setRemision(Remisiones remision) {
        this.remision = remision;
    }

    public Personas getVistoBueno() {
        return vistoBueno;
    }

    public void setVistoBueno(Personas vistoBueno) {
        this.vistoBueno = vistoBueno;
    }

    public MovPersonas getMovPersona() {
        return movPersona;
    }

    public void setMovPersona(MovPersonas movPersona) {
        this.movPersona = movPersona;
    }

    public Sucursales getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursales sucursal) {
        this.sucursal = sucursal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMovimiento != null ? idMovimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MovRemisiones)) {
            return false;
        }
        MovRemisiones other = (MovRemisiones) object;
        if ((this.idMovimiento == null && other.idMovimiento != null) || (this.idMovimiento != null && !this.idMovimiento.equals(other.idMovimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.MovRemisiones[ idMovimiento=" + idMovimiento + " ]";
    }
    
}
