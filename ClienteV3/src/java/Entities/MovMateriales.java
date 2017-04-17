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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Kmilo
 */
@Entity
@Table(name = "mov_materiales")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MovMateriales.findAll", query = "SELECT m FROM MovMateriales m"),
    @NamedQuery(name = "MovMateriales.findByIdMovimientoMaterial", query = "SELECT m FROM MovMateriales m WHERE m.idMovimientoMaterial = :idMovimientoMaterial"),
    @NamedQuery(name = "MovMateriales.findByFechaMovimiento", query = "SELECT m FROM MovMateriales m WHERE m.fechaMovimiento = :fechaMovimiento"),
    @NamedQuery(name = "MovMateriales.findByHoraMovimiento", query = "SELECT m FROM MovMateriales m WHERE m.horaMovimiento = :horaMovimiento"),
    @NamedQuery(name = "MovMateriales.findByCantida", query = "SELECT m FROM MovMateriales m WHERE m.cantida = :cantida"),
    @NamedQuery(name = "MovMateriales.findByObservacion", query = "SELECT m FROM MovMateriales m WHERE m.observacion = :observacion"),
    @NamedQuery(name = "MovMateriales.findByTipoEvento", query = "SELECT m FROM MovMateriales m WHERE m.tipoEvento = :tipoEvento"),
    @NamedQuery(name = "MovMateriales.findByFecha", query = "SELECT m FROM MovMateriales m WHERE m.fecha = :fecha")})
public class MovMateriales implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id_Movimiento_Material")
    private Integer idMovimientoMaterial;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha_Movimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaMovimiento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Hora_Movimiento")
    @Temporal(TemporalType.TIME)
    private Date horaMovimiento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Cantida")
    private int cantida;
    @Size(max = 140)
    @Column(name = "Observacion")
    private String observacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Tipo_Evento")
    private boolean tipoEvento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @JoinColumn(name = "Usuario", referencedColumnName = "Id_Persona")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Personas usuario;
    @JoinColumn(name = "Sucursal", referencedColumnName = "Id_Sucursal")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Sucursales sucursal;
    @JoinColumn(name = "Movimiento_Persona", referencedColumnName = "Id_Mov_Persona")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MovPersonas movimientoPersona;
    @JoinColumn(name = "Id_Material", referencedColumnName = "Id_Material")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Materiales idMaterial;
    @JoinColumn(name = "Id_Movimiento_Documento", referencedColumnName = "Id_Mov_Documento")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MovDocumentos idMovimientoDocumento;

    public MovMateriales() {
    }

    public MovMateriales(Integer idMovimientoMaterial) {
        this.idMovimientoMaterial = idMovimientoMaterial;
    }

    public MovMateriales(Integer idMovimientoMaterial, Date fechaMovimiento, Date horaMovimiento, int cantida, boolean tipoEvento, Date fecha) {
        this.idMovimientoMaterial = idMovimientoMaterial;
        this.fechaMovimiento = fechaMovimiento;
        this.horaMovimiento = horaMovimiento;
        this.cantida = cantida;
        this.tipoEvento = tipoEvento;
        this.fecha = fecha;
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

    public Date getHoraMovimiento() {
        return horaMovimiento;
    }

    public void setHoraMovimiento(Date horaMovimiento) {
        this.horaMovimiento = horaMovimiento;
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

    public boolean getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(boolean tipoEvento) {
        this.tipoEvento = tipoEvento;
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

    public Sucursales getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursales sucursal) {
        this.sucursal = sucursal;
    }

    public MovPersonas getMovimientoPersona() {
        return movimientoPersona;
    }

    public void setMovimientoPersona(MovPersonas movimientoPersona) {
        this.movimientoPersona = movimientoPersona;
    }

    public Materiales getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(Materiales idMaterial) {
        this.idMaterial = idMaterial;
    }

    public MovDocumentos getIdMovimientoDocumento() {
        return idMovimientoDocumento;
    }

    public void setIdMovimientoDocumento(MovDocumentos idMovimientoDocumento) {
        this.idMovimientoDocumento = idMovimientoDocumento;
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
        if (!(object instanceof MovMateriales)) {
            return false;
        }
        MovMateriales other = (MovMateriales) object;
        if ((this.idMovimientoMaterial == null && other.idMovimientoMaterial != null) || (this.idMovimientoMaterial != null && !this.idMovimientoMaterial.equals(other.idMovimientoMaterial))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.MovMateriales[ idMovimientoMaterial=" + idMovimientoMaterial + " ]";
    }
    
}
