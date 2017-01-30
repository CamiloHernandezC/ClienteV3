/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
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
 * @author chernandez
 */
@Entity
@Table(name = "Empresa_Origen_Cli")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EmpresaOrigenCli.findAll", query = "SELECT e FROM EmpresaOrigenCli e"),
    @NamedQuery(name = "EmpresaOrigenCli.findByIdEmorigen", query = "SELECT e FROM EmpresaOrigenCli e WHERE e.idEmorigen = :idEmorigen"),
    @NamedQuery(name = "EmpresaOrigenCli.findByIdTributario", query = "SELECT e FROM EmpresaOrigenCli e WHERE e.idTributario = :idTributario"),
    @NamedQuery(name = "EmpresaOrigenCli.findByNombre1", query = "SELECT e FROM EmpresaOrigenCli e WHERE e.nombre1 = :nombre1"),
    @NamedQuery(name = "EmpresaOrigenCli.findByNombre2", query = "SELECT e FROM EmpresaOrigenCli e WHERE e.nombre2 = :nombre2"),
    @NamedQuery(name = "EmpresaOrigenCli.findByDireccion", query = "SELECT e FROM EmpresaOrigenCli e WHERE e.direccion = :direccion"),
    @NamedQuery(name = "EmpresaOrigenCli.findByTelefono", query = "SELECT e FROM EmpresaOrigenCli e WHERE e.telefono = :telefono"),
    @NamedQuery(name = "EmpresaOrigenCli.findByFecha", query = "SELECT e FROM EmpresaOrigenCli e WHERE e.fecha = :fecha")})
public class EmpresaOrigenCli implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "Id_Emorigen")
    private String idEmorigen;
    @Size(max = 25)
    @Column(name = "Id_Tributario")
    private String idTributario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Nombre_1")
    private String nombre1;
    @Size(max = 45)
    @Column(name = "Nombre_2")
    private String nombre2;
    @Size(max = 120)
    @Column(name = "Direccion")
    private String direccion;
    @Size(max = 25)
    @Column(name = "Telefono")
    private String telefono;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @JoinColumn(name = "Id_Departamento", referencedColumnName = "Id_Departamento")
    @ManyToOne
    private DepartamentosCli idDepartamento;
    @JoinColumn(name = "Id_Estado", referencedColumnName = "Id_Estado")
    @ManyToOne(optional = false)
    private EstadosCli idEstado;
    @JoinColumns({
        @JoinColumn(name = "Id_Departamento", referencedColumnName = "Id_Departamento"),
        @JoinColumn(name = "Id_Municipio", referencedColumnName = "Id_Municipio")})
    @ManyToOne
    private MunicipiosCli municipiosCli;
    @JoinColumn(name = "Id_Pais", referencedColumnName = "Id_Pais")
    @ManyToOne
    private PaisesCli idPais;
    @JoinColumn(name = "Usuario", referencedColumnName = "Id_Persona")
    @ManyToOne(optional = false)
    private PersonasCli usuario;
    @JoinColumn(name = "Id_Sucursal", referencedColumnName = "Id_Sucursal")
    @ManyToOne(optional = false)
    private SucursalesCli idSucursal;
    @OneToMany(mappedBy = "idemorigen")
    private List<NovedadesCli> novedadesCliList;
    @OneToMany(mappedBy = "idEmpresaOrigen")
    private List<PersonasCli> personasCliList;

    public EmpresaOrigenCli() {
    }

    public EmpresaOrigenCli(String idEmorigen) {
        this.idEmorigen = idEmorigen;
    }

    public EmpresaOrigenCli(String idEmorigen, String nombre1, Date fecha) {
        this.idEmorigen = idEmorigen;
        this.nombre1 = nombre1;
        this.fecha = fecha;
    }

    public String getIdEmorigen() {
        return idEmorigen;
    }

    public void setIdEmorigen(String idEmorigen) {
        this.idEmorigen = idEmorigen;
    }

    public String getIdTributario() {
        return idTributario;
    }

    public void setIdTributario(String idTributario) {
        this.idTributario = idTributario;
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

    public DepartamentosCli getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(DepartamentosCli idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public EstadosCli getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(EstadosCli idEstado) {
        this.idEstado = idEstado;
    }

    public MunicipiosCli getMunicipiosCli() {
        return municipiosCli;
    }

    public void setMunicipiosCli(MunicipiosCli municipiosCli) {
        this.municipiosCli = municipiosCli;
    }

    public PaisesCli getIdPais() {
        return idPais;
    }

    public void setIdPais(PaisesCli idPais) {
        this.idPais = idPais;
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
    public List<NovedadesCli> getNovedadesCliList() {
        return novedadesCliList;
    }

    public void setNovedadesCliList(List<NovedadesCli> novedadesCliList) {
        this.novedadesCliList = novedadesCliList;
    }

    @XmlTransient
    public List<PersonasCli> getPersonasCliList() {
        return personasCliList;
    }

    public void setPersonasCliList(List<PersonasCli> personasCliList) {
        this.personasCliList = personasCliList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEmorigen != null ? idEmorigen.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmpresaOrigenCli)) {
            return false;
        }
        EmpresaOrigenCli other = (EmpresaOrigenCli) object;
        if ((this.idEmorigen == null && other.idEmorigen != null) || (this.idEmorigen != null && !this.idEmorigen.equals(other.idEmorigen))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.EmpresaOrigenCli[ idEmorigen=" + idEmorigen + " ]";
    }
    
}
