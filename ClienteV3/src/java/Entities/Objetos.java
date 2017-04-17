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
@Table(name = "objetos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Objetos.findAll", query = "SELECT o FROM Objetos o"),
    @NamedQuery(name = "Objetos.findByIdObjeto", query = "SELECT o FROM Objetos o WHERE o.idObjeto = :idObjeto"),
    @NamedQuery(name = "Objetos.findBySerial", query = "SELECT o FROM Objetos o WHERE o.serial = :serial"),
    @NamedQuery(name = "Objetos.findByIdExterno", query = "SELECT o FROM Objetos o WHERE o.idExterno = :idExterno"),
    @NamedQuery(name = "Objetos.findByDescripcion", query = "SELECT o FROM Objetos o WHERE o.descripcion = :descripcion"),
    @NamedQuery(name = "Objetos.findByColor1", query = "SELECT o FROM Objetos o WHERE o.color1 = :color1"),
    @NamedQuery(name = "Objetos.findByColor2", query = "SELECT o FROM Objetos o WHERE o.color2 = :color2"),
    @NamedQuery(name = "Objetos.findByColor3", query = "SELECT o FROM Objetos o WHERE o.color3 = :color3"),
    @NamedQuery(name = "Objetos.findByUnidadPeso", query = "SELECT o FROM Objetos o WHERE o.unidadPeso = :unidadPeso"),
    @NamedQuery(name = "Objetos.findByPeso", query = "SELECT o FROM Objetos o WHERE o.peso = :peso"),
    @NamedQuery(name = "Objetos.findByUnidadVolumen", query = "SELECT o FROM Objetos o WHERE o.unidadVolumen = :unidadVolumen"),
    @NamedQuery(name = "Objetos.findByVolumen", query = "SELECT o FROM Objetos o WHERE o.volumen = :volumen"),
    @NamedQuery(name = "Objetos.findByFecha", query = "SELECT o FROM Objetos o WHERE o.fecha = :fecha")})
public class Objetos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id_Objeto")
    private Integer idObjeto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "Serial")
    private String serial;
    @Size(max = 32)
    @Column(name = "Id_Externo")
    private String idExterno;
    @Size(max = 140)
    @Column(name = "Descripcion")
    private String descripcion;
    @Size(max = 10)
    @Column(name = "Color1")
    private String color1;
    @Size(max = 10)
    @Column(name = "Color2")
    private String color2;
    @Size(max = 10)
    @Column(name = "Color3")
    private String color3;
    @Column(name = "Unidad_Peso")
    private Integer unidadPeso;
    @Column(name = "Peso")
    private Integer peso;
    @Column(name = "Unidad_Volumen")
    private Integer unidadVolumen;
    @Column(name = "Volumen")
    private Integer volumen;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @JoinColumn(name = "Usuario", referencedColumnName = "Id_Persona")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Personas usuario;
    @JoinColumn(name = "Sucursal", referencedColumnName = "Id_Sucursal")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Sucursales sucursal;
    @JoinColumn(name = "Entidad", referencedColumnName = "Id_Entidad")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Entidades entidad;
    @JoinColumn(name = "Marca", referencedColumnName = "Id_Marca")
    @ManyToOne(fetch = FetchType.LAZY)
    private Marcas marca;
    @JoinColumn(name = "Linea", referencedColumnName = "Id_Linea")
    @ManyToOne(fetch = FetchType.LAZY)
    private Lineas linea;
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
    @OneToMany(mappedBy = "objeto", fetch = FetchType.LAZY)
    private List<Notificaciones> notificacionesList;

    public Objetos() {
    }

    public Objetos(Integer idObjeto) {
        this.idObjeto = idObjeto;
    }

    public Objetos(Integer idObjeto, String serial, Date fecha) {
        this.idObjeto = idObjeto;
        this.serial = serial;
        this.fecha = fecha;
    }

    public Integer getIdObjeto() {
        return idObjeto;
    }

    public void setIdObjeto(Integer idObjeto) {
        this.idObjeto = idObjeto;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getIdExterno() {
        return idExterno;
    }

    public void setIdExterno(String idExterno) {
        this.idExterno = idExterno;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public Integer getUnidadPeso() {
        return unidadPeso;
    }

    public void setUnidadPeso(Integer unidadPeso) {
        this.unidadPeso = unidadPeso;
    }

    public Integer getPeso() {
        return peso;
    }

    public void setPeso(Integer peso) {
        this.peso = peso;
    }

    public Integer getUnidadVolumen() {
        return unidadVolumen;
    }

    public void setUnidadVolumen(Integer unidadVolumen) {
        this.unidadVolumen = unidadVolumen;
    }

    public Integer getVolumen() {
        return volumen;
    }

    public void setVolumen(Integer volumen) {
        this.volumen = volumen;
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

    public Sucursales getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursales sucursal) {
        this.sucursal = sucursal;
    }

    public Entidades getEntidad() {
        return entidad;
    }

    public void setEntidad(Entidades entidad) {
        this.entidad = entidad;
    }

    public Marcas getMarca() {
        return marca;
    }

    public void setMarca(Marcas marca) {
        this.marca = marca;
    }

    public Lineas getLinea() {
        return linea;
    }

    public void setLinea(Lineas linea) {
        this.linea = linea;
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
    public List<Notificaciones> getNotificacionesList() {
        return notificacionesList;
    }

    public void setNotificacionesList(List<Notificaciones> notificacionesList) {
        this.notificacionesList = notificacionesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idObjeto != null ? idObjeto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Objetos)) {
            return false;
        }
        Objetos other = (Objetos) object;
        if ((this.idObjeto == null && other.idObjeto != null) || (this.idObjeto != null && !this.idObjeto.equals(other.idObjeto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Objetos[ idObjeto=" + idObjeto + " ]";
    }
    
}
