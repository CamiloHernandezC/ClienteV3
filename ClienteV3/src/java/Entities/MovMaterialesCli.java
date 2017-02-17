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
 * @author MAURICIO
 */
@Entity
@Table(name = "Mov_Materiales_Cli")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MovMaterialesCli.findAll", query = "SELECT m FROM MovMaterialesCli m"),
    @NamedQuery(name = "MovMaterialesCli.findByIdMovimiento", query = "SELECT m FROM MovMaterialesCli m WHERE m.idMovimiento = :idMovimiento"),
    @NamedQuery(name = "MovMaterialesCli.findByFechamov", query = "SELECT m FROM MovMaterialesCli m WHERE m.fechamov = :fechamov"),
    @NamedQuery(name = "MovMaterialesCli.findByHoramov", query = "SELECT m FROM MovMaterialesCli m WHERE m.horamov = :horamov"),
    @NamedQuery(name = "MovMaterialesCli.findByCantidad", query = "SELECT m FROM MovMaterialesCli m WHERE m.cantidad = :cantidad"),
    @NamedQuery(name = "MovMaterialesCli.findByObservacion", query = "SELECT m FROM MovMaterialesCli m WHERE m.observacion = :observacion"),
    @NamedQuery(name = "MovMaterialesCli.findByTipoEvento", query = "SELECT m FROM MovMaterialesCli m WHERE m.tipoEvento = :tipoEvento"),
    @NamedQuery(name = "MovMaterialesCli.findByFecha", query = "SELECT m FROM MovMaterialesCli m WHERE m.fecha = :fecha")})
public class MovMaterialesCli implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id_Movimiento")
    private Long idMovimiento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha_mov")
    @Temporal(TemporalType.DATE)
    private Date fechamov;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Hora_mov")
    @Temporal(TemporalType.TIME)
    private Date horamov;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Cantidad")
    private long cantidad;
    @Size(max = 120)
    @Column(name = "Observacion")
    private String observacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Tipo_Evento")
    private boolean tipoEvento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @JoinColumn(name = "Id_Material", referencedColumnName = "Id_Material")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MaterialesCli idMaterial;
    @JoinColumn(name = "Id_Mov_Documento", referencedColumnName = "Id_Mov_Documento")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MovDocumentosCli idMovDocumento;
    @JoinColumn(name = "Id_Mov_Persona", referencedColumnName = "Id_Movimiento")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MovPersonasCli idMovPersona;
    @JoinColumn(name = "Usuario", referencedColumnName = "Id_Persona")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PersonasCli usuario;
    @JoinColumn(name = "Id_Sucursal", referencedColumnName = "Id_Sucursal")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private SucursalesCli idSucursal;

    public MovMaterialesCli() {
    }

    public MovMaterialesCli(Long idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public MovMaterialesCli(Long idMovimiento, Date fechamov, Date horamov, long cantidad, boolean tipoEvento, Date fecha) {
        this.idMovimiento = idMovimiento;
        this.fechamov = fechamov;
        this.horamov = horamov;
        this.cantidad = cantidad;
        this.tipoEvento = tipoEvento;
        this.fecha = fecha;
    }

    public Long getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(Long idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public Date getFechamov() {
        return fechamov;
    }

    public void setFechamov(Date fechamov) {
        this.fechamov = fechamov;
    }

    public Date getHoramov() {
        return horamov;
    }

    public void setHoramov(Date horamov) {
        this.horamov = horamov;
    }

    public long getCantidad() {
        return cantidad;
    }

    public void setCantidad(long cantidad) {
        this.cantidad = cantidad;
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

    public MaterialesCli getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(MaterialesCli idMaterial) {
        this.idMaterial = idMaterial;
    }

    public MovDocumentosCli getIdMovDocumento() {
        return idMovDocumento;
    }

    public void setIdMovDocumento(MovDocumentosCli idMovDocumento) {
        this.idMovDocumento = idMovDocumento;
    }

    public MovPersonasCli getIdMovPersona() {
        return idMovPersona;
    }

    public void setIdMovPersona(MovPersonasCli idMovPersona) {
        this.idMovPersona = idMovPersona;
    }

    public PersonasCli getUsuario() {
        return usuario;
    }

    public void setUsuario(PersonasCli usuario) {
        this.usuario = usuario;
    }

    public SucursalesCli getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(SucursalesCli idSucursal) {
        this.idSucursal = idSucursal;
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
        if (!(object instanceof MovMaterialesCli)) {
            return false;
        }
        MovMaterialesCli other = (MovMaterialesCli) object;
        if ((this.idMovimiento == null && other.idMovimiento != null) || (this.idMovimiento != null && !this.idMovimiento.equals(other.idMovimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.MovMaterialesCli[ idMovimiento=" + idMovimiento + " ]";
    }
    
}
