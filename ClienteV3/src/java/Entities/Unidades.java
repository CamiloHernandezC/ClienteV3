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
@Table(name = "unidades")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Unidades.findAll", query = "SELECT u FROM Unidades u"),
    @NamedQuery(name = "Unidades.findByIdUnidad", query = "SELECT u FROM Unidades u WHERE u.idUnidad = :idUnidad"),
    @NamedQuery(name = "Unidades.findByTipoUnidad", query = "SELECT u FROM Unidades u WHERE u.tipoUnidad = :tipoUnidad"),
    @NamedQuery(name = "Unidades.findByUnidadSIAB", query = "SELECT u FROM Unidades u WHERE u.unidadSIAB = :unidadSIAB"),
    @NamedQuery(name = "Unidades.findByDescripcion", query = "SELECT u FROM Unidades u WHERE u.descripcion = :descripcion")})
public class Unidades implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id_Unidad")
    private Integer idUnidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Tipo_Unidad")
    private Character tipoUnidad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "Unidad_SI_AB")
    private String unidadSIAB;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "Descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "unidad", fetch = FetchType.LAZY)
    private List<Materiales> materialesList;
    @OneToMany(mappedBy = "unidadVolumen", fetch = FetchType.LAZY)
    private List<Vehiculos> vehiculosList;
    @OneToMany(mappedBy = "unidadPeso", fetch = FetchType.LAZY)
    private List<Vehiculos> vehiculosList1;

    public Unidades() {
    }

    public Unidades(Integer idUnidad) {
        this.idUnidad = idUnidad;
    }

    public Unidades(Integer idUnidad, Character tipoUnidad, String unidadSIAB, String descripcion) {
        this.idUnidad = idUnidad;
        this.tipoUnidad = tipoUnidad;
        this.unidadSIAB = unidadSIAB;
        this.descripcion = descripcion;
    }

    public Integer getIdUnidad() {
        return idUnidad;
    }

    public void setIdUnidad(Integer idUnidad) {
        this.idUnidad = idUnidad;
    }

    public Character getTipoUnidad() {
        return tipoUnidad;
    }

    public void setTipoUnidad(Character tipoUnidad) {
        this.tipoUnidad = tipoUnidad;
    }

    public String getUnidadSIAB() {
        return unidadSIAB;
    }

    public void setUnidadSIAB(String unidadSIAB) {
        this.unidadSIAB = unidadSIAB;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<Materiales> getMaterialesList() {
        return materialesList;
    }

    public void setMaterialesList(List<Materiales> materialesList) {
        this.materialesList = materialesList;
    }

    @XmlTransient
    public List<Vehiculos> getVehiculosList() {
        return vehiculosList;
    }

    public void setVehiculosList(List<Vehiculos> vehiculosList) {
        this.vehiculosList = vehiculosList;
    }

    @XmlTransient
    public List<Vehiculos> getVehiculosList1() {
        return vehiculosList1;
    }

    public void setVehiculosList1(List<Vehiculos> vehiculosList1) {
        this.vehiculosList1 = vehiculosList1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUnidad != null ? idUnidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Unidades)) {
            return false;
        }
        Unidades other = (Unidades) object;
        if ((this.idUnidad == null && other.idUnidad != null) || (this.idUnidad != null && !this.idUnidad.equals(other.idUnidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Unidades[ idUnidad=" + idUnidad + " ]";
    }
    
}
