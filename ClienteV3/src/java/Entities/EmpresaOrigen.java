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
 * @author Kmilo
 */
@Entity
@Table(name = "empresa_origen")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EmpresaOrigen.findAll", query = "SELECT e FROM EmpresaOrigen e"),
    @NamedQuery(name = "EmpresaOrigen.findByIdEmpresaOrigen", query = "SELECT e FROM EmpresaOrigen e WHERE e.idEmpresaOrigen = :idEmpresaOrigen"),
    @NamedQuery(name = "EmpresaOrigen.findByNit", query = "SELECT e FROM EmpresaOrigen e WHERE e.nit = :nit"),
    @NamedQuery(name = "EmpresaOrigen.findByNombre1", query = "SELECT e FROM EmpresaOrigen e WHERE e.nombre1 = :nombre1"),
    @NamedQuery(name = "EmpresaOrigen.findByNombre2", query = "SELECT e FROM EmpresaOrigen e WHERE e.nombre2 = :nombre2"),
    @NamedQuery(name = "EmpresaOrigen.findByDireccion", query = "SELECT e FROM EmpresaOrigen e WHERE e.direccion = :direccion"),
    @NamedQuery(name = "EmpresaOrigen.findByTelefono", query = "SELECT e FROM EmpresaOrigen e WHERE e.telefono = :telefono"),
    @NamedQuery(name = "EmpresaOrigen.findByFecha", query = "SELECT e FROM EmpresaOrigen e WHERE e.fecha = :fecha")})
public class EmpresaOrigen implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id_Empresa_Origen")
    private Integer idEmpresaOrigen;
    @Size(max = 13)
    @Column(name = "NIT")
    private String nit;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "Nombre_1")
    private String nombre1;
    @Size(max = 32)
    @Column(name = "Nombre_2")
    private String nombre2;
    @Size(max = 140)
    @Column(name = "Direccion")
    private String direccion;
    @Size(max = 20)
    @Column(name = "Telefono")
    private String telefono;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @JoinColumn(name = "Usuario", referencedColumnName = "Id_Persona")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Personas usuario;
    @JoinColumn(name = "Pais", referencedColumnName = "Id_Pais")
    @ManyToOne(fetch = FetchType.LAZY)
    private Paises pais;
    @JoinColumn(name = "Departamento", referencedColumnName = "Id_Departamento")
    @ManyToOne(fetch = FetchType.LAZY)
    private Departamentos departamento;
    @JoinColumn(name = "Municipio", referencedColumnName = "Id_Municipio")
    @ManyToOne(fetch = FetchType.LAZY)
    private Municipios municipio;
    @JoinColumn(name = "Estado", referencedColumnName = "Id_Estado")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Estados estado;
    @OneToMany(mappedBy = "empresaOrigen", fetch = FetchType.LAZY)
    private List<Personas> personasList;
    @OneToMany(mappedBy = "empresaOrigen", fetch = FetchType.LAZY)
    private List<Notificaciones> notificacionesList;

    public EmpresaOrigen() {
    }

    public EmpresaOrigen(Integer idEmpresaOrigen) {
        this.idEmpresaOrigen = idEmpresaOrigen;
    }

    public EmpresaOrigen(Integer idEmpresaOrigen, String nombre1, Date fecha) {
        this.idEmpresaOrigen = idEmpresaOrigen;
        this.nombre1 = nombre1;
        this.fecha = fecha;
    }

    public Integer getIdEmpresaOrigen() {
        return idEmpresaOrigen;
    }

    public void setIdEmpresaOrigen(Integer idEmpresaOrigen) {
        this.idEmpresaOrigen = idEmpresaOrigen;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getNombre1() {
        return nombre1;
    }

    public void setNombre1(String nombre1) {
        this.nombre1 = nombre1;
    }

    public String getNombre2() {
        return nombre2;
    }

    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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

    public Paises getPais() {
        return pais;
    }

    public void setPais(Paises pais) {
        this.pais = pais;
    }

    public Departamentos getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamentos departamento) {
        this.departamento = departamento;
    }

    public Municipios getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipios municipio) {
        this.municipio = municipio;
    }

    public Estados getEstado() {
        return estado;
    }

    public void setEstado(Estados estado) {
        this.estado = estado;
    }

    @XmlTransient
    public List<Personas> getPersonasList() {
        return personasList;
    }

    public void setPersonasList(List<Personas> personasList) {
        this.personasList = personasList;
    }

    @XmlTransient
    public List<Notificaciones> getNotificacionesList() {
        return notificacionesList;
    }

    public void setNotificacionesList(List<Notificaciones> notificacionesList) {
        this.notificacionesList = notificacionesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEmpresaOrigen != null ? idEmpresaOrigen.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmpresaOrigen)) {
            return false;
        }
        EmpresaOrigen other = (EmpresaOrigen) object;
        if ((this.idEmpresaOrigen == null && other.idEmpresaOrigen != null) || (this.idEmpresaOrigen != null && !this.idEmpresaOrigen.equals(other.idEmpresaOrigen))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.EmpresaOrigen[ idEmpresaOrigen=" + idEmpresaOrigen + " ]";
    }
    
}
