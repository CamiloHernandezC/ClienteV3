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
@Table(name = "Vehiculos_Cli")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VehiculosCli.findAll", query = "SELECT v FROM VehiculosCli v"),
    @NamedQuery(name = "VehiculosCli.findByPlaca", query = "SELECT v FROM VehiculosCli v WHERE v.placa = :placa"),
    @NamedQuery(name = "VehiculosCli.findByDescripcion", query = "SELECT v FROM VehiculosCli v WHERE v.descripcion = :descripcion"),
    @NamedQuery(name = "VehiculosCli.findByModelo", query = "SELECT v FROM VehiculosCli v WHERE v.modelo = :modelo"),
    @NamedQuery(name = "VehiculosCli.findByColor1", query = "SELECT v FROM VehiculosCli v WHERE v.color1 = :color1"),
    @NamedQuery(name = "VehiculosCli.findByColor2", query = "SELECT v FROM VehiculosCli v WHERE v.color2 = :color2"),
    @NamedQuery(name = "VehiculosCli.findByColor3", query = "SELECT v FROM VehiculosCli v WHERE v.color3 = :color3"),
    @NamedQuery(name = "VehiculosCli.findByPeso", query = "SELECT v FROM VehiculosCli v WHERE v.peso = :peso"),
    @NamedQuery(name = "VehiculosCli.findByVolumen", query = "SELECT v FROM VehiculosCli v WHERE v.volumen = :volumen"),
    @NamedQuery(name = "VehiculosCli.findByFecha", query = "SELECT v FROM VehiculosCli v WHERE v.fecha = :fecha")})
public class VehiculosCli implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "Placa")
    private String placa;
    @Size(max = 120)
    @Column(name = "Descripcion")
    private String descripcion;
    @Size(max = 4)
    @Column(name = "Modelo")
    private String modelo;
    @Size(max = 25)
    @Column(name = "Color1")
    private String color1;
    @Size(max = 25)
    @Column(name = "Color2")
    private String color2;
    @Size(max = 25)
    @Column(name = "Color3")
    private String color3;
    @Size(max = 6)
    @Column(name = "Peso")
    private String peso;
    @Size(max = 6)
    @Column(name = "Volumen")
    private String volumen;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @JoinColumn(name = "Id_Departamento", referencedColumnName = "Id_Departamento")
    @ManyToOne(fetch = FetchType.LAZY)
    private DepartamentosCli idDepartamento;
    @JoinColumn(name = "Id_Entidad", referencedColumnName = "Id_Entidad")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EntidadesCli idEntidad;
    @JoinColumn(name = "Id_Estado", referencedColumnName = "Id_Estado")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EstadosCli idEstado;
    @JoinColumn(name = "Id_Linea", referencedColumnName = "Id_Linea")
    @ManyToOne(fetch = FetchType.LAZY)
    private LineasCli idLinea;
    @JoinColumn(name = "Id_Marca", referencedColumnName = "Id_Marca")
    @ManyToOne(fetch = FetchType.LAZY)
    private MarcasCli idMarca;
    @JoinColumn(name = "Id_Municipio", referencedColumnName = "Id_Municipio")
    @ManyToOne(fetch = FetchType.LAZY)
    private MunicipiosCli idMunicipio;
    @JoinColumn(name = "Id_Pais", referencedColumnName = "Id_Pais")
    @ManyToOne(fetch = FetchType.LAZY)
    private PaisesCli idPais;
    @JoinColumn(name = "Usuario", referencedColumnName = "Id_Persona")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PersonasCli usuario;
    @JoinColumn(name = "Unidad_Peso", referencedColumnName = "Id_Unidad")
    @ManyToOne(fetch = FetchType.LAZY)
    private UnidadesCli unidadPeso;
    @JoinColumn(name = "Unidad_Volumen", referencedColumnName = "Id_Unidad")
    @ManyToOne(fetch = FetchType.LAZY)
    private UnidadesCli unidadVolumen;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vehiculosCli", fetch = FetchType.LAZY)
    private List<VehiculosSucursal> vehiculosSucursalList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "placa", fetch = FetchType.LAZY)
    private List<MovVehiculosCli> movVehiculosCliList;
    @OneToMany(mappedBy = "idVehiculo", fetch = FetchType.LAZY)
    private List<NotificacionesCli> notificacionesCliList;

    public VehiculosCli() {
    }

    public VehiculosCli(String placa) {
        this.placa = placa;
    }

    public VehiculosCli(String placa, Date fecha) {
        this.placa = placa;
        this.fecha = fecha;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor1() {
        return color1;
    }

    public void setColor1(String color1) {
        this.color1 = color1;
    }

    public String getColor2() {
        return color2;
    }

    public void setColor2(String color2) {
        this.color2 = color2;
    }

    public String getColor3() {
        return color3;
    }

    public void setColor3(String color3) {
        this.color3 = color3;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getVolumen() {
        return volumen;
    }

    public void setVolumen(String volumen) {
        this.volumen = volumen;
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

    public EntidadesCli getIdEntidad() {
        return idEntidad;
    }

    public void setIdEntidad(EntidadesCli idEntidad) {
        this.idEntidad = idEntidad;
    }

    public EstadosCli getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(EstadosCli idEstado) {
        this.idEstado = idEstado;
    }

    public LineasCli getIdLinea() {
        return idLinea;
    }

    public void setIdLinea(LineasCli idLinea) {
        this.idLinea = idLinea;
    }

    public MarcasCli getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(MarcasCli idMarca) {
        this.idMarca = idMarca;
    }

    public MunicipiosCli getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(MunicipiosCli idMunicipio) {
        this.idMunicipio = idMunicipio;
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

    public UnidadesCli getUnidadPeso() {
        return unidadPeso;
    }

    public void setUnidadPeso(UnidadesCli unidadPeso) {
        this.unidadPeso = unidadPeso;
    }

    public UnidadesCli getUnidadVolumen() {
        return unidadVolumen;
    }

    public void setUnidadVolumen(UnidadesCli unidadVolumen) {
        this.unidadVolumen = unidadVolumen;
    }

    @XmlTransient
    public List<VehiculosSucursal> getVehiculosSucursalList() {
        return vehiculosSucursalList;
    }

    public void setVehiculosSucursalList(List<VehiculosSucursal> vehiculosSucursalList) {
        this.vehiculosSucursalList = vehiculosSucursalList;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (placa != null ? placa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VehiculosCli)) {
            return false;
        }
        VehiculosCli other = (VehiculosCli) object;
        if ((this.placa == null && other.placa != null) || (this.placa != null && !this.placa.equals(other.placa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.VehiculosCli[ placa=" + placa + " ]";
    }
    
}
