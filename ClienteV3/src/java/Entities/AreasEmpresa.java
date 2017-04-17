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
@Table(name = "areas_empresa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AreasEmpresa.findAll", query = "SELECT a FROM AreasEmpresa a"),
    @NamedQuery(name = "AreasEmpresa.findByIdAreaEmpresa", query = "SELECT a FROM AreasEmpresa a WHERE a.idAreaEmpresa = :idAreaEmpresa"),
    @NamedQuery(name = "AreasEmpresa.findByDescripcion", query = "SELECT a FROM AreasEmpresa a WHERE a.descripcion = :descripcion")})
public class AreasEmpresa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id_Area_Empresa")
    private Integer idAreaEmpresa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 140)
    @Column(name = "Descripcion")
    private String descripcion;
    @JoinColumn(name = "Sucursal", referencedColumnName = "Id_Sucursal")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Sucursales sucursal;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "area", fetch = FetchType.LAZY)
    private List<PersonasSucursal> personasSucursalList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "area", fetch = FetchType.LAZY)
    private List<MovPersonas> movPersonasList;

    public AreasEmpresa() {
    }

    public AreasEmpresa(Integer idAreaEmpresa) {
        this.idAreaEmpresa = idAreaEmpresa;
    }

    public AreasEmpresa(Integer idAreaEmpresa, String descripcion) {
        this.idAreaEmpresa = idAreaEmpresa;
        this.descripcion = descripcion;
    }

    public Integer getIdAreaEmpresa() {
        return idAreaEmpresa;
    }

    public void setIdAreaEmpresa(Integer idAreaEmpresa) {
        this.idAreaEmpresa = idAreaEmpresa;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Sucursales getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursales sucursal) {
        this.sucursal = sucursal;
    }

    @XmlTransient
    public List<PersonasSucursal> getPersonasSucursalList() {
        return personasSucursalList;
    }

    public void setPersonasSucursalList(List<PersonasSucursal> personasSucursalList) {
        this.personasSucursalList = personasSucursalList;
    }

    @XmlTransient
    public List<MovPersonas> getMovPersonasList() {
        return movPersonasList;
    }

    public void setMovPersonasList(List<MovPersonas> movPersonasList) {
        this.movPersonasList = movPersonasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAreaEmpresa != null ? idAreaEmpresa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AreasEmpresa)) {
            return false;
        }
        AreasEmpresa other = (AreasEmpresa) object;
        if ((this.idAreaEmpresa == null && other.idAreaEmpresa != null) || (this.idAreaEmpresa != null && !this.idAreaEmpresa.equals(other.idAreaEmpresa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.AreasEmpresa[ idAreaEmpresa=" + idAreaEmpresa + " ]";
    }
    
}
