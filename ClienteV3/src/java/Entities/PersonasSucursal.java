/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Utils.Constants;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
 * @author Kmilo
 */
@Entity
@Table(name = "personas_sucursal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PersonasSucursal.findAll", query = "SELECT p FROM PersonasSucursal p"),
    @NamedQuery(name = "PersonasSucursal.findByIdPersona", query = "SELECT p FROM PersonasSucursal p WHERE p.personasSucursalPK.idPersona = :idPersona"),
    @NamedQuery(name = "PersonasSucursal.findBySucursal", query = "SELECT p FROM PersonasSucursal p WHERE p.personasSucursalPK.sucursal = :sucursal"),
    @NamedQuery(name = "PersonasSucursal.findByIdExterno", query = "SELECT p FROM PersonasSucursal p WHERE p.idExterno = :idExterno"),
    @NamedQuery(name = "PersonasSucursal.findByFecha", query = "SELECT p FROM PersonasSucursal p WHERE p.fecha = :fecha")})
public class PersonasSucursal extends AbstractEntity {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PersonasSucursalPK personasSucursalPK;
    @Size(max = 32)
    @Column(name = "Id_Externo")
    private String idExterno;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @JoinColumn(name = "Usuario", referencedColumnName = "Id_Persona")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Personas usuario;
    @JoinColumn(name = "Id_Persona", referencedColumnName = "Id_Persona", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Personas personas;
    @JoinColumn(name = "Sucursal", referencedColumnName = "Id_Sucursal", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Sucursales sucursales;
    @JoinColumn(name = "Entidad", referencedColumnName = "Id_Entidad")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Entidades entidad;
    @NotNull
    @JoinColumn(name = "Estado", referencedColumnName = "Id_Estado")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Estados estado;
    @JoinColumn(name = "Area", referencedColumnName = "Id_Area_Empresa")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AreasEmpresa area;
    @JoinColumn(name = "Horario", referencedColumnName = "Id_Horario")
    @ManyToOne(fetch = FetchType.LAZY)
    private Horarios horario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personasSucursal", fetch = FetchType.LAZY)
    private List<MovPersonas> movPersonasList;

    public PersonasSucursal() {
    }

    public PersonasSucursal(PersonasSucursalPK personasSucursalPK) {
        this.personasSucursalPK = personasSucursalPK;
    }

    public PersonasSucursal(PersonasSucursalPK personasSucursalPK, Date fecha) {
        this.personasSucursalPK = personasSucursalPK;
        this.fecha = fecha;
    }

    public PersonasSucursal(int idPersona, int sucursal) {
        this.personasSucursalPK = new PersonasSucursalPK(idPersona, sucursal);
    }

    public PersonasSucursalPK getPersonasSucursalPK() {
        return personasSucursalPK;
    }

    public void setPersonasSucursalPK(PersonasSucursalPK personasSucursalPK) {
        this.personasSucursalPK = personasSucursalPK;
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

    public Personas getUsuario() {
        return usuario;
    }

    public void setUsuario(Personas usuario) {
        this.usuario = usuario;
    }

    public Personas getPersonas() {
        return personas;
    }

    public void setPersonas(Personas personas) {
        this.personas = personas;
    }

    public Sucursales getSucursales() {
        return sucursales;
    }

    public void setSucursales(Sucursales sucursales) {
        this.sucursales = sucursales;
    }

    public Entidades getEntidad() {
        return entidad;
    }

    public void setEntidad(Entidades entidad) {
        this.entidad = entidad;
    }

    public Estados getEstado() {
        return estado;
    }

    public void setEstado(Estados estado) {
        this.estado = estado;
    }

    public AreasEmpresa getArea() {
        return area;
    }

    public void setArea(AreasEmpresa area) {
        this.area = area;
    }

    public Horarios getHorario() {
        return horario;
    }

    public void setHorario(Horarios horario) {
        this.horario = horario;
    }

    @XmlTransient
    public List<MovPersonas> getMovPersonasList() {
        return movPersonasList;
    }

    public void setMovPersonasList(List<MovPersonas> movPersonasList) {
        this.movPersonasList = movPersonasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (personasSucursalPK != null ? personasSucursalPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonasSucursal)) {
            return false;
        }
        PersonasSucursal other = (PersonasSucursal) object;
        if ((this.personasSucursalPK == null && other.personasSucursalPK != null) || (this.personasSucursalPK != null && !this.personasSucursalPK.equals(other.personasSucursalPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.PersonasSucursal[ personasSucursalPK=" + personasSucursalPK + " ]";
    }
    
    public boolean isLocked() {
        if (Objects.equals(estado.getIdEstado(), Constants.STATUS_BLOCKED)) {
            return true;
}
        return false;
    }

    @Override
    public int getPrimaryKey() {
        //Nothing to do here
        return 0;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        //Nothing to do here
    }

    @Override
    public void setUser(Personas user) {
        usuario = user;
    }

    @Override
    public void setDate(Date date) {
        fecha = date;
    }

    @Override
    public void setStatus(Integer STATUS_INACTIVE) {
        estado = new Estados(STATUS_INACTIVE);
    }

}
