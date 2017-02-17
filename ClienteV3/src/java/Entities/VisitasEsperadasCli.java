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
 * @author MAURICIO
 */
@Entity
@Table(name = "Visitas_Esperadas_Cli")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VisitasEsperadasCli.findAll", query = "SELECT v FROM VisitasEsperadasCli v"),
    @NamedQuery(name = "VisitasEsperadasCli.findByIdPersona", query = "SELECT v FROM VisitasEsperadasCli v WHERE v.visitasEsperadasCliPK.idPersona = :idPersona"),
    @NamedQuery(name = "VisitasEsperadasCli.findByIdSucursal", query = "SELECT v FROM VisitasEsperadasCli v WHERE v.visitasEsperadasCliPK.idSucursal = :idSucursal"),
    @NamedQuery(name = "VisitasEsperadasCli.findByFechaVisita", query = "SELECT v FROM VisitasEsperadasCli v WHERE v.visitasEsperadasCliPK.fechaVisita = :fechaVisita"),
    @NamedQuery(name = "VisitasEsperadasCli.findByHoraInicio", query = "SELECT v FROM VisitasEsperadasCli v WHERE v.horaInicio = :horaInicio"),
    @NamedQuery(name = "VisitasEsperadasCli.findByHoraHasta", query = "SELECT v FROM VisitasEsperadasCli v WHERE v.horaHasta = :horaHasta"),
    @NamedQuery(name = "VisitasEsperadasCli.findByFuncionarioVisitado", query = "SELECT v FROM VisitasEsperadasCli v WHERE v.funcionarioVisitado = :funcionarioVisitado"),
    @NamedQuery(name = "VisitasEsperadasCli.findByFecha", query = "SELECT v FROM VisitasEsperadasCli v WHERE v.fecha = :fecha")})
public class VisitasEsperadasCli implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected VisitasEsperadasCliPK visitasEsperadasCliPK;
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
    @Size(min = 1, max = 14)
    @Column(name = "Funcionario_Visitado")
    private String funcionarioVisitado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @JoinColumn(name = "Id_Persona", referencedColumnName = "Id_Persona", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PersonasCli personasCli;
    @JoinColumn(name = "Usuario", referencedColumnName = "Id_Persona")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PersonasCli usuario;
    @JoinColumn(name = "Id_Sucursal", referencedColumnName = "Id_Sucursal", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private SucursalesCli sucursalesCli;

    public VisitasEsperadasCli() {
    }

    public VisitasEsperadasCli(VisitasEsperadasCliPK visitasEsperadasCliPK) {
        this.visitasEsperadasCliPK = visitasEsperadasCliPK;
    }

    public VisitasEsperadasCli(VisitasEsperadasCliPK visitasEsperadasCliPK, Date horaInicio, Date horaHasta, String funcionarioVisitado, Date fecha) {
        this.visitasEsperadasCliPK = visitasEsperadasCliPK;
        this.horaInicio = horaInicio;
        this.horaHasta = horaHasta;
        this.funcionarioVisitado = funcionarioVisitado;
        this.fecha = fecha;
    }

    public VisitasEsperadasCli(String idPersona, long idSucursal, Date fechaVisita) {
        this.visitasEsperadasCliPK = new VisitasEsperadasCliPK(idPersona, idSucursal, fechaVisita);
    }

    public VisitasEsperadasCliPK getVisitasEsperadasCliPK() {
        return visitasEsperadasCliPK;
    }

    public void setVisitasEsperadasCliPK(VisitasEsperadasCliPK visitasEsperadasCliPK) {
        this.visitasEsperadasCliPK = visitasEsperadasCliPK;
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

    public String getFuncionarioVisitado() {
        return funcionarioVisitado;
    }

    public void setFuncionarioVisitado(String funcionarioVisitado) {
        this.funcionarioVisitado = funcionarioVisitado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public PersonasCli getPersonasCli() {
        return personasCli;
    }

    public void setPersonasCli(PersonasCli personasCli) {
        this.personasCli = personasCli;
    }

    public PersonasCli getUsuario() {
        return usuario;
    }

    public void setUsuario(PersonasCli usuario) {
        this.usuario = usuario;
    }

    public SucursalesCli getSucursalesCli() {
        return sucursalesCli;
    }

    public void setSucursalesCli(SucursalesCli sucursalesCli) {
        this.sucursalesCli = sucursalesCli;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (visitasEsperadasCliPK != null ? visitasEsperadasCliPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VisitasEsperadasCli)) {
            return false;
        }
        VisitasEsperadasCli other = (VisitasEsperadasCli) object;
        if ((this.visitasEsperadasCliPK == null && other.visitasEsperadasCliPK != null) || (this.visitasEsperadasCliPK != null && !this.visitasEsperadasCliPK.equals(other.visitasEsperadasCliPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.VisitasEsperadasCli[ visitasEsperadasCliPK=" + visitasEsperadasCliPK + " ]";
    }
    
}
