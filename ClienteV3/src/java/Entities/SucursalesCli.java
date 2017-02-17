/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author MAURICIO
 */
@Entity
@Table(name = "Sucursales_Cli")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SucursalesCli.findAll", query = "SELECT s FROM SucursalesCli s"),
    @NamedQuery(name = "SucursalesCli.findByIdSucursal", query = "SELECT s FROM SucursalesCli s WHERE s.idSucursal = :idSucursal"),
    @NamedQuery(name = "SucursalesCli.findByNombre", query = "SELECT s FROM SucursalesCli s WHERE s.nombre = :nombre"),
    @NamedQuery(name = "SucursalesCli.findByDireccion", query = "SELECT s FROM SucursalesCli s WHERE s.direccion = :direccion"),
    @NamedQuery(name = "SucursalesCli.findByTelefono", query = "SELECT s FROM SucursalesCli s WHERE s.telefono = :telefono")})
public class SucursalesCli implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id_Sucursal")
    private Long idSucursal;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "Nombre")
    private String nombre;
    @Size(max = 120)
    @Column(name = "Direccion")
    private String direccion;
    @Size(max = 25)
    @Column(name = "Telefono")
    private String telefono;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSucursal", fetch = FetchType.LAZY)
    private List<VehiculosCli> vehiculosCliList;
    @OneToMany(mappedBy = "idSucursal", fetch = FetchType.LAZY)
    private List<EmpresaOrigenCli> empresaOrigenCliList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSucursal", fetch = FetchType.LAZY)
    private List<NovedadesCli> novedadesCliList;
    @JoinColumn(name = "Id_Cliente", referencedColumnName = "Id_Cliente")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ClientesCli idCliente;
    @JoinColumn(name = "Departamento", referencedColumnName = "Id_Departamento")
    @ManyToOne(fetch = FetchType.LAZY)
    private DepartamentosCli departamento;
    @JoinColumn(name = "Municipio", referencedColumnName = "Id_Municipio")
    @ManyToOne(fetch = FetchType.LAZY)
    private MunicipiosCli municipio;
    @JoinColumn(name = "Pais", referencedColumnName = "Id_Pais")
    @ManyToOne(fetch = FetchType.LAZY)
    private PaisesCli pais;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sucursalesCli", fetch = FetchType.LAZY)
    private List<PersonasSucursalCli> personasSucursalCliList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSucursal", fetch = FetchType.LAZY)
    private List<MaterialesCli> materialesCliList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSucursal", fetch = FetchType.LAZY)
    private List<MovVehiculosCli> movVehiculosCliList;
    @OneToMany(mappedBy = "idSucursal", fetch = FetchType.LAZY)
    private List<NotificacionesCli> notificacionesCliList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sucursalesCli", fetch = FetchType.LAZY)
    private List<VisitasEsperadasCli> visitasEsperadasCliList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sucursalesCli", fetch = FetchType.LAZY)
    private List<PorteriaSucursalCli> porteriaSucursalCliList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSucursal", fetch = FetchType.LAZY)
    private List<MovDocumentosCli> movDocumentosCliList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSucursal", fetch = FetchType.LAZY)
    private List<ObjetosCli> objetosCliList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSucursal", fetch = FetchType.LAZY)
    private List<MovPersonasCli> movPersonasCliList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSucursal", fetch = FetchType.LAZY)
    private List<MovMaterialesCli> movMaterialesCliList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSucursal", fetch = FetchType.LAZY)
    private List<MovHerramientasCli> movHerramientasCliList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSucursal", fetch = FetchType.LAZY)
    private List<AreasEmpresaCli> areasEmpresaCliList;

    public SucursalesCli() {
    }

    public SucursalesCli(Long idSucursal) {
        this.idSucursal = idSucursal;
    }

    public SucursalesCli(Long idSucursal, String nombre) {
        this.idSucursal = idSucursal;
        this.nombre = nombre;
    }

    public Long getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Long idSucursal) {
        this.idSucursal = idSucursal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    @XmlTransient
    public List<VehiculosCli> getVehiculosCliList() {
        return vehiculosCliList;
    }

    public void setVehiculosCliList(List<VehiculosCli> vehiculosCliList) {
        this.vehiculosCliList = vehiculosCliList;
    }

    @XmlTransient
    public List<EmpresaOrigenCli> getEmpresaOrigenCliList() {
        return empresaOrigenCliList;
    }

    public void setEmpresaOrigenCliList(List<EmpresaOrigenCli> empresaOrigenCliList) {
        this.empresaOrigenCliList = empresaOrigenCliList;
    }

    @XmlTransient
    public List<NovedadesCli> getNovedadesCliList() {
        return novedadesCliList;
    }

    public void setNovedadesCliList(List<NovedadesCli> novedadesCliList) {
        this.novedadesCliList = novedadesCliList;
    }

    public ClientesCli getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(ClientesCli idCliente) {
        this.idCliente = idCliente;
    }

    public DepartamentosCli getDepartamento() {
        return departamento;
    }

    public void setDepartamento(DepartamentosCli departamento) {
        this.departamento = departamento;
    }

    public MunicipiosCli getMunicipio() {
        return municipio;
    }

    public void setMunicipio(MunicipiosCli municipio) {
        this.municipio = municipio;
    }

    public PaisesCli getPais() {
        return pais;
    }

    public void setPais(PaisesCli pais) {
        this.pais = pais;
    }

    @XmlTransient
    public List<PersonasSucursalCli> getPersonasSucursalCliList() {
        return personasSucursalCliList;
    }

    public void setPersonasSucursalCliList(List<PersonasSucursalCli> personasSucursalCliList) {
        this.personasSucursalCliList = personasSucursalCliList;
    }

    @XmlTransient
    public List<MaterialesCli> getMaterialesCliList() {
        return materialesCliList;
    }

    public void setMaterialesCliList(List<MaterialesCli> materialesCliList) {
        this.materialesCliList = materialesCliList;
    }

    @XmlTransient
    public List<MovVehiculosCli> getMovVehiculosCliList() {
        return movVehiculosCliList;
    }

    public void setMovVehiculosCliList(List<MovVehiculosCli> movVehiculosCliList) {
        this.movVehiculosCliList = movVehiculosCliList;
    }

    @XmlTransient
    public List<NotificacionesCli> getNotificacionesCliList() {
        return notificacionesCliList;
    }

    public void setNotificacionesCliList(List<NotificacionesCli> notificacionesCliList) {
        this.notificacionesCliList = notificacionesCliList;
    }

    @XmlTransient
    public List<VisitasEsperadasCli> getVisitasEsperadasCliList() {
        return visitasEsperadasCliList;
    }

    public void setVisitasEsperadasCliList(List<VisitasEsperadasCli> visitasEsperadasCliList) {
        this.visitasEsperadasCliList = visitasEsperadasCliList;
    }

    @XmlTransient
    public List<PorteriaSucursalCli> getPorteriaSucursalCliList() {
        return porteriaSucursalCliList;
    }

    public void setPorteriaSucursalCliList(List<PorteriaSucursalCli> porteriaSucursalCliList) {
        this.porteriaSucursalCliList = porteriaSucursalCliList;
    }

    @XmlTransient
    public List<MovDocumentosCli> getMovDocumentosCliList() {
        return movDocumentosCliList;
    }

    public void setMovDocumentosCliList(List<MovDocumentosCli> movDocumentosCliList) {
        this.movDocumentosCliList = movDocumentosCliList;
    }

    @XmlTransient
    public List<ObjetosCli> getObjetosCliList() {
        return objetosCliList;
    }

    public void setObjetosCliList(List<ObjetosCli> objetosCliList) {
        this.objetosCliList = objetosCliList;
    }

    @XmlTransient
    public List<MovPersonasCli> getMovPersonasCliList() {
        return movPersonasCliList;
    }

    public void setMovPersonasCliList(List<MovPersonasCli> movPersonasCliList) {
        this.movPersonasCliList = movPersonasCliList;
    }

    @XmlTransient
    public List<MovMaterialesCli> getMovMaterialesCliList() {
        return movMaterialesCliList;
    }

    public void setMovMaterialesCliList(List<MovMaterialesCli> movMaterialesCliList) {
        this.movMaterialesCliList = movMaterialesCliList;
    }

    @XmlTransient
    public List<MovHerramientasCli> getMovHerramientasCliList() {
        return movHerramientasCliList;
    }

    public void setMovHerramientasCliList(List<MovHerramientasCli> movHerramientasCliList) {
        this.movHerramientasCliList = movHerramientasCliList;
    }

    @XmlTransient
    public List<AreasEmpresaCli> getAreasEmpresaCliList() {
        return areasEmpresaCliList;
    }

    public void setAreasEmpresaCliList(List<AreasEmpresaCli> areasEmpresaCliList) {
        this.areasEmpresaCliList = areasEmpresaCliList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSucursal != null ? idSucursal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SucursalesCli)) {
            return false;
        }
        SucursalesCli other = (SucursalesCli) object;
        if ((this.idSucursal == null && other.idSucursal != null) || (this.idSucursal != null && !this.idSucursal.equals(other.idSucursal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.SucursalesCli[ idSucursal=" + idSucursal + " ]";
    }
    
}
