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
 * @author Kmilo
 */
@Entity
@Table(name = "remisiones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Remisiones.findAll", query = "SELECT r FROM Remisiones r"),
    @NamedQuery(name = "Remisiones.findByIdRemision", query = "SELECT r FROM Remisiones r WHERE r.idRemision = :idRemision"),
    @NamedQuery(name = "Remisiones.findByEntradaSalida", query = "SELECT r FROM Remisiones r WHERE r.entradaSalida = :entradaSalida"),
    @NamedQuery(name = "Remisiones.findByFecha", query = "SELECT r FROM Remisiones r WHERE r.fecha = :fecha"),
    @NamedQuery(name = "Remisiones.findByDestino", query = "SELECT r FROM Remisiones r WHERE r.destino = :destino"),
    @NamedQuery(name = "Remisiones.findByContenedores", query = "SELECT r FROM Remisiones r WHERE r.contenedores = :contenedores"),
    @NamedQuery(name = "Remisiones.findByUnidadesSueltas", query = "SELECT r FROM Remisiones r WHERE r.unidadesSueltas = :unidadesSueltas")})
public class Remisiones implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha_Inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;
    @Column(name = "Fecha_Fin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "remision", fetch = FetchType.LAZY)
    private List<MovRemisiones> movRemisionesList;
    @JoinColumn(name = "Empresa_Destino", referencedColumnName = "Id_Empresa_Origen")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EmpresaOrigen empresaDestino;
    @JoinColumn(name = "Almacen", referencedColumnName = "Id_Almacen")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Almacen almacen;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "remision", fetch = FetchType.LAZY)
    private List<Cardex> cardexList;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id_Remision")
    private Integer idRemision;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Entrada_Salida")
    private boolean entradaSalida;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Size(max = 32)
    @Column(name = "Destino")
    private String destino;
    @Column(name = "Contenedores")
    private Integer contenedores;
    @Column(name = "Unidades_Sueltas")
    private Integer unidadesSueltas;
    @JoinColumn(name = "Visto_Porteria", referencedColumnName = "Id_Persona")
    @ManyToOne(fetch = FetchType.LAZY)
    private Personas vistoPorteria;
    @JoinColumn(name = "Almacenista", referencedColumnName = "Id_Persona")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Personas almacenista;
    @JoinColumn(name = "Sucursal", referencedColumnName = "Id_Sucursal")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Sucursales sucursal;
    @JoinColumn(name = "Estado", referencedColumnName = "Id_Estado")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Estados estado;
    @JoinColumn(name = "Mov_Persona", referencedColumnName = "Id_Mov_Persona")
    @ManyToOne(fetch = FetchType.LAZY)
    private MovPersonas movPersona;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "remision", fetch = FetchType.LAZY)
    private List<MovMateriales> movMaterialesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "remisiones", fetch = FetchType.LAZY)
    private List<TrasladosMaterial> trasladosMaterialList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "remisiones1", fetch = FetchType.LAZY)
    private List<TrasladosMaterial> trasladosMaterialList1;

    public Remisiones() {
    }

    public Remisiones(Integer idRemision) {
        this.idRemision = idRemision;
    }

    public Remisiones(Integer idRemision, boolean entradaSalida, Date fecha) {
        this.idRemision = idRemision;
        this.entradaSalida = entradaSalida;
        this.fecha = fecha;
    }

    public Integer getIdRemision() {
        return idRemision;
    }

    public void setIdRemision(Integer idRemision) {
        this.idRemision = idRemision;
    }

    public boolean getEntradaSalida() {
        return entradaSalida;
    }

    public void setEntradaSalida(boolean entradaSalida) {
        this.entradaSalida = entradaSalida;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Integer getContenedores() {
        return contenedores;
    }

    public void setContenedores(Integer contenedores) {
        this.contenedores = contenedores;
    }

    public Integer getUnidadesSueltas() {
        return unidadesSueltas;
    }

    public void setUnidadesSueltas(Integer unidadesSueltas) {
        this.unidadesSueltas = unidadesSueltas;
    }

    public Personas getVistoPorteria() {
        return vistoPorteria;
    }

    public void setVistoPorteria(Personas vistoPorteria) {
        this.vistoPorteria = vistoPorteria;
    }

    public Personas getAlmacenista() {
        return almacenista;
    }

    public void setAlmacenista(Personas almacenista) {
        this.almacenista = almacenista;
    }

    public Sucursales getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursales sucursal) {
        this.sucursal = sucursal;
    }

    public Estados getEstado() {
        return estado;
    }

    public void setEstado(Estados estado) {
        this.estado = estado;
    }

    public MovPersonas getMovPersona() {
        return movPersona;
    }

    public void setMovPersona(MovPersonas movPersona) {
        this.movPersona = movPersona;
    }

    @XmlTransient
    public List<MovMateriales> getMovMaterialesList() {
        return movMaterialesList;
    }

    public void setMovMaterialesList(List<MovMateriales> movMaterialesList) {
        this.movMaterialesList = movMaterialesList;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRemision != null ? idRemision.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Remisiones)) {
            return false;
        }
        Remisiones other = (Remisiones) object;
        if ((this.idRemision == null && other.idRemision != null) || (this.idRemision != null && !this.idRemision.equals(other.idRemision))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Remisiones[ idRemision=" + idRemision + " ]";
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    @XmlTransient
    public List<MovRemisiones> getMovRemisionesList() {
        return movRemisionesList;
    }

    public void setMovRemisionesList(List<MovRemisiones> movRemisionesList) {
        this.movRemisionesList = movRemisionesList;
    }

    public EmpresaOrigen getEmpresaDestino() {
        return empresaDestino;
    }

    public void setEmpresaDestino(EmpresaOrigen empresaDestino) {
        this.empresaDestino = empresaDestino;
    }

    public Almacen getAlmacen() {
        return almacen;
    }

    public void setAlmacen(Almacen almacen) {
        this.almacen = almacen;
    }

    @XmlTransient
    public List<Cardex> getCardexList() {
        return cardexList;
    }

    public void setCardexList(List<Cardex> cardexList) {
        this.cardexList = cardexList;
    }
    
}
