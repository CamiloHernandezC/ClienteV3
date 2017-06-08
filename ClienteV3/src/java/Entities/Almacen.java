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
@Table(name = "almacen")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Almacen.findAll", query = "SELECT a FROM Almacen a"),
    @NamedQuery(name = "Almacen.findByIdAlmacen", query = "SELECT a FROM Almacen a WHERE a.idAlmacen = :idAlmacen"),
    @NamedQuery(name = "Almacen.findByDescripcion", query = "SELECT a FROM Almacen a WHERE a.descripcion = :descripcion")})
public class Almacen implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id_Almacen")
    private Integer idAlmacen;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "Descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "almacen", fetch = FetchType.LAZY)
    private List<Remisiones> remisionesList;
    @JoinColumn(name = "Sucursal", referencedColumnName = "Id_Sucursal")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Sucursales sucursal;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "almacen", fetch = FetchType.LAZY)
    private List<Cardex> cardexList;

    public Almacen() {
    }

    public Almacen(Integer idAlmacen) {
        this.idAlmacen = idAlmacen;
    }

    public Almacen(Integer idAlmacen, String descripcion) {
        this.idAlmacen = idAlmacen;
        this.descripcion = descripcion;
    }

    public Integer getIdAlmacen() {
        return idAlmacen;
    }

    public void setIdAlmacen(Integer idAlmacen) {
        this.idAlmacen = idAlmacen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<Remisiones> getRemisionesList() {
        return remisionesList;
    }

    public void setRemisionesList(List<Remisiones> remisionesList) {
        this.remisionesList = remisionesList;
    }

    public Sucursales getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursales sucursal) {
        this.sucursal = sucursal;
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
        hash += (idAlmacen != null ? idAlmacen.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Almacen)) {
            return false;
        }
        Almacen other = (Almacen) object;
        if ((this.idAlmacen == null && other.idAlmacen != null) || (this.idAlmacen != null && !this.idAlmacen.equals(other.idAlmacen))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Almacen[ idAlmacen=" + idAlmacen + " ]";
    }
    
}
