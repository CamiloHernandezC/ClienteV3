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
@Table(name = "visitas_esperadas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VisitasEsperadas.findAll", query = "SELECT v FROM VisitasEsperadas v"),
    @NamedQuery(name = "VisitasEsperadas.findByIdPersona", query = "SELECT v FROM VisitasEsperadas v WHERE v.visitasEsperadasPK.idPersona = :idPersona"),
    @NamedQuery(name = "VisitasEsperadas.findBySucursal", query = "SELECT v FROM VisitasEsperadas v WHERE v.visitasEsperadasPK.sucursal = :sucursal"),
    @NamedQuery(name = "VisitasEsperadas.findByFechaVisita", query = "SELECT v FROM VisitasEsperadas v WHERE v.fechaVisita = :fechaVisita"),
    @NamedQuery(name = "VisitasEsperadas.findByHoraInicio", query = "SELECT v FROM VisitasEsperadas v WHERE v.horaInicio = :horaInicio"),
    @NamedQuery(name = "VisitasEsperadas.findByHoraHasta", query = "SELECT v FROM VisitasEsperadas v WHERE v.horaHasta = :horaHasta"),
    @NamedQuery(name = "VisitasEsperadas.findByFecha", query = "SELECT v FROM VisitasEsperadas v WHERE v.fecha = :fecha")})
public class VisitasEsperadas implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected VisitasEsperadasPK visitasEsperadasPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha_Visita")
    @Temporal(TemporalType.DATE)
    private Date fechaVisita;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Hora_Inicio")
    @Temporal(TemporalType.TIME)
    private Date horaInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Hora_Hasta")
    @Temporal(TemporalType.TIME)
    private Date horaHasta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @JoinColumn(name = "Usuario", referencedColumnName = "Id_Persona")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Personas usuario;
    @JoinColumn(name = "Sucursal", referencedColumnName = "Id_Sucursal", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Sucursales sucursales;
    @JoinColumn(name = "Id_Persona", referencedColumnName = "Id_Persona", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Personas personas;
    @JoinColumn(name = "Funcionario_Visitado", referencedColumnName = "Id_Persona")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Personas funcionarioVisitado;

    public VisitasEsperadas() {
    }

    public VisitasEsperadas(VisitasEsperadasPK visitasEsperadasPK) {
        this.visitasEsperadasPK = visitasEsperadasPK;
    }

    public VisitasEsperadas(VisitasEsperadasPK visitasEsperadasPK, Date fechaVisita, Date horaInicio, Date horaHasta, Date fecha) {
        this.visitasEsperadasPK = visitasEsperadasPK;
        this.fechaVisita = fechaVisita;
        this.horaInicio = horaInicio;
        this.horaHasta = horaHasta;
        this.fecha = fecha;
    }

    public VisitasEsperadas(int idPersona, int sucursal) {
        this.visitasEsperadasPK = new VisitasEsperadasPK(idPersona, sucursal);
    }

    public VisitasEsperadasPK getVisitasEsperadasPK() {
        return visitasEsperadasPK;
    }

    public void setVisitasEsperadasPK(VisitasEsperadasPK visitasEsperadasPK) {
        this.visitasEsperadasPK = visitasEsperadasPK;
    }

    public Date getFechaVisita() {
        return fechaVisita;
    }

    public void setFechaVisita(Date fechaVisita) {
        this.fechaVisita = fechaVisita;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getHoraHasta() {
        return horaHasta;
    }

    public void setHoraHasta(Date horaHasta) {
        this.horaHasta = horaHasta;
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

    public Sucursales getSucursales() {
        return sucursales;
    }

    public void setSucursales(Sucursales sucursales) {
        this.sucursales = sucursales;
    }

    public Personas getPersonas() {
        return personas;
    }

    public void setPersonas(Personas personas) {
        this.personas = personas;
    }

    public Personas getFuncionarioVisitado() {
        return funcionarioVisitado;
    }

    public void setFuncionarioVisitado(Personas funcionarioVisitado) {
        this.funcionarioVisitado = funcionarioVisitado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (visitasEsperadasPK != null ? visitasEsperadasPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VisitasEsperadas)) {
            return false;
        }
        VisitasEsperadas other = (VisitasEsperadas) object;
        if ((this.visitasEsperadasPK == null && other.visitasEsperadasPK != null) || (this.visitasEsperadasPK != null && !this.visitasEsperadasPK.equals(other.visitasEsperadasPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.VisitasEsperadas[ visitasEsperadasPK=" + visitasEsperadasPK + " ]";
    }
    
}
