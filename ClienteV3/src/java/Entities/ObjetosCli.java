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
@Table(name = "Objetos_Cli")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ObjetosCli.findAll", query = "SELECT o FROM ObjetosCli o"),
    @NamedQuery(name = "ObjetosCli.findByIdObjeto", query = "SELECT o FROM ObjetosCli o WHERE o.idObjeto = :idObjeto"),
    @NamedQuery(name = "ObjetosCli.findBySerialObjeto", query = "SELECT o FROM ObjetosCli o WHERE o.serialObjeto = :serialObjeto"),
    @NamedQuery(name = "ObjetosCli.findByIdExterno", query = "SELECT o FROM ObjetosCli o WHERE o.idExterno = :idExterno"),
    @NamedQuery(name = "ObjetosCli.findByDescripcion", query = "SELECT o FROM ObjetosCli o WHERE o.descripcion = :descripcion"),
    @NamedQuery(name = "ObjetosCli.findByColor1", query = "SELECT o FROM ObjetosCli o WHERE o.color1 = :color1"),
    @NamedQuery(name = "ObjetosCli.findByColor2", query = "SELECT o FROM ObjetosCli o WHERE o.color2 = :color2"),
    @NamedQuery(name = "ObjetosCli.findByColor3", query = "SELECT o FROM ObjetosCli o WHERE o.color3 = :color3"),
    @NamedQuery(name = "ObjetosCli.findByUnidadPeso", query = "SELECT o FROM ObjetosCli o WHERE o.unidadPeso = :unidadPeso"),
    @NamedQuery(name = "ObjetosCli.findByPeso", query = "SELECT o FROM ObjetosCli o WHERE o.peso = :peso"),
    @NamedQuery(name = "ObjetosCli.findByUnidadVolumen", query = "SELECT o FROM ObjetosCli o WHERE o.unidadVolumen = :unidadVolumen"),
    @NamedQuery(name = "ObjetosCli.findByVolumen", query = "SELECT o FROM ObjetosCli o WHERE o.volumen = :volumen"),
    @NamedQuery(name = "ObjetosCli.findByFecha", query = "SELECT o FROM ObjetosCli o WHERE o.fecha = :fecha")})
public class ObjetosCli implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 14)
    @Column(name = "Id_Objeto")
    private String idObjeto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Serial_Objeto")
    private String serialObjeto;
    @Size(max = 40)
    @Column(name = "Id_Externo")
    private String idExterno;
    @Size(max = 120)
    @Column(name = "Descripcion")
    private String descripcion;
    @Size(max = 25)
    @Column(name = "Color1")
    private String color1;
    @Size(max = 25)
    @Column(name = "Color2")
    private String color2;
    @Size(max = 25)
    @Column(name = "Color3")
    private String color3;
    @Column(name = "Unidad_Peso")
    private Integer unidadPeso;
    @Size(max = 6)
    @Column(name = "Peso")
    private String peso;
    @Column(name = "Unidad_Volumen")
    private Integer unidadVolumen;
    @Size(max = 6)
    @Column(name = "Volumen")
    private String volumen;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @OneToMany(mappedBy = "idObjeto", fetch = FetchType.LAZY)
    private List<NotificacionesCli> notificacionesCliList;
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
    @JoinColumn(name = "Id_Sucursal", referencedColumnName = "Id_Sucursal")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private SucursalesCli idSucursal;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idObjeto", fetch = FetchType.LAZY)
    private List<MovHerramientasCli> movHerramientasCliList;

    public ObjetosCli() {
    }

    public ObjetosCli(String idObjeto) {
        this.idObjeto = idObjeto;
    }

    public ObjetosCli(String idObjeto, String serialObjeto, Date fecha) {
        this.idObjeto = idObjeto;
        this.serialObjeto = serialObjeto;
        this.fecha = fecha;
    }

    public String getIdObjeto() {
        return idObjeto;
    }

    public void setIdObjeto(String idObjeto) {
        this.idObjeto = idObjeto;
    }

    public String getSerialObjeto() {
        return serialObjeto;
    }

    public void setSerialObjeto(String serialObjeto) {
        this.serialObjeto = serialObjeto;
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

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public Integer getUnidadVolumen() {
        return unidadVolumen;
    }

    public void setUnidadVolumen(Integer unidadVolumen) {
        this.unidadVolumen = unidadVolumen;
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

    @XmlTransient
    public List<NotificacionesCli> getNotificacionesCliList() {
        return notificacionesCliList;
    }

    public void setNotificacionesCliList(List<NotificacionesCli> notificacionesCliList) {
        this.notificacionesCliList = notificacionesCliList;
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

    public SucursalesCli getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(SucursalesCli idSucursal) {
        this.idSucursal = idSucursal;
    }

    @XmlTransient
    public List<MovHerramientasCli> getMovHerramientasCliList() {
        return movHerramientasCliList;
    }

    public void setMovHerramientasCliList(List<MovHerramientasCli> movHerramientasCliList) {
        this.movHerramientasCliList = movHerramientasCliList;
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
        if (!(object instanceof ObjetosCli)) {
            return false;
        }
        ObjetosCli other = (ObjetosCli) object;
        if ((this.idObjeto == null && other.idObjeto != null) || (this.idObjeto != null && !this.idObjeto.equals(other.idObjeto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.ObjetosCli[ idObjeto=" + idObjeto + " ]";
    }
    
}
