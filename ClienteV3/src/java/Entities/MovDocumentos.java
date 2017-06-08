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
@Table(name = "mov_documentos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MovDocumentos.findAll", query = "SELECT m FROM MovDocumentos m"),
    @NamedQuery(name = "MovDocumentos.findByIdMovDocumento", query = "SELECT m FROM MovDocumentos m WHERE m.idMovDocumento = :idMovDocumento"),
    @NamedQuery(name = "MovDocumentos.findByIDDocumento", query = "SELECT m FROM MovDocumentos m WHERE m.iDDocumento = :iDDocumento"),
    @NamedQuery(name = "MovDocumentos.findByFechaMovimiento", query = "SELECT m FROM MovDocumentos m WHERE m.fechaMovimiento = :fechaMovimiento"),
    @NamedQuery(name = "MovDocumentos.findByHoraMovimiento", query = "SELECT m FROM MovDocumentos m WHERE m.horaMovimiento = :horaMovimiento"),
    @NamedQuery(name = "MovDocumentos.findByTipoEvento", query = "SELECT m FROM MovDocumentos m WHERE m.tipoEvento = :tipoEvento"),
    @NamedQuery(name = "MovDocumentos.findByObservacion", query = "SELECT m FROM MovDocumentos m WHERE m.observacion = :observacion"),
    @NamedQuery(name = "MovDocumentos.findByFecha", query = "SELECT m FROM MovDocumentos m WHERE m.fecha = :fecha")})
public class MovDocumentos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id_Mov_Documento")
    private Integer idMovDocumento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "ID_Documento")
    private String iDDocumento;
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
    @Column(name = "Tipo_Evento")
    private boolean tipoEvento;
    @Size(max = 140)
    @Column(name = "Observacion")
    private String observacion;
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
    @JoinColumn(name = "Entidad", referencedColumnName = "Id_Entidad")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Entidades entidad;
    @JoinColumn(name = "Movimiento_Persona", referencedColumnName = "Id_Mov_Persona")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MovPersonas movimientoPersona;

    public MovDocumentos() {
    }

    public MovDocumentos(Integer idMovDocumento) {
        this.idMovDocumento = idMovDocumento;
    }

    public MovDocumentos(Integer idMovDocumento, String iDDocumento, Date fechaMovimiento, Date horaMovimiento, boolean tipoEvento, Date fecha) {
        this.idMovDocumento = idMovDocumento;
        this.iDDocumento = iDDocumento;
        this.fechaMovimiento = fechaMovimiento;
        this.horaMovimiento = horaMovimiento;
        this.tipoEvento = tipoEvento;
        this.fecha = fecha;
    }

    public Integer getIdMovDocumento() {
        return idMovDocumento;
    }

    public void setIdMovDocumento(Integer idMovDocumento) {
        this.idMovDocumento = idMovDocumento;
    }

    public String getIDDocumento() {
        return iDDocumento;
    }

    public void setIDDocumento(String iDDocumento) {
        this.iDDocumento = iDDocumento;
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

    public boolean getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(boolean tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
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

    public Entidades getEntidad() {
        return entidad;
    }

    public void setEntidad(Entidades entidad) {
        this.entidad = entidad;
    }

    public MovPersonas getMovimientoPersona() {
        return movimientoPersona;
    }

    public void setMovimientoPersona(MovPersonas movimientoPersona) {
        this.movimientoPersona = movimientoPersona;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMovDocumento != null ? idMovDocumento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MovDocumentos)) {
            return false;
        }
        MovDocumentos other = (MovDocumentos) object;
        if ((this.idMovDocumento == null && other.idMovDocumento != null) || (this.idMovDocumento != null && !this.idMovDocumento.equals(other.idMovDocumento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.MovDocumentos[ idMovDocumento=" + idMovDocumento + " ]";
    }
    
}
