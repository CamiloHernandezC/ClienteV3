/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author MAURICIO
 */
@Entity
@Table(name = "Mov_Documentos_Cli")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MovDocumentosCli.findAll", query = "SELECT m FROM MovDocumentosCli m"),
    @NamedQuery(name = "MovDocumentosCli.findByIdMovDocumento", query = "SELECT m FROM MovDocumentosCli m WHERE m.idMovDocumento = :idMovDocumento"),
    @NamedQuery(name = "MovDocumentosCli.findByIdDocumento", query = "SELECT m FROM MovDocumentosCli m WHERE m.idDocumento = :idDocumento"),
    @NamedQuery(name = "MovDocumentosCli.findByFechaMov", query = "SELECT m FROM MovDocumentosCli m WHERE m.fechaMov = :fechaMov"),
    @NamedQuery(name = "MovDocumentosCli.findByHoraMov", query = "SELECT m FROM MovDocumentosCli m WHERE m.horaMov = :horaMov"),
    @NamedQuery(name = "MovDocumentosCli.findByTipoEvento", query = "SELECT m FROM MovDocumentosCli m WHERE m.tipoEvento = :tipoEvento"),
    @NamedQuery(name = "MovDocumentosCli.findByObservacion", query = "SELECT m FROM MovDocumentosCli m WHERE m.observacion = :observacion"),
    @NamedQuery(name = "MovDocumentosCli.findByFecha", query = "SELECT m FROM MovDocumentosCli m WHERE m.fecha = :fecha")})
public class MovDocumentosCli implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id_Mov_Documento")
    private Long idMovDocumento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "Id_Documento")
    private String idDocumento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha_Mov")
    @Temporal(TemporalType.DATE)
    private Date fechaMov;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Hora_Mov")
    @Temporal(TemporalType.TIME)
    private Date horaMov;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Tipo_Evento")
    private boolean tipoEvento;
    @Size(max = 1000)
    @Column(name = "Observacion")
    private String observacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @JoinColumn(name = "Id_Entidad", referencedColumnName = "Id_Entidad")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EntidadesCli idEntidad;
    @JoinColumn(name = "Id_Mov_Persona", referencedColumnName = "Id_Movimiento")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MovPersonasCli idMovPersona;
    @JoinColumn(name = "Usuario", referencedColumnName = "Id_Persona")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PersonasCli usuario;
    @JoinColumn(name = "Id_Sucursal", referencedColumnName = "Id_Sucursal")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private SucursalesCli idSucursal;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMovDocumento", fetch = FetchType.LAZY)
    private List<MovMaterialesCli> movMaterialesCliList;

    public MovDocumentosCli() {
    }

    public MovDocumentosCli(Long idMovDocumento) {
        this.idMovDocumento = idMovDocumento;
    }

    public MovDocumentosCli(Long idMovDocumento, String idDocumento, Date fechaMov, Date horaMov, boolean tipoEvento, Date fecha) {
        this.idMovDocumento = idMovDocumento;
        this.idDocumento = idDocumento;
        this.fechaMov = fechaMov;
        this.horaMov = horaMov;
        this.tipoEvento = tipoEvento;
        this.fecha = fecha;
    }

    public Long getIdMovDocumento() {
        return idMovDocumento;
    }

    public void setIdMovDocumento(Long idMovDocumento) {
        this.idMovDocumento = idMovDocumento;
    }

    public String getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(String idDocumento) {
        this.idDocumento = idDocumento;
    }

    public Date getFechaMov() {
        return fechaMov;
    }

    public void setFechaMov(Date fechaMov) {
        this.fechaMov = fechaMov;
    }

    public Date getHoraMov() {
        return horaMov;
    }

    public void setHoraMov(Date horaMov) {
        this.horaMov = horaMov;
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

    public EntidadesCli getIdEntidad() {
        return idEntidad;
    }

    public void setIdEntidad(EntidadesCli idEntidad) {
        this.idEntidad = idEntidad;
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

    @XmlTransient
    public List<MovMaterialesCli> getMovMaterialesCliList() {
        return movMaterialesCliList;
    }

    public void setMovMaterialesCliList(List<MovMaterialesCli> movMaterialesCliList) {
        this.movMaterialesCliList = movMaterialesCliList;
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
        if (!(object instanceof MovDocumentosCli)) {
            return false;
        }
        MovDocumentosCli other = (MovDocumentosCli) object;
        if ((this.idMovDocumento == null && other.idMovDocumento != null) || (this.idMovDocumento != null && !this.idMovDocumento.equals(other.idMovDocumento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.MovDocumentosCli[ idMovDocumento=" + idMovDocumento + " ]";
    }
    
}
