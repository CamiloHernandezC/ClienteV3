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
 * @author Kmilo
 */
@Entity
@Table(name = "sucursales")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sucursales.findAll", query = "SELECT s FROM Sucursales s"),
    @NamedQuery(name = "Sucursales.findByIdSucursal", query = "SELECT s FROM Sucursales s WHERE s.idSucursal = :idSucursal"),
    @NamedQuery(name = "Sucursales.findByNombre", query = "SELECT s FROM Sucursales s WHERE s.nombre = :nombre"),
    @NamedQuery(name = "Sucursales.findByDireccion", query = "SELECT s FROM Sucursales s WHERE s.direccion = :direccion"),
    @NamedQuery(name = "Sucursales.findByTelefono", query = "SELECT s FROM Sucursales s WHERE s.telefono = :telefono")})
public class Sucursales implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id_Sucursal")
    private Integer idSucursal;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "Nombre")
    private String nombre;
    @Size(max = 140)
    @Column(name = "Direccion")
    private String direccion;
    @Size(max = 20)
    @Column(name = "Telefono")
    private String telefono;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sucursales", fetch = FetchType.LAZY)
    private List<PorteriasSucursal> porteriasSucursalList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sucursales", fetch = FetchType.LAZY)
    private List<VisitasEsperadas> visitasEsperadasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sucursal", fetch = FetchType.LAZY)
    private List<AreasEmpresa> areasEmpresaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sucursales", fetch = FetchType.LAZY)
    private List<MaterialesSucursal> materialesSucursalList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sucursal", fetch = FetchType.LAZY)
    private List<MovRemisiones> movRemisionesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sucursal", fetch = FetchType.LAZY)
    private List<Remisiones> remisionesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sucursal", fetch = FetchType.LAZY)
    private List<Almacen> almacenList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sucursal", fetch = FetchType.LAZY)
    private List<Objetos> objetosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sucursales", fetch = FetchType.LAZY)
    private List<PersonasSucursal> personasSucursalList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sucursal", fetch = FetchType.LAZY)
    private List<MovDocumentos> movDocumentosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sucursales", fetch = FetchType.LAZY)
    private List<VehiculosSucursal> vehiculosSucursalList;
    @JoinColumn(name = "Cliente", referencedColumnName = "Id_Cliente")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Clientes cliente;
    @JoinColumn(name = "Pais", referencedColumnName = "Id_Pais")
    @ManyToOne(fetch = FetchType.LAZY)
    private Paises pais;
    @JoinColumn(name = "Departamento", referencedColumnName = "Id_Departamento")
    @ManyToOne(fetch = FetchType.LAZY)
    private Departamentos departamento;
    @JoinColumn(name = "Municipio", referencedColumnName = "Id_Municipio")
    @ManyToOne(fetch = FetchType.LAZY)
    private Municipios municipio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sucursal", fetch = FetchType.LAZY)
    private List<Horarios> horariosList;
    @OneToMany(mappedBy = "sucursal", fetch = FetchType.LAZY)
    private List<Notificaciones> notificacionesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sucursalEntrada", fetch = FetchType.LAZY)
    private List<TrasladosMaterial> trasladosMaterialList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sucursalSalida", fetch = FetchType.LAZY)
    private List<TrasladosMaterial> trasladosMaterialList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sucursal", fetch = FetchType.LAZY)
    private List<MovObjetos> movObjetosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sucursal", fetch = FetchType.LAZY)
    private List<AccesoUsuario> accesoUsuarioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sucursal", fetch = FetchType.LAZY)
    private List<Cardex> cardexList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sucursal", fetch = FetchType.LAZY)
    private List<MovVehiculos> movVehiculosList;

    public Sucursales() {
    }

    public Sucursales(Integer idSucursal) {
        this.idSucursal = idSucursal;
    }

    public Sucursales(Integer idSucursal, String nombre) {
        this.idSucursal = idSucursal;
        this.nombre = nombre;
    }

    public Integer getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Integer idSucursal) {
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
    public List<PorteriasSucursal> getPorteriasSucursalList() {
        return porteriasSucursalList;
    }

    public void setPorteriasSucursalList(List<PorteriasSucursal> porteriasSucursalList) {
        this.porteriasSucursalList = porteriasSucursalList;
    }

    @XmlTransient
    public List<VisitasEsperadas> getVisitasEsperadasList() {
        return visitasEsperadasList;
    }

    public void setVisitasEsperadasList(List<VisitasEsperadas> visitasEsperadasList) {
        this.visitasEsperadasList = visitasEsperadasList;
    }

    @XmlTransient
    public List<AreasEmpresa> getAreasEmpresaList() {
        return areasEmpresaList;
    }

    public void setAreasEmpresaList(List<AreasEmpresa> areasEmpresaList) {
        this.areasEmpresaList = areasEmpresaList;
    }

    @XmlTransient
    public List<MaterialesSucursal> getMaterialesSucursalList() {
        return materialesSucursalList;
    }

    public void setMaterialesSucursalList(List<MaterialesSucursal> materialesSucursalList) {
        this.materialesSucursalList = materialesSucursalList;
    }

    @XmlTransient
    public List<MovRemisiones> getMovRemisionesList() {
        return movRemisionesList;
    }

    public void setMovRemisionesList(List<MovRemisiones> movRemisionesList) {
        this.movRemisionesList = movRemisionesList;
    }

    @XmlTransient
    public List<Remisiones> getRemisionesList() {
        return remisionesList;
    }

    public void setRemisionesList(List<Remisiones> remisionesList) {
        this.remisionesList = remisionesList;
    }

    @XmlTransient
    public List<Almacen> getAlmacenList() {
        return almacenList;
    }

    public void setAlmacenList(List<Almacen> almacenList) {
        this.almacenList = almacenList;
    }

    @XmlTransient
    public List<Objetos> getObjetosList() {
        return objetosList;
    }

    public void setObjetosList(List<Objetos> objetosList) {
        this.objetosList = objetosList;
    }

    @XmlTransient
    public List<PersonasSucursal> getPersonasSucursalList() {
        return personasSucursalList;
    }

    public void setPersonasSucursalList(List<PersonasSucursal> personasSucursalList) {
        this.personasSucursalList = personasSucursalList;
    }

    @XmlTransient
    public List<MovDocumentos> getMovDocumentosList() {
        return movDocumentosList;
    }

    public void setMovDocumentosList(List<MovDocumentos> movDocumentosList) {
        this.movDocumentosList = movDocumentosList;
    }

    @XmlTransient
    public List<VehiculosSucursal> getVehiculosSucursalList() {
        return vehiculosSucursalList;
    }

    public void setVehiculosSucursalList(List<VehiculosSucursal> vehiculosSucursalList) {
        this.vehiculosSucursalList = vehiculosSucursalList;
    }

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
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

    @XmlTransient
    public List<Horarios> getHorariosList() {
        return horariosList;
    }

    public void setHorariosList(List<Horarios> horariosList) {
        this.horariosList = horariosList;
    }

    @XmlTransient
    public List<Notificaciones> getNotificacionesList() {
        return notificacionesList;
    }

    public void setNotificacionesList(List<Notificaciones> notificacionesList) {
        this.notificacionesList = notificacionesList;
    }

    @XmlTransient
    public List<TrasladosMaterial> getTrasladosMaterialList() {
        return trasladosMaterialList;
    }

    public void setTrasladosMaterialList(List<TrasladosMaterial> trasladosMaterialList) {
        this.trasladosMaterialList = trasladosMaterialList;
    }

    @XmlTransient
    public List<TrasladosMaterial> getTrasladosMaterialList1() {
        return trasladosMaterialList1;
    }

    public void setTrasladosMaterialList1(List<TrasladosMaterial> trasladosMaterialList1) {
        this.trasladosMaterialList1 = trasladosMaterialList1;
    }

    @XmlTransient
    public List<MovObjetos> getMovObjetosList() {
        return movObjetosList;
    }

    public void setMovObjetosList(List<MovObjetos> movObjetosList) {
        this.movObjetosList = movObjetosList;
    }

    @XmlTransient
    public List<AccesoUsuario> getAccesoUsuarioList() {
        return accesoUsuarioList;
    }

    public void setAccesoUsuarioList(List<AccesoUsuario> accesoUsuarioList) {
        this.accesoUsuarioList = accesoUsuarioList;
    }

    @XmlTransient
    public List<Cardex> getCardexList() {
        return cardexList;
    }

    public void setCardexList(List<Cardex> cardexList) {
        this.cardexList = cardexList;
    }

    @XmlTransient
    public List<MovVehiculos> getMovVehiculosList() {
        return movVehiculosList;
    }

    public void setMovVehiculosList(List<MovVehiculos> movVehiculosList) {
        this.movVehiculosList = movVehiculosList;
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
        if (!(object instanceof Sucursales)) {
            return false;
        }
        Sucursales other = (Sucursales) object;
        if ((this.idSucursal == null && other.idSucursal != null) || (this.idSucursal != null && !this.idSucursal.equals(other.idSucursal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Sucursales[ idSucursal=" + idSucursal + " ]";
    }
    
}
