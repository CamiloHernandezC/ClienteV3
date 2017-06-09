/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Utils.BundleUtils;
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
    @NamedQuery(name = "Remisiones.findByFechaInicio", query = "SELECT r FROM Remisiones r WHERE r.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "Remisiones.findByContenedores", query = "SELECT r FROM Remisiones r WHERE r.contenedores = :contenedores"),
    @NamedQuery(name = "Remisiones.findByUnidadesSueltas", query = "SELECT r FROM Remisiones r WHERE r.unidadesSueltas = :unidadesSueltas"),
    @NamedQuery(name = "Remisiones.findByFechaFin", query = "SELECT r FROM Remisiones r WHERE r.fechaFin = :fechaFin")})
public class Remisiones implements Serializable {

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
    @Column(name = "Fecha_Inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;
    @Column(name = "Contenedores")
    private Integer contenedores;
    @Column(name = "Unidades_Sueltas")
    private Integer unidadesSueltas;
    @Column(name = "Fecha_Fin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "remision", fetch = FetchType.LAZY)
    private List<MovRemisiones> movRemisionesList;
    @JoinColumn(name = "Almacenista", referencedColumnName = "Id_Persona")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Personas almacenista;
    @JoinColumn(name = "Sucursal", referencedColumnName = "Id_Sucursal")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Sucursales sucursal;
    @JoinColumn(name = "Estado", referencedColumnName = "Id_Estado")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Estados estado;
    @JoinColumn(name = "Empresa_Destino", referencedColumnName = "Id_Empresa_Origen")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EmpresaOrigen empresaDestino;
    @JoinColumn(name = "Almacen", referencedColumnName = "Id_Almacen")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Almacen almacen;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "remisiones", fetch = FetchType.LAZY)
    private List<TrasladosMaterial> trasladosMaterialList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "remisiones1", fetch = FetchType.LAZY)
    private List<TrasladosMaterial> trasladosMaterialList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "remision", fetch = FetchType.LAZY)
    private List<Cardex> cardexList;

    public Remisiones() {
    }

    public Remisiones(Integer idRemision) {
        this.idRemision = idRemision;
    }

    public Remisiones(Integer idRemision, boolean entradaSalida, Date fechaInicio) {
        this.idRemision = idRemision;
        this.entradaSalida = entradaSalida;
        this.fechaInicio = fechaInicio;
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

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
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
    public List<Cardex> getCardexList() {
        return cardexList;
    }

    public void setCardexList(List<Cardex> cardexList) {
        this.cardexList = cardexList;
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
    
    public String movementType(){
        if(entradaSalida){
            return BundleUtils.getBundleProperty("Entry");
        }
        return BundleUtils.getBundleProperty("Exit");
    }
    
}
