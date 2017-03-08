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
@Table(name = "Personas_Sucursal_Cli")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PersonasSucursalCli.findAll", query = "SELECT p FROM PersonasSucursalCli p"),
    @NamedQuery(name = "PersonasSucursalCli.findByIdPersona", query = "SELECT p FROM PersonasSucursalCli p WHERE p.personasSucursalCliPK.idPersona = :idPersona"),
    @NamedQuery(name = "PersonasSucursalCli.findBySucursal", query = "SELECT p FROM PersonasSucursalCli p WHERE p.personasSucursalCliPK.sucursal = :sucursal"),
    @NamedQuery(name = "PersonasSucursalCli.findByIdExterno", query = "SELECT p FROM PersonasSucursalCli p WHERE p.idExterno = :idExterno"),
    @NamedQuery(name = "PersonasSucursalCli.findByFecha", query = "SELECT p FROM PersonasSucursalCli p WHERE p.fecha = :fecha")})
public class PersonasSucursalCli implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PersonasSucursalCliPK personasSucursalCliPK;
    @Size(max = 2147483647)
    @Column(name = "Id_Externo")
    private String idExterno;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @JoinColumn(name = "Area", referencedColumnName = "Id_areaemp")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AreasEmpresaCli area;
    @JoinColumn(name = "Entidad", referencedColumnName = "Id_Entidad")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EntidadesCli entidad;
    @JoinColumn(name = "Estado", referencedColumnName = "Id_Estado")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EstadosCli estado;
    @JoinColumn(name = "Id_Persona", referencedColumnName = "Id_Persona", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PersonasCli personasCli;
    @JoinColumn(name = "Usuario", referencedColumnName = "Id_Persona")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PersonasCli usuario;
    @JoinColumn(name = "Sucursal", referencedColumnName = "Id_Sucursal", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private SucursalesCli sucursalesCli;

    public PersonasSucursalCli() {
    }

    public PersonasSucursalCli(PersonasSucursalCliPK personasSucursalCliPK) {
        this.personasSucursalCliPK = personasSucursalCliPK;
    }

    public PersonasSucursalCli(PersonasSucursalCliPK personasSucursalCliPK, Date fecha) {
        this.personasSucursalCliPK = personasSucursalCliPK;
        this.fecha = fecha;
    }

    public PersonasSucursalCli(String idPersona, long sucursal) {
        this.personasSucursalCliPK = new PersonasSucursalCliPK(idPersona, sucursal);
    }

    public PersonasSucursalCliPK getPersonasSucursalCliPK() {
        return personasSucursalCliPK;
    }

    public void setPersonasSucursalCliPK(PersonasSucursalCliPK personasSucursalCliPK) {
        this.personasSucursalCliPK = personasSucursalCliPK;
    }

    public String getIdExterno() {
        return idExterno;
    }

    public void setIdExterno(String idExterno) {
        this.idExterno = idExterno;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public AreasEmpresaCli getArea() {
        return area;
    }

    public void setArea(AreasEmpresaCli area) {
        this.area = area;
    }

    public EntidadesCli getEntidad() {
        return entidad;
    }

    public void setEntidad(EntidadesCli entidad) {
        this.entidad = entidad;
    }

    public EstadosCli getEstado() {
        return estado;
    }

    public void setEstado(EstadosCli estado) {
        this.estado = estado;
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
        hash += (personasSucursalCliPK != null ? personasSucursalCliPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonasSucursalCli)) {
            return false;
        }
        PersonasSucursalCli other = (PersonasSucursalCli) object;
        if ((this.personasSucursalCliPK == null && other.personasSucursalCliPK != null) || (this.personasSucursalCliPK != null && !this.personasSucursalCliPK.equals(other.personasSucursalCliPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.PersonasSucursalCli[ personasSucursalCliPK=" + personasSucursalCliPK + " ]";
    }
    
}
